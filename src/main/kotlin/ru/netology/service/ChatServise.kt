package ru.netology.service

import ru.netology.model.*

class ChatService {
    private val chats = mutableMapOf<Int, Chat>()
    private var lastMessageId = 0

    fun getChats(): List<Chat> = chats.values.toList()

    fun getUnreadChatsCount(): Int =
        chats.values.count { chat ->
            chat.messages.any { !it.isRead && !it.isDeleted }
        }

    fun getLastMessages(): List<String> =
        chats.values.map { chat ->
            chat.messages.filterNot { it.isDeleted }.lastOrNull()?.text ?: "нет сообщений"
        }

    fun getMessages(companionId: Int, limit: Int): List<Message> {
        val chat = chats[companionId] ?: return emptyList()
        val notDeleted = chat.messages.filterNot { it.isDeleted }
        val result = notDeleted.takeLast(limit)
        result.forEach { it.isRead = true }
        return result
    }

    fun sendMessage(senderId: Int, companionId: Int, text: String): Int {
        val chat = chats.getOrPut(companionId) { Chat(companionId) }
        val message = Message(++lastMessageId, senderId, text)
        chat.messages.add(message)
        return message.id
    }

    fun deleteMessage(companionId: Int, messageId: Int): Boolean {
        val chat = chats[companionId] ?: return false
        return chat.messages.find { it.id == messageId }?.let {
            it.isDeleted = true
            true
        } ?: false
    }

    fun deleteChat(companionId: Int): Boolean =
        chats.remove(companionId) != null

    fun clear() {
        chats.clear()
        lastMessageId = 0
    }
}