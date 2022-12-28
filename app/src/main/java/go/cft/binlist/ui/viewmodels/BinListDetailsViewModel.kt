package go.cft.binlist.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import go.cft.domain.models.params.GetUriStringPhoneParam
import go.cft.domain.models.params.GetUriStringUrlParam
import go.cft.domain.usecases.GetUriStringPhoneUseCase
import go.cft.domain.usecases.GetUriStringUrlUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BinListDetailsViewModel @Inject constructor() : ViewModel() {

    @Inject
    protected lateinit var getUriStringPhoneUseCase: GetUriStringPhoneUseCase

    @Inject
    protected lateinit var getUriStringUrlUseCase: GetUriStringUrlUseCase

    private val _uriStringUrlFlow = Channel<String>()
    val uriStringUrlFlow = _uriStringUrlFlow.receiveAsFlow()

    private val _uriStringPhoneFlow = Channel<String>()
    val uriStringPhoneFlow = _uriStringPhoneFlow.receiveAsFlow()

    fun getUriStringUrl(url: String) {
        try {
            val result = getUriStringUrlUseCase.execute(GetUriStringUrlParam(url)).result
            viewModelScope.launch {
                _uriStringUrlFlow.send(result)
            }
        } catch (ex: Exception) {
//            Todo nothing
        }
    }

    fun getUriStringPhone(phone: String) {
        try {
            val result = getUriStringPhoneUseCase.execute(GetUriStringPhoneParam(phone)).result
            viewModelScope.launch {
                _uriStringUrlFlow.send(result)
            }
        } catch (ex: Exception) {
//            Todo nothing
        }

    }
}