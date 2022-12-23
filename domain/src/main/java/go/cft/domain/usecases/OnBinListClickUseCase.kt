package go.cft.domain.usecases

import go.cft.domain.exceptions.OnBinListClickException
import go.cft.domain.models.params.GetBinListByBinParam
import go.cft.domain.models.params.OnBinListClickParam
import go.cft.domain.models.results.OnBinListClickResult
import go.cft.domain.repositories.BinListRepository
import javax.inject.Inject

class OnBinListClickUseCase @Inject constructor() {

    @Inject
    lateinit var repository: BinListRepository

    suspend fun execute(param: OnBinListClickParam): OnBinListClickResult {
        try {
            val getBinListByBinParam = GetBinListByBinParam(param.bin)
            val binList = repository.getBinListByBin(getBinListByBinParam)?.idBinList
            return OnBinListClickResult(
                binList ?: throw OnBinListClickException("Fond null by BIN")
            )
        } catch (ex: Exception) {
            throw OnBinListClickException("Get list by BIN error")
        }
    }
}