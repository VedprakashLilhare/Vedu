package com.ved.Vedu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ved.mysafety.databinding.MainItemBinding

class NodeAdapter (private val notes: List<Nodeitems>):
    RecyclerView.Adapter<NodeAdapter.NodeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodeViewHolder {
        val binding:MainItemBinding=MainItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NodeViewHolder(binding)

    }

    override fun getItemCount(): Int {

        return notes.size
    }

    override fun onBindViewHolder(holder: NodeViewHolder, position: Int) {
        val note:Nodeitems = notes[position]
        holder.bind(note)

    }
    class NodeViewHolder(private val binding: MainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Nodeitems) {
            binding.country.text = note.country
            binding.Helpline.text=note.phone
            binding.email.text=note.email
            binding.add.text=note.address
        }

    }

}