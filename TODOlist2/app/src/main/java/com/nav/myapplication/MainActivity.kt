package com.nav.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.room.*

class MainActivity : AppCompatActivity(), MainSceneContract.View {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        arrangeBack()
        Room.databaseBuilder(this, OurDatabase::class.java, "note_database")
            .fallbackToDestructiveMigration().build()
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return super.onSupportNavigateUp()
    }

    override fun arrangeBack() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }
}

//@Entity
//data class User(
//    @PrimaryKey(autoGenerate = true)
//    val Id: Int = 0,
//    val Title: String,
//    val Pined: Int,
//    val Context: String
//)
//
//@Dao
//interface UserDao {
//    @Insert
//    fun insertUser(user: User)
//
//    @Query("select * from User")
//    fun getAllTitles(): List<User>
//
//    @Query(
//        "select * from User where Title Like :name or Context Like :name"
//    )
//    fun search(name: String): List<User>
//
//    @Query("select * from User where Pined = 1")
//    fun getPinedUsers(): List<User>
//
//    @Query("select Context from User where Title = :title")
//    fun getContext(title: String): String
//}
//
//@Database(entities = [User::class], version = 2)
//abstract class OurDataBase : RoomDatabase() {
//    abstract fun getUserDao(): UserDao
//}


@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstName: String,
    val lastName: String?
)

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        )
    ]
)
data class UserProfile(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val avatar: String?,
    val date: String?
)

@Dao
interface UserDao {

    @Query("select * from user_table")
    fun getAllUsers(): List<User>

    @Query("select * from user_table where id = :userId limit 1")
    fun getUser(userId: Int): User

    @Insert
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)

}

@Database(entities = arrayOf(User::class, UserProfile::class), version = 2)
abstract class OurDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}
