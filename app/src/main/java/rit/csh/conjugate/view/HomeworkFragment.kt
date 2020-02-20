package rit.csh.conjugate.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.add_item_layout.*
import kotlinx.android.synthetic.main.add_item_layout.view.*
import kotlinx.android.synthetic.main.fragment_homework.*
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.okButton
import org.jetbrains.anko.support.v4.alert
import rit.csh.conjugate.R
import rit.csh.conjugate.data.Course
import rit.csh.conjugate.data.CourseWithHomework
import rit.csh.conjugate.data.Homework
import rit.csh.conjugate.ui_logic.CourseWithHomeworkAdapter
import rit.csh.conjugate.viewmodel.MainActivityViewModel
import java.util.*


class HomeworkFragment : Fragment() {

    private val TAG = "HomeworkFragment"
    lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = requireActivity().run{
            ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        }

        return inflater.inflate(R.layout.fragment_homework, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        course_with_homework_rv.layoutManager = LinearLayoutManager(requireContext())
        course_with_homework_rv.adapter =
            CourseWithHomeworkAdapter(
                listOf(),
                requireContext(),
                ::addHomeworkForCourse,
                viewModel::removeHomework)

        viewModel.coursesWithHomeworks.observe(viewLifecycleOwner, Observer {
            setCourses(it)
        })

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setCourses(courses: List<CourseWithHomework>){
        Log.i(TAG, courses.toString())
        (course_with_homework_rv.adapter as CourseWithHomeworkAdapter).setCourses(courses)
    }

    private fun addHomeworkForCourse(course: Course){
        alert("Assignment title/description"){
            val view = layoutInflater.inflate(R.layout.add_item_layout, null)
            view.item_name_et.hint = "Assignment title"
            customView = view
            okButton {
                val dateTimePicker = DateTimePickerDialog(requireContext(), object:DateTimePickerDialog.OnDateAndTimeSetListener{
                    override fun onDateAndTimeSet(calendar: Calendar) {
                        viewModel.addHomework(Homework(0, item_name_et.text.toString(), calendar.time, course.courseId))
                    }
                })
                dateTimePicker.show()
            }
            cancelButton {  }
        }
    }
}
