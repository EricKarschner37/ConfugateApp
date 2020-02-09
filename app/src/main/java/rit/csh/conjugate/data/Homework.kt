package rit.csh.conjugate.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.*

@Entity
class Homework (
    @PrimaryKey(autoGenerate = true) val homeworkId: Long,
    val name: String,
    @TypeConverters(Converters::class) val dueDate: Date,
    val courseKey: Long
) {
    class Converters{

        @TypeConverter
        fun dateToLong(date: Date?): Long = date?.run{ time } ?: 0

        @TypeConverter
        fun longToDate(long: Long?): Date = long?.run{ Date(this) }?: Date()

    }
}