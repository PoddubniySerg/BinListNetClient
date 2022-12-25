package go.cft.data.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import go.cft.data.DataApp
import go.cft.data.DataRepository
import go.cft.data.device.room.BinListDao
import go.cft.data.device.room.BinListDaoDataSource
import go.cft.data.network.retrofit.BinListRetrofitDataSource
import go.cft.data.repositories.DeviceBinListRepository
import go.cft.data.repositories.NetworkBinListRepository
import go.cft.domain.repositories.BinListRepository

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideBinListRepository(
        networkBinListRepository: NetworkBinListRepository,
        binListDaoDataSource: BinListDaoDataSource
    ): BinListRepository {
        val repository = DataRepository()
        repository.deviceBinListRepository = binListDaoDataSource
        repository.networkBinListRepository = networkBinListRepository
        return repository
    }

    @Provides
    fun provideNetworkBinListRepository(): NetworkBinListRepository = BinListRetrofitDataSource()

    @Provides
    fun provideDeviceBinListRepository(): DeviceBinListRepository =
        BinListDaoDataSource()

    @Provides
    fun provideBinListDao(application: Application): BinListDao =
        (application as DataApp).database.binListDao()
}