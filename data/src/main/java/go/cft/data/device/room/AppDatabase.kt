package go.cft.data.device.room

import androidx.room.Database
import androidx.room.RoomDatabase
import go.cft.data.device.room.entities.BinListEntity

@Database(entities = [BinListEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun binListDao(): BinListDao
}