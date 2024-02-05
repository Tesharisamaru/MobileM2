package fr.mastersid.rio.stackoverflow.data

sealed interface QuestionResponse {
    object Pending : QuestionResponse
    @JvmInline
    value class Success ( val list : List < Question >) : QuestionResponse
}