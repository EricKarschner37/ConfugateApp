package rit.csh.conjugate.data

import androidx.room.Embedded
import androidx.room.Relation

data class CourseWithHomework(
    @Embedded val course: Course,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "courseKey"
    )
    val homeworks: List<Homework>
)