package com.task.nycnewsarticles.main.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.media.shaadoow.foundation.mvi.BaseFragment
import com.task.nycnewsarticles.R
import com.task.nycnewsarticles.foundatiion.utilz.DataState
import com.task.nycnewsarticles.main.adapters.ArticlesAdapter
import com.task.nycnewsarticles.main.intent.MainScreenIntent
import com.task.nycnewsarticles.main.model.Articles
import com.task.nycnewsarticles.main.model.Result
import com.task.nycnewsarticles.main.vm.ArticlesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.articles_list_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@AndroidEntryPoint
class ArticlesListFragment : BaseFragment(R.layout.articles_list_fragment) {



    companion object {
        fun newInstance() = ArticlesListFragment()
    }

    @Inject
    lateinit var articlesListViewModel: ArticlesListViewModel
    var nycArticles=ArrayList<Result>()
    lateinit var adapter: ArticlesAdapter
    lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            articlesListViewModel.userIntent.send(MainScreenIntent.GetArticles())
        }
        subscribeObservers()
        linearLayoutManager = LinearLayoutManager(context)
        articles.layoutManager = linearLayoutManager
        adapter = ArticlesAdapter(activityContext(),this@ArticlesListFragment, nycArticles)
        articles.adapter = adapter
    }

    //As a result, the ViewModel updates the View with new states and is then displayed to the user.
    private fun subscribeObservers() {
        articlesListViewModel.nycArticlesResponse.observe(
            viewLifecycleOwner,
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


}