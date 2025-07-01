package ru.netology.model

data class Chat(
    val companionId: Int,
    val messages: MutableList<Message> = mutableListOf()
)