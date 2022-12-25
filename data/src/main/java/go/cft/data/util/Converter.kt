package go.cft.data.util

import go.cft.data.device.room.entities.BankBinListEntity
import go.cft.data.device.room.entities.BinListEntity
import go.cft.data.device.room.entities.CountryBinListEntity
import go.cft.data.device.room.entities.NumberBinListEntity
import go.cft.domain.models.entity.BankBinList
import go.cft.domain.models.entity.CountryBinList
import go.cft.domain.models.entity.IdBinList
import go.cft.domain.models.entity.NumberBinList
import javax.inject.Inject

class Converter @Inject constructor() {

    fun convert(binList: IdBinList): BinListEntity {
        val properties = binList.binList
        val id = binList.id ?: throw RuntimeException("Id can not be null")

        return BinListEntity(
            id,
            convert(properties?.number),
            properties?.scheme,
            properties?.type,
            properties?.brand,
            properties?.prepaid,
            convert(properties?.country),
            convert(properties?.bank)
        )
    }

    private fun convert(numberBinList: NumberBinList?): NumberBinListEntity? {
        return if (numberBinList == null) null
        else
            NumberBinListEntity(
                numberBinList.length,
                numberBinList.luhn
            )
    }

    private fun convert(countryBinList: CountryBinList?): CountryBinListEntity? {
        return if (countryBinList == null) null
        else
            CountryBinListEntity(
                countryBinList.numeric,
                countryBinList.alpha2,
                countryBinList.name,
                countryBinList.emoji,
                countryBinList.currency,
                countryBinList.latitude,
                countryBinList.longitude
            )
    }

    private fun convert(bankBinList: BankBinList?): BankBinListEntity? {
        return if (bankBinList == null) null
        else
            BankBinListEntity(
                bankBinList.name,
                bankBinList.url,
                bankBinList.phone,
                bankBinList.city
            )
    }
}