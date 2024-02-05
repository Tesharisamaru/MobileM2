package fr.mastersid.rio.stackoverflow.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import fr.mastersid.rio.stackoverflow.data.QuestionRepository
import fr.mastersid.rio.stackoverflow.data.QuestionRepositoryImpl

@Module
@InstallIn( ViewModelComponent :: class )
abstract class QuestionRepositoryModule {
    @Binds
    abstract fun bindQuestionRepository ( questionRepositoryImpl : QuestionRepositoryImpl):
            QuestionRepository
}