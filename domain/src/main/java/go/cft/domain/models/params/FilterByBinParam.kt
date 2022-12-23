package go.cft.domain.models.params

import go.cft.domain.models.entity.IdBinList

data class FilterByBinParam(val bin: String, val idsBinList: List<IdBinList>)
