package com.example.kotlin_shopping_list_cooroutine.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.kotlin_shopping_list_cooroutine.data.entity.VegetablesEntity

@Dao
interface VegetablesDao {

    @Query("SELECT * FROM VegetablesEntity WHERE Months LIKE :month")
    suspend fun getVegetables(month: String): List<VegetablesEntity>
}