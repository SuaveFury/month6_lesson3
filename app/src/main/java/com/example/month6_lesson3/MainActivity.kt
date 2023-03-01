package com.example.month6_lesson3

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.month6_lesson3.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var item = ""
    private var array = ArrayList<String>()
    private val adapter = Adapter(array,this::clickItem)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        checkAndClick()
        initAdapter()
    }
    private fun initAdapter() {
        binding.rv.adapter = adapter
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun checkAndClick(){
        binding.btnMain.setOnClickListener {
            if(binding.etMain.text.isNotEmpty()){
                val hash = binding.etMain.text.toString().replace(",", " ")
                val texts: List<String> = hash.split(" ")
                for (i in texts){
                    if(i.startsWith("#")){array.add(i)}}
                binding.etMain.text.clear()
                adapter.notifyDataSetChanged()
            }
            else {Toast.makeText(this,getString(R.string.toast), Toast.LENGTH_LONG).show()}
        }
    }
    private fun clickItem(itemX: String) {
        binding.etMain.setText(binding.etMain.text.toString().replace(item,itemX))
        binding.rv.isVisible = false
    }
    private fun init(){
        binding.etMain.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(char: CharSequence?, begin: Int, check: Int, later: Int) {
            }
            override fun onTextChanged(itemText: CharSequence?, begin: Int, last: Int, check: Int) {
                val rvItem = itemText.toString().replace(",", " ").split(" ")
                for (word in rvItem){
                    if(word.startsWith("#")){item = word}
                    binding.rv.isVisible = word.startsWith("#")}
            }
            override fun afterTextChanged(char: Editable?) {}})
    }
}