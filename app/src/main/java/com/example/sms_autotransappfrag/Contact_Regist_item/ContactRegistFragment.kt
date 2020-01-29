package com.example.sms_autotransappfrag.Contact_Regist_item

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample_yena.Contact
import com.example.roomexample_yena.ContactAdapter
import com.example.roomexample_yena.ContactViewModel
import com.example.sms_autotransappfrag.MainActivity
import com.example.sms_autotransappfrag.MainActivity.Companion.MAIN_FRAG
import com.example.sms_autotransappfrag.MainViewAdapter
import com.example.sms_autotransappfrag.MainViewModel
import com.example.sms_autotransappfrag.R
import kotlinx.android.synthetic.main.fragment_contact_regist.*
import kotlinx.android.synthetic.main.sub_contact_register_view.*


class ContactRegistFragment : Fragment() {
    val TAG = "ContactRegistFragment"
    private lateinit var contactViewModel : ContactViewModel
    private var id: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_contact_regist, container, false)
        inflater.inflate(R.layout.sub_contact_register_view,contact_register,true)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        home.setOnClickListener({
            (activity as MainActivity).changeFragment(MAIN_FRAG)
        })



        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.sub_contact_register_view,contact_register,true)

        //item insert
        btinsert.setOnClickListener({
            val receNumber = txtSetReceNumber.text.toString()
            val receName = txtSetReceName.text.toString()
            val tranNumber = txtSetTransNumber.text.toString()
            val tranName = txtSetTransName.text.toString()

            val initial = receName[0].toUpperCase()
            val contact = Contact(id,R.drawable.receive_sms!!,receNumber,receName,
                R.drawable.exchange!!,R.drawable.send_sms!!,tranNumber,tranName,initial)

            contactViewModel.insert(contact)

            val contacts  = contactViewModel.getAll().value
            Log.d("sss","${contact.id.toString()} contacts size ${contacts?.size.toString()}")

        })

        //item delete
        btdelete.setOnClickListener({
            val contact  = contactViewModel.getAll().value.orEmpty().last()
            val contacts  = contactViewModel.getAll().value

            Log.d("sss","${contact.id.toString()} contacts size ${contacts?.size.toString()}")
            Log.d("sss","${contact.id.toString()} contact index ${contacts?.lastIndex}")

            if(contacts?.lastIndex!! > 0 ) {
                contactViewModel.delete(contact)
                Log.d("sss","${contact.id.toString()} contacts After size ${contacts?.size.toString()}")
            }

        })
// Set contactItemClick & contactItemLongClick lambda
        val adapter = ContactAdapter({ contact ->
            id = contact.id
        }, { contact ->
            deleteDialog(contact)
        })

        val lm = LinearLayoutManager(context)
        contact_list.adapter = adapter
        contact_list.layoutManager = lm
        contact_list.setHasFixedSize(true)

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        contactViewModel.getAll().observe(requireActivity(), Observer<List<Contact>>{contacts ->
            adapter.setContacts(contacts!!)
        })



    }
    private fun deleteDialog(contact: Contact) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Delete selected contact?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
                contactViewModel.delete(contact)
            }
        builder.show()
    }


}
