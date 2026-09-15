package com.waxd.messaging.domain.vcarddetail.usecase

import androidx.core.net.toUri
import com.waxd.messaging.datamodel.MediaScratchFileProvider
import com.waxd.messaging.di.core.IoDispatcher
import com.waxd.messaging.domain.vcarddetail.model.AddVCardToContactsResult
import com.waxd.messaging.util.UriUtil
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal interface AddVCardToContacts {
    suspend operator fun invoke(vCardUri: String, displayName: String?): AddVCardToContactsResult
}

internal class AddVCardToContactsImpl @Inject constructor(
    @param:IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
) : AddVCardToContacts {

    override suspend operator fun invoke(
        vCardUri: String,
        displayName: String?,
    ): AddVCardToContactsResult {
        return withContext(ioDispatcher) {
            val scratchUri = UriUtil.persistContentToScratchSpace(
                vCardUri.toUri(),
            ) ?: return@withContext AddVCardToContactsResult.Failed

            if (!displayName.isNullOrBlank()) {
                MediaScratchFileProvider.addUriToDisplayNameEntry(scratchUri, displayName)
            }

            AddVCardToContactsResult.Prepared(scratchUri.toString())
        }
    }
}
