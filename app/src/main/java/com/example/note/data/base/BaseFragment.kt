package com.example.note.data.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.note.presentation.notes.UIState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding>(@LayoutRes layoutId: Int) :
    Fragment(
        layoutId
    ) {
    protected abstract val vm: VM
    protected abstract val binding: VB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialise()
        listener()
        setupRequest()

    }

    protected open fun initialise() {}
    protected open fun listener() {}
    protected open fun setupRequest() {}

    protected fun <T> StateFlow<UIState<T>>.collectState(
        onLoading: () -> Unit,
        onSuccess: (T) -> Unit,
        onError: (String) -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(STARTED) {
                this@collectState.collect {
                    when (it) {
                        is UIState.Empty -> {
                        }
                        is UIState.Error -> {
                            onError(it.msg)
                        }
                        is UIState.Loading -> {
                            onLoading()
                        }
                        is UIState.Success -> {
                            it.data?.let { it1 -> onSuccess(it1) }
                        }
                    }
                }
            }
        }
    }
}