package com.mict.connect.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mict.connect.R
import com.mict.connect.adapters.AssignmentAdapter
import com.mict.connect.databinding.ActivitySubjectAssignmentBinding
import com.mict.connect.global_objects.Constants
import com.mict.connect.global_objects.Constants.SEL_SUBJECT
import com.mict.connect.model.Assignment
import com.mict.connect.model.ListOfAssignments
import com.google.gson.Gson

class SubjectAssignmentActivity : AppCompatActivity() {

    private var _binding: ActivitySubjectAssignmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var subName: String
    private lateinit var assignmentList: List<Assignment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySubjectAssignmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.vec_arrow_back)
            setDisplayHomeAsUpEnabled(true)
        }
        subName = intent.getStringExtra(SEL_SUBJECT)!!
        assignmentList = Gson().fromJson(
            intent.getStringExtra(Constants.ASSIGNMENT_LIST),
            ListOfAssignments::class.java
        ).assnList
        setUpViews()
    }

    private fun setUpViews() {
        binding.apply {
            supportActionBar?.title = subName
            labelTxt.text = getString(R.string.assn_due_str, subName)
            assnRecycler.adapter =
                AssignmentAdapter(this@SubjectAssignmentActivity, assignmentList.toMutableList())
        }
    }
}