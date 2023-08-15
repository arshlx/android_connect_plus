package com.mict.connect.main

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.mict.connect.R
import com.mict.connect.databinding.ActivityAssignmentDetailBinding
import com.mict.connect.global_objects.Constants
import com.mict.connect.model.Assignment
import com.google.gson.Gson

class AssignmentDetailActivity : AppCompatActivity() {

    private var _binding: ActivityAssignmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var assn: Assignment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAssignmentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.vec_arrow_back)
            setDisplayHomeAsUpEnabled(true)
        }
        assn = Gson().fromJson(intent.getStringExtra(Constants.ASSIGNMENT), Assignment::class.java)
        binding.apply {
            supportActionBar?.title = assn.subject
            assnNameTxt.text = assn.assnName
            dueDateTxt.text = assn.dueDate
            descriptionTxt.text = assn.desc
            groupLayout.isVisible = assn.isGroup
            sendEmail.setOnClickListener {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:") // only email apps should handle this
                    putExtra(Intent.EXTRA_EMAIL, "arshdeep100@gmail.com")
                }
                try {
                    startActivity(intent)
                } catch (ex: ActivityNotFoundException) {
                    Toast.makeText(
                        this@AssignmentDetailActivity,
                        R.string.application_not_found,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}