package fr.human.it.ig2i.todoappkotlin.data.model

import java.util.*

data class Task(
    val id: Int,
    val content: String,
    val creationDate: Date,
    private var isActive: Boolean
) {

    fun isActive(): Boolean {
        return isActive
    }

    fun finishTask() {
        isActive = false;
    }

}
