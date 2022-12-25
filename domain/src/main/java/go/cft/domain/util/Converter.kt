package go.cft.domain.util

import go.cft.domain.models.entity.IdBinList
import go.cft.domain.models.entity.ListItemBinList
import javax.inject.Inject

class Converter @Inject constructor() {

    companion object {
        const val BIN_SECTION_SIZE = 4
        const val BIN_SEPARATOR = " "
    }

    fun convert(idBinList: IdBinList): ListItemBinList {
        val id = idBinList.id
        val country = idBinList.binList?.country
        val bank = idBinList.binList?.bank
        return ListItemBinList(
            buildString {
                append(id?.subSequence(startIndex = 0, BIN_SECTION_SIZE))
                append(BIN_SEPARATOR)
                append(id?.subSequence(BIN_SECTION_SIZE, id.length))
            },
            buildString {
                append(country?.emoji ?: "Country no flag,")
                append(" ")
                append(country?.name ?: "Country no name")
            },
            buildString {
                append(bank?.name ?: "Bank no name")
                append(", ")
                append(bank?.city ?: "Bank no city")
            }
        )
    }
}