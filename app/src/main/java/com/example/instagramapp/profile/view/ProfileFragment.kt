package com.example.instagramapp.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramapp.R
import com.example.instagramapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var profileBinding: FragmentProfileBinding? = null
    private val binding get() = profileBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = binding.profileRv
        rv.layoutManager = GridLayoutManager(requireContext(), 3)
        rv.adapter = PostAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
