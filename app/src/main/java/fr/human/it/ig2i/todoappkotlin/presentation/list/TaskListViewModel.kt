package fr.human.it.ig2i.todoappkotlin.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.human.it.ig2i.todoappkotlin.data.InMemoryDataBase
import fr.human.it.ig2i.todoappkotlin.data.model.Task

class TaskListViewModel : ViewModel() {

    private val _tasks = MutableLiveData(InMemoryDataBase.tasks)
    val tasks: LiveData<List<Task>>
        get() = _tasks

    fun refreshTasks() {
        _tasks.postValue(InMemoryDataBase.tasks)
    }

}