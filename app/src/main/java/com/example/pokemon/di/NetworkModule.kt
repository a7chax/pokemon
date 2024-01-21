package com.example.pokemon.di

import com.example.pokemon.data.*
import com.example.pokemon.domain.repositories.IPokemonRepositories
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providePokemonService(retrofit : Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun providePokemonRepository(repositories: PokemonRepositories): IPokemonRepositories {
        return  repositories
    }
}