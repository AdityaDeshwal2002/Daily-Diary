package com.example.diary.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.diary.Database.NotesDatabase
import com.example.diary.Model.Notes
import com.example.diary.Repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    val repository:NotesRepository
    init {
        val dao=NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository=NotesRepository(dao)
    }
    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }
    fun getLowNotes():LiveData<List<Notes>> =repository.getLowNotes()
    fun getHighNotes():LiveData<List<Notes>> =repository.getHighNotes()
    fun getMediumNotes():LiveData<List<Notes>> =repository.getMediumNotes()

    fun getNotes():LiveData<List<Notes>> = repository.getAllNotes()

    fun deleteNotes(id:Int){
        repository.deleteNotes(id)
    }
    fun updateNotes(notes : Notes){
        repository.updateNotes(notes)
    }
}