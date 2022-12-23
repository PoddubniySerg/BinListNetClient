package go.cft.domain.usecases

import go.cft.domain.exceptions.FilterByBinException
import go.cft.domain.models.params.FilterByBinParam
import go.cft.domain.models.results.FilterByBinResult
import go.cft.domain.util.Sorter
import javax.inject.Inject

class FilterByBinUseCase @Inject constructor() {

    @Inject
    lateinit var sorter: Sorter

    fun execute(param: FilterByBinParam): FilterByBinResult {
        try {
            val result = sorter.sort(param.idsBinList.filter { it.id == param.bin })
            return FilterByBinResult(result)
        } catch (ex: Exception) {
            throw FilterByBinException("Bins filter error")
        }
    }
}