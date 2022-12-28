package go.cft.domain.usecases

import go.cft.domain.exceptions.GetBinListException
import go.cft.domain.models.params.GetBinListByBinParam
import go.cft.domain.models.params.GetBinListParam
import go.cft.domain.models.params.IsBinSavedParam
import go.cft.domain.models.params.SaveBinParam
import go.cft.domain.models.results.GetBinList
import go.cft.domain.repositories.BinListRepository
import javax.inject.Inject

open class GetBinListUseCase @Inject constructor() {

    @Inject
    protected lateinit var repository: BinListRepository

    suspend fun execute(param: GetBinListParam): GetBinList {
        try {
            val isBinExist = repository.isBinSaved(IsBinSavedParam(param.bin))
            val binList =
                repository.getBinListByBin(GetBinListByBinParam(param.bin, isBinExist))?.result
                    ?: throw GetBinListException("Null result get data by bin")
            if (isBinExist.not()) repository.saveBin(SaveBinParam(binList))
            return GetBinList(binList)
        } catch (ex: Exception) {
            ex.printStackTrace()
            throw GetBinListException("BIN not found")
        }
    }
}