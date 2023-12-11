package org.umi.floria.room

import androidx.room.*


@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users")
    suspend fun getAllUser(): List<User>

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Long): User

    @Update
    suspend fun updateUser(user: User)

}