package fr.mastersid.rio.stackoverflow.data

class ListQuestionJson (
    val items: List<QuestionJson>
)
data class QuestionJson(
    /*On souhaite récupérer answer_count / question_id / title */
    val question_id: Int,
    val title: String,
    val answer_count : Int,
)
