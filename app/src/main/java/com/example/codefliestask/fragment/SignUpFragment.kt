package com.example.codefliestask.fragment

import android.app.Activity
import android.content.Intent
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
import com.example.codefliestask.databinding.FragmentSignUpBinding
import com.example.codefliestask.utils.Resource
import com.example.codefliestask.viewModel.MainViewModel


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: MainViewModel
    private var mFirstName : String = ""
    private var mLastName : String = ""
    private var mUserGmail : String = ""
    private var mUserNumber : String = ""
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
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewmodel


        binding.registerbtnL.setOnClickListener {
            checkUserDetails()
        }
    }

    private fun checkUserDetails() {
        mFirstName = binding.fnameE.text.toString()
        mLastName = binding.lastNameE.text.toString()
        mUserGmail = binding.emailE.text.toString()
        mUserNumber = binding.mobileE.text.toString()
        mUserPassword = binding.mobileE.text.toString()

        if (mFirstName.isEmpty()){
            Toast.makeText(requireContext() , "Please Enter First Name" , Toast.LENGTH_SHORT).show()
            return
        }
        if (mLastName.isEmpty()){
            Toast.makeText(requireContext() , "Please Enter Last Name" , Toast.LENGTH_SHORT).show()
            return
        }
        if (!isValidEmail(mUserGmail)){
            Toast.makeText(requireContext() , "Please Enter Valid Gmail" , Toast.LENGTH_SHORT).show()
            return
        }

        if (mUserNumber.isEmpty()){
            Toast.makeText(requireContext() , "Please Enter Phone Number" , Toast.LENGTH_SHORT).show()
            return
        }
        if (mUserPassword.isEmpty()){
            Toast.makeText(requireContext() , "Please Enter Password" , Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.pushPost(mFirstName,mLastName,mUserGmail,mUserPassword,mUserNumber.toLong())
        observeUserRegistration()

    }

    private fun observeUserRegistration() {
        viewModel.myPushUser.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    if (it.data?.message == "You'r register successfully!") {
                        Toast.makeText(
                            requireContext(),
                            "Registation Success !! Please Login",
                            Toast.LENGTH_SHORT
                        ).show()


                        findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)

                    } else {
                        Toast.makeText(requireContext(), "" + it.data?.message, Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), "" + it.data!!.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


}