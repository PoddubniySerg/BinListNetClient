package go.cft.data.device.room.entities

import androidx.room.ColumnInfo
import go.cft.domain.models.entity.CountryBinList

data class CountryBinListEntity(
    @ColumnInfo(name = "numeric") override val numeric: String?,
    @ColumnInfo(name = "alpha_2") override val alpha2: String?,
    @ColumnInfo(name = "name") override val name: String?,
    @ColumnInfo(name = "emoji") override val emoji: String?,
    @ColumnInfo(name = "currency") override val currency: String?,
    @ColumnInfo(name = "latitude") override val latitude: Int?,
    @ColumnInfo(name = "longitude") override val longitude: Int?
) : CountryBinList
