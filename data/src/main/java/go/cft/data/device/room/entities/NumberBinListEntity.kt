package go.cft.data.device.room.entities

import androidx.room.ColumnInfo
import go.cft.domain.models.entity.NumberBinList

data class NumberBinListEntity(
    @ColumnInfo(name = "length") override val length: Int?,
    @ColumnInfo(name = "luhn") override val luhn: Boolean?
) : NumberBinList
