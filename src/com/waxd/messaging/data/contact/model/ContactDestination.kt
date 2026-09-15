package com.waxd.messaging.data.contact.model

internal data class ContactDestination(
    val dataId: Long,
    val contactId: Long,
    val value: String,
    val normalizedValue: String,
    val displayValue: String,
    val kind: Kind,
    val type: Int,
    val customLabel: String?,
    val isPrimary: Boolean,
    val isSuperPrimary: Boolean,
) {
    enum class Kind {
        PHONE,
        EMAIL,
    }
}
