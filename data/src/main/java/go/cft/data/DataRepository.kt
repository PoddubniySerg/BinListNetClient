package go.cft.data

import go.cft.data.models.MainBinList
import go.cft.data.repositories.DeviceBinListRepository
import go.cft.data.repositories.NetworkBinListRepository
import go.cft.domain.models.entity.IdBinList
import go.cft.domain.models.params.GetBinListByBinParam
import go.cft.domain.models.params.IsBinSavedParam
import go.cft.domain.models.params.SaveBinParam
import go.cft.domain.models.results.GetBinListByBinResult
import go.cft.domain.repositories.BinListRepository
import javax.inject.Inject

class DataRepository @Inject constructor() : BinListRepository {

    @Inject
    lateinit var networkBinListRepository: NetworkBinListRepository

    @Inject
    lateinit var deviceBinListRepository: DeviceBinListRepository

    override suspend fun getBinListByBin(param: GetBinListByBinParam): GetBinListByBinResult {
        val binList = networkBinListRepository.getBinList(param.bin) ?: throw RuntimeException()
        return GetBinListByBinResult(MainBinList(param.bin, binList))
    }

    override suspend fun getBinsList(): List<IdBinList> {
        return deviceBinListRepository.getBinsList()
    }

    override fun isBinSaved(param: IsBinSavedParam): Boolean {
        return deviceBinListRepository.isBinSaved(param.bin)
    }

    override suspend fun saveBin(param: SaveBinParam) {
        deviceBinListRepository.saveBin(param.binList)
    }
}