package com.nav.myapplication


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.notes_fragment.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NotesFragment : Fragment() {
    companion object {
        fun newInstance(): NotesFragment {
            val args = Bundle()
            val fragment = NotesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.notes_fragment, null)
        val date = getDate()
        view.date.text = "Edited $date";
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDate(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return current.format(formatter)
    }
}