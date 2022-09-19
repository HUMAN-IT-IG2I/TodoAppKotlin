package fr.human.it.ig2i.todoappkotlin.data

import fr.human.it.ig2i.todoappkotlin.data.model.Task
import java.util.*

object InMemoryDataBase {

    private val _tasks: MutableList<Task>

    init {
        val currentDate = Calendar.getInstance().time

        _tasks = mutableListOf(
            Task(
                1,
                "Benchmark Android native development solutions",
                currentDate,
                false
            ),
            Task(
                2,
                "Realize a TodoApp in Java",
                currentDate,
                false
            ),
            Task(
                3,
                "Realize a TodoApp in Kotlin",
                currentDate,
                true
            ),
            Task(
                4,
                "Find out which solution is the best",
                currentDate,
                true
            )
        )

    }

    val task: List<Task>
        get() = _tasks.filter { it.isActive() }

}