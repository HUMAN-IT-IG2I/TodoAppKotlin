package fr.human.it.ig2i.todoappkotlin.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import fr.human.it.ig2i.todoappkotlin.R
import fr.human.it.ig2i.todoappkotlin.data.model.Task
import fr.human.it.ig2i.todoappkotlin.databinding.FragmentTaskListBinding
import fr.human.it.ig2i.todoappkotlin.presentation.create.CreateTaskFragment
import fr.human.it.ig2i.todoappkotlin.presentation.details.TaskDetailsFragment

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

        requireActivity().setTitle(R.string.task_list_fragment_title)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshTasks()
        viewModel.tasks.observe(viewLifecycleOwner) { tasks -> adapter.tasks = tasks }
        binding.createTaskFab.setOnClickListener { navigateToCreation() }
    }

    private fun navigateToDetails(task: Task) {
        parentFragmentManager.beginTransaction().also { transaction ->
            val bundle = Bundle().also {
                it.putInt(getString(R.string.task_id_arg_name_for_details), task.id)
            }
            transaction.replace(
                R.id.fragment_container,
                TaskDetailsFragment::class.java,
                bundle,
                getString(R.string.task_details_fragment_title)
            )
            transaction.setReorderingAllowed(true)
            transaction.addToBackStack(getString(R.string.task_details_fragment_title))
            transaction.commit()
        }
    }

    private fun navigateToCreation() {
        parentFragmentManager.beginTransaction().also { transaction ->
            transaction.replace(
                R.id.fragment_container,
                CreateTaskFragment::class.java,
                null,
                getString(R.string.task_creation_error_message)
            )
            transaction.setReorderingAllowed(true)
            transaction.addToBackStack(getString(R.string.task_creation_error_message))
            transaction.commit()
        }
    }

}