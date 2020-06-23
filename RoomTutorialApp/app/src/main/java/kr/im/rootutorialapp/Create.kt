package kr.im.rootutorialapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Create(@PrimaryKey val uid : Int, @ColumnInfo(name = "description") val desc : String, val date : String)