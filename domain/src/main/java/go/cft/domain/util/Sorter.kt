package go.cft.domain.util

import go.cft.domain.models.entity.IdBinList
import javax.inject.Inject

class Sorter @Inject constructor() {

    fun sort(list: List<IdBinList>): List<IdBinList> {
        return list.sortedBy { it.id }
    }
}