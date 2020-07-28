package com.example.forumdisigin.ui.tags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.forumdisigin.R

class TagFragment : Fragment() {

    private lateinit var tagViewModel: TagViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        tagViewModel =
                ViewModelProviders.of(this).get(TagViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tag, container, false)
        val textView: TextView = root.findViewById(R.id.text_tag)
        tagViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
