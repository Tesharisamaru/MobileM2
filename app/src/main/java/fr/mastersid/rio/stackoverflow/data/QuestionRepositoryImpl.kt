package fr.mastersid.rio.stackoverflow.data

import android.util.Log
import fr.mastersid.rio.stackoverflow.module.CoroutineScopeIO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionRepositoryImpl @Inject constructor(
    private val questionWebService: QuestionWebService,
    private val  questionDao: QuestionDao,
    @CoroutineScopeIO private val coroutineScopeIO : CoroutineScope
) : QuestionRepository {
    override val questionResponse : MutableStateFlow<QuestionResponse> = MutableStateFlow(
        QuestionResponse.Success(emptyList())
    )
    init {
        coroutineScopeIO.launch {
            questionDao.getQuestionListFlow().collect { list ->
                questionResponse . emit ( QuestionResponse.Success (list))
            }
        }
    }
    override suspend fun updateQuestionInfo() {
        questionResponse.emit(QuestionResponse.Pending)
        val list = questionWebService
            .getQuestionList()
        //questionResponse.emit ( QuestionResponse.Success( list ))
        questionDao.insertAll(list)
    }

}