package com.crypto.com.myapplication.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "currencies",
)

@Parcelize
data class Currency(
    @PrimaryKey(autoGenerate = false) val id : String = "",
    val name : String = "",
    val symbol : String = "") : Parcelable
