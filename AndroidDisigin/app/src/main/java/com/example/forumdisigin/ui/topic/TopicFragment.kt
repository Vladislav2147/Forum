package com.example.forumdisigin.ui.topic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.forumdisigin.R

class TopicFragment : Fragment() {

    private lateinit var topicViewModel: TopicViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        topicViewModel =
                ViewModelProviders.of(this).get(TopicViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_topic, container, false)
        val textView: TextView = root.findViewById(R.id.text_topic)
        topicViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
