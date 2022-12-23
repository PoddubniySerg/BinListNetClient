package go.cft.data.repositories

import go.cft.domain.models.entity.IdBinList

interface DeviceBinListRepository {

    suspend fun getBinsList(): List<IdBinList>

    fun isBinSaved(bin: String): Boolean

    suspend fun saveBin(mainBinList: IdBinList)
}