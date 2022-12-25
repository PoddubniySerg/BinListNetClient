package go.cft.binlist.util

import go.cft.binlist.models.*
import go.cft.domain.models.entity.*
import javax.inject.Inject

class Converter @Inject constructor() {

    fun convertFullEntity(idBinList: IdBinList): MainBinListUi {
        val binList = idBinList.binList ?: throw RuntimeException("Bin List is null")
        return MainBinListUi(
            idBinList.id,
            convertFullEntity(binList)
        )
    }

    private fun convertFullEntity(binList: BinList): BinListUi {
        return BinListUi(
            convertFullEntity(binList.number),
            binList.scheme,
            binList.type,
            binList.brand,
            binList.prepaid,
            convertFullEntity(binList.country),
            convertFullEntity(binList.bank)
        )
    }

    private fun convertFullEntity(numberBinList: NumberBinList?): NumberBinListUi? {
        return if (numberBinList == null) null
        else
            NumberBinListUi(
                numberBinList.length,
                numberBinList.luhn
            )
    }

    private fun convertFullEntity(countryBinList: CountryBinList?): CountryBinListUi? {
        return if (countryBinList == null) null
        else
            CountryBinListUi(
                countryBinList.numeric,
                countryBinList.alpha2,
                countryBinList.name,
                countryBinList.emoji,
                countryBinList.currency,
                countryBinList.latitude,
                countryBinList.longitude
            )
    }

    private fun convertFullEntity(bankBinList: BankBinList?): BankBinListUi? {
        return if (bankBinList == null) null
        else
            BankBinListUi(
                bankBinList.name,
                bankBinList.url,
                bankBinList.phone,
                bankBinList.city
            )
    }
}