package com.waxd.messaging.ui.conversation.metadata.mapper

import com.waxd.messaging.data.conversation.model.metadata.ConversationMetadata
import com.waxd.messaging.sms.MmsSmsUtils
import com.waxd.messaging.ui.conversation.metadata.model.ConversationMetadataUiState
import javax.inject.Inject

internal interface ConversationMetadataUiStateMapper {
    fun map(metadata: ConversationMetadata): ConversationMetadataUiState
}

internal class ConversationMetadataUiStateMapperImpl @Inject constructor() :
    ConversationMetadataUiStateMapper {

    override fun map(metadata: ConversationMetadata): ConversationMetadataUiState {
        val avatar = when {
            metadata.isGroupConversation -> ConversationMetadataUiState.Avatar.Group

            else -> {
                ConversationMetadataUiState.Avatar.Single(
                    photoUri = metadata.otherParticipantPhotoUri,
                    normalizedDestination = metadata.otherParticipantNormalizedDestination,
                    displayName = metadata.conversationName,
                )
            }
        }

        return ConversationMetadataUiState.Present(
            title = metadata.conversationName,
            avatar = avatar,
            participantCount = metadata.participantCount,
            otherParticipantDisplayDestination = metadata.otherParticipantDisplayDestination,
            otherParticipantPhoneNumber = metadata
                .otherParticipantNormalizedDestination
                ?.takeIf(MmsSmsUtils::isPhoneNumber),
            otherParticipantContactLookupKey = metadata.otherParticipantContactLookupKey,
            isArchived = metadata.isArchived,
            isBlocked = metadata.isBlocked,
            composerAvailability = metadata.composerAvailability,
        )
    }
}
