package com.example.kotlin_shopping_list_cooroutine.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class VegetablesEntity (
    @PrimaryKey val Name: String,
    @ColumnInfo val Name_Ita: String,
    @ColumnInfo val Months: String
)