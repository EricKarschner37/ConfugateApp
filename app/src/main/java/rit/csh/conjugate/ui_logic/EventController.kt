package rit.csh.conjugate.ui_logic

import android.database.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object EventController: Observable<Boolean>() {
    var current: MutableLiveData<Event?> = MutableLiveData(null)

    fun setEvent(event: Event){
        if (current.value == null){
            current.value = event
        }
    }

    fun finish(){
        current.value = null
    }

    enum class Event{
        START_ADD_HOMEWORK,
        VIEW_HOMEWORK,
        VIEW_COURSES
    }
}