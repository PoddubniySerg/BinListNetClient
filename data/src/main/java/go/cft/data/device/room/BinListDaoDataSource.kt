package go.cft.data.device.room

import go.cft.data.models.MainBinList
import go.cft.data.repositories.DeviceBinListRepository
import go.cft.data.util.Converter
import go.cft.domain.models.entity.IdBinList
import javax.inject.Inject

open class BinListDaoDataSource @Inject constructor() : DeviceBinListRepository {

    @Inject
    protected lateinit var binListDao: BinListDao

    @Inject
    protected lateinit var converter: Converter

    override suspend fun getBinsList(): List<IdBinList> =
        binListDao.getBinsList().map { MainBinList(it.id, it) }

    override suspend fun getBinListByBin(bin: String): IdBinList =
        MainBinList(bin, binListDao.getBinListByBin(bin))

    override suspend fun isBinSaved(bin: String): Boolean = binListDao.isBinSaved(bin)

    override suspend fun saveBin(mainBinList: IdBinList) =
        binListDao.save(converter.convert(mainBinList))

    override suspend fun removeBin(bin: String) = binListDao.remove(bin)
}