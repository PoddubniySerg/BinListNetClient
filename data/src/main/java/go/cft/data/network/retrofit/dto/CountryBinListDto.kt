package go.cft.data.network.retrofit.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import go.cft.domain.models.entity.CountryBinList

@JsonClass(generateAdapter = true)
data class CountryBinListDto(
    @Json(name = "numeric") override val numeric: String?,
    @Json(name = "alpha2") override val alpha2: String?,
    @Json(name = "name") override val name: String?,
    @Json(name = "emoji") override val emoji: String?,
    @Json(name = "currency") override val currency: String?,
    @Json(name = "latitude") override val latitude: Int?,
    @Json(name = "longitude") override val longitude: Int?
) : CountryBinList
