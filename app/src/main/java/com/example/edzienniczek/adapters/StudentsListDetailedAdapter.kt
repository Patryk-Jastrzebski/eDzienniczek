package com.example.edzienniczek.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.edzienniczek.R
import com.example.edzienniczek.enitities.Student
import com.example.edzienniczek.subject.SubjectDetailsFragmentArgs
import com.example.edzienniczek.subject.SubjectDetailsFragmentDirections
import com.example.edzienniczek.subject.SubjectDetailsViewModel
import com.example.edzienniczek.subject.SubjectDetailsViewModelFactory
import android.content.Intent.getIntent

import android.content.Intent




class StudentsListDetailedAdapter(private val students: LiveData<List<Student>>, private val viewModel
: SubjectDetailsViewModel
): RecyclerView.Adapter<StudentsListDetailedAdapter.StudentListDetailHolder>()
{



    inner class StudentListDetailHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val textStudentNameDetail = view.findViewById<TextView>(R.id.student_nameInDetail)
        val buttonDelete = view.findViewById<Button>(R.id.button_delete_studentInDetail)
        val buttonAddGrade = view.findViewById<TextView>(R.id.button_add_gradeInDetail)
        val studentAlbumText = view.findViewById<TextView>(R.id.subject_nameInDetail)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentListDetailHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_student_row_detail, parent, false)


        return StudentListDetailHolder(view)
    }

    override fun onBindViewHolder(holder: StudentListDetailHolder, position: Int) {

        holder.textStudentNameDetail.text = students.value?.get(position)?.studentName
        holder.studentAlbumText.text = students.value?.get(position)?.studentAlbum
        var subjectNameDetail = students.value?.get(position)?.subjectName


        holder.buttonDelete.setOnClickListener {
            students.value?.let { existingStudents ->
                viewModel.deleteStudent(existingStudents.get(position))
            }
        }
        holder.buttonAddGrade.setOnClickListener {
            val action = SubjectDetailsFragmentDirections.actionSubjectDetailsFragmentToStudentAddGradeFragment(
                holder.textStudentNameDetail.text.toString(),
                subjectNameDetail.toString()
                )
            Navigation.findNavController(it).navigate(action)
        }


    }
    override fun getItemCount() = students.value?.size?:0
}