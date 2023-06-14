package com.example.register.kampus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.register.R


class UniversityAdapter(private val universities: List<University>) :
    RecyclerView.Adapter<UniversityAdapter.ViewHolder>() {

    private var itemClickListener: ((University) -> Unit)? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val universityTextView: TextView = itemView.findViewById(R.id.universityTextView)
        val singkatanTextView: TextView = itemView.findViewById(R.id.abbreviationTextView)
        val alamatTextView: TextView = itemView.findViewById(R.id.addressTextView)

        init {
            itemView.setOnClickListener {
                itemClickListener?.invoke(universities[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_university, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val university = universities[position]
        holder.universityTextView.text = university.university
        holder.singkatanTextView.text = university.singkatan
        holder.alamatTextView.text = university.alamat
    }

    override fun getItemCount(): Int {
        return universities.size
    }

    fun setOnItemClickListener(listener: (University) -> Unit) {
        itemClickListener = listener
    }
}

