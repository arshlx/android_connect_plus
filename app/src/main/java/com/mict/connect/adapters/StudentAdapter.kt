package com.mict.connect.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mict.connect.R
import com.mict.connect.databinding.ItemStudentBinding
import com.mict.connect.global_objects.Constants
import com.mict.connect.main.MainActivity
import com.mict.connect.model.ListOfStudents
import com.mict.connect.model.Student
import com.google.gson.Gson

class StudentAdapter(private val fragment: Fragment, private val students: ListOfStudents) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    inner class StudentViewHolder(private val binding: ItemStudentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            binding.apply {
                nameTxt.text = student.name
                schoolTxt.text = student.school
                studentImg.setImageResource(
                    when (adapterPosition) {
                        0 -> R.drawable.stu_3
                        1 -> R.drawable.stu_1
                        else -> R.drawable.stu_2
                    }
                )
                container.setOnClickListener {
                    fragment.apply {
                        requireActivity().finish()
                        startActivity(
                            Intent(requireActivity(), MainActivity::class.java).putExtra(
                                Constants.STUDENT, Gson().toJson(student)
                            ).putExtra(
                                Constants.STU_LIST, Gson().toJson(students)
                            )
                        )
                    }
                }
//                Glide.with(fragment.requireContext()).load(student.url).into(studentImg)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(
            ItemStudentBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students.stuList[position])
    }

    override fun getItemCount() = students.stuList.size
}