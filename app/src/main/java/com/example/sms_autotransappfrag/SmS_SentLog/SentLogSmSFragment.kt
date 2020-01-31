package com.example.sms_autotransappfrag.SmS_SentLog


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomexample_yena.*
import com.example.sms_autotransappfrag.MainActivity

import com.example.sms_autotransappfrag.R
import kotlinx.android.synthetic.main.fragment_send_sm.*
import kotlinx.android.synthetic.main.fragment_sent_log_sm.*
import kotlinx.android.synthetic.main.sub_contact_register_view.*

class SentLogSmSFragment : Fragment() {
    val TAG = "SentLogSmSFragment"
    private lateinit var contactViewModel : ContactViewModel
    private lateinit var contactLogViewModel : ContactLogViewModel
    private var id: Long? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sent_log_sm, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Set contactItemClick & contactItemLongClick lambda
        val adapter = ContactLogAdapter({ contactLog ->
            id = contactLog.id

        }, { contactLog ->
            deleteLogDialog(contactLog)
        })

        val lm = LinearLayoutManager(context)
        contact_log_list.adapter = adapter
        contact_log_list.layoutManager = lm
        contact_log_list.setHasFixedSize(true)


        // Set contactItemClick & contactItemLongClick lambda
        val adapter2 = ContactAdapter({ contact ->
            id = contact.id

        }, { contact ->
            deleteDialog(contact)

        })
        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        contactViewModel.getAll().observe(requireActivity(), Observer<List<Contact>>{contacts ->
            adapter2.setContacts(contacts!!)
        })


        contactLogViewModel = ViewModelProviders.of(this).get(ContactLogViewModel::class.java)
        contactLogViewModel.getAll().observe((requireActivity()), Observer<List<ContactLog>>{ contactsLog ->
            adapter.setContacts(contactsLog!!)
        })

        val intent = (activity as MainActivity).intent
        contactLog(intent, "onCreateIntent" )

//item delete
        del_item_bt.setOnClickListener({
            val contactlog  = contactLogViewModel.getAll().value.orEmpty().last()
            val contactslog  = contactLogViewModel.getAll().value

            if(contactslog?.lastIndex!! > 0 ) {
                contactLogViewModel.delete(contactlog)

            }

        })
    }
    private fun deleteDialog(contact: Contact) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Delete selected contactLog?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
                contactViewModel.delete(contact)
            }
        builder.show()
    }
    private fun deleteLogDialog(contactLog: ContactLog) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Delete selected contactLog?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
                contactLogViewModel.delete(contactLog)
            }
        builder.show()
    }
    // get SmS function
    private fun contactLog(getintent : Intent, intentType :String) {

        val receivesender = getintent.getStringExtra(MainActivity.EXTRA_BROD_NUMBER)
        val contents = getintent.getStringExtra(MainActivity.EXTRA_BROD_CONTENTS)
        val receivedDate = getintent.getStringExtra(MainActivity.EXTRA_BROD_RECEIVED_DATE)


        Log.d(TAG,"${intentType}"
                + " sender : ${receivesender}"
                + " contents : ${contents}"
                + " receivedDate : ${receivedDate}"

        )
        val contacts  : List<Contact> ?= contactViewModel.getAll().value
        contacts?.forEach { contact -> Unit
            Log.d( TAG, "inside contacts"    )
            val receiveNumber = contact.receiveNumber.replace("-", "")
            Log.d(
                TAG, "inside contacts number ${receiveNumber}"

            )
            if (receiveNumber == receivesender) {
                val receiveName = contact.receiveName
                val receiveTime = receivedDate
                val receiveNumber = contact.receiveNumber
                val message = contents
                val transNumber = contact.transNumber
                val transName = contact.transName
                val transTime = receivedDate
                val contactlog = ContactLog(
                    id,
                    receiveName,
                    receiveTime,
                    receiveNumber,
                    message,
                    transName,
                    transTime,
                    transNumber
                )


                contactLogViewModel.insert(contactlog)

                Log.d(
                    TAG, "[inside ${intentType}: ] receiveName: ${receiveName}" +
                            " receiveTime : ${receiveTime}" +
                            " receiveNumber : ${receiveNumber}" +
                            " message : ${message}" +
                            " transNumber : ${transNumber}" +
                            " transName : ${transName}" +
                            " transTime : ${transTime}"
                )
            } else {
                Log.d(
                    TAG, "[${intentType}: ] receivesender: ${receivesender}" +
                            " contents : ${contents}" +
                            " receiveNumber : ${receiveNumber}" +
                            " receivedDate : ${receivedDate}"
                )
            }

        }

    }


}
