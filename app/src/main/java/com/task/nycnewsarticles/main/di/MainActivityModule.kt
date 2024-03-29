package com.task.nycnewsarticles.main.di

import com.task.nycnewsarticles.main.network.MainApi
import com.task.nycnewsarticles.main.repository.MainRepo
import com.task.nycnewsarticles.main.vm.ArticlesListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import retrofit2.Retrofit
import javax.inject.Singleton

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object MainActivityModule {



    @Singleton
    @Provides
    fun provideMainApi(retrofit: Retrofit.Builder): MainApi {
        return retrofit.build()
            .create(MainApi::class.java)
    }
    @Singleton
    @Provides
    fun provideMainRepo(
        mainApi: MainApi
    ): MainRepo {
        return MainRepo(mainApi)
    }

    @Singleton
    @Provides
    fun provideArticlesListViewModel(
        mainRepo: MainRepo
    ): ArticlesListViewModel {
        return ArticlesListViewModel(mainRepo)
    }

}