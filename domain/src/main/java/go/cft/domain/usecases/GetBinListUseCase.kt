package go.cft.domain.usecases

import go.cft.domain.exceptions.GetBinListException
import go.cft.domain.models.params.GetBinListByBinParam
import go.cft.domain.models.params.GetBinListParam
import go.cft.domain.models.params.IsBinSavedParam
import go.cft.domain.models.params.SaveBinParam
import go.cft.domain.models.results.GetBinListResult
import go.cft.domain.repositories.BinListRepository
import javax.inject.Inject

class GetBinListUseCase @Inject constructor() {

    @Inject
    lateinit var repository: BinListRepository

    suspend fun execute(param: GetBinListParam): GetBinListResult {
        try {
            val binList =
                repository.getBinListByBin(GetBinListByBinParam(param.bin))?.idBinList
                    ?: throw GetBinListException("Null result get data by bin")
            val isBinNotExist = repository.isBinSaved(IsBinSavedParam(binList.id)).not()
            if (isBinNotExist) repository.saveBin(SaveBinParam(binList))
            return GetBinListResult(binList.binList)
        } catch (ex: Exception) {
            throw GetBinListException("Error get data by bin")
        }
    }
}