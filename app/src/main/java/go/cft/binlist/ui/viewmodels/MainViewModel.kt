package go.cft.binlist.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import go.cft.binlist.states.LoadingState
import go.cft.binlist.util.Converter
import go.cft.domain.models.entity.IdBinList
import go.cft.domain.models.entity.ListItemBinList
import go.cft.domain.models.params.*
import go.cft.domain.usecases.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor() : ViewModel() {

    @Inject
    protected lateinit var binValidateUseCase: BinValidateUseCase

    @Inject
    protected lateinit var filterByBinUseCase: FilterByBinUseCase

    @Inject
    protected lateinit var getBinListUseCase: GetBinListUseCase

    @Inject
    protected lateinit var getBinsListUseCase: GetBinsListUseCase

    @Inject
    protected lateinit var binListOnClickUseCase: BinListOnClickUseCase

    @Inject
    protected lateinit var deleteBinListUseCase: DeleteBinListUseCase

    @Inject
    protected lateinit var converter: Converter

    private val _binListFlow = MutableStateFlow<List<ListItemBinList>>(emptyList())
    val binListFlow = _binListFlow.asStateFlow()

    private val _onClickItemFlow = Channel<IdBinList>()
    val onClickItemFlow = _onClickItemFlow.receiveAsFlow()

    private val _checkNewBinFlow = Channel<IdBinList>()
    val checkNewBinFlow = _checkNewBinFlow.receiveAsFlow()

    private val _errorFlow = Channel<Exception>()
    val errorFlow = _errorFlow.receiveAsFlow()

    private val _loadingState = MutableStateFlow<LoadingState>(LoadingState.Loaded)
    val loadingState = _loadingState.asStateFlow()

    fun loadBins() {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.StartingApp
                _binListFlow.value = getBinsListUseCase.execute().result
            } catch (ex: Exception) {
                _errorFlow.send(ex)
            } finally {
                _loadingState.value = LoadingState.StartedApp
            }
        }
    }

    fun onItemClick(bin: String) {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.IsLoading
                _onClickItemFlow.send(binListOnClickUseCase.execute(OnBinListClickParam(bin)).result)
            } catch (ex: Exception) {
                _errorFlow.send(ex)
            } finally {
                _loadingState.value = LoadingState.Loaded
            }
        }
    }

    fun isValid(bin: CharSequence?): Boolean {
        return try {
            _loadingState.value = LoadingState.IsLoading
            binValidateUseCase.execute(BinValidateParam(bin)).result
        } catch (ex: Exception) {
            false
        }
    }

    fun filter(bin: CharSequence?) {
        viewModelScope.launch {
            try {
                val filteredList = filterByBinUseCase.execute(FilterByBinParam(bin))
                _binListFlow.value = filteredList.result
            } catch (ex: Exception) {
                _errorFlow.send(ex)
            } finally {
                _loadingState.value = LoadingState.Loaded
            }
        }
    }

    fun checkBinButtonOnClick(bin: String) {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.IsLoading
                val result = getBinListUseCase.execute(GetBinListParam(bin)).result
                _binListFlow.value = getBinsListUseCase.execute().result
                _checkNewBinFlow.send(result)
            } catch (ex: Exception) {
                _errorFlow.send(ex)
            } finally {
                _loadingState.value = LoadingState.Loaded
            }
        }
    }

    fun removeBinList(bin: String) {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.IsLoading
                deleteBinListUseCase.execute(DeleteBinListParam(bin))
                _binListFlow.value = getBinsListUseCase.execute().result
            } catch (ex: Exception) {
                _errorFlow.send(ex)
            } finally {
                _loadingState.value = LoadingState.Loaded
            }
        }
    }
}