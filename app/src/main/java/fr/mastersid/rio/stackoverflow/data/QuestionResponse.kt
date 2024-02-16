package fr.mastersid.rio.stackoverflow.data

sealed interface QuestionResponse {
    data object Pending : QuestionResponse

    data object NetError : QuestionResponse

    data object HttpError : QuestionResponse

    @JvmInline
    value class Success ( val list : List < Question >) : QuestionResponse
}