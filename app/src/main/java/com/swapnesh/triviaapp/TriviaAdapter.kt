package com.swapnesh.triviaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.booklayout.view.*

class TriviaAdapter(
    private val articleList: List<Questiondetails>,
    private val listener: (Questiondetails) -> Unit
): RecyclerView.Adapter<TriviaAdapter.ArticleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticleHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.booklayout, parent, false))

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) = holder.bind(articleList[position], listener)

    override fun getItemCount() = articleList.size

    class ArticleHolder(articleView: View): RecyclerView.ViewHolder(articleView) {

        fun bind(article: Questiondetails, listener: (Questiondetails) -> Unit) = with(itemView) {
            datetime.text = "GAME : " + article.date+" "+article.time
            namepersoon.text = "NAME : " +article.name
            answerone.text = article.answerone
            answertwo.text = article.answertwo

            setOnClickListener { listener(article) }
        }
    }
}