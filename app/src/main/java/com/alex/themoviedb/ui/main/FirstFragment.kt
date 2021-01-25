package com.alex.themoviedb.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.alex.themoviedb.DataBinderMapperImpl
import com.alex.themoviedb.R
import com.alex.themoviedb.databinding.FragmentFirstBinding


private const val TAG = "FirstFragment"

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var movieAdapter: MovieAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        movieAdapter = MovieAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = movieAdapter
        }

        observeData()
    }


    private fun observeData() {
        mainViewModel.getMovies().observe(viewLifecycleOwner, Observer { movies ->
            Log.d(TAG, "observeData: $movies")
            if (movies.isNotEmpty()){
                movieAdapter.addItems(movies)
            }
        })
    }


}