package org.umi.floria.room

import androidx.room.*


@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users")
    suspend fun getAllUser(): List<User>

    @Query("SELECT name FROM users WHERE id = :id")
    suspend fun getUserById(id: Long): String?

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun login(email: String, password: String): User?

}