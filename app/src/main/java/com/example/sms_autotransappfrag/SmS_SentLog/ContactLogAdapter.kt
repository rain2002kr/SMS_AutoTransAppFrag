package com.example.roomexample_yena

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sms_autotransappfrag.R

class ContactLogAdapter(val contactItemClick: (ContactLog) -> Unit, val contactItemLongClick: (ContactLog) -> Unit)
    : RecyclerView.Adapter<ContactLogAdapter.ViewHolder>() {

    private var contactsLog: List<ContactLog> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sub_contact_log_view, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return contactsLog.size
    }

    override fun onBindViewHolder(viewHolder:ViewHolder , position: Int) {
        viewHolder.bind(contactsLog[position])
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val txtlogReceName = itemView.findViewById<TextView>(R.id.txtlogReceName)
        private val txtlogReceTime = itemView.findViewById<TextView>(R.id.txtlogReceTime)
        private val txtlogReceNumber = itemView.findViewById<TextView>(R.id.txtlogReceNumber)
        private val txtlogMessage = itemView.findViewById<TextView>(R.id.txtlogMessage)
        private val txtlogTransName = itemView.findViewById<TextView>(R.id.txtlogTransName)
        private val txtlogTransTime = itemView.findViewById<TextView>(R.id.txtlogTransTime)
        private val txtlogTransNumber = itemView.findViewById<TextView>(R.id.txtlogTransNumber)

        fun bind(contactLog: ContactLog) {

            txtlogReceName.text = contactLog.receiveName
            txtlogReceTime.text = contactLog.receiveTime
            txtlogReceNumber.text = contactLog.receiveNumber
            txtlogMessage.text = contactLog.message
            txtlogTransName.text = contactLog.transName
            txtlogTransTime.text = contactLog.transTime
            txtlogTransNumber.text = contactLog.transNumber

            itemView.setOnClickListener {
                contactItemClick(contactLog)
            }

            itemView.setOnLongClickListener {
                contactItemLongClick(contactLog)
                true
            }
        }
    }

    fun setContacts(contactsLog: List<ContactLog>) {
        this.contactsLog = contactsLog
        notifyDataSetChanged()
    }


}