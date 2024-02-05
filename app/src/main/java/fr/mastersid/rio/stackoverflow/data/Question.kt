package fr.mastersid.rio.stackoverflow.data;

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question (val id: Int, val title : String, val answerCount : Int) : Parcelable
