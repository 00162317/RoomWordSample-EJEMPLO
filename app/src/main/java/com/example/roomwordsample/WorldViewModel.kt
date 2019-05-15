package com.example.roomwordsample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO 13: Crear una clase llamada WordViewModelque obtiene el Applicationcomo un parámetro y se extiende AndroidViewModel.
class WordViewModel(application: Application) : AndroidViewModel(application){
    //TODO 14: Agregue una variable miembro privada para mantener una referencia al repositorio.
    private var repository: WordRepository? = null
    //TODO 15: Agregue una LiveDatavariable miembro privada para almacenar en caché la lista de palabras.
    var allWords: LiveData<List<Word>>? = null
    //TODO 16: Cree un initbloque que obtenga una referencia a la WordDaode WordRoomDatabasey construya el WordRepositorybasado en él.
    init {
        val wordsDao = WordRoomDatabase.getDatabase(application).wordDao()
        repository = WordRepository(wordsDao)
        //TODO 17: En el initbloque, inicialice la allWordspropiedad con los datos del repositorio:
        allWords = repository!!.allWords
    }
    //TODO 18: Cree un insert()método de envoltura que llame al insert()método del Repositorio . De esta manera, la implementación
    //TODO 18: de insert()está completamente oculta de la interfaz de usuario. Queremos que el insert()método sea llamado
    //TODO 18: fuera del hilo principal, por lo que estamos lanzando una nueva coroutine, basada en el alcance de coroutine
    //TODO 18: definido anteriormente. Porque estamos haciendo una operación de base de datos, estamos usando el IO Dispatcher.
    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository!!.insert(word)
    }

}
