package com.hackerrank.android.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hackerrank.android.R
import com.hackerrank.android.databinding.FoodItemBinding
import com.hackerrank.android.models.Food

class FoodListAdapter(
    private val context: Context,
    private val foodList: ArrayList<Food>,
    private val listener: OnClickListener
) :
    RecyclerView.Adapter<FoodListAdapter.FoodItemViewHolder>() {

    private lateinit var binding: FoodItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        binding = FoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodItemViewHolder(binding, listener)
    }

    override fun onBindViewHolder(viewHolder: FoodItemViewHolder, position: Int) {
        val food = foodList[position]
        viewHolder.bind(food)
        //Write binding logic here
//        viewHolder.foodImage.setImageResource(foodList[position].imageResourceId)
//        viewHolder.foodName.text = foodList[position].name
//
//        if (foodList[position].inCart) {
//            viewHolder.cartButton.text =
//                context.resources.getString(R.string.remove_from_cart_btn_text)
//        } else {
//            viewHolder.cartButton.text = context.resources.getString(R.string.add_to_cart_btn_text)
//        }
//        viewHolder.cartButton.
    }

    override fun getItemCount(): Int {
        //Modify the logic to return actual items
        return foodList.size
    }

    class FoodItemViewHolder(private val binding: FoodItemBinding, private val listener: OnClickListener) : RecyclerView.ViewHolder(binding.root) {
        var foodImage: ImageView = binding.foodImage
        var foodName: TextView = binding.foodName
        var cartButton: Button = binding.foodCartButton
        var foodItemCard: CardView = binding.foodItemCard

        fun bind(food: Food) {
            binding.food = food
            binding.listener = listener
//            cartButton.setOnClickListener(listener)

//            cartButton.setOnClickListener { binding.food.inCart = !binding.food.inCart }
        }
    }
}
