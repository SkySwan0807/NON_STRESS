package com.example.trial.di

import android.content.Context
import com.example.profedata.database.local.db.AppDatabase
import com.example.profedata.database.local.dao.CursoDao
import com.example.profedata.database.local.dao.CalificacionDao
import com.example.profedata.database.local.dao.EstudianteDao
import com.example.profedata.database.local.dao.MateriaDao
import com.example.profedata.database.local.dao.TareaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideCursoDao(database: AppDatabase): CursoDao {
        return database.cursoDao()
    }

    @Provides
    fun provideCalificacionDao(database: AppDatabase): CalificacionDao {
        return database.calificacionDao()
    }

    @Provides
    fun provideEstudianteDao(database: AppDatabase): EstudianteDao {
        return database.estudianteDao()
    }

    @Provides
    fun provideTareaDao(database: AppDatabase): TareaDao {
        return database.tareaDao()
    }

    @Provides
    fun provideMateriaDao(database: AppDatabase): MateriaDao {
        return database.materiaDao()
    }
}