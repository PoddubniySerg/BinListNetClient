package go.cft.data.network.retrofit

import go.cft.data.network.retrofit.dto.BinListDto
import go.cft.data.repositories.NetworkBinListRepository
import javax.inject.Inject

class BinListRetrofitDataSource @Inject constructor() : NetworkBinListRepository {

    override suspend fun getBinList(url: String): BinListDto? {
        val responseLoader = BinListRetrofitInstance.BinListRetrofit
        val response = responseLoader?.getBinList(url)
        if (response != null && response.isSuccessful) return response.body()
        throw RuntimeException(response?.code().toString())
    }
}