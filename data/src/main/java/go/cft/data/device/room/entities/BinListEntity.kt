package go.cft.data.device.room.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import go.cft.domain.models.entity.BinList

@Entity(tableName = "bin_lists")
data class BinListEntity(
    @PrimaryKey val id: String,
    @Embedded override val number: NumberBinListEntity?,
    @ColumnInfo(name = "scheme") override val scheme: String?,
    @ColumnInfo(name = "type") override val type: String?,
    @ColumnInfo(name = "brand") override val brand: String?,
    @ColumnInfo(name = "prepaid") override val prepaid: Boolean?,
    @Embedded(prefix = "country") override val country: CountryBinListEntity?,
    @Embedded(prefix = "bank") override val bank: BankBinListEntity?
) : BinList
