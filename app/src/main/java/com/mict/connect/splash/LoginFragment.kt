package com.mict.connect.splash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.mict.connect.R
import com.mict.connect.databinding.FragmentLoginBinding
import com.mict.connect.global_objects.Constants.LOGGED_IN
import com.mict.connect.global_objects.Constants.LOGIN


class LoginFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            idEdt.apply {
                setOnFocusChangeListener { _, _ -> resetErrors() }
                doOnTextChanged { _, _, _, _ -> enableLoginBtn() }
            }
            passwordEdt.apply {
                setOnFocusChangeListener { _, _ -> resetErrors() }
                doOnTextChanged { _, _, _, _ -> enableLoginBtn() }
            }
            loginBtn.setOnClickListener {
                if (!idEdt.text.isNullOrEmpty() && !passwordEdt.text.isNullOrEmpty()) {
                    when {
                        !idEdt.text.toString().trim()
                            .equals(getString(R.string.id), false) -> {
                            idEdtLayout.error = getString(R.string.incorrect_id)
                        }
                        !passwordEdt.text.toString()
                            .trim().equals(getString(R.string.pass), false) -> {
                            passwordEdtLayout.error = getString(R.string.incorrect_password)
                        }
                        else -> {
                            requireActivity().getSharedPreferences(LOGIN, Context.MODE_PRIVATE)
                                .edit().putBoolean(LOGGED_IN, true).apply()
                            parentFragmentManager.commit {
                                replace(R.id.container, StudentsFragment.newInstance())
                            }
                        }
                    }
                }
            }
        }
    }

    private fun resetErrors() {
        binding.apply {
            idEdtLayout.error = null
            passwordEdtLayout.error = null
        }
    }

    private fun enableLoginBtn() {
        binding.apply {
            loginBtn.isEnabled = !idEdt.text.isNullOrEmpty() && !passwordEdt.text.isNullOrEmpty()
        }
    }
}
