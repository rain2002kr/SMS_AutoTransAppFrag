package com.example.sms_autotransappfrag.BroadCast_SMS

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import android.widget.Toast
import com.example.sms_autotransappfrag.MainActivity
import java.util.*

class Receiver_SmS : BroadcastReceiver() {
    private val SimpleDateFormat= "yyyy-MM-dd HH:mm"
   // private lateinit var contactViewModel : ContactViewModel
    private val TAG = "Receiver_SmS"

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context,"broadcast 문자받음 start", Toast.LENGTH_LONG).show()
        val bundle = intent.extras
        val messages = parseSmsMessage(bundle!!)

        if (messages!!.size > 0) {
            val sender : String? = messages[0]?.originatingAddress
            val contents : String? = messages[0]?.messageBody.toString()
            val receivedDate = Date(messages[0]!!.timestampMillis)
            Log.d(this.javaClass.name, "sender: $sender")
            Log.d(this.javaClass.name, "contents: $contents")
            Log.d(this.javaClass.name, "received date: $receivedDate")
            Toast.makeText(context,"broad send Sms $sender : $contents",Toast.LENGTH_LONG).show()
            sendToActivity(context,sender.toString(),contents.toString(),receivedDate.toString())
        }
    }

    private fun sendToActivity(context: Context,sender: String,contents: String,receivedDate: String) {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra(MainActivity.EXTRA_BROD_NUMBER, sender)
        intent.putExtra(MainActivity.EXTRA_BROD_CONTENTS, contents)
        intent.putExtra(MainActivity.EXTRA_BROD_RECEIVED_DATE, receivedDate)
        context.startActivity(intent)
    }

    private fun parseSmsMessage(bundle: Bundle): Array<SmsMessage?>? {
        val objs = bundle["pdus"] as Array<Any>?
        val messages =
            arrayOfNulls<SmsMessage>(objs!!.size)
        for (i in objs.indices) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val format = bundle.getString("format")
                messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray, format)
            } else {
                messages[i] =
                    SmsMessage.createFromPdu(objs[i] as ByteArray)
            }
        }
        return messages
    }


}