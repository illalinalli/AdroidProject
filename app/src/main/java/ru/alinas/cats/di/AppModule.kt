package ru.alinas.cats.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.alinas.cats.db.CatDao
import ru.alinas.cats.db.CatDatabase
import ru.alinas.cats.db.CatRepository

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideCatDatabase(@ApplicationContext context: Context): CatDatabase {
        return Room.databaseBuilder(context, CatDatabase::class.java, "cat_database.db")
            .build()
    }

    @Provides
    fun provideCatDao(catDatabase: CatDatabase): CatDao {
        return catDatabase.catDao()
    }

    @Provides
    fun provideCatRepository(catDao: CatDao): CatRepository {
        return CatRepository(catDao)
    }
}