package com.mict.connect.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.mict.connect.databinding.ItemAssignmentBinding
import com.mict.connect.global_objects.Constants
import com.mict.connect.main.AssignmentDetailActivity
import com.mict.connect.model.Assignment

class AssignmentAdapter(
    private val context: Context,
    private val assignmentList: MutableList<Assignment>
) :
    RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder>() {

    inner class AssignmentViewHolder(private val binding: ItemAssignmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(assn: Assignment) {
            binding.apply {
                subjectTxt.text = assn.subject
                assnNameTxt.text = assn.assnName
                dueDateTxt.text = assn.dueDate
                descriptionTxt.text = assn.desc
                groupLayout.isVisible = assn.isGroup
                root.setOnClickListener {
                    context.startActivity(
                        Intent(
                            Intent(
                                context,
                                AssignmentDetailActivity::class.java
                            ).putExtra(
                                Constants.ASSIGNMENT, Gson().toJson(assn)
                            )
                        )
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentViewHolder {
        return AssignmentViewHolder(
            ItemAssignmentBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AssignmentViewHolder, position: Int) {
        holder.bind(assignmentList[position])
    }

    override fun getItemCount() = assignmentList.size
}