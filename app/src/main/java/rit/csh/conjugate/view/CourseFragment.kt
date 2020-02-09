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
import kotlinx.android.synthetic.main.add_course_layout.view.*
import kotlinx.android.synthetic.main.fragment_course.*
import org.jetbrains.anko.okButton
import org.jetbrains.anko.support.v4.alert

import rit.csh.conjugate.R
import rit.csh.conjugate.data.Course
import rit.csh.conjugate.ui_logic.CourseAdapter
import rit.csh.conjugate.viewmodel.MainActivityViewModel


class CourseFragment : Fragment() {

    private val TAG = "CourseFragment"
    lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        viewModel = requireActivity().run{
            ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        }

        return inflater.inflate(R.layout.fragment_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG, "view created")

        course_rv.layoutManager = LinearLayoutManager(requireContext())
        course_rv.adapter =
            CourseAdapter(listOf(), requireContext())
        viewModel.coursesWithHomeworks.observe(viewLifecycleOwner, Observer { coursesWithHomeworks ->
            setCourses(coursesWithHomeworks.map { it.course })
        })

        add_course_fab.setOnClickListener {
            addCourseAlert()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setCourses(courses: List<Course>){
        Log.i(TAG, courses.toString())
        (course_rv.adapter as CourseAdapter).setCourses(courses)
    }

    private fun addCourseAlert(){
        alert("Add course"){
            val view = layoutInflater.inflate(R.layout.add_course_layout, null)
            customView = view
            okButton {
                val course = Course(0, listOf(), "location", view.course_name_et.text.toString())
                addCourse(course)
            }
        }.show()
    }

    private fun addCourse(course: Course){
        viewModel.addCourse(course)
    }
}
