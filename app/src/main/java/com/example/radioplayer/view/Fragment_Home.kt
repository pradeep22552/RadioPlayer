package com.example.radioplayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import androidx.lifecycle.Observer
import com.bumptech.glide.Glide

import com.example.radioplayer.R
import com.example.radioplayer.utils.Resultres

import com.example.radioplayer.viewmodel.PlayingViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_Home.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class Fragment_Home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

   lateinit var txt_song:TextView
   lateinit var img_song:ImageView
   private val viewModel by viewModels<PlayingViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment__home, container, false)
        txt_song= root.findViewById(R.id.tv_songName)
        img_song=root.findViewById(R.id.img_song)

        subscribeUi()
        return root
    }




    private fun subscribeUi() {



        viewModel.songs

            .observe(requireActivity(), Observer { result ->

            when (result.status) {
                Resultres.Status.SUCCESS -> {
                    result.data?.let { list ->
                       txt_song.setText(list.get(0).name )
                        Glide.with(requireView()).load(list.get(0).image_url ).into(img_song)

                    }

                }
                   // loading.visibility = View.GONE


                Resultres.Status.ERROR -> {
                    result.message?.let {
                        showError(it)
                    }

                }

                Resultres.Status.LOADING -> {
                   // loading.visibility = View.VISIBLE
                }
            }

        })
    }

    private fun showError(msg: String) {
        view?.let {
            Snackbar.make(it, msg, Snackbar.LENGTH_INDEFINITE).setAction("DISMISS") {
            }.show()
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment_Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}