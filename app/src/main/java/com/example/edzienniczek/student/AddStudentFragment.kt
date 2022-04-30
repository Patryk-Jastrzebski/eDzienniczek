package com.example.edzienniczek.student

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
import com.example.edzienniczek.enitities.Student
import com.example.edzienniczek.student.viewmodel.StudentListViewModel

class AddStudentFragment: Fragment() {

    private lateinit var viewModel: StudentListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(StudentListViewModel::class.java)

        return inflater.inflate(R.layout.add_student, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val studentNameText = view.findViewById<EditText>(R.id.student_name_text)
        val subjectNameText = view.findViewById<EditText>(R.id.student_subject_text)
        val studentAlbumNumber = view.findViewById<EditText>(R.id.student_album_number)

        //ADD NEW

        view.findViewById<Button>(R.id.add_student).apply {
            setOnClickListener{
                val student = Student(0,studentNameText.text.toString(),subjectNameText.text.toString(),studentAlbumNumber.text.toString() )
                viewModel.addStudent(student)
                view.findNavController().navigate(R.id.action_addStudentFragment_to_studentFragment)
            }

        }
    }

}