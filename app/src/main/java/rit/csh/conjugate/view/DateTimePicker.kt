package rit.csh.conjugate.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import java.util.*

class DateTimePickerDialog(context: Context, onDateAndTimeSetListener: OnDateAndTimeSetListener) {
    private val cal = Calendar.getInstance()
    private val now = Calendar.getInstance()
    private val datePicker = DatePickerDialog(context, DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.MONTH, month - 1)
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        timePicker.show()
    }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
    private val timePicker = TimePickerDialog(context, TimePickerDialog.OnTimeSetListener{ view, hour, minute ->
        cal.set(Calendar.HOUR, hour)
        cal.set(Calendar.MINUTE, minute)
        onDateAndTimeSetListener.onDateAndTimeSet(cal)
    }, now.get(Calendar.HOUR), now.get(Calendar.MINUTE), false)

    fun show(){
        datePicker.show()
    }

    interface OnDateAndTimeSetListener {
        fun onDateAndTimeSet(calendar: Calendar)
    }
}