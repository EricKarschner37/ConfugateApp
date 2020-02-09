package rit.csh.conjugate.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.EventLog
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import rit.csh.conjugate.R
import rit.csh.conjugate.ui_logic.EventController
import rit.csh.conjugate.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    lateinit var homeworkFragment: HomeworkFragment
    lateinit var courseFragment: CourseFragment
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        homeworkFragment = HomeworkFragment()
        courseFragment = CourseFragment()

        EventController.current.observe(this, Observer {
            it?.let{
                handleEvent(it)
            }
        })

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.nav_close, R.string.nav_open)
        toggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun handleEvent(e: EventController.Event){

        when (e){
            EventController.Event.START_ADD_HOMEWORK -> showAddHomework()
            EventController.Event.VIEW_COURSES -> showCourses()
            EventController.Event.VIEW_HOMEWORK -> showHomework()
        }
        EventController.finish()
    }

    private fun showHomework(){
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                homeworkFragment
            )
            .addToBackStack(null)
            .commit()
    }

    private fun showCourses(){
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                courseFragment
            )
            .addToBackStack(null)
            .commit()
    }

    private fun showAddHomework(){
        supportFragmentManager.beginTransaction()

            .replace(
                R.id.fragment_container,
                AddHomeworkFragment()
            )
            .addToBackStack(null)
            .commit()
    }
}
