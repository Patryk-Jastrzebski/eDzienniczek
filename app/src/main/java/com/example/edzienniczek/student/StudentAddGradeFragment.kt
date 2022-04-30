package com.example.edzienniczek.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edzienniczek.R
import com.example.edzienniczek.enitities.Grade
import com.example.edzienniczek.enitities.Student
import com.example.edzienniczek.subject.SubjectDetailsFragmentArgs
import com.example.edzienniczek.subject.SubjectDetailsViewModelFactory
import org.w3c.dom.Text

class StudentAddGradeFragment : Fragment() {
    private lateinit var viewModel: StudentAddGradeViewModel

    private val grades = listOf(2, 3, 3.5, 4, 4.5, 5)
    val args: StudentAddGradeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factory=
            StudentAddGradeViewModelFactory((requireNotNull(this.activity).application), args.studentNameInDetail, args.subjectSelectedInDetail)

        viewModel = ViewModelProvider(this, factory).get(StudentAddGradeViewModel::class.java)

        return inflater.inflate(R.layout.add_grade, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        //ADAPTER TO SHOW GRADES

        val gradeListAdapter = GradesListAdapter(viewModel.studentGrades, viewModel)
        viewModel.studentGrades.observe(viewLifecycleOwner,
            Observer<List<Grade>> { gradeListAdapter.notifyDataSetChanged() }
        )

        val layouManager = LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.grades_recyclerView).let {
            it.adapter = gradeListAdapter
            it.layoutManager = layouManager
        }

        //ADAPTER TO ADD GRADE

        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,grades)
        view.findViewById<AutoCompleteTextView>(R.id.autoComplete_grade).setAdapter(arrayAdapter)
        val grade = view.findViewById<TextView>(R.id.autoComplete_grade)

        //VARIABLES AND VALUES

        val detailNameOfStudent = args.studentNameInDetail
        view.findViewById<TextView>(R.id.student_name_details).text = detailNameOfStudent

        val detailSubjectName = args.subjectSelectedInDetail
        view.findViewById<TextView>(R.id.student_selected_subject).text = detailSubjectName

        //ADD GRADE

        //view.findViewById<EditText>(R.id.student_avarage).text = a
        view.findViewById<Button>(R.id.add_grade_to_student).setOnClickListener {
            viewModel.addGrade(detailSubjectName, detailNameOfStudent, grade.text.toString().toFloat())

        }

    }

}