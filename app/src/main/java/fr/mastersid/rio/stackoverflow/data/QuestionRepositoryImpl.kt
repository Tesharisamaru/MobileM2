package fr.mastersid.rio.stackoverflow.data

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class QuestionRepositoryImpl @Inject constructor(
    private val questionWebService: QuestionWebService
) : QuestionRepository {
    override val questionResponse : MutableStateFlow<QuestionResponse> = MutableStateFlow(
        QuestionResponse.Success(emptyList())
    )

    override suspend fun updateQuestionInfo() {
        questionResponse.emit(QuestionResponse.Pending)
        val list = questionWebService
            .getQuestionList()
        questionResponse.emit ( QuestionResponse.Success( list ))
    }
}