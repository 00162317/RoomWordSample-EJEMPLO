package com.example.roomwordsample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//TODO 7.1: Anote la clase como una base de datos de sala, declare las entidades que pertenecen a la
//TODO 7.1: base de datos y establezca el número de versión. Listado de las entidades creará tablas en la base de datos.
@Database(entities = [Word::class], version = 1)
//TODO 7: Crea una public abstractclase que se extienda RoomDatabase y llámala WordRoomDatabase.
public abstract class WordRoomDatabase : RoomDatabase() {
    //TODO 7.2: Defina los DAOs que funcionan con la base de datos. Proporcionar un método abstracto "getter" para cada @Dao.
    abstract fun wordDao(): WordDao

    //TODO 8: Haga WordRoomDatabaseun singleton para evitar que se abran varias instancias de la base de datos al mismo tiempo.
    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                //TODO 9: Agregue el código para obtener una base de datos. Este código utiliza el generador de base de datos de
                //TODO 9: Room para crear un RoomDatabaseobjeto en el contexto de la aplicación desde la WordRoomDatabaseclase y
                //TODO 9: lo nombra "word_database".
                val instance = Room.databaseBuilder(
                context.applicationContext,
                WordRoomDatabase::class.java,
                "Word_database"
                ).build()
                    INSTANCE = instance
                instance
            }
        }
    }
}