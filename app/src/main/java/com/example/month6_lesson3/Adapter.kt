package com.example.month6_lesson3


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.month6_lesson3.databinding.RvItemBinding
class Adapter(
    private val array: ArrayList<String>,
    private val clickItem: (String) -> Unit
): RecyclerView.Adapter<Adapter.ViewHolder>() {
    inner class ViewHolder(private var binding: RvItemBinding): RecyclerView.ViewHolder(binding.root){
        fun init(text: String){
            binding.rvItem.text = text
            itemView.setOnClickListener {
                clickItem(text)}
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.init(array[position])
    }
    override fun getItemCount(): Int = array.size
}