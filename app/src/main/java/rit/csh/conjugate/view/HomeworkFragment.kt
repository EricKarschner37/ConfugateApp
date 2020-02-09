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
import kotlinx.android.synthetic.main.fragment_homework.*
import rit.csh.conjugate.R
import rit.csh.conjugate.data.CourseWithHomework
import rit.csh.conjugate.ui_logic.CourseWithHomeworkAdapter
import rit.csh.conjugate.viewmodel.MainActivityViewModel


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
                requireContext()
            )

        viewModel.coursesWithHomeworks.observe(viewLifecycleOwner, Observer {
            setCourses(it)
        })

        add_homework_fab.setOnClickListener {
            startAddHomework()
        }

        super.onViewCreated(view, savedInstanceState)
    }


    private fun setCourses(courses: List<CourseWithHomework>){
        Log.i(TAG, courses.toString())
        (course_with_homework_rv.adapter as CourseWithHomeworkAdapter).setCourses(courses)
    }

    private fun startAddHomework(){
        viewModel.startAddHomework()
    }
}
