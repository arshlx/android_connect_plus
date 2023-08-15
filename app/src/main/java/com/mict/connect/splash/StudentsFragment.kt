package com.mict.connect.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mict.connect.R
import com.mict.connect.adapters.StudentAdapter
import com.mict.connect.databinding.FragmentStudentsBinding
import com.mict.connect.global_objects.TaskStatus
import com.mict.connect.splash.vm.SplashViewModel

class StudentsFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance() = StudentsFragment()
    }

    private var _binding: FragmentStudentsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SplashViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SplashViewModel::class.java]
        requireActivity().setActionBar(binding.toolbar)
        requireActivity().title = getString(R.string.linked_students)
        viewModel.apply {
            studentStatus.observe(viewLifecycleOwner, studentObserver)
//            getStudentList()
            initStudentList(requireContext())
        }
    }

    private val studentObserver = Observer<String> {
        when (it) {
            TaskStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            TaskStatus.SUCCESS -> {
                binding.apply {
                    progressBar.visibility = View.GONE
                    studentRecycler.apply {
                        scheduleLayoutAnimation()
                        adapter =
                            StudentAdapter(this@StudentsFragment, viewModel.students)
                    }
                }
            }
        }
    }
}