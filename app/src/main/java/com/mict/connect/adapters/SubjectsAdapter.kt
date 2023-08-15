package com.mict.connect.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mict.connect.databinding.ItemSubjectBinding
import com.mict.connect.global_objects.Constants.ASSIGNMENT_LIST
import com.mict.connect.global_objects.Constants.SEL_SUBJECT
import com.mict.connect.main.SubjectAssignmentActivity
import com.mict.connect.model.Assignment
import com.mict.connect.model.ListOfAssignments
import com.mict.connect.model.Subject
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class SubjectsAdapter(
    private val fragment: Fragment,
    private val subjectList: List<Subject>
) :
    RecyclerView.Adapter<SubjectsAdapter.SubjectsViewHolder>() {
    private val dateFormat = SimpleDateFormat("mm-dd-yyyy", Locale("us"))
    private val requiredFormat = SimpleDateFormat("dd-mm", Locale("us"))

    inner class SubjectsViewHolder(private val binding: ItemSubjectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(subject: Subject) {
            binding.apply {
                subjectTxt.text = subject.subjectName
                numAssnTxt.text = subject.assignments.size.toString()
                dueAssnRecycler.adapter = SubjectAssnAdapter(setUpAssnList(subject.assignments))
                container.setOnClickListener {
                    val list = Gson().toJson(
                        ListOfAssignments(subject.assignments))
                    fragment.startActivity(
                        Intent(fragment.requireContext(), SubjectAssignmentActivity::class.java)
                            .putExtra(SEL_SUBJECT, subject.subjectName)
                            .putExtra(ASSIGNMENT_LIST, list)
                    )
                }
            }
        }
    }

    private fun setUpAssnList(assignments: List<Assignment>): List<String> {
        val assnList = mutableListOf<String>()
        assignments.forEach {
            assnList.add(requiredFormat.format(dateFormat.parse(it.dueDate)!!) + " " + it.assnName)
        }
        return assnList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectsViewHolder {
        return SubjectsViewHolder(
            ItemSubjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SubjectsViewHolder, position: Int) {
        holder.bind(subjectList[position])
    }

    override fun getItemCount() = subjectList.size
}