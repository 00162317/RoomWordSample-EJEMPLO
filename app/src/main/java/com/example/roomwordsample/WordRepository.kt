package com.example.roomwordsample

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

//TODO 10: Crear una clase pública llamada WordRepository.
//TODO 10.1: Declarar el DAO como una propiedad privada en el constructor.
//TODO 10.2: Agregue la lista de palabras como propiedad pública e inicialícela. Sala ejecuta todas las consultas en un
//TODO 10.2: hilo separado. Observado LiveDatanotificará al observador cuando los datos hayan cambiado.
class WordRepository(private val wordDao: WordDao) {
    //TODO 11: Agregue la lista de palabras como propiedad pública e inicialícela. Sala ejecuta todas las consultas en un hilo separado.
    //TODO 11: Observado LiveDatanotificará al observador cuando los datos hayan cambiado.
    val allWords: LiveData<List<Word>> = wordDao.getAllWords()
    //TODO 12: Agrega una envoltura para el insert()método. Debe llamar a esto en un subproceso que no sea de UI o su aplicación se
    //TODO 12: bloqueará. Room garantiza que no realice operaciones de larga duración en el subproceso principal, bloqueando la
    //TODO 12: interfaz de usuario. Agregue la @WorkerThreadanotación para marcar que se debe llamar a este método desde un
    //TODO 12: subproceso que no sea UI. Agregue el suspendmodificador para decirle al compilador que esto debe llamarse desde una rutina u otra función de suspensión.
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}