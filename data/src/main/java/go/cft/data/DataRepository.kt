package go.cft.data

import go.cft.data.models.MainBinList
import go.cft.data.repositories.DeviceBinListRepository
import go.cft.data.repositories.NetworkBinListRepository
import go.cft.domain.models.entity.IdBinList
import go.cft.domain.models.params.GetBinListByBinParam
import go.cft.domain.models.params.IsBinSavedParam
import go.cft.domain.models.params.RemoveBinListByBinParam
import go.cft.domain.models.params.SaveBinParam
import go.cft.domain.models.results.BinListByBin
import go.cft.domain.repositories.BinListRepository
import javax.inject.Inject

class DataRepository : BinListRepository {

    @Inject
    lateinit var networkBinListRepository: NetworkBinListRepository

    @Inject
    lateinit var deviceBinListRepository: DeviceBinListRepository

    override suspend fun getBinListByBin(param: GetBinListByBinParam): BinListByBin {
        val binList =
            if (param.isExist) deviceBinListRepository.getBinListByBin(param.bin).binList
            else networkBinListRepository.getBinList(param.bin)
        return BinListByBin(MainBinList(param.bin, binList ?: throw RuntimeException()))
    }

    override suspend fun getBinsList(): List<IdBinList> {
        return deviceBinListRepository.getBinsList()
    }

    override suspend fun isBinSaved(param: IsBinSavedParam): Boolean {
        return deviceBinListRepository.isBinSaved(param.bin)
    }

    override suspend fun saveBin(param: SaveBinParam) {
        deviceBinListRepository.saveBin(param.binList)
    }

    override suspend fun removeBinList(param: RemoveBinListByBinParam) {
        deviceBinListRepository.removeBin(param.bin)
    }
}