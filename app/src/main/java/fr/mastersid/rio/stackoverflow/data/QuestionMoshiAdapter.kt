package fr.mastersid.rio.stackoverflow.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class QuestionMoshiAdapter {
    @FromJson
    fun fromJson(listQuestionJson: ListQuestionJson): List<Question>{
        return listQuestionJson.list.map{ questionJson ->
            Question( questionJson.question_id, questionJson.title, questionJson.main.answer_count)

        }
    }

    @ToJson
    fun toJson ( listQuestion : List <Question>) : ListQuestionJson {
        return ListQuestionJson(
            listQuestion. map { question ->
                QuestionJson ( question.id, question.title, MainJson(question.answerCount))
            }
        )
    }
}