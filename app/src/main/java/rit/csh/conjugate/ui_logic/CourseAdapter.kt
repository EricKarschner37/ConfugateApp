package rit.csh.conjugate.ui_logic

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.course_item.view.*
import rit.csh.conjugate.R
import rit.csh.conjugate.data.Course

class CourseAdapter(private var courses: List<Course>, context: Context):
    RecyclerView.Adapter<CourseAdapter.ViewHolder>(){

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(inflater.inflate(R.layout.course_item, parent, false))

    override fun getItemCount(): Int = courses.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleView.text = courses[position].displayName
        holder.locationView.text = courses[position].location
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleView = itemView.course_title_view
        val locationView = itemView.course_location_view
    }

    fun setCourses(courses: List<Course>){
        this.courses = courses
        notifyDataSetChanged()
    }
}