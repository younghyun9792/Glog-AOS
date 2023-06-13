package com.example.glog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class BoardAdapter(private val context: Context) : RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    var datas = mutableListOf<BoardData>()
    lateinit var db: Glogdb
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        //private val imgProfile: ImageView = itemView.findViewById(R.id.img_rv_photo)
        private val bid: TextView = itemView.findViewById(R.id.bid)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val content: TextView = itemView.findViewById(R.id.content)
        private val hart: TextView = itemView.findViewById(R.id.hart_text)
        private val hit: TextView = itemView.findViewById(R.id.hit_text)

        fun bind(item: BoardData) {
            bid.text = item.bid.toString()
            title.text = item.title.toString()
            content.text = item.content.toString()
            hart.text = item.hart.toString()
            hit.text = item.hit.toString()

        }
    }

    override fun getItemCount() = datas.size


}