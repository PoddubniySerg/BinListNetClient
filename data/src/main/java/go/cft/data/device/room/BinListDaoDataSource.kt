package go.cft.data.device.room

import go.cft.data.models.MainBinList
import go.cft.data.repositories.DeviceBinListRepository
import go.cft.data.util.Converter
import go.cft.domain.models.entity.IdBinList
import javax.inject.Inject

class BinListDaoDataSource @Inject constructor() : DeviceBinListRepository {

    @Inject
    lateinit var binListDao: BinListDao

    @Inject
    lateinit var converter: Converter

    override suspend fun getBinsList(): List<IdBinList> {
        return binListDao.getBinsList().map { MainBinList(it.id, it) }
    }

    override fun isBinSaved(bin: String): Boolean {
        return binListDao.isBinSaved(bin)
    }

    override suspend fun saveBin(mainBinList: IdBinList) {
        binListDao.save(converter.convert(mainBinList))
    }
}