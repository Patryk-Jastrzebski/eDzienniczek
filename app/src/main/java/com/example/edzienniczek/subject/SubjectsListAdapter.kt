package com.example.edzienniczek.subject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.edzienniczek.R
import com.example.edzienniczek.enitities.Subject

class SubjectsListAdapter(private val subjects: LiveData<List<Subject>>,private val viewModel
    : SubjectListViewModel
): RecyclerView.Adapter<SubjectsListAdapter.SubjectListHolder>()
{

    inner class SubjectListHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val textSubjectName = view.findViewById<TextView>(R.id.subject_name)
        val buttonDelete = view.findViewById<Button>(R.id.button_delete_subject)
        val startTime = view.findViewById<TextView>(R.id.subject_start_time)
        val endTime = view.findViewById<TextView>(R.id.subject_end_time)
        val clickSubject = view.findViewById<TextView>(R.id.subject_name)

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_subject_row, parent, false)

        return SubjectListHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectListHolder, position: Int) {
        holder.textSubjectName.text = subjects.value?.get(position)?.SubjectName
        holder.startTime.text = subjects.value?.get(position)?.startTime
        holder.endTime.text = subjects.value?.get(position)?.endTime


        holder.buttonDelete.setOnClickListener {
            subjects.value?.let { existingSubjects ->
                viewModel.deleteSubject(existingSubjects.get(position))

            }
        }
        holder.clickSubject.setOnClickListener {
            val action = SubjectListFragmentDirections.actionSubjectFragmentToSubjectDetailsFragment(holder.textSubjectName.text.toString())
            Navigation.findNavController(it).navigate(action)

        }
    }
    override fun getItemCount() = subjects.value?.size?:0
}