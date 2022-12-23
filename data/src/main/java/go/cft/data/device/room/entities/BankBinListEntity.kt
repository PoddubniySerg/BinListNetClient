package go.cft.data.device.room.entities

import androidx.room.ColumnInfo
import go.cft.domain.models.entity.BankBinList

data class BankBinListEntity(
    @ColumnInfo(name = "name") override val name: String?,
    @ColumnInfo(name = "url") override val url: String?,
    @ColumnInfo(name = "phone") override val phone: String?,
    @ColumnInfo(name = "city") override val city: String?
) : BankBinList
