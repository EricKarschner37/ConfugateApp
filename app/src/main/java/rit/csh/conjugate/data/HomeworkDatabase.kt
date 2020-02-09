package rit.csh.conjugate.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Homework::class, Course::class], version = 1, exportSchema = false)
@TypeConverters(Course.Converters::class, Homework.Converters::class)
abstract class HomeworkDatabase: RoomDatabase() {

    abstract fun homeworkDao(): HomeworkDao

    companion object {

        @Volatile
        private var INSTANCE: HomeworkDatabase? = null

        fun getDatabase(context: Context): HomeworkDatabase {
            INSTANCE?.let{
                return it
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HomeworkDatabase::class.java,
                    "homework_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}