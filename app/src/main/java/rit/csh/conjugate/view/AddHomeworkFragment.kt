package rit.csh.conjugate.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_add_homework.*

import rit.csh.conjugate.R
import rit.csh.conjugate.data.Homework
import rit.csh.conjugate.view.DateTimePickerDialog.OnDateAndTimeSetListener
import rit.csh.conjugate.viewmodel.MainActivityViewModel
import java.util.*


class AddHomeworkFragment : Fragment() {

    lateinit var viewModel: MainActivityViewModel
    lateinit var dateTimePicker: DateTimePickerDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = requireActivity().run{
            ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        }
        return inflater.inflate(R.layout.fragment_add_homework, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dateTimePicker = DateTimePickerDialog(requireContext(), object: OnDateAndTimeSetListener{
            override fun onDateAndTimeSet(calendar: Calendar) {
                val homework = Homework(0, homework_name_et.text.toString(), calendar.time, 0)
                viewModel.addHomework(homework)
                viewModel.startViewHomework()
            }
        })

        submit_btn.setOnClickListener { dateTimePicker.show() }

        super.onViewCreated(view, savedInstanceState)
    }
}
