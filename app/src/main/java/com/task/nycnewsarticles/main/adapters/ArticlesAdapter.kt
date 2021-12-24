package com.task.nycnewsarticles.main.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.media.shaadoow.foundation.mvi.BaseAdapter
import com.task.nycnewsarticles.R
import com.task.nycnewsarticles.main.model.Result
import com.task.nycnewsarticles.main.ui.ArticlesListFragment
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.articles_item.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class ArticlesAdapter(var context: Context ,var articlesListFragment: ArticlesListFragment, var  articles: ArrayList<Result>
) : BaseAdapter(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.articles_item, parent, false)
        return ViewHolder(view)
    }

    var articleImage:String=""
    override fun onBindViewHolder(holder1: RecyclerView.ViewHolder, position: Int) {
        val holder=holder1 as ViewHolder
        if(articles[position].media.size>0){
            articleImage =articles[position].media.get(0).mediametadata.get(0).url
            Glide.with(context)
                .load(articleImage)
                .fitCenter()
                .into(holder.articleImage)
        }

        holder.mTitle.text=articles[position].title
        holder.mAuthor.text=articles[position].byline
        holder.mTime.text=articles[position].published_date
     /*   holder.itemView.setOnClickListener {
            if(articles[position].media.size>0){
                articleImage =articles[position].media.get(0).mediametadata.get(0).url
            }else{
                articleImage=""
            }
            val articleDetailFragment= ArticleDetailFragment.newInstance(articleImage,articles[position].title,articles[position].abstract,articles[position].published_date,articles[position].byline)
            (activityContext(context) as MainActivity).replaceFragmentFromMain(articleDetailFragment)
        }*/

        holder.itemView.setOnClickListener {
            val bundle= Bundle()
            if(articles[position].media.size>0){
                bundle.putString("image",articles[position].media.get(0).mediametadata.get(0).url)
            }else{
                bundle.putString("image"," ")
            }
            bundle.putString("title",articles[position].title)
            bundle.putString("abstract",articles[position].abstract)
            bundle.putString("time",articles[position].published_date)
            bundle.putString("authoreName",articles[position].byline)
            articlesListFragment.lifecycleScope.launchWhenResumed {
                NavHostFragment.findNavController(articlesListFragment).navigate(R.id.action_articlesListFragment_to_articleDetailFragment,bundle)
            }
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var articleImage: CircleImageView = itemView.article_image
        internal var mTitle: TextView = itemView.title
        internal var mAuthor: TextView = itemView.author
        internal var mTime: TextView = itemView.time

    }

}