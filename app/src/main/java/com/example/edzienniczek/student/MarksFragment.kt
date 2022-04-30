package com.example.edzienniczek.student

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.edzienniczek.R
import com.example.edzienniczek.edzienniczekApplication
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MarksFragment: Fragment(R.layout.fragment_marks) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_clear).setOnClickListener {
            clearDataBase()


        }



    }

    fun clearDataBase() {
        val builder = AlertDialog.Builder(activity).apply {
            setTitle("Wyczyść bazę")
            setMessage("Czy chcesz wczyścić całą bazę? Aplikacja zostanie wyłączona.")
            setPositiveButton("Tak") { dialog, id ->
                GlobalScope.launch {

                    context.deleteDatabase("students_database")
                    System.exit(0)
                }
                dialog.cancel()
                activity?.viewModelStore?.clear()
            }
            setNegativeButton("Nie") { dialog, id ->
                dialog.cancel()

            }
        }
        val alert = builder.create()
        alert.show()

    }

}