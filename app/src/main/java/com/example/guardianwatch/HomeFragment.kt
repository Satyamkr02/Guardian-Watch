package com.example.guardianwatch

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
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

        fetchContacts()

        val inviteAdapter = InviteAdapter(fetchContacts())

        val inviteRecycler = requireView().findViewById<RecyclerView>(R.id.recycler_invite_card)
        inviteRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        inviteRecycler.adapter = inviteAdapter

    }

    @SuppressLint("Range")
    private fun fetchContacts(): ArrayList<ContactModel> {

        Log.d("FetchContact89", "fetchContacts: start")
        val cr = requireActivity().contentResolver
        val cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)

        val listContacts: ArrayList<ContactModel> = ArrayList()


        if (cursor != null && cursor.count > 0) {

            while (cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val hasPhoneNumber =
                    cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))

                if (hasPhoneNumber > 0) {

                    val pCur = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        arrayOf(id),
                        ""
                    )

                    if (pCur != null && pCur.count > 0) {

                        while (pCur.moveToNext()) {

                            val phoneNum =
                                pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                            listContacts.add(ContactModel(name, phoneNum))

                        }
                        pCur.close()

                    }

                }
            }

            cursor.close()

        }
        Log.d("FetchContact89", "fetchContacts: end")
        return listContacts

    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}