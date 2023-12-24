package com.mambo.play.tmdb.data.sources.remote.di

import com.mambo.play.tmdb.data.sources.remote.TmdbApi
import com.mambo.play.tmdb.data.sources.remote.TmdbApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    fun providesHttpClient(): HttpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    @Provides
    fun providesTmdbApi(client: HttpClient): TmdbApi = TmdbApiImpl(client = client)

}