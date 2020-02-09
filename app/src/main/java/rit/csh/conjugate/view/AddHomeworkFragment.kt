package rit.csh.conjugate.view


import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_add_homework.*

import rit.csh.conjugate.R
import rit.csh.conjugate.data.Homework
import rit.csh.conjugate.viewmodel.MainActivityViewModel
import java.time.Year
import java.util.*


class AddHomeworkFragment : Fragment() {

    lateinit var viewModel: MainActivityViewModel
    lateinit var datePicker: DatePickerDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = requireActivity().run{
            ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        }
        return inflater.inflate(R.layout.fragment_add_homework, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val now = Calendar.getInstance()

        datePicker = DatePickerDialog(requireContext(),
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val c = Calendar.getInstance()
                c.set(year, month - 1, dayOfMonth, 0, 0)
                val homework = Homework(0,
                    homework_name_et.text.toString(),
                    c.time,
                    0)
                viewModel.addHomework(homework)
                viewModel.startViewHomework()
            }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))

        datePicker.setTitle("Choose due date")

        submit_btn.setOnClickListener { datePicker.show() }

        super.onViewCreated(view, savedInstanceState)
    }
}
