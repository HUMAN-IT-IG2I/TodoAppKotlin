package fr.human.it.ig2i.todoappkotlin.presentation.create

import androidx.lifecycle.ViewModel
import fr.human.it.ig2i.todoappkotlin.data.InMemoryDataBase

class CreateTaskViewModel : ViewModel() {

    fun add(content: String) = InMemoryDataBase.addTask(content)

}