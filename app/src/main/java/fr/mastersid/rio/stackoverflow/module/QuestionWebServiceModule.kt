package fr.mastersid.rio.stackoverflow.module

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.mastersid.rio.stackoverflow.data.QuestionWebService
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

private const val BASE_URL ="https://api.stackexchange.com/2.3/"

    @Module
@InstallIn(SingletonComponent::class)
class QuestionWebServiceModule {

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    fun provideQuestionWebservice ( retrofit:Retrofit): QuestionWebService {
        return retrofit.create( QuestionWebService::class.java )
    }
}