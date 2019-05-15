package com.example.roomwordsample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


//TODO 22: En MainActivity, crea una variable miembro para el ViewModel:
private lateinit var wordViewModel: WordViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        //TODO 20: Llamando al recycler
        val adapter = WordListAdapter(this)
        recyclerview.adapter=adapter
        recyclerview.layoutManager=LinearLayoutManager(this)
        //TODO 23: En onCreate(), obtener una ViewModelde la ViewModelProvider.
        //TODO 23.1: ViewModelProviderscreará el archivo ViewModel
        //TODO 23.2: Cuando la actividad se vuelve a crear, ViewModelProvidersdevuelve el existente ViewModel.
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        //TODO 24: También en onCreate(), agregue un observador para el LiveDatadevuelto por getAllWords().
        //TODO 24.1: El onChanged()método se activa cuando los datos observados cambian y la actividad está en primer plano.
        wordViewModel.allWords!!.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setWords(it) }
        })
        //TODO 27:
        fab.setOnClickListener { view ->
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }
    //TODO 25: Defina un código de solicitud como miembro de MainActivity:
    companion object {
        const val newWordActivityRequestCode = 1
    }
    //TODO 26: En MainActivity, agregue el onActivityResult()código para el archivo NewWordActivity.
    //TODO 26.1:Si la actividad regresa con RESULT_OK, inserte la palabra devuelta en la base de datos llamando al insert()método de WordViewModel.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let {
                val word = Word(it.getStringExtra(NewWordActivity.EXTRA_REPLY))
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
