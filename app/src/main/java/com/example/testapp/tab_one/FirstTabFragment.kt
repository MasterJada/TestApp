package com.example.testapp.tab_one


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import com.example.testapp.R
import com.example.testapp.databinding.FragmentBlankBinding

class FirstTabFragment : Fragment(), LifecycleOwner {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBlankBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false)
        val viewModel = ViewModelProviders.of(activity!!)[FirstTabViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    companion object {
        var instance  = FirstTabFragment()
    }
}
