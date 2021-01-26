package com.alex.themoviedb.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alex.themoviedb.R
import com.alex.themoviedb.databinding.FragmentHomeBinding
import com.alex.themoviedb.model.Movie
import com.alex.themoviedb.utils.Constants
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var popularMovieAdapter: HomeMovieAdapter
    private lateinit var upcomingMovieAdapter: HomeMovieAdapter
    private lateinit var topRatedMovieAdapter: HomeMovieAdapter
    private lateinit var sliderMovieAdapter: SliderAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        popularMovieAdapter = HomeMovieAdapter(this::onMovieClick)
        upcomingMovieAdapter = HomeMovieAdapter(this::onMovieClick)
        topRatedMovieAdapter = HomeMovieAdapter(this::onMovieClick)
        sliderMovieAdapter = SliderAdapter(this::onMovieClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        observeData()


    }

    private fun initView() {
        binding.recyclerViewPopular.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = popularMovieAdapter
        }
        binding.recyclerViewUpcoming.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = upcomingMovieAdapter
        }
        binding.recyclerViewTopRated.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = topRatedMovieAdapter
        }

        binding.imageSlider.apply {
//            setIndicatorAnimation(IndicatorAnimationType.WORM);
            setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
            setSliderAdapter(sliderMovieAdapter)
        }

        binding.popularSeeAll.setOnClickListener {
            showAllMovies(Constants.TERM_POPULAR)
        }

        binding.upcomingSeeAll.setOnClickListener {
            showAllMovies(Constants.TERM_UPCOMING)
        }
        binding.topRatedSeeAll.setOnClickListener {
            showAllMovies(Constants.TERM_TOP_RATED)
        }


    }

    private fun showAllMovies(term: String) {
        val bundle = bundleOf(Constants.KEY_TERM to term)
        findNavController().navigate(R.id.action_HomeFragment_to_PopularFragment, bundle)
    }

    private fun observeData() {
        viewModel.pagedListPopularMovies.pagedList.observe(viewLifecycleOwner, {
            if (it?.size!! > 5) {
                val list: List<Movie> = it.subList(0, 5)
                popularMovieAdapter.setItems(list)
            }
        })
        viewModel.pagedListUpcomingMovies.pagedList.observe(viewLifecycleOwner, {
            if (it?.size!! > 5) {
                val list: List<Movie> = it.subList(0, 5)
                upcomingMovieAdapter.setItems(list)
            }
        })
        viewModel.pagedListTopRatedMovies.pagedList.observe(viewLifecycleOwner, {
            if (it?.size!! > 5) {
                val list: List<Movie> = it.subList(0, 5)
                topRatedMovieAdapter.setItems(list)
            }
        })

        viewModel.sliderMovies.observe(viewLifecycleOwner,{
            if (it?.size!!>0){
                sliderMovieAdapter.newItems(it)
            }
        })

    }

    private fun onMovieClick(movie: Movie) {
        val bundle = bundleOf(Constants.KEY_MOVIE to movie)
        findNavController().navigate(R.id.action_HomeFragment_to_DetailsFragment, bundle)
    }

    override fun onResume() {
        super.onResume()

        binding.imageSlider.startAutoCycle();
    }

    override fun onPause() {
        super.onPause()
        binding.imageSlider.stopAutoCycle()
    }

}