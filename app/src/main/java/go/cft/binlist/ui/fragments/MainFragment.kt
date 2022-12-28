package go.cft.binlist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import go.cft.binlist.R
import go.cft.binlist.databinding.FragmentMainBinding
import go.cft.binlist.states.LoadingState
import go.cft.binlist.ui.activities.MainActivity
import go.cft.binlist.ui.fragments.adapters.BinsListAdapter
import go.cft.binlist.ui.viewmodels.MainViewModel
import go.cft.binlist.util.Converter
import go.cft.domain.models.entity.IdBinList
import go.cft.domain.models.entity.ListItemBinList
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
open class MainFragment : Fragment() {

    companion object {
        private const val TEXT_SEPARATOR = ' '
        private const val BIN_SECTION_SIZE = 4
        const val ID_BIN_LIST_ARG_KEY = "id_bin_list"

        fun newInstance() = MainFragment()
    }

    private val adapter = BinsListAdapter(
        { item -> onItemClick(item) },
        { item -> removeItem(item) }
    )

    @Inject
    protected lateinit var converter: Converter

    private val viewModel by activityViewModels<MainViewModel>()
    private var binding: FragmentMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) viewModel.loadBins()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        val actionBar = (activity as MainActivity?)!!.supportActionBar
        actionBar?.setHomeButtonEnabled(false)
        actionBar?.setDisplayHomeAsUpEnabled(false)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.recyclerView?.adapter = adapter

        viewModel.binListFlow.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.onClickItemFlow.onEach {
            openBinListDetails(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.checkNewBinFlow.onEach {
            openBinListDetails(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        with(binding!!) {
            textInputBin.doOnTextChanged { bin, start, _, count ->
                bin?.let {
                    val id = getIdFromBin(it)
                    validate(id)
                    viewModel.filter(bin)
                }
                formatInputText(start, count)
            }

            checkBinButton.setOnClickListener {
                if (textInputLayout.isErrorEnabled.not())
                    viewModel.checkBinButtonOnClick(getIdFromBin(textInputBin.text.toString()).toString())
            }

            viewModel.errorFlow.onEach {
                Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
            }.launchIn(viewLifecycleOwner.lifecycleScope)

            viewModel.loadingState.onEach {
                when (it) {
                    is LoadingState.IsLoading -> loadStart()
                    is LoadingState.Loaded -> loadStop()
                    is LoadingState.StartedApp -> loadStop()
                    is LoadingState.StartingApp -> goToWelcomeFragment()
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun validate(bin: CharSequence?) {
        val inputLayout = binding?.textInputLayout
        if (viewModel.isValid(bin).not()) {
            inputLayout?.error =
                "At least 6 and no more than 8 characters, can contain only digits from 0 to 9"
            binding?.textInputBin?.requestFocus()
            return
        }
        inputLayout?.isErrorEnabled = false
    }

    private fun formatInputText(start: Int, count: Int) {
        val text = binding?.textInputBin?.text
        text?.let {
            if (start == 0 && count == BIN_SECTION_SIZE) it.insert(count, TEXT_SEPARATOR.toString())
            if (start == BIN_SECTION_SIZE - 1 && count == 1) {
                it.insert(start + 1, TEXT_SEPARATOR.toString())
            }
            if (start == BIN_SECTION_SIZE && count == 0) {
                it.delete(BIN_SECTION_SIZE - 1, BIN_SECTION_SIZE)
            }
        }
    }

    private fun getIdFromBin(bin: CharSequence): CharSequence {
        return if (bin.length > BIN_SECTION_SIZE)
            buildString {
                append(bin.subSequence(0 until BIN_SECTION_SIZE))
                append(bin.subSequence(BIN_SECTION_SIZE + 1 until bin.length))
            }
        else bin
    }

    private fun openBinListDetails(idBinList: IdBinList) {
        val parcelAble = converter.convertFullEntity(idBinList)
        val args = Bundle().apply {
            putParcelable(ID_BIN_LIST_ARG_KEY, parcelAble)
        }
        parentFragmentManager.commit {
            addToBackStack(this.toString())
            setCustomAnimations(
                android.R.anim.slide_out_right,
                android.R.anim.slide_out_right
            )
            replace(R.id.container, BinListDetailsFragment::class.java, args)
        }
    }

    private fun onItemClick(item: ListItemBinList) {
        binding?.let {
            if (it.recyclerView.isClickable)
                viewModel.onItemClick(getIdFromBin(item.bin).toString())
        }
    }

    private fun removeItem(item: ListItemBinList) {
        viewModel.removeBinList(getIdFromBin(item.bin).toString())
    }

    private fun loadStart() {
        binding?.let {
            with(it) {
                progressBar.isVisible = true
                checkBinButton.isEnabled = false
                recyclerView.isClickable = false
            }
        }

    }

    private fun loadStop() {
        binding?.let {
            with(it) {
                progressBar.isVisible = false
                checkBinButton.isEnabled = true
                recyclerView.isClickable = true
            }
        }
    }

    private fun goToWelcomeFragment() {
        parentFragmentManager.commit(allowStateLoss = true) {
            addToBackStack(this.toString())
            setCustomAnimations(
                R.anim.enter_fragment_animation,
                R.anim.exit_fragment_animation,
                R.anim.enter_fragment_animation,
                R.anim.exit_fragment_animation
            )
            replace(R.id.container, WelcomeFragment.newInstance())
        }
    }
}