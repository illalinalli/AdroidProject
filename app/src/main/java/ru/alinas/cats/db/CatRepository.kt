package ru.alinas.cats.db

import javax.inject.Inject

class CatRepository @Inject constructor(private val catDao: CatDao) {
    suspend fun insertCat(cat: CatEntity) {
        catDao.insertCat(cat)
    }

    suspend fun getAllCats(): List<CatEntity> {
        return catDao.getAllCats()
    }
}