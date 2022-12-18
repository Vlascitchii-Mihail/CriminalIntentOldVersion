package com.vm.timemanager.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.vm.timemanager.databinding.FragmentSevenDaysBinding
import com.vm.timemanager.viewModel.DaysViewModel

/**
 * Handles the view of the Seven Days.
 */
class SevenDaysFragment : Fragment() {

//    private var _binding: FragmentSevenDaysBinding? = null
//    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_seven_days, container, false)
         val binding = FragmentSevenDaysBinding.inflate(inflater, container, false)
        val fragment = this

        binding.apply {
            monday.setOnClickListener {

                val action = SevenDaysFragmentDirections.actionSevenDaysFragmentToDaysFragment(monday.text.toString())
                fragment.findNavController().navigate(action)
            }

            tuesday.setOnClickListener {
                val action = SevenDaysFragmentDirections.actionSevenDaysFragmentToDaysFragment(tuesday.text.toString())
                fragment.findNavController().navigate(action)
            }

            wednesday.setOnClickListener {
                val action = SevenDaysFragmentDirections.actionSevenDaysFragmentToDaysFragment(wednesday.text.toString())
                fragment.findNavController().navigate(action)
            }

            thursday.setOnClickListener {
                val action = SevenDaysFragmentDirections.actionSevenDaysFragmentToDaysFragment(thursday.text.toString())
                fragment.findNavController().navigate(action)
            }

            friday.setOnClickListener {
                val action = SevenDaysFragmentDirections.actionSevenDaysFragmentToDaysFragment(friday.text.toString())
                fragment.findNavController().navigate(action)
            }

            saturday.setOnClickListener {
                val action = SevenDaysFragmentDirections.actionSevenDaysFragmentToDaysFragment(saturday.text.toString())
                fragment.findNavController().navigate(action)
            }

            sunday.setOnClickListener {
                val action = SevenDaysFragmentDirections.actionSevenDaysFragmentToDaysFragment(sunday.text.toString())
                fragment.findNavController().navigate(action)
            }
        }



        return binding.root
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        _binding = null
//    }
}