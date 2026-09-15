package com.waxd.messaging.data.conversationpicker.repository

import android.content.ContentResolver
import android.database.ContentObserver
import com.waxd.messaging.data.conversation.model.ConversationId
import com.waxd.messaging.data.conversationpicker.model.TargetConversation
import com.waxd.messaging.datamodel.MessagingContentProvider
import com.waxd.messaging.datamodel.data.ConversationListData
import com.waxd.messaging.datamodel.data.ConversationListItemData
import com.waxd.messaging.di.core.MessagingDbDispatcher
import javax.inject.Inject
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal interface TargetsRepository {
    fun observeTargets(): Flow<ImmutableList<TargetConversation>>
}

internal class TargetsRepositoryImpl @Inject constructor(
    private val contentResolver: ContentResolver,
    @param:MessagingDbDispatcher
    private val messagingDbDispatcher: CoroutineDispatcher,
) : TargetsRepository {

    override fun observeTargets(): Flow<ImmutableList<TargetConversation>> {
        return observeConversations()
            .map { queryTargets() }
            .flowOn(messagingDbDispatcher)
    }

    private fun queryTargets(): ImmutableList<TargetConversation> {
        val cursor = contentResolver.query(
            MessagingContentProvider.CONVERSATIONS_URI,
            ConversationListItemData.PROJECTION,
            ConversationListData.WHERE_NOT_ARCHIVED,
            null,
            ConversationListData.SORT_ORDER,
        ) ?: return persistentListOf()

        return cursor.use {
            buildList(it.count) {
                val item = ConversationListItemData()
                while (it.moveToNext()) {
                    item.bind(it)

                    val conversationId = ConversationId
                        .fromOrNull(item.conversationId)
                        ?: continue

                    add(
                        TargetConversation(
                            conversationId = conversationId,
                            name = item.name.orEmpty(),
                            icon = item.icon,
                            normalizedDestination = item.otherParticipantNormalizedDestination,
                            isGroup = item.isGroup,
                        ),
                    )
                }
            }
        }.toImmutableList()
    }

    private fun observeConversations(): Flow<Unit> {
        return callbackFlow {
            val observer = object : ContentObserver(null) {
                override fun onChange(selfChange: Boolean) {
                    trySend(Unit)
                }
            }

            contentResolver.registerContentObserver(
                MessagingContentProvider.CONVERSATIONS_URI,
                true,
                observer,
            )

            trySend(Unit)

            awaitClose {
                contentResolver.unregisterContentObserver(observer)
            }
        }
    }
}
