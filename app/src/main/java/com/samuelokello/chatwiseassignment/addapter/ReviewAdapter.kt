package com.samuelokello.chatwiseassignment.addapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.samuelokello.chatwiseassignment.R
import com.samuelokello.chatwiseassignment.Review

class ReviewAdapter(private val reviews: List<Review>) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ratingBar: RatingBar = view.findViewById(R.id.reviewRatingBar)
        val comment: TextView = view.findViewById(R.id.reviewComment)
        val reviewer: TextView = view.findViewById(R.id.reviewerName)
        val date: TextView = view.findViewById(R.id.reviewDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = reviews[position]
        holder.ratingBar.rating = review.rating.toFloat()
        holder.comment.text = review.comment
        holder.reviewer.text = review.reviewerName
        holder.date.text = review.date
    }

    override fun getItemCount() = reviews.size
}