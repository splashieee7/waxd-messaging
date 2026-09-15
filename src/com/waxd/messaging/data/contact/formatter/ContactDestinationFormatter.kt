package com.waxd.messaging.data.contact.formatter

import com.waxd.messaging.data.phone.formatter.PhoneNumberFormatter
import com.waxd.messaging.sms.MmsSmsUtils
import java.util.Locale
import javax.inject.Inject

internal interface ContactDestinationFormatter {

    fun canonicalize(value: String): String

    fun canonicalize(value: String, countryCandidates: List<String>): String

    fun formatPhoneForDisplay(value: String): String

    fun countryCandidates(): List<String>
}

internal class ContactDestinationFormatterImpl @Inject constructor(
    private val phoneNumberFormatter: PhoneNumberFormatter,
) : ContactDestinationFormatter {

    override fun canonicalize(value: String): String {
        return canonicalize(value = value) { trimmed ->
            phoneNumberFormatter.getCanonicalForEnteredNumber(trimmed)
        }
    }

    override fun canonicalize(
        value: String,
        countryCandidates: List<String>,
    ): String {
        return canonicalize(value = value) { trimmed ->
            phoneNumberFormatter.getCanonicalForEnteredNumber(trimmed, countryCandidates)
        }
    }

    override fun formatPhoneForDisplay(value: String): String {
        return phoneNumberFormatter.formatForDisplay(value)
    }

    override fun countryCandidates(): List<String> {
        return phoneNumberFormatter.countryCandidates()
    }

    private inline fun canonicalize(
        value: String,
        canonicalizePhoneNumber: (trimmed: String) -> String,
    ): String {
        val trimmed = value.trim()

        return when {
            trimmed.isEmpty() -> trimmed
            MmsSmsUtils.isEmailAddress(trimmed) -> trimmed.lowercase(Locale.ROOT)
            else -> canonicalizePhoneNumber(trimmed).stripPhoneSeparators()
        }
    }

    private fun String.stripPhoneSeparators(): String {
        return filterNot { character ->
            character.isWhitespace() || character in PHONE_SEPARATOR_CHARS
        }
    }

    private companion object {
        private val PHONE_SEPARATOR_CHARS = setOf('-', '(', ')', '.', '/')
    }
}
