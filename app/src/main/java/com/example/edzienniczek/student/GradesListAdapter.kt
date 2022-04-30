package com.example.edzienniczek.student

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.edzienniczek.R
import com.example.edzienniczek.enitities.Grade
import com.example.edzienniczek.subject.SubjectDetailsFragmentDirections

class GradesListAdapter(private val studentGrades: LiveData<List<Grade>>,
private val viewModel: StudentAddGradeViewModel) :RecyclerView.Adapter<GradesListAdapter.GradeListHolder>() {

    inner class GradeListHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val gradeText = view.findViewById<TextView>(R.id.grade_text)


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_grade_row, parent, false)

        return GradeListHolder(view)
    }

    override fun onBindViewHolder(holder: GradeListHolder, position: Int) {

        holder.gradeText.text = studentGrades.value?.get(position)?.grade.toString()




    }
    override fun getItemCount() = studentGrades.value?.size?:0
}