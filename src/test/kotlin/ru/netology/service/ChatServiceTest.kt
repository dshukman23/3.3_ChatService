package ru.netology.service

import org.junit.*
import org.junit.Assert.*
import ru.netology.model.Message

class ChatServiceTest {

    private lateinit var service: ChatService

    @Before
    fun setUp() {
        service = ChatService()
    }

    @Test
    fun `should create chat and send message`() {
        val messageId = service.sendMessage(1, 2, "Hello")
        assertEquals(1, messageId)
        assertEquals(1, service.getChats().size)
    }

    @Test
    fun `should get unread chats count`() {
        service.sendMessage(1, 3, "Hi")
        service.sendMessage(1, 4, "Hey")

        service.getMessages(3, 10) // Прочитали чат 3
        assertEquals(1, service.getUnreadChatsCount())
    }

    @Test
    fun `should get last messages`() {
        service.sendMessage(1, 5, "Msg1")
        service.sendMessage(1, 6, "Msg2")

        val lastMessages = service.getLastMessages()
        assertTrue(lastMessages.contains("Msg1"))
        assertTrue(lastMessages.contains("Msg2"))
    }

    @Test
    fun `should delete message`() {
        val id = service.sendMessage(1, 7, "To Delete")
        assertTrue(service.deleteMessage(7, id))
        assertFalse(service.deleteMessage(7, id + 100)) // Несуществующее сообщение
    }

    @Test
    fun `should delete chat`() {
        service.sendMessage(1, 8, "Test")
        assertTrue(service.deleteChat(8))
        assertEquals(0, service.getChats().size)
    }

    @Test
    fun `should return no_messages if all_deleted`() {
        service.sendMessage(1, 9, "Old message")
        service.deleteMessage(9, 1)
        val lastMessages = service.getLastMessages()
        assertTrue(lastMessages.contains("нет сообщений"))
    }
}