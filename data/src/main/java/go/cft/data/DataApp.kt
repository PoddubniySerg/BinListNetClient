package go.cft.data

import android.app.Application
import androidx.room.Room
import go.cft.data.device.room.AppDatabase

open class DataApp: Application() {

    open lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "db"
        ).build()
    }
}