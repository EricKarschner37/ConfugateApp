package rit.csh.conjugate.ui_logic

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.homework_item.view.*
import rit.csh.conjugate.R
import rit.csh.conjugate.data.Homework

class HomeworkAdapter(private val homeworks: List<Homework>, context: Context): RecyclerView.Adapter<HomeworkAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.homework_item, parent, false))
    }

    override fun getItemCount() = homeworks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleView.text = homeworks[position].name
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleView = itemView.homework_title_view
    }
}