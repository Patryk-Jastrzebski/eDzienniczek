package com.example.edzienniczek.subject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edzienniczek.R
import com.example.edzienniczek.enitities.Student
import com.example.edzienniczek.adapters.StudentsListDetailedAdapter


class SubjectDetailsFragment: Fragment() {

    val args: SubjectDetailsFragmentArgs by navArgs()

    private lateinit var viewModel: SubjectDetailsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val factory=SubjectDetailsViewModelFactory((requireNotNull(this.activity).application), args.subjectName)

        viewModel = ViewModelProvider(this,factory).get(SubjectDetailsViewModel::class.java)




        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val studentsListDetailedAdapter = StudentsListDetailedAdapter(viewModel.students, viewModel)


        viewModel.students.observe(viewLifecycleOwner,
            Observer<List<Student>> { studentsListDetailedAdapter.notifyDataSetChanged() }
        )

        val layouManager = LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.students_detailedList_recyclerView).let {
            it.adapter = studentsListDetailedAdapter
            it.layoutManager = layouManager
        }



        val detailNameOfSubject = args.subjectName
        view.findViewById<TextView>(R.id.details_Subject_Name).text = detailNameOfSubject



    }
}