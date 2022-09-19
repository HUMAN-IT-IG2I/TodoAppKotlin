package fr.human.it.ig2i.todoappkotlin.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import fr.human.it.ig2i.todoappkotlin.data.model.Task
import fr.human.it.ig2i.todoappkotlin.databinding.FragmentTaskListBinding

class TaskListFragment : Fragment() {

    private var _binding: FragmentTaskListBinding? = null
    val binding: FragmentTaskListBinding
        get() = _binding!!

    private val viewModel by viewModels<TaskListViewModel>()

    private val adapter by lazy {
        TaskAdapter(::navigateToDetails)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTaskListBinding.inflate(layoutInflater)

        with(binding.taskList) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@TaskListFragment.adapter
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshTasks()
        viewModel.tasks.observe(viewLifecycleOwner) { tasks -> adapter.tasks = tasks}
        binding.createTaskFab.setOnClickListener { navigateToCreation()}
    }

    private fun navigateToDetails(task: Task) {
    }

    private fun navigateToCreation() {
    }

}