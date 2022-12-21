package com.example.codefliestask.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codefliestask.MainActivity
import com.example.codefliestask.R
import com.example.codefliestask.adapter.TherapyAdapter
import com.example.codefliestask.databinding.FragmentHomeBinding
import com.example.codefliestask.model.Data
import com.example.codefliestask.utils.Resource
import com.example.codefliestask.viewModel.MainViewModel


class HomeFragment : Fragment() {
    lateinit var viewmodel: MainViewModel
    private lateinit var therapyAdapter: TherapyAdapter
    private lateinit var binding : FragmentHomeBinding
    private lateinit var list: List<Data>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        list = listOf<Data>()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = (activity as MainActivity).viewmodel

        viewmodel.getTherapies.observe(viewLifecycleOwner, Observer {response ->
            when(response){
                is Resource.Success ->{
                    response.data?.let { it ->

                        list = it.data
                        setUpRecyclerView()


                    }
                }
                is Resource.Error ->{
                    response.message.let { message ->
                        Toast.makeText(requireContext(),"Error occured $message", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading ->{
                }
            }
        })


        setUpRecyclerView()

    }

    private fun setUpRecyclerView(){
        therapyAdapter = TherapyAdapter(list)
        binding.rvTherapy.apply {
            adapter = therapyAdapter
            layoutManager = GridLayoutManager(activity,3)
        }
    }
}