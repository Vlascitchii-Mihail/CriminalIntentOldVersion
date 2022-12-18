package com.bignerdranch.android.criminalintent

import android.content.Context
import data_base.CrimeDatabase
import androidx.room.Room
import java.util.UUID
import androidx.lifecycle.LiveData
import data_base.migration_1_2
import data_base.migration_2_3
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Executors
import java.io.File

private const val DATABASE_NAME = "crime-database"

//Singleton
//private constructor forbids (запрещает) creating a new object
/**
 * @param context - CriminalIntentApplication : Application type
 */
class CrimeRepository private constructor(context: Context) {

    //creating a reference to the database CrimeDatabase
    //CrimeRepository() Создает конкретную реализацию базы данных
    /**
     * @param context.applicationContext - app's context - CriminalIntentApplication : Application
     * @param CrimeDatabase::class.java - database's class
     * @param DATABASE_NAME - database's name
     */
    private val database: CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME

    //addMigrations() - executing the migration
    ).addMigrations(migration_1_2, migration_2_3).build()

    //the object which references on the background thread
    private val executor = Executors.newSingleThreadExecutor()

    //the object file shows the correct place for adding a photo
    private val filesDir = context.applicationContext.filesDir

    //provides access to the CrimeDao interface
    private val crimeDao = database.crimeDao()

    //provides access to CrimeDao.getCrimes()
    //LiveData<List<Crime>> provide an access to the data between 2 threads and starts the functions in the second thread
    fun getCrimes(): LiveData<List<Crime>> = crimeDao.getCrimes()

    fun getCrimesFlow(): Flow<List<Crime>> = crimeDao.getCrimesFlow()

    ////provides access to CrimeDao.getCrime()
    //LiveData<List<Crime>> provide an access to the data between 2 threads and starts the functions in the second thread
    fun getCrime(id: UUID) : LiveData<Crime?> = crimeDao.getCrime(id)

    fun updateCrime(crime: Crime) {

        //starting the code in the background thread
        executor.execute {

            //updating crime
            crimeDao.updateCrime(crime)
        }
    }

    fun addCrime(crime: Crime) {

        //starting the code in the background thread
        executor.execute{

            //adding a new crime
            crimeDao.addCrime(crime)
        }
    }

    //defines the location of a photo
    fun getPhotoFile(crime: Crime): File = File(filesDir, crime.photoFileName)

    companion object {

        //CrimeRepository variable
        private var INSTANCE: CrimeRepository? = null

        //creating and initialising new CrimeRepository object
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        //CrimeRepository provider
        fun get(): CrimeRepository {
            return INSTANCE ?: throw IllegalStateException("Crime Repository must be initialized")
        }
    }
}