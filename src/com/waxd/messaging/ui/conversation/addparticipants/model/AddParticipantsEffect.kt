package com.waxd.messaging.ui.conversation.addparticipants.model

internal sealed interface AddParticipantsEffect {

    data class ShowMessage(
        val messageResId: Int,
    ) : AddParticipantsEffect
}
