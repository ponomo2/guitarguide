package com.example.myapplication.db.users

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface UserRepositoryInterface {
    /**
     * Get the account info of the current signed-in user.
     */
    suspend fun getUser(): Flow<User?>

}

class UserRepository(
    private val userDao: UserDao
) : UserRepositoryInterface {
    override suspend fun getUser(): Flow<User?> {
        return userDao.getUser()
    }
}