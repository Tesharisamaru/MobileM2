package fr.mastersid.rio.stackoverflow.data

class ListQuestionJson (
    val list: List<QuestionJson>
)
data class QuestionJson(
    /*On souhaite récupérer answer_count / question_id / title */
    val question_id: Int,
    val title: String,
    val main: MainJson,
)

data class MainJson(
 val answer_count : Int
)
