package com.alex.themoviedb.ui.main.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.alex.themoviedb.R
import com.alex.themoviedb.databinding.FragmentPopularBinding
import com.alex.themoviedb.model.Movie
import com.alex.themoviedb.utils.Constants


private const val TAG = "PopularFragment"

class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding
    private lateinit var viewModel: PopularViewModel
    private lateinit var movieAdapter :MovieAdapter

    private var TERM :String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            TERM = arguments?.getString(Constants.KEY_TERM, Constants.TERM_POPULAR)

        viewModel = ViewModelProvider(this).get(PopularViewModel::class.java)
        movieAdapter = MovieAdapter(this::movieItemClick)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = movieAdapter
        }

        observeData()

        TERM?.let {
            viewModel.searchMovies(TERM.toString())
        }


    }


    private fun observeData() {
        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
//            Log.d(TAG, "observeData: ${movies?.size}")
            movieAdapter.submitList(movies)
        })
    }


    private fun movieItemClick(movie: Movie){
//        Log.d(TAG, "movieItemClick: $movie")
//        val bundle = bundleOf("id" to movie.id)
        val bundle = bundleOf(Constants.KEY_MOVIE to movie)
        findNavController().navigate(R.id.action_PopularFragment_to_DetailsFragment,bundle)
    }


}