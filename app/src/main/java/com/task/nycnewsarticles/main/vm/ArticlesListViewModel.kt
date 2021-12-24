package com.task.nycnewsarticles.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.nycnewsarticles.foundatiion.utilz.DataState
import com.task.nycnewsarticles.main.intent.MainScreenIntent
import com.task.nycnewsarticles.main.model.Articles
import com.task.nycnewsarticles.main.repository.MainRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@FlowPreview
@InternalCoroutinesApi
class ArticlesListViewModel
constructor(
    private val mainRepo: MainRepo
): ViewModel() {
    val userIntent = Channel<MainScreenIntent>(Channel.UNLIMITED)

    private val _nycArticlesResponse: MutableLiveData<DataState<Articles>> = MutableLiveData()
    val nycArticlesResponse: LiveData<DataState<Articles>>
        get() = _nycArticlesResponse


    init {
        handleIntent()
    }

    // The ViewModel handles these events and communicates with the Model.
    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainScreenIntent.GetArticles -> getArticles()
                }
            }
        }
    }
    //As a result, the ViewModel updates the View with new states and is then displayed to the user.
    fun getArticles() {
        viewModelScope.launch {
            mainRepo.getArticles()
                .onEach { dataState ->
                    _nycArticlesResponse.value = dataState
                }.launchIn(viewModelScope)
        }
    }

}