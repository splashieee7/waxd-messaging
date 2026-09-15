package com.waxd.messaging.sms

import android.content.res.Resources
import com.waxd.messaging.R

internal fun cleanseMmsSubject(
    resources: Resources,
    subject: String?,
): String? {
    return cleanseMmsSubject(
        subject = subject,
        emptySubjectStrings = resources.getStringArray(R.array.empty_subject_strings),
    )
}

internal fun cleanseMmsSubject(
    subject: String?,
    emptySubjectStrings: Array<String>,
): String? {
    return subject
        ?.takeIf(String::isNotEmpty)
        ?.takeUnless { candidateSubject ->
            emptySubjectStrings.any { emptySubjectString ->
                candidateSubject.equals(other = emptySubjectString, ignoreCase = true)
            }
        }
}
