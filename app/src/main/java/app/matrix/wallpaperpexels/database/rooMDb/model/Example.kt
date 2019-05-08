package app.matrix.wallpaperpexels.database.rooMDb.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Example")
data class Example(
    @PrimaryKey val id:Int?,
    @ColumnInfo(name = "userName") val userName: String?
)