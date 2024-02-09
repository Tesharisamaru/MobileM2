package fr.mastersid.rio.stackoverflow.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.mastersid.rio.stackoverflow.data.QuestionDao
import fr.mastersid.rio.stackoverflow.data.QuestionRoomDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object QuestionRoomDatabaseModule {

    @Provides
    fun provideQuestionDao(questionRoomDatabase: QuestionRoomDatabase): QuestionDao {
        return questionRoomDatabase.questionDao()
    }

    @Provides
    @Singleton
    fun provideQuestionRoomDatabase(@ApplicationContext appContext : Context):
            QuestionRoomDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            QuestionRoomDatabase:: class.java,
            "Question_database"
        ).build()
    }
}