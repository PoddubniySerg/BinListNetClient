package go.cft.data.network.retrofit.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import go.cft.domain.models.entity.BankBinList
import go.cft.domain.models.entity.BinList
import go.cft.domain.models.entity.CountryBinList
import go.cft.domain.models.entity.NumberBinList

@JsonClass(generateAdapter = true)
data class BinListDto(
    @Json(name = "number") override val number: NumberBinListDto?,
    @Json(name = "scheme") override val scheme: String?,
    @Json(name = "type") override val type: String?,
    @Json(name = "brand") override val brand: String?,
    @Json(name = "prepaid") override val prepaid: Boolean?,
    @Json(name = "country") override val country: CountryBinListDto?,
    @Json(name = "bank") override val bank: BankBinListDto?
) : BinList
