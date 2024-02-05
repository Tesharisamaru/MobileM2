package fr.mastersid.rio.stackoverflow.data

import com.squareup.moshi.FromJson

class QuestionMoshiAdapter {
    @FromJson
    fun fromJson(listQuestionJson: ListQuestionJson): List<Question>{
        return listQuestionJson.list.map{ questionJson ->
            Question( questionJson.question_id, questionJson.title, questionJson.main.answer_count)

        }
    }
}