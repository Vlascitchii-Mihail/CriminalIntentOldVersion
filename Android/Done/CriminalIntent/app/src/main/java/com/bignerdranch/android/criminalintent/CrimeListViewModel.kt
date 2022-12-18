package com.bignerdranch.android.criminalintent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CrimeListViewModel: ViewModel() {
    var dataListSize: Int? = 0
//    val crimes = mutableListOf<Crime>()

//    init {
//        for (i in 0 until 100) {
//            val crime = Crime()
//            crime.title = "Crime #$"
//            crime.isSolved = i % 2 == 0
//            if (i % 5 == 0) crime.requiresPolice = true
//            crimes += crime
//        }
//    }

    //provides access CrimeRepository object
    private val crimeRepository = CrimeRepository.get()

    //returns a list of crimes
    val crimeListLiveData = crimeRepository.getCrimes()
    private val _crimesFlow: MutableStateFlow<List<Crime>> = MutableStateFlow(emptyList())
    val crimesFlow: StateFlow<List<Crime>>
        get() = _crimesFlow.asStateFlow()

    init {
        viewModelScope.launch {
            crimeRepository.getCrimesFlow().collect {
                _crimesFlow.value = it
            }
        }
    }
    //adding a new crime from the menu
    fun addCrime(crime: Crime) {
        crimeRepository.addCrime(crime)
    }
}