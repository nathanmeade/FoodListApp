package com.hackerrank.android.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hackerrank.android.R
import com.hackerrank.android.models.Food

class FoodListAdapter(private val context: Context, private val foodList: ArrayList<Food>, val listener : (Int) -> Unit) :
    RecyclerView.Adapter<FoodListAdapter.FoodItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.food_item, parent, false)
        return FoodItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: FoodItemViewHolder, position: Int) {
        //Write binding logic here
        viewHolder.foodImage.setImageResource(foodList[position].imageResourceId)
        viewHolder.foodName.text = foodList[position].name
        viewHolder.cartButton.text = if (foodList[position].inCart) R.string.remove_from_cart_btn_text else R.string.add_to_cart_btn_text
        viewHolder.cartButton.setOnClickListener(listener)
    }

    override fun getItemCount(): Int {
        //Modify the logic to return actual items
        return foodList.size
    }

    class FoodItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var foodImage: ImageView
        var foodName: TextView
        var cartButton: Button
        var foodItemCard: CardView

        init {
            foodItemCard = itemView.findViewById(R.id.food_item_card)
            foodImage = itemView.findViewById(R.id.food_image)
            foodName = itemView.findViewById(R.id.food_name)
            cartButton = itemView.findViewById(R.id.food_cart_button)
        }
    }
}