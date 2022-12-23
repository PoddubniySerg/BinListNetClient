package go.cft.data.device.room

import androidx.room.*
import go.cft.data.device.room.entities.BinListEntity

@Dao
interface BinListDao {
    @Query("SELECT * FROM bin_lists")
    suspend fun getBinsList(): List<BinListEntity>

    @Query("SELECT COUNT() > 0 FROM bin_lists WHERE id = :bin")
    fun isBinSaved(bin: String): Boolean

    @Insert
    suspend fun save(binList: BinListEntity)
}