package com.example.edzienniczek.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.edzienniczek.R
import com.example.edzienniczek.enitities.Student
import com.example.edzienniczek.student.viewmodel.StudentListViewModel

class StudentsListAdapter(private val students: LiveData<List<Student>>, private val viewModel
: StudentListViewModel
): RecyclerView.Adapter<StudentsListAdapter.StudentListHolder>()
{

    inner class StudentListHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val textStudentName = view.findViewById<TextView>(R.id.student_name)
        val buttonDelete = view.findViewById<Button>(R.id.button_delete_student)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_student_row, parent, false)

        return StudentListHolder(view)
    }

    override fun onBindViewHolder(holder: StudentListHolder, position: Int) {
        holder.textStudentName.text = students.value?.get(position)?.studentName

        holder.buttonDelete.setOnClickListener {
            students.value?.let { existingStudents ->
                viewModel.deleteStudent(existingStudents.get(position))

            }
        }

    }

    override fun getItemCount() = students.value?.size?:0

}