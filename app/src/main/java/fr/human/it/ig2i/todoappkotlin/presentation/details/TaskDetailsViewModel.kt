package fr.human.it.ig2i.todoappkotlin.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.human.it.ig2i.todoappkotlin.data.InMemoryDataBase
import fr.human.it.ig2i.todoappkotlin.data.model.Task

class TaskDetailsViewModel : ViewModel() {

    private val _currentTask = MutableLiveData<Task?>()
    val currentTask: LiveData<Task?>
        get() = _currentTask

    private val _isTaskFinished = MutableLiveData<Boolean?>(null)
    val isTaskFinished: LiveData<Boolean?>
        get() = _isTaskFinished

    fun setCurrentTaskId(id: Int) {
        _currentTask.postValue(InMemoryDataBase.getTaskById(id))
    }

    fun markTaskAsDone() {
        InMemoryDataBase.finishTask(_currentTask.value!!).let {
            _isTaskFinished.postValue(it)
        }
    }

}