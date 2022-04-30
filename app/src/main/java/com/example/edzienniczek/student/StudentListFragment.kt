package com.example.edzienniczek.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edzienniczek.R
import com.example.edzienniczek.enitities.Student
import com.example.edzienniczek.adapters.StudentsListAdapter
import com.example.edzienniczek.student.viewmodel.StudentListViewModel

class StudentListFragment: Fragment() {

    private lateinit var viewModel: StudentListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(StudentListViewModel::class.java)

        return inflater.inflate(R.layout.fragment_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)


        val studentsListAdapter = StudentsListAdapter(viewModel.students, viewModel)

        viewModel.students.observe(viewLifecycleOwner,
            Observer<List<Student>> { studentsListAdapter.notifyDataSetChanged() }
        )
        val layoutManager = LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.students_recyclerView).let {
            it.adapter = studentsListAdapter
            it.layoutManager = layoutManager


        }

        view.findViewById<Button>(R.id.button_add_student).apply {
            setOnClickListener {
                view.findNavController().navigate(R.id.action_studentFragment_to_addStudentFragment)
            }
        }

    }

}