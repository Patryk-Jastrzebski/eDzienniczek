package com.example.edzienniczek.subject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.edzienniczek.R
import com.example.edzienniczek.enitities.Subject

class AddSubjectFragment: Fragment() {

    private lateinit var viewModel: SubjectListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(SubjectListViewModel::class.java)
        return inflater.inflate(R.layout.add_subject, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val subjectNameText = view.findViewById<EditText>(R.id.subject_name_text)
        val subjectStartTime = view.findViewById<EditText>(R.id.subject_start_time)
        val subjectEndTime = view.findViewById<EditText>(R.id.subject_end_time)
        //ADD NEW

        view.findViewById<Button>(R.id.add_subject).apply {
            setOnClickListener{

                println(subjectNameText.text)

                val subject = Subject(0,subjectNameText.text.toString(), subjectStartTime.text.toString(), subjectEndTime.text.toString())
                viewModel.addSubject(subject)
                view.findNavController().navigate(R.id.action_addSubjectFragment_to_subjectFragment)

            }

        }



    }

}