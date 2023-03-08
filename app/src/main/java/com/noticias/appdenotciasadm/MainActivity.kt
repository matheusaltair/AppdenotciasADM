package com.noticias.appdenotciasadm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.noticias.appdenotciasadm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.btnSendNews.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val news = binding.editNews.text.toString()
            val date = binding.editDate.text.toString()
            val writer = binding.editWriter.text.toString()

            if(title.isEmpty() || news.isEmpty() || date.isEmpty() || writer.isEmpty()){
                Toast.makeText(this,"Preencha todos os campoos", Toast.LENGTH_SHORT).show()
            } else {
                saveNews(title, news, date, writer)
            }
        }
    }
    private fun saveNews(title: String, news: String, date: String, writer: String  ) {
        val data = hashMapOf(
            "Title" to title,
            "News" to news,
            "Date" to date,
            "Writer" to writer
        )
        db.collection("Noticias").document("Noticia")
            .set(data).addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    Toast.makeText(this,"Notícia enviada com sucesso", Toast.LENGTH_SHORT).show()
                }
            }
    }
}