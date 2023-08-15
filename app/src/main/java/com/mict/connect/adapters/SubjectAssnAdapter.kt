package com.mict.connect.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mict.connect.databinding.ItemDueAssignmentBinding

class SubjectAssnAdapter(private val assignmentList: List<String>) : RecyclerView.Adapter<SubjectAssnAdapter.SubjectAssnViewHolder>() {
    inner class SubjectAssnViewHolder(private val binding: ItemDueAssignmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(assignment: String) {
            binding.assignmentTxt.text = assignment
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectAssnViewHolder {
        return SubjectAssnViewHolder(
            ItemDueAssignmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SubjectAssnViewHolder, position: Int) {
        holder.bind(assignmentList[position])
    }

    override fun getItemCount() = assignmentList.size
}