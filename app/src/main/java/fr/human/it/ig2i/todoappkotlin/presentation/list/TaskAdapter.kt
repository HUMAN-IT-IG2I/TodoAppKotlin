package fr.human.it.ig2i.todoappkotlin.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import fr.human.it.ig2i.todoappkotlin.R
import fr.human.it.ig2i.todoappkotlin.data.model.Task
import fr.human.it.ig2i.todoappkotlin.databinding.TaskItemBinding
import fr.human.it.ig2i.todoappkotlin.utils.extractDateFormatDateField
import fr.human.it.ig2i.todoappkotlin.utils.extractTimeFormatDateField

class TaskAdapter(val onTaskClicked: (Task) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    var tasks = listOf<Task>()
        set(newTasks) {
            val diffCallback = TaskDiffCallback(field, newTasks)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newTasks
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TaskViewHolder(
        TaskItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
        holder.itemView.setOnClickListener { onTaskClicked(task) }
    }

    override fun getItemCount() = tasks.size

    class TaskViewHolder(
        private val binding: TaskItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            with(binding) {
                root.context.let { context ->
                    taskId.text = context.getString(R.string.task_id, task.id)
                    taskContent.text = task.content
                    val creationDate = extractDateFormatDateField(task.creationDate)
                    val creationTime = extractTimeFormatDateField(task.creationDate)
                    taskDate.text =
                        context.getString(R.string.task_date, creationDate, creationTime)
                }
            }
        }
    }

    class TaskDiffCallback(
        private val oldTasks: List<Task>,
        private val newTasks: List<Task>,
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldTasks.size

        override fun getNewListSize() = newTasks.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldTasks[oldItemPosition].id == newTasks[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldTasks[oldItemPosition] == newTasks[newItemPosition]

    }

}