package fr.mastersid.rio.stackoverflow.data;

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName="question_table")
data class Question (@PrimaryKey val id: Int, val title : String, val answerCount : Int) : Parcelable
