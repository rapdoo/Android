package kr.im.rootutorialapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CreateDao {

    @Query("SELECT * FROM `create`")
    fun getALL() : List<Create>

    @Insert
    fun insertCreateAll(vararg create : Create)
}