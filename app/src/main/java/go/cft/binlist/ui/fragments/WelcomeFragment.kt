package go.cft.binlist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import go.cft.binlist.databinding.FragmentWelcomeBinding
import go.cft.binlist.states.LoadingState
import go.cft.binlist.ui.viewmodels.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeFragment @Inject constructor() : Fragment() {

    companion object {
        fun newInstance() = WelcomeFragment()
    }

    private val viewModel by activityViewModels<MainViewModel>()
    private var binding: FragmentWelcomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadingState.onEach {
            delay(1000)
            when (it) {
                is LoadingState.StartedApp -> {
                    while (parentFragmentManager.isStateSaved) delay(2000)
                    parentFragmentManager.popBackStack()
                }
                else -> {
//                    Todo nothing
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}