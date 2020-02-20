package rit.csh.conjugate.ui_logic

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.course_with_homework_item.view.*
import rit.csh.conjugate.R
import rit.csh.conjugate.data.Course
import rit.csh.conjugate.data.CourseWithHomework
import rit.csh.conjugate.data.Homework

class CourseWithHomeworkAdapter(private var coursesWithHomework: List<CourseWithHomework>, private val context: Context, private val addHomeworkForCourse: (Course) -> Unit, private val deleteHomework: (Homework) -> Unit):
    RecyclerView.Adapter<CourseWithHomeworkAdapter.ViewHolder>(){

    private val inflater = LayoutInflater.from(context)
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.course_with_homework_item, parent, false))
    }

    override fun getItemCount() = coursesWithHomework.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleView.text = coursesWithHomework[position].course.displayName
        val adapter = HomeworkAdapter(
            coursesWithHomework[position].homeworks,
            context,
            deleteHomework
        )
        holder.homeworkRV.setRecycledViewPool(viewPool)
        holder.homeworkRV.layoutManager = LinearLayoutManager(context)
        holder.homeworkRV.adapter = adapter
        holder.addHomeworkButton.setOnClickListener {
            addHomeworkForCourse(coursesWithHomework[position].course)
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleView: TextView = itemView.course_title_view
        val homeworkRV: RecyclerView = itemView.homework_rv
        val addHomeworkButton: ImageButton = itemView.add_homework_btn
    }

    fun setCourses(courses: List<CourseWithHomework>){
        coursesWithHomework = courses
        notifyDataSetChanged()
    }
}