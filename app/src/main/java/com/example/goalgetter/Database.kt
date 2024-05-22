package com.example.goalgetter

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import java.sql.Date

//Database class using Goal entities
@Database(entities = [Goal::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun GoalDao(): GoalDao
}

//Dao interface for interacting with database
@Dao
interface GoalDao {
    //Define SQL functions to use by DAO object
    @Query("SELECT * FROM Goal ORDER BY title")
    suspend fun getAll(): MutableList<Goal>
    @Query("SELECT * FROM Goal WHERE id = :id")
    suspend fun getGoalByID(id : Int): Goal?
    @Insert
    suspend fun insertAll(vararg goals: Goal)
    @Update(entity = Goal::class)
    suspend fun updateGoal(goal : Goal)
    @Delete
    suspend fun delete(goal: Goal)
}

//Table definition for Goal Entities
@Entity
data class Goal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "dueDate") var dueDate: Date,
    @ColumnInfo(name = "dateCreated") var dateCreated: Date,
    @ColumnInfo(name = "plans") var plans : List<Plan>
)

// Table definition for Plan Entities
@Entity
data class Plan(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "dueDate") var dueDate: Date
)