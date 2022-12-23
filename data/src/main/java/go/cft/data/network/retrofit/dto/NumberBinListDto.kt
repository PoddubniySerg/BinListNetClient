package go.cft.data.network.retrofit.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import go.cft.domain.models.entity.NumberBinList

@JsonClass(generateAdapter = true)
data class NumberBinListDto(
    @Json(name = "length") override val length: Int?,
    @Json(name = "luhn") override val luhn: Boolean?
) : NumberBinList
