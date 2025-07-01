package ru.netology

import ru.netology.model.Message
import ru.netology.service.ChatService

fun main() {
    val service = ChatService()

    // Отправляем сообщения
    service.sendMessage(1, 2, "Привет!")
    service.sendMessage(1, 3, "Как дела?")
    service.sendMessage(3, 1, "Всё хорошо!")

    // Получаем список чатов
    println("Чаты:")
    service.getChats().forEach {
        println("Собеседник: ${it.companionId}")
    }

    // Непрочитанные чаты
    println("\nНепрочитанных чатов: ${service.getUnreadChatsCount()}")

    // Последние сообщения
    println("\nПоследние сообщения из чатов:")
    service.getLastMessages().forEach(::println)

    // Получаем сообщения из чата
    println("\nСообщения от собеседника 3:")
    service.getMessages(3, 10).forEach {
        println("Сообщение от ${it.senderId}: ${it.text}")
    }

    // Удаляем чат
    service.deleteChat(2)
    println("\nПосле удаления чата 2:")
    service.getChats().forEach {
        println("Собеседник: ${it.companionId}")
    }
}