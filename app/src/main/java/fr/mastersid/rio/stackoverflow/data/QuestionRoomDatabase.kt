package fr.mastersid.rio.stackoverflow.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Question::class],
    version = 1,
    exportSchema = false,
)
abstract class QuestionRoomDatabase : RoomDatabase(){
    abstract fun questionDao(): QuestionDao
}