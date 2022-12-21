package com.example.codefliestask.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.codefliestask.MainActivity
import com.example.codefliestask.R
import com.example.codefliestask.databinding.FragmentLoginBinding
import com.example.codefliestask.databinding.FragmentSignUpBinding
import com.example.codefliestask.utils.Resource
import com.example.codefliestask.viewModel.MainViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: MainViewModel

    private var mUserGmail : String = ""
    private var mUserPassword : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewmodel


        binding.LoginbtnL.setOnClickListener {
            checkUserDetails()
        }

        binding.registerbtnscreen.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }
    private fun checkUserDetails() {

        mUserGmail = binding.emailE.text.toString()
        mUserPassword = binding.passwordE.text.toString()


        if (!isValidEmail(mUserGmail)){
            Toast.makeText(requireContext() , "Please Enter Valid Gmail" , Toast.LENGTH_SHORT).show()
            return
        }

        if (mUserPassword.isEmpty()){
            Toast.makeText(requireContext() , "Please Enter Password" , Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.pushPostLogin(mUserGmail,mUserPassword)
        observeUserLogin()

    }
    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun observeUserLogin() {
        viewModel.myPushUserLogin.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    if (it.data?.message == "You'r logged in successfully!") {
                        Toast.makeText(
                            requireContext(),
                            "Login Success !!",
                            Toast.LENGTH_SHORT
                        ).show()

                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

                    } else {
                        Toast.makeText(requireContext(), "" + it.data?.message, Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Error -> {
//                    Toast.makeText(requireContext(), "" + it.data!!.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}