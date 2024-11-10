package com.hninor.pruebainterrapidisimo.features.login.data.local

import com.hninor.pruebainterrapidisimo.core.database.dao.UserDao
import com.hninor.pruebainterrapidisimo.core.database.entitites.UserDB

class LoginLocalDataSource(private val userDao: UserDao) {

    suspend fun saveUser(userDB: UserDB) {
        userDao.deleteAll()
        userDao.insertUser(userDB)
    }
}