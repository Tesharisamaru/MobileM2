package fr.mastersid.rio.stackoverflow.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersid.rio.stackoverflow.data.Question
import fr.mastersid.rio.stackoverflow.data.QuestionRepository
import fr.mastersid.rio.stackoverflow.data.QuestionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionListViewModel @Inject constructor(
    private  val questionRepository: QuestionRepository
): ViewModel() {
    private val _questionList : MutableLiveData<List<Question>> = MutableLiveData ( emptyList () )
    val questionList : LiveData<List<Question>> = _questionList

    private val _isUpdating = MutableLiveData ( false )
    private val _isNetFailing = MutableLiveData ( false)
    private val _isHttpFailing = MutableLiveData ( false)

    val isUpdating : LiveData<Boolean> = _isUpdating
    val isNetFailing : LiveData<Boolean> = _isNetFailing
    val isHttpFailing : LiveData<Boolean> = _isHttpFailing

    init {
        viewModelScope.launch ( Dispatchers.IO ) {
            questionRepository.questionResponse.collect { response ->
                when (response){
                    is QuestionResponse.Pending -> {
                        _isUpdating.postValue ( true )
                        _isNetFailing.postValue( false)
                    }
                    is QuestionResponse.Success -> {
                        _questionList.postValue ( response . list )
                        _isUpdating.postValue ( false )
                        _isNetFailing.postValue(false)
                        _isHttpFailing.postValue(false)
                    }
                    is QuestionResponse.NetError -> {
                        _isNetFailing.postValue(true)
                        _isHttpFailing.postValue(false)
                        _isUpdating.postValue(false)
                    }
                    is QuestionResponse.HttpError -> {
                        _isNetFailing.postValue(false)
                        _isHttpFailing.postValue(true)
                        _isUpdating.postValue(false)
                    }
                }
            }
        }
    }
    fun updateQuestionList () {
        viewModelScope.launch( Dispatchers.IO ) {
            questionRepository.updateQuestionInfo ()
        }
    }
}