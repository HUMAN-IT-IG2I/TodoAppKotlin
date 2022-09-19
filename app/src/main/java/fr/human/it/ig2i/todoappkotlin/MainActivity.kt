package fr.human.it.ig2i.todoappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.human.it.ig2i.todoappkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}