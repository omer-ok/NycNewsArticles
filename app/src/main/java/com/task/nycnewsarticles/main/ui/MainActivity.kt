package com.task.nycnewsarticles.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.media.shaadoow.foundation.mvi.BaseActivity
import com.task.nycnewsarticles.R
import com.task.nycnewsarticles.foundatiion.utilz.DataState
import com.task.nycnewsarticles.foundatiion.utilz.replaceFragment
import com.task.nycnewsarticles.main.adapters.ArticlesAdapter
import com.task.nycnewsarticles.main.intent.MainScreenIntent
import com.task.nycnewsarticles.main.model.Articles
import com.task.nycnewsarticles.main.model.Result
import com.task.nycnewsarticles.main.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@AndroidEntryPoint
class MainActivity : BaseActivity() {
    @Inject
    lateinit var mainViewModel: MainViewModel
    var nycArticles=ArrayList<Result>()
    lateinit var adapter: ArticlesAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    var isLoading=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            mainViewModel.userIntent.send(MainScreenIntent.GetArticles())
        }
        subscribeObservers()
        linearLayoutManager = LinearLayoutManager(this)
        articles.layoutManager = linearLayoutManager
        adapter = ArticlesAdapter(this, nycArticles)
        articles.adapter = adapter
    }
    //As a result, the ViewModel updates the View with new states and is then displayed to the user.
    private fun subscribeObservers() {
        mainViewModel.nycArticlesResponse.observe(
            this,
            Observer { nycArticlesResponse ->
                when (nycArticlesResponse) {
                    is DataState.Success<Articles> -> {
                        if (nycArticlesResponse.data.results.size>0) {
                            noItems.visibility= View.GONE
                            for (articles in nycArticlesResponse.data.results) {
                                nycArticles.add(articles)
                                adapter.notifyItemInserted(nycArticles.size)
                            }
                        }
                        if (nycArticles.size==0)
                            noItems.visibility= View.VISIBLE
                    }
                    is DataState.Error -> {

                    }
                    is DataState.Loading -> {

                    }
                }
            })
    }

    fun replaceFragmentFromMain(fragment: Fragment) {
        supportFragmentManager.replaceFragment(R.id.content, fragment)
    }
}