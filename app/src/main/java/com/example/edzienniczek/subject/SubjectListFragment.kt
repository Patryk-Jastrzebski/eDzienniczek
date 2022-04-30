package com.example.edzienniczek.subject

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
import com.example.edzienniczek.enitities.Subject

class SubjectListFragment: Fragment() {

    lateinit var viewModel: SubjectListViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subject, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val factory = SubjectListViewModelFactory((requireNotNull(this.activity).application))
        viewModel =
            ViewModelProvider(requireActivity(), factory).get(SubjectListViewModel::class.java)

        val subjectsListAdapter = SubjectsListAdapter(viewModel.subjects, viewModel)

        viewModel.subjects.observe(viewLifecycleOwner,Observer<List<Subject>>{subjectsListAdapter.notifyDataSetChanged()})
        val layoutManager = LinearLayoutManager(view.context)

        view.findViewById<RecyclerView>(R.id.subjects_recyclerView).let {
            it.adapter = subjectsListAdapter
            it.layoutManager = layoutManager


        }




        view.findViewById<Button>(R.id.button_add_subject).apply {
            setOnClickListener {


                view.findNavController().navigate(R.id.action_subjectFragment_to_addSubjectFragment)

                //val subject = Subject(test,"Matematyka2")
                //viewModel.addSubject(subject)

            }

        }

    }
}