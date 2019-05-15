package com.example.roomwordsample

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

//TODO 4: Anote la clase con @Daopara identificarla como una clase DAO para la sala.
@Dao
interface WordDao {
    //TODO 4.1: Declara un método de suspensión para insertar una palabra: suspend fun insert(word: Word)
    @Insert
    suspend fun insert(word: Word)
    //TODO 4.2: Declarar un método para eliminar todas las palabras: fun deleteAll().
    @Query("DELETE FROM word_table")
    fun deleteAll()
    //TODO 4.3: No hay una anotación de conveniencia para eliminar múltiples entidades, por lo tanto, anote el método con el genérico @Query.
    /*@Query("SELECT * from word_table ORDER BY word ASC")
    fun getAllWords(): List<Word>*/
    //TODO 6: Observar los cambios en los datos en múltiples componentes de su aplicación puede crear rutas de dependencia explícitas
    //TODO 6: y rígidas entre los componentes. Esto hace que las pruebas y la depuración sean difíciles, entre otras cosas.
    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAllWords(): LiveData<List<Word>>
}
//TODO 5: Si la tabla tiene más de una columna, puede usar. @Insert(onConflict = OnConflictStrategy.REPLACE)