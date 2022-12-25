package go.cft.domain.repositories

import go.cft.domain.models.entity.IdBinList
import go.cft.domain.models.params.GetBinListByBinParam
import go.cft.domain.models.params.IsBinSavedParam
import go.cft.domain.models.params.RemoveBinListByBinParam
import go.cft.domain.models.params.SaveBinParam
import go.cft.domain.models.results.BinListByBin

interface BinListRepository {

    suspend fun getBinListByBin(param: GetBinListByBinParam): BinListByBin?

    suspend fun getBinsList(): List<IdBinList>

    suspend fun isBinSaved(param: IsBinSavedParam): Boolean

    suspend fun saveBin(param: SaveBinParam)

    suspend fun removeBinList(param: RemoveBinListByBinParam)
}