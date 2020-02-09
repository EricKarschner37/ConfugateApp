package rit.csh.conjugate.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import rit.csh.conjugate.data.*
import rit.csh.conjugate.ui_logic.EventController

class MainActivityViewModel(application: Application): AndroidViewModel(application) {
    private val homeworkRepository: HomeworkRepository
    val coursesWithHomeworks: LiveData<List<CourseWithHomework>>

    init {
        val homeworkDao = HomeworkDatabase.getDatabase(application).homeworkDao()
        homeworkRepository = HomeworkRepository(homeworkDao)
        coursesWithHomeworks = homeworkRepository.coursesWithHomework

        startViewHomework()
    }

    fun addCourse(course: Course){
        viewModelScope.launch {
            homeworkRepository.insert(course)
        }
    }

    fun addHomework(homework: Homework){
        viewModelScope.launch{
            homeworkRepository.insert(homework)
        }
    }

    fun startAddHomework(){
        EventController.setEvent(EventController.Event.START_ADD_HOMEWORK)
    }

    fun startViewHomework(){
        EventController.setEvent(EventController.Event.VIEW_HOMEWORK)
    }

    fun startViewCourses(){
        EventController.setEvent(EventController.Event.VIEW_COURSES)
    }
}