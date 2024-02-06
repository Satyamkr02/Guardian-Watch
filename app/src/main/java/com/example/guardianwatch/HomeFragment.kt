package com.example.guardianwatch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listMembers = listOf<MemberModel>(

            MemberModel("Satyam"),
            MemberModel("Nisha"),
            MemberModel("Ramesh Singhania"),
            MemberModel("Esha Yadav"),
            MemberModel("Ashish"),
            MemberModel("Amit Ray"),
            MemberModel("Saurabh Chauhan"), 
            MemberModel("Shivam NCC DTU"),
            MemberModel("Swarit Singh"),
            MemberModel("Shivam"),
            MemberModel("Shubham Sourav"),
            MemberModel("Vivek Singh"),

        )

        val adapter = MemberAdapter(listMembers)

        val recycler = requireView().findViewById<RecyclerView>(R.id.recycler_member)
        recycler.layoutManager = LinearLayoutManager( requireContext())
        recycler.adapter = adapter

    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}