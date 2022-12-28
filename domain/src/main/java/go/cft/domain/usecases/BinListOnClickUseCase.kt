package go.cft.domain.usecases

import go.cft.domain.exceptions.OnBinListClickException
import go.cft.domain.models.params.GetBinListByBinParam
import go.cft.domain.models.params.OnBinListClickParam
import go.cft.domain.models.results.BinListOnClicked
import go.cft.domain.repositories.BinListRepository
import javax.inject.Inject

open class BinListOnClickUseCase @Inject constructor() {

    @Inject
    protected lateinit var repository: BinListRepository

    suspend fun execute(param: OnBinListClickParam): BinListOnClicked {
        try {
            val getBinListByBinParam = GetBinListByBinParam(param.bin, isExist = true)
            val binList = repository.getBinListByBin(getBinListByBinParam)?.result
            return BinListOnClicked(
                binList ?: throw OnBinListClickException("Fond null by BIN")
            )
        } catch (ex: Exception) {
            throw OnBinListClickException("Get list by BIN not found")
        }
    }
}