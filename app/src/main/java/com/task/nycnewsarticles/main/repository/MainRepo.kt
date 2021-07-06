package com.task.nycnewsarticles.main.repository

import android.util.Log
import com.task.nycnewsarticles.foundatiion.utilz.DataState
import com.task.nycnewsarticles.main.model.Articles
import com.task.nycnewsarticles.main.network.MainApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepo
@Inject
constructor(private val mainApi: MainApi) {

    fun getArticles(): Flow<DataState<Articles>> = flow {
        emit(DataState.Loading)
        try {

            val reponse: Articles = mainApi.getArticles("OGOPAGvYGwEALz56ZHsc9VhjLZSblK3I")
            emit(DataState.Success(reponse))
        } catch (e: Exception) {
            Log.i("Excep",e.toString())
            emit(DataState.Error(e))
        }
    }
}