package go.cft.data.repositories

import go.cft.domain.models.entity.BinList

interface NetworkBinListRepository {

    suspend fun getBinList(url: String): BinList?
}