package go.cft.data.network.retrofit.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import go.cft.domain.models.entity.BankBinList

@JsonClass(generateAdapter = true)
data class BankBinListDto(
    @Json(name = "name") override val name: String?,
    @Json(name = "url") override val url: String?,
    @Json(name = "phone") override val phone: String?,
    @Json(name = "city") override val city: String?
) : BankBinList
