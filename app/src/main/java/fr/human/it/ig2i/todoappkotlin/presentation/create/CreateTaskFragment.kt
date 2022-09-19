package fr.human.it.ig2i.todoappkotlin.presentation.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import fr.human.it.ig2i.todoappkotlin.R
import fr.human.it.ig2i.todoappkotlin.databinding.FragmentCreateTaskBinding
import fr.human.it.ig2i.todoappkotlin.utils.displayToast

class CreateTaskFragment : Fragment() {

    private var _binding: FragmentCreateTaskBinding? = null
    val binding: FragmentCreateTaskBinding
        get() = _binding!!

    private val viewModel by viewModels<CreateTaskViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCreateTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.cancelButton.setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }

        binding.createButton.setOnClickListener { createTask() }
    }

    private fun createTask() {
        binding.taskContentEditText.text?.toString()?.let { content ->
            if (content.isNotBlank()) {
                if (viewModel.add(content)) {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                } else {
                    displayToast(requireContext(), R.string.task_creation_error_message)
                }
                return
            }
        }
        displayToast(requireContext(), R.string.task_content_empty_error_message)
    }
}