package fr.mastersid.rio.stackoverflow.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/* https://api.stackexchange.com/2.3/ */
/*Requete de ce projet */
/* questions?pagesize=20&order=desc&sort=activity&site=stackoverflow */

/*On souhaite récupérer answer_count / question_id / title */

interface QuestionWebService {
    @GET("questions?")
    suspend fun getQuestionList (
        @Query("pagesize") id : Int = 20 ,
        @Query("order") order : String = "desc" ,
        @Query("sort") sort : String = "activity" ,
        @Query("site") site : String = "stackoverflow" ,
    ): ListQuestionJson
}