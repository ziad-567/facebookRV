package com.example.contacts

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact (val name:String,val phone:String,val description:String):Parcelable