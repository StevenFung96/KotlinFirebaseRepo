package com.example.kotlintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    //ViewModel + XmlBinding
    lateinit var viewModel: MainActivityViewModel
    lateinit var binding: ActivityMainBinding

    //XmlComponents + Variables
    lateinit var rvUser: RecyclerView
    lateinit var adapter: UsersAdapter
    lateinit var listItems: List<User>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Bind main xml view
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Setup ViewModel
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        //Components Binding
        binding.btnCreateEdit.setOnClickListener(this)
        binding.btnRetrieve.setOnClickListener(this)
        binding.btnDelete.setOnClickListener(this)
        rvUser = binding.rvUser

        //Layout Manager
        rvUser.layoutManager = LinearLayoutManager(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCreateEdit -> {
                viewModel.createBtnClickListener()
            }
            R.id.btnRetrieve -> {
                viewModel.getSavedUser().observe(this, Observer {
                    Log.e("test", it.toString())
                    listItems = it
                    adapter = UsersAdapter(listItems)
                    rvUser.adapter = adapter
                    /*ListView
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
                    listView.adapter = adapter*/
                })
            }
            R.id.btnDelete -> {
                viewModel.deleteBtnClickListener()
            }
        }
    }
}
