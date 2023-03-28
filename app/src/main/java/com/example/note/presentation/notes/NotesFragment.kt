package com.example.note.presentation.notes

import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.note.R
import com.example.note.data.base.BaseFragment
import com.example.note.databinding.FragmentNotesBinding
import com.example.note.domain.model.Note
import com.example.note.presentation.extencion.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : BaseFragment<NotesViewModel, FragmentNotesBinding>(R.layout.fragment_notes) {

//    private lateinit var viewHolder: RecyclerView.ViewHolder
    override val vm: NotesViewModel by lazy {
        ViewModelProvider(requireActivity())[NotesViewModel::class.java]
    }

    override val binding: FragmentNotesBinding by viewBinding(FragmentNotesBinding::bind)

    private val list = mutableListOf<Note>()
    private val adapter by lazy { NotesAdapter(this::onItemClick, this::onLongClick) }

    private fun onItemClick(note: Note) {
        val bundle = bundleOf().apply {
            putSerializable(ARG_ADD_EDIT, note)
        }
        findNavController().navigate(R.id.action_notesFragment_to_fillingNotesFragment, bundle)

    }

    private fun onLongClick(note: Note, position: Int) {
        vm.delete(position, note)
        adapter.getPosition(position)

        adapter.deleteItem(position)
    }


    override fun setupRequest() {
        vm.noteState.collectState(onLoading = {
            binding.progressBar.isVisible = true
        }, onError = {
            binding.progressBar.isVisible = false
            showToast(it)
        }, onSuccess = {
            binding.progressBar.isVisible = false
            adapter.updateList(it as MutableList<Note>)
        })

        vm.deleteNoteState.collectState(onLoading = {
            binding.progressBar.isVisible = true
        }, onError = {
            binding.progressBar.isVisible = false
        }, onSuccess = {
            showToast(msg = "deleted")
            binding.progressBar.isVisible = false

        })

        vm.createNoteState.collectState(onLoading = {
            binding.progressBar.isVisible = true
        }, onError = {
            binding.progressBar.isVisible = false
        }, onSuccess = {
            binding.progressBar.isVisible = false

        })


    }

    override fun listener() {
        binding.floatingActionButton2.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_fillingNotesFragment)
        }
    }

    override fun initialise() {
        binding.recycler.adapter = adapter


    }

    companion object {
        const val ARG_ADD_EDIT = "edit_notes"
    }

}





