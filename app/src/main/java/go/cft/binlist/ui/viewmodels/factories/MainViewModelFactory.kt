package go.cft.binlist.ui.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import go.cft.binlist.ui.viewmodels.MainViewModel
import javax.inject.Inject

class MainViewModelFactory @Inject constructor() : ViewModelProvider.Factory {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return this.viewModel as T
    }
}