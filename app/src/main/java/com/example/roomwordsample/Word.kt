package com.example.roomwordsample
import androidx.room.ColumnInfo

import androidx.room.Entity

import androidx.room.PrimaryKey

import androidx.annotation.NonNull
//TODO 3: Hay que hacerla significativa
//TODO 3.1: @Entity es para representar una TABLA
@Entity(tableName = "word_table")


class Word(
    //TODO 3.2: @PrimaryKey. Para mantener las cosas simples, cada palabra actúa como su propia clave principal.
    @PrimaryKey
    //TODO 3.3: @ColumnInfo(name = "word"). Es el nombre de la COLUMNA de la tabla
    @ColumnInfo(name = "word")
    val word:String)