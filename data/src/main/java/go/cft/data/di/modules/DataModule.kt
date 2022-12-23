package go.cft.data.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import go.cft.data.DataRepository
import go.cft.data.device.room.BinListDao
import go.cft.data.device.room.BinListDaoDataSource
import go.cft.data.DataApp
import go.cft.data.network.retrofit.BinListRetrofitDataSource
import go.cft.data.repositories.DeviceBinListRepository
import go.cft.data.repositories.NetworkBinListRepository
import go.cft.domain.repositories.BinListRepository

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideBinListDao(application: Application): BinListDao {
        return (application as DataApp).database.binListDao()
    }

    @Provides
    fun provideNetworkBinListRepository(): NetworkBinListRepository {
        return BinListRetrofitDataSource()
    }

    @Provides
    fun provideDeviceBinListRepository(): DeviceBinListRepository {
        return BinListDaoDataSource()
    }

    @Provides
    fun provideBinListRepository(): BinListRepository {
        return DataRepository()
    }
}