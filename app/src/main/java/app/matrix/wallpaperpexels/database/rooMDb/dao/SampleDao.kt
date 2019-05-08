package app.matrix.ticketingsystem.database.localDatabase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import app.matrix.wallpaperpexels.database.rooMDb.model.Example

@Dao
interface SampleDao {

    @Insert
    fun insert(example: Example)

    @Query("SELECT * FROM TicketSystem ORDER BY userName ASC")
    fun getAllTicket(): List<Example>

    @Delete
    fun delete(user: Example)


}