package go.cft.data.models

import go.cft.domain.models.entity.BinList
import go.cft.domain.models.entity.IdBinList

data class MainBinList(
    override val id: String,
    override val binList: BinList
) : IdBinList
