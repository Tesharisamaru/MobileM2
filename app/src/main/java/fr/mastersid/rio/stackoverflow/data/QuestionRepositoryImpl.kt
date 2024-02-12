package fr.mastersid.rio.stackoverflow.data


import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import fr.mastersid.rio.stackoverflow.module.CoroutineScopeIO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
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
                questionResponse.emit(QuestionResponse.Success (list))
            }
        }
    }
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun updateQuestionInfo() {
        try {
        questionResponse.emit(QuestionResponse.Pending)
        val list = questionWebService
            .getQuestionList()
        questionDao.insertAll(list)
        } catch (e:IOException){
             Log.d("firstBug","${e.message}")

        } catch(e:HttpException){
            Log.d("secondBug", "${e.message}")
        }
    }

}