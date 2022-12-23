package go.cft.domain.repositories

import go.cft.domain.models.entity.BinList
import go.cft.domain.models.entity.IdBinList
import go.cft.domain.models.params.GetBinListByBinParam
import go.cft.domain.models.params.IsBinSavedParam
import go.cft.domain.models.params.SaveBinParam
import go.cft.domain.models.results.GetBinListByBinResult

interface BinListRepository {

    suspend fun getBinListByBin(param: GetBinListByBinParam): GetBinListByBinResult?

    suspend fun getBinsList(): List<IdBinList>

    fun isBinSaved(param: IsBinSavedParam): Boolean

    suspend fun saveBin(param: SaveBinParam)
}