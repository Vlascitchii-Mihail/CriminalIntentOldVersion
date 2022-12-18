package com.bignerdranch.android.criminalintent

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID
import java.util.Date

//the class contains al the information about crime
//Room creates database for any class with annotation @Entity
//@Entity класс определяет структкру таблицы
//@PrimaryKey - primary column
/**
 * @param UUID - generates unique ID using the function randomUUID()
 */
//Date() - the current date
@Entity
data class Crime(@PrimaryKey val id: UUID = UUID.randomUUID(), var title: String = "",
                 var date: Date = Date(), var isSolved: Boolean = false, var suspect: String = "", var suspectPhoneNumber: String = "") {
    //photo name
    val photoFileName get() = "IMG_$id.jpg"

}