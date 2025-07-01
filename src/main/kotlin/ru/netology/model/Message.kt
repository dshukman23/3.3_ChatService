package ru.netology.model

data class Message(
    val id: Int,
    val senderId: Int,
    val text: String,
    var isRead: Boolean = false,
    var isDeleted: Boolean = false
)