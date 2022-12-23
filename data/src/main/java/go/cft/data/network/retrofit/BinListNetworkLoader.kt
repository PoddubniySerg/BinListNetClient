package go.cft.data.network.retrofit

import go.cft.data.network.retrofit.dto.BinListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url

interface BinListNetworkLoader {

    @Headers(
        "Accept: Application/json",
        "Content-type: Application/json",
        "Accept-Version: 3"
    )
    @GET
    suspend fun getBinList(@Url bin: String): Response<BinListDto>
}