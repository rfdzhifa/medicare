package com.example.medicare

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.view.*

class AdapterRVHome(private val menu : MutableList<DataRVHome>) :RecyclerView.Adapter<AdapterRVHome.ViewHolder>(){
    class ViewHolder (view: View): RecyclerView.ViewHolder (view) {

        val Image = view.findViewById<ImageView>(R.id.IV_image)
        val Title = view.findViewById<TextView>(R.id.IV_title)

        fun bindView(menu: DataRVHome, listener: Int) {
            Image.setImageResource(menu.icon)
            Title.text = menu.title
        }

    }

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(menu[position], position)
    }

    override fun getItemCount(): Int = menu.size
}