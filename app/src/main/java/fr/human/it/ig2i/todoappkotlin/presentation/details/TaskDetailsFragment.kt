package fr.human.it.ig2i.todoappkotlin.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import fr.human.it.ig2i.todoappkotlin.R
import fr.human.it.ig2i.todoappkotlin.data.model.Task
import fr.human.it.ig2i.todoappkotlin.databinding.FragmentTaskDetailsBinding
import fr.human.it.ig2i.todoappkotlin.utils.displayToast
import fr.human.it.ig2i.todoappkotlin.utils.extractDateFormatDateField
import fr.human.it.ig2i.todoappkotlin.utils.extractTimeFormatDateField

class TaskDetailsFragment : Fragment() {

    private var _binding: FragmentTaskDetailsBinding? = null
    val binding: FragmentTaskDetailsBinding
        get() = _binding!!

    private val viewModel by viewModels<TaskDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskDetailsBinding.inflate(layoutInflater)

        loadSelectedTask()

        requireActivity().setTitle(R.string.task_details_fragment_title)

        return binding.root
    }

    private fun loadSelectedTask() {
        arguments?.let { bundle ->
            val taskId = bundle.getInt(getString(R.string.task_id_arg_name_for_details))
            viewModel.setCurrentTaskId(taskId)
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.currentTask.observe(viewLifecycleOwner) {
            it?.let { task ->
                displayTask(task)
            }
        }

        viewModel.isTaskFinished.observe(viewLifecycleOwner) {
            it?.let { isTaskFinished ->
                onTaskFinished(isTaskFinished)
            }
        }

        binding.backButton.setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
        binding.finishButton.setOnClickListener { viewModel.markTaskAsDone() }
    }

    private fun displayTask(task: Task) {
        val creationDate = extractDateFormatDateField(task.creationDate)
        val creationTime = extractTimeFormatDateField(task.creationDate)
        with(binding) {
            taskId.text = getString(R.string.task_id, task.id)
            taskContent.text = task.content
            taskCreationDate.text = getString(R.string.task_date, creationDate, creationTime)
        }
    }

    private fun onTaskFinished(isTaskFinished: Boolean) {
        if (isTaskFinished) {
            displayToast(requireContext(), R.string.task_successfully_finish)
        } else {
            displayToast(requireContext(), R.string.task_failed_finish)
        }
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

}