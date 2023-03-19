package com.hackerrank.android

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.hackerrank.android.adapters.FoodListAdapter
import com.hackerrank.android.adapters.VerticalDecoration
import com.hackerrank.android.databinding.ActivityMainBinding
import com.hackerrank.android.models.Food


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

//    private val listener = OnClickListener {
//      if (){
//
//      }
//    }

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

//    fun listenerFunction(): (View) -> Unit {
//        return { view1 ->
//            if (view1 is Button){
//                if (view1.text.equals(resources.getText(R.string.add_to_cart_btn_text))){
//                    view1.text = resources.getText(R.string.remove_from_cart_btn_text)
//                } else {
//                    view1.text = resources.getText(R.string.add_to_cart_btn_text)
//                }
//            }
//            Log.d("nathanTest", "integer: $view1")
////            view1.context.applicationContext.text
//        }
    fun listenerFunction(): OnClickListener {
        val onClickListener = OnClickListener { view1 ->
            if (view1 is Button){
                if (view1.text.equals(resources.getText(R.string.add_to_cart_btn_text))){
                    view1.text = resources.getText(R.string.remove_from_cart_btn_text)
                } else {
                    view1.text = resources.getText(R.string.add_to_cart_btn_text)
                }
            }
            Log.d("nathanTest", "integer: $view1")
//            view1.context.applicationContext.text
        }
        return onClickListener
    }

    private fun setupFoodMenu() {
        //Write your logic here
        foodListAdapter = FoodListAdapter(this, foodList, listenerFunction())
        binding.foodList.adapter = foodListAdapter
        binding.foodList.layoutManager = LinearLayoutManager(this)
        binding.foodList.addItemDecoration(decoration)
    }
}
