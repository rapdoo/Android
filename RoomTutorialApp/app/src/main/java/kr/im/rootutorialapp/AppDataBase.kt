package kr.im.rootutorialapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Create::class), version = 1)

abstract class AppDataBase : RoomDatabase() {

    abstract fun createDao() : CreateDao
}