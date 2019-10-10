package com.example.kotlintest

import android.service.autofill.TextValueSanitizer
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UsersAdapter(private val users: List<User>) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.ViewHolder {
        val inflatedView = parent.inflate(R.layout.row_user_list, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UsersAdapter.ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val tvUsername: TextView = v.findViewById(R.id.tvUsername)

        fun bind(user: User) {
            tvUsername.text = user.name
        }
    }

}