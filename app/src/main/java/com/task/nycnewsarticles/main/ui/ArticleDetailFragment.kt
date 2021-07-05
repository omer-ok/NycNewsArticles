package com.task.nycnewsarticles.main.ui

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.media.shaadoow.foundation.mvi.BaseFragment
import com.task.nycnewsarticles.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_article_detail.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@AndroidEntryPoint
class ArticleDetailFragment : BaseFragment(R.layout.fragment_article_detail) {

    companion object {
        fun newInstance(image:String,title: String,abstract:String,time:String,authoreName:String): ArticleDetailFragment {
            val fragment = ArticleDetailFragment()
            val bundle= Bundle()
            bundle.putString("image",image)
            bundle.putString("title",title)
            bundle.putString("abstract",abstract)
            bundle.putString("time",time)
            bundle.putString("authoreName",authoreName)
            fragment.arguments = bundle
            return fragment
        }
    }
    lateinit var mImage:String
    lateinit var mTitle:String
    lateinit var mAbstract:String
    lateinit var mTime:String
    lateinit var mAuthoreName:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val image=arguments?.getString("image")
        val title=arguments?.getString("title")
        val abstract=arguments?.getString("abstract")
        val time=arguments?.getString("time")
        val authoreName=arguments?.getString("authoreName")
        if(image!=null){
            mImage =image
        }
        if(title!=null){
            mTitle =title
        }
        if(abstract!=null){
            mAbstract =abstract
        }
        if(time!=null){
            mTime =time
        }
        if(authoreName!=null){
            mAuthoreName =authoreName
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext())
            .load(mImage)
            .fitCenter()
            .into(articleImage)
        title_article.text =mTitle
        articleDes.text = mAbstract
        time.text = mTime
        authoreName.text =mAuthoreName
    }
}