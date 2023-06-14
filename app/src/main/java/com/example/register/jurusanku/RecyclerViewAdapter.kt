package com.example.register.jurusanku

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.register.R
import com.example.register.SpaceItemDecoration

class RecyclerViewAdapter(private val dataItems: List<String>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list, parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataItems[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataItems.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val prodiTextView: TextView = itemView.findViewById(R.id.prodiTextView)
        private val universityTextView: TextView = itemView.findViewById(R.id.universityTextView)
        private val kecocokanTextView: TextView = itemView.findViewById(R.id.kecocokanTextView)

        fun bind(item: String) {
            val data = item.split(", ")

            val prodi = data[0].substringAfter("Prodi: ")
            val university = "Pilihan Universitas " + data[1].substringAfter("University: ")
            val matchPercentage = "Kecocokan " + data[2].substringAfter("Match: ")


            prodiTextView.text = prodi
            universityTextView.text = university
            kecocokanTextView.text = matchPercentage
        }
    }
}
