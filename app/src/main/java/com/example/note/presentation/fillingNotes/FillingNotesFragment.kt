package com.example.note.presentation.fillingNotes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.note.R
import com.example.note.data.base.BaseFragment
import com.example.note.databinding.FragmentFillingNotesBinding
import com.example.note.domain.model.Note
import com.example.note.presentation.extencion.showToast
import com.example.note.presentation.notes.NotesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FillingNotesFragment :
    BaseFragment<FillingNotesViewModel, FragmentFillingNotesBinding>(R.layout.fragment_filling_notes) {

    private var note: Note? = null

    override val vm: FillingNotesViewModel by lazy {
        ViewModelProvider(requireActivity())[FillingNotesViewModel::class.java]

    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun initialise() {
        if (arguments != null) {
            this.note =
                requireArguments().getSerializable(NotesFragment.ARG_ADD_EDIT, Note::class.java)
            setData()
        }


    }

    override val binding: FragmentFillingNotesBinding by viewBinding(FragmentFillingNotesBinding::bind)

    override fun listener() {

        with(binding) {
            btn.setOnClickListener {
                if (note != null) {
                    vm.update(
                        note!!.copy(
                            title = titleET.text.toString(),
                            description = descriptionET.text.toString()
                        )
                    )
                } else {
                    vm.create(
                        titleET.text.toString(), descriptionET.text.toString()
                    )
                    findNavController().navigate(R.id.action_notesFragment_to_fillingNotesFragment)

                }
            }
        }

    }


    private fun setData() {
        with(binding) {
            titleET.setText(note!!.title)
            descriptionET.setText(note!!.description)
        }
    }


    override fun setupRequest() {
        vm.createNoteState.collectState(onLoading = {
            binding.progressBar.isVisible = true
        }, onError = {
            binding.progressBar.isVisible = false
            showToast(it)
        }, onSuccess = {
            binding.progressBar.isVisible = false
            showToast(getString(R.string.note_is_created))
            findNavController().navigateUp()
        })
        vm.editNoteStade.collectState(onLoading = {
            binding.progressBar.isVisible = true
        }, onError = {
            binding.progressBar.isVisible = false
            showToast(it)
        }, onSuccess = {
            binding.progressBar.isVisible = false
            showToast(getString(R.string.note_is_edited))
            findNavController().navigateUp()
        })
    }


}