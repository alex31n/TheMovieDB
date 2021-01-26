package com.alex.themoviedb.ui.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alex.themoviedb.R
import com.alex.themoviedb.databinding.FragmentDetailsBinding
import com.alex.themoviedb.model.Movie
import com.alex.themoviedb.utils.Constants

private const val TAG = "DetailsFragment"

class DetailsFragment : Fragment() {

    private var movie: Movie? = null

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var detailsViewModel: DetailsViewModel
    private val castAdapter = CastAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movie = arguments?.getParcelable(Constants.KEY_MOVIE)
        detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

    }
    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (movie != null) {
            binding.movie = movie

        }

        movie?.movieId?.let {
            detailsViewModel.getCredits(it)
        }
        initView()
        observeData()
    }

    private fun initView(){
        binding.recyclerViewCast.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
//            layoutManager = GridLayoutManager(requireContext(), 3)
            setHasFixedSize(true)
            adapter= castAdapter
        }
    }

    private fun observeData() {
        detailsViewModel.castResult.observe(viewLifecycleOwner, {
//            Log.d(TAG, "observeData: $it")
            if (it?.cast?.isNotEmpty() == true){
                castAdapter.addItems(it.cast)
            }
        })
    }
}