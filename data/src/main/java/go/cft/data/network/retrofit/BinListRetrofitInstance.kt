package go.cft.data.network.retrofit

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object BinListRetrofitInstance {

    private const val BASE_URL = "https://lookup.binlist.net"

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    val BinListRetrofit: BinListNetworkLoader? =
        retrofit.create(BinListNetworkLoader::class.java)
}