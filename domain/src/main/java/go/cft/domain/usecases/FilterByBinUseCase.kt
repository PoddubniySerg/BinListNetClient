package go.cft.domain.usecases

import go.cft.domain.exceptions.FilterByBinException
import go.cft.domain.models.params.FilterByBinParam
import go.cft.domain.models.results.FilteredBinsList
import go.cft.domain.repositories.BinListRepository
import go.cft.domain.util.Converter
import javax.inject.Inject

open class FilterByBinUseCase @Inject constructor() {

    @Inject
    protected lateinit var repository: BinListRepository

    @Inject
    protected lateinit var converter: Converter

    suspend fun execute(param: FilterByBinParam): FilteredBinsList {
        try {
            val bin = param.bin
            val result =
                if (bin == null || bin == "")
                    repository.getBinsList().map { converter.convert(it) }
                else repository.getBinsList().map { converter.convert(it) }
                    .filter { it.bin.startsWith(param.bin) }
                    .sortedBy { it.bin }
            return FilteredBinsList(result)
        } catch (ex: Exception) {
            throw FilterByBinException("Bins filter error")
        }
    }
}