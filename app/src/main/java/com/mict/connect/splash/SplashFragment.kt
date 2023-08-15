package com.mict.connect.splash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mict.connect.R
import com.mict.connect.databinding.FragmentSplashBinding
import com.mict.connect.global_objects.Constants
import com.mict.connect.splash.vm.SplashViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SplashViewModel::class.java]
        val prefs = requireActivity().getSharedPreferences(Constants.LOGIN, Context.MODE_PRIVATE)
        viewModel.loggedIn = if (prefs.contains(Constants.LOGGED_IN))
            prefs.getBoolean(Constants.LOGGED_IN, false) else false
        delayFunction()
    }

    private fun delayFunction() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            parentFragmentManager.beginTransaction().replace(
                R.id.container,
                if (viewModel.loggedIn) StudentsFragment.newInstance() else LoginFragment.newInstance()
            ).commit()
        }
    }
}