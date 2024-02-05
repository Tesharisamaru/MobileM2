package fr.mastersid.rio.stackoverflow.data

import kotlinx.coroutines.flow.Flow

interface QuestionRepository {
    val questionResponse : Flow<QuestionResponse>
    suspend fun updateQuestionInfo ()
}