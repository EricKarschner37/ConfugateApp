package rit.csh.conjugate.ui_logic

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.course_with_homework_item.view.*
import rit.csh.conjugate.R
import rit.csh.conjugate.data.CourseWithHomework

class CourseWithHomeworkAdapter(private var coursesWithHomework: List<CourseWithHomework>, val context: Context):
    RecyclerView.Adapter<CourseWithHomeworkAdapter.ViewHolder>(){

    private val inflater = LayoutInflater.from(context)
    private val layoutManager = LinearLayoutManager(context)
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.course_with_homework_item, parent, false))
    }

    override fun getItemCount() = coursesWithHomework.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleView.text = coursesWithHomework[position].course.displayName
        val adapter = HomeworkAdapter(
            coursesWithHomework[position].homeworks,
            context
        )
        holder.homeworkRV.setRecycledViewPool(viewPool)
        holder.homeworkRV.layoutManager = layoutManager
        holder.homeworkRV.adapter = adapter
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleView = itemView.course_title_view
        val homeworkRV = itemView.homework_rv
    }

    fun setCourses(courses: List<CourseWithHomework>){
        coursesWithHomework = courses
        notifyDataSetChanged()
    }
}