package com.hackerrank.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.hackerrank.android.adapters.FoodListAdapter
import com.hackerrank.android.adapters.VerticalDecoration
import com.hackerrank.android.databinding.ActivityMainBinding
import com.hackerrank.android.models.Food
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var foodListAdapter: FoodListAdapter? = null
    var decoration: ItemDecoration = VerticalDecoration(40)

    private val foodList: ArrayList<Food> = arrayListOf(
        Food("Pancake", R.drawable.pancake),
        Food("Veg Roll", R.drawable.roll),
        Food("Pizza", R.drawable.pizza),
        Food("Soup", R.drawable.soup)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the view hierarchy and bind the object to it
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Set the view hierarchy as the current layout for the activity
        setContentView(binding.root)

        // Setup and add the HackerRank logo in the toolbar
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_logo)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupFoodMenu()
    }

    private fun setupFoodMenu() {
        //Write your logic here
    }
}