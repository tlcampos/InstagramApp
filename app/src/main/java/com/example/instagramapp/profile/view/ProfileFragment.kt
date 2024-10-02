package com.example.instagramapp.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramapp.R
import com.example.instagramapp.common.base.BaseFragment
import com.example.instagramapp.databinding.FragmentProfileBinding

class ProfileFragment
    : BaseFragment<FragmentProfileBinding,Profile.Presenter>(
    R.layout.fragment_profile
    , FragmentProfileBinding::bind) {

    override lateinit var presenter: Profile.Presenter

    override fun setUpViews() {
        val rv = binding?.profileRv
        rv?.layoutManager = GridLayoutManager(requireContext(), 3)
        rv?.adapter = PostAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onDestroy() {
        binding = null
        //presenter.onDestroy()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
            return PostViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_profile_grid, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return 30
        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            holder.bind(R.drawable.ic_insta_add)
        }

        private class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(image: Int) {
               itemView.findViewById<ImageView>(R.id.item_profile_img_grid).setImageResource(image)
            }
        }
    }
}
