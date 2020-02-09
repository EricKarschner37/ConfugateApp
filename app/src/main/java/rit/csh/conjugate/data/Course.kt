package rit.csh.conjugate.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.sql.Time

@Entity
data class Course(
    @PrimaryKey val courseId: Long,
    @TypeConverters(Converters::class) val times: List<ClassTime>,
    val location: String,
    val displayName: String
){
    class Converters {

        private val gson = Gson()

        @TypeConverter
        fun classTimesToString(times: List<ClassTime>?): String{
            return gson.toJson(times)
        }

        @TypeConverter
        fun stringToClassTimes(data: String?): List<ClassTime>{
            val listType = object: TypeToken<List<ClassTime>>() {}.type

            return gson.fromJson(data, listType)
        }
    }

    data class ClassTime(
        val day: DayOfWeek,
        val startTime: Time,
        val endTime: Time
    )

    enum class DayOfWeek{
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY
    }
}