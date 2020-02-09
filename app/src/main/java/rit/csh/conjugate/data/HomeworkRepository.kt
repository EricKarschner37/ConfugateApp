package rit.csh.conjugate.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class HomeworkRepository(private val homeworkDao: HomeworkDao) {

    val coursesWithHomework: LiveData<List<CourseWithHomework>> = homeworkDao.getCoursesWithHomeworks()

    suspend fun insert(homework: Homework){
        homeworkDao.insert(homework)
    }

    suspend fun insert(course: Course){
        homeworkDao.insert(course)
    }

    suspend fun deleteCourses(){
        homeworkDao.deleteCourses()
    }

    suspend fun deleteHomeworks(){
        homeworkDao.deleteHomeworks()
    }

    suspend fun deleteAll(){
        deleteCourses()
        deleteHomeworks()
    }
}