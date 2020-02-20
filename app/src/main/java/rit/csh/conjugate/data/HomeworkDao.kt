package rit.csh.conjugate.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HomeworkDao {

    @Transaction
    @Query("SELECT * from Course")
    fun getCoursesWithHomeworks(): LiveData<List<CourseWithHomework>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(homework: Homework)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(course: Course)

    @Delete
    suspend fun delete(homework: Homework)

    @Delete
    suspend fun delete(course: Course)

    @Query("DELETE FROM Course")
    suspend fun deleteCourses()

    @Query("DELETE FROM Homework")
    suspend fun deleteHomeworks()
}