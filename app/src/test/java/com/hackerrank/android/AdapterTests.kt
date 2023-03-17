package com.hackerrank.android

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hackerrank.android.adapters.FoodListAdapter
import com.hackerrank.android.models.Food
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows

@RunWith(RobolectricTestRunner::class)
class AdapterTests {
    enum class FoodItemViewType {
        TITLE, IMAGE, BUTTON
    }

    private var blackColor = 0
    private var lightShadeGreen = 0
    private var darkShadeGreen = 0
    private var cornerRadius = 0F
    private lateinit var context: Context
    private lateinit var adapter: FoodListAdapter
    private lateinit var rvParent: RecyclerView
    private lateinit var rootLayout: ConstraintLayout
    private lateinit var constraintSet: ConstraintSet
    private val foodList = arrayListOf(
        Food("Pancake", R.drawable.pancake),
        Food("Veg Roll", R.drawable.roll),
        Food("Pizza", R.drawable.pizza),
        Food("Soup", R.drawable.soup)
    )

    @Before
    fun setUp() {
        context = RuntimeEnvironment.getApplication()
        adapter = FoodListAdapter(context, foodList) { }
        rvParent = RecyclerView(context)
        rvParent.layoutManager = LinearLayoutManager(context)
        blackColor = Color.parseColor(context.resources.getString(R.color.black))
        lightShadeGreen =
            Color.parseColor(context.resources.getString(R.color.light_shade_green))
        darkShadeGreen =
            Color.parseColor(context.resources.getString(R.color.dark_shade_green))
        cornerRadius = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            8F,
            context.resources.displayMetrics
        )
    }

    @Test
    fun validateItemCount() {
        Assert.assertEquals(adapter.itemCount, 4)
    }

    @Test
    fun validateFoodTitleInFirstFoodItem() {
        validateFoodTitleInFoodItem(0, "Pancake")
    }

    @Test
    fun validateFoodImageInFirstFoodItem() {
        validateFoodImageInFoodItem(0, R.drawable.pancake)
    }

    @Test
    fun validateButtonTextInFirstFoodItem() {
        validateButtonTextInFoodItem(0, ADD_TO_CART_TEXT)
    }

    @Test
    fun validateFoodTitleInSecondFoodItem() {
        validateFoodTitleInFoodItem(1, "Veg Roll")
    }

    @Test
    fun validateFoodImageInSecondFoodItem() {
        validateFoodImageInFoodItem(1, R.drawable.roll)
    }

    @Test
    fun validateButtonTextInSecondFoodItem() {
        validateButtonTextInFoodItem(1, ADD_TO_CART_TEXT)
    }

    @Test
    fun validateFoodTitleInThirdFoodItem() {
        validateFoodTitleInFoodItem(2, "Pizza")
    }

    @Test
    fun validateFoodImageInThirdFoodItem() {
        validateFoodImageInFoodItem(2, R.drawable.pizza)
    }

    @Test
    fun validateButtonTextInThirdFoodItem() {
        validateButtonTextInFoodItem(2, ADD_TO_CART_TEXT)
    }

    @Test
    fun validateFoodTitleInFourthFoodItem() {
        validateFoodTitleInFoodItem(3, "Soup")
    }

    @Test
    fun validateFoodImageInFourthFoodItem() {
        validateFoodImageInFoodItem(3, R.drawable.soup)
    }

    @Test
    fun validateButtonTextInFourthFoodItem() {
        validateButtonTextInFoodItem(3, ADD_TO_CART_TEXT)
    }

    @Test
    fun validateCardBackgroundColorInFirstFoodItem() {
        validateCardBackgroundColorInFoodItem(0, lightShadeGreen)
    }

    @Test
    fun validateCardBackgroundColorInSecondFoodItem() {
        validateCardBackgroundColorInFoodItem(1, lightShadeGreen)
    }

    @Test
    fun validateCardBackgroundColorInThirdFoodItem() {
        validateCardBackgroundColorInFoodItem(2, lightShadeGreen)
    }

    @Test
    fun validateCardBackgroundColorInFourthFoodItem() {
        validateCardBackgroundColorInFoodItem(3, lightShadeGreen)
    }

    @Test
    fun validateButtonBackgroundColorInFirstFoodItem() {
        validateButtonBackgroundColorInFoodItem(0, darkShadeGreen)
    }

    @Test
    fun validateButtonBackgroundColorInSecondFoodItem() {
        validateButtonBackgroundColorInFoodItem(1, darkShadeGreen)
    }

    @Test
    fun validateButtonBackgroundColorInThirdFoodItem() {
        validateButtonBackgroundColorInFoodItem(2, darkShadeGreen)
    }

    @Test
    fun validateButtonBackgroundColorInFourthFoodItem() {
        validateButtonBackgroundColorInFoodItem(3, darkShadeGreen)
    }

    @Test
    fun validateFoodTitleColorInFirstFoodItem() {
        validateFoodTitleColorInFoodItem(0, blackColor)
    }

    @Test
    fun validateFoodTitleColorInSecondFoodItem() {
        validateFoodTitleColorInFoodItem(1, blackColor)
    }

    @Test
    fun validateFoodTitleColorInThirdFoodItem() {
        validateFoodTitleColorInFoodItem(2, blackColor)
    }

    @Test
    fun validateFoodTitleColorInFourthFoodItem() {
        validateFoodTitleColorInFoodItem(3, blackColor)
    }

    @Test
    fun validateFoodTitleFontInFirstFoodItem() {
        validateFoodTitleFontInFoodItem(0)
    }

    @Test
    fun validateFoodTitleFontInSecondFoodItem() {
        validateFoodTitleFontInFoodItem(1)
    }

    @Test
    fun validateFoodTitleFontInThirdFoodItem() {
        validateFoodTitleFontInFoodItem(2)
    }

    @Test
    fun validateFoodTitleFontInFourthFoodItem() {
        validateFoodTitleFontInFoodItem(3)
    }

    @Test
    fun validateCardCornerRadiusInFirstFoodItem() {
        validateCardCornerRadiusInFoodItem(0, cornerRadius)
    }

    @Test
    fun validateCardCornerRadiusInSecondFoodItem() {
        validateCardCornerRadiusInFoodItem(1, cornerRadius)
    }

    @Test
    fun validateCardCornerRadiusInThirdFoodItem() {
        validateCardCornerRadiusInFoodItem(2, cornerRadius)
    }

    @Test
    fun validateCardCornerRadiusInFourthFoodItem() {
        validateCardCornerRadiusInFoodItem(3, cornerRadius)
    }

    private fun validateCardBackgroundColorInFoodItem(position: Int, expectedBgColor: Int) {
        val viewHolder = adapter.onCreateViewHolder(rvParent, position)
        adapter.onBindViewHolder(viewHolder, position)
        val bgColor = viewHolder.foodItemCard.cardBackgroundColor.defaultColor
        try {
            Assert.assertEquals(
                "$TEST_TITLE Testing if card background color is correct. Result: ",
                expectedBgColor,
                bgColor
            )
        } catch (e: Exception) {
            Assert.fail("Exception while accessing card background color in food item : $e")
        }
    }

    private fun validateCardCornerRadiusInFoodItem(position: Int, expectedRadius: Float) {
        val viewHolder = adapter.onCreateViewHolder(rvParent, position)
        adapter.onBindViewHolder(viewHolder, position)
        val radius = viewHolder.foodItemCard.radius
        try {
            Assert.assertEquals(
                "$TEST_TITLE Testing if card corner radius is correct. Result: ",
                expectedRadius,
                radius
            )
        } catch (e: Exception) {
            Assert.fail("Exception while accessing card corner radius in food item : $e")
        }
    }

    private fun validateFoodTitleInFoodItem(position: Int, expectedText: String?) {
        val viewHolder = adapter.onCreateViewHolder(rvParent, position)
        adapter.onBindViewHolder(viewHolder, position)
        try {
            Assert.assertEquals(
                "$TEST_TITLE Testing if food title is correct. Result: ",
                expectedText,
                viewHolder.foodName.text
            )
        } catch (e: Exception) {
            Assert.fail("Exception while accessing food title in food item : $e")
        }
    }

    private fun validateFoodTitleColorInFoodItem(position: Int, expectedTextColor: Int) {
        val viewHolder = adapter.onCreateViewHolder(rvParent, position)
        adapter.onBindViewHolder(viewHolder, position)
        val textColor = viewHolder.foodName.currentTextColor
        try {
            Assert.assertEquals(
                "$TEST_TITLE Testing if food title color is correct. Result: ",
                expectedTextColor,
                textColor
            )
        } catch (e: Exception) {
            Assert.fail("Exception while accessing food title color in food item : $e")
        }
    }

    private fun validateFoodTitleFontInFoodItem(position: Int) {
        val viewHolder = adapter.onCreateViewHolder(rvParent, position)
        adapter.onBindViewHolder(viewHolder, position)
        val style = viewHolder.foodName.typeface.style
        try {
            Assert.assertEquals(
                "$TEST_TITLE Testing if food title font is correct. Result: ",
                Typeface.BOLD,
                style
            )
        } catch (e: Exception) {
            Assert.fail("Exception while accessing food title font in food item : $e")
        }
    }

    private fun validateFoodImageInFoodItem(position: Int, expectedDrawable: Int) {
        val viewHolder = adapter.onCreateViewHolder(rvParent, position)
        adapter.onBindViewHolder(viewHolder, position)
        try {
            Assert.assertEquals(
                "$TEST_TITLE Testing if food image is correct. Result: ",
                expectedDrawable,
                Shadows.shadowOf(viewHolder.foodImage.drawable).createdFromResId
            )
        } catch (e: Exception) {
            Assert.fail("Exception while accessing food image in food item : $e")
        }
    }

    private fun validateButtonTextInFoodItem(position: Int, expectedText: String?) {
        val viewHolder = adapter.onCreateViewHolder(rvParent, position)
        adapter.onBindViewHolder(viewHolder, position)
        try {
            Assert.assertEquals(
                "$TEST_TITLE Testing if add to cart button text is correct. Result: ",
                expectedText,
                viewHolder.cartButton.text
            )
        } catch (e: Exception) {
            Assert.fail("Exception while accessing add to cart button in food item : $e")
        }
    }

    private fun validateButtonBackgroundColorInFoodItem(position: Int, expectedBgColor: Int) {
        val viewHolder = adapter.onCreateViewHolder(rvParent, position)
        adapter.onBindViewHolder(viewHolder, position)
        val bgColor = (viewHolder.cartButton.background as GradientDrawable).color?.defaultColor
        try {
            Assert.assertEquals(
                "$TEST_TITLE Testing if add to cart button background color is correct. Result: ",
                expectedBgColor,
                bgColor
            )
        } catch (e: Exception) {
            Assert.fail("Exception while accessing add to cart button background color in food item : $e")
        }
    }

    fun validateFoodItemCardStyling(
        position: Int,
        viewType: FoodItemViewType,
        style: FoodItemStyle
    ) {
        val viewHolder = adapter.onCreateViewHolder(rvParent, position)
        adapter.onBindViewHolder(viewHolder, position)
        var item: View? = null
        val itemId: Int
        var VIEW_NAME = ""
        var VIEW_PROPERTY: Int
        var PROPERTY_NAME = ""
        when (viewType) {
            FoodItemViewType.TITLE -> {
                item = viewHolder.foodName
                itemId = R.id.food_name
                VIEW_NAME = "food title"
            }
            FoodItemViewType.IMAGE -> {
                item = viewHolder.foodImage
                itemId = R.id.food_image
                VIEW_NAME = "food image"
            }
            FoodItemViewType.BUTTON -> {
                item = viewHolder.cartButton
                itemId = R.id.food_cart_button
                VIEW_NAME = "add to cart button"
            }
        }
        with(style) {
            testMargins?.let {
                it.forEachIndexed { index, value ->
                    when (index) {
                        0 -> {
                            if (value != UNDEFINED) {
                                VIEW_PROPERTY = (item.layoutParams as ConstraintLayout.LayoutParams).leftMargin
                                PROPERTY_NAME = "left margin"
                                assertStyle(
                                    VIEW_NAME,
                                    PROPERTY_NAME,
                                    VIEW_PROPERTY,
                                    value
                                )
                            }
                        }
                        1 -> {
                            if (value != UNDEFINED) {
                                VIEW_PROPERTY = (item.layoutParams as ConstraintLayout.LayoutParams).rightMargin
                                PROPERTY_NAME = "right margin"
                                assertStyle(
                                    VIEW_NAME,
                                    PROPERTY_NAME,
                                    VIEW_PROPERTY,
                                    value
                                )
                            }
                        }
                        2 -> {
                            if (value != UNDEFINED) {
                                VIEW_PROPERTY = (item.layoutParams as ConstraintLayout.LayoutParams).topMargin
                                PROPERTY_NAME = "top margin"
                                assertStyle(
                                    VIEW_NAME,
                                    PROPERTY_NAME,
                                    VIEW_PROPERTY,
                                    value
                                )
                            }
                        }
                        3 -> {
                            if (value != UNDEFINED) {
                                VIEW_PROPERTY =
                                    (item.layoutParams as ConstraintLayout.LayoutParams).bottomMargin
                                PROPERTY_NAME = "bottom margin"
                                assertStyle(
                                    VIEW_NAME,
                                    PROPERTY_NAME,
                                    VIEW_PROPERTY,
                                    value
                                )
                            }
                        }
                    }
                }
            }

            rootLayout =
                viewHolder.foodItemCard.findViewById<View>(R.id.card_layout) as ConstraintLayout
            constraintSet = ConstraintSet()
            constraintSet.clone(rootLayout)

            horizontalConstraints?.let {
                if (it.isHorizontalStart) {
                    if (it.horizToStart != UNDEFINED) {
                        VIEW_PROPERTY = constraintSet.getConstraint(itemId).layout.startToStart
                        PROPERTY_NAME = "startToStart constraint"
                        assertStyle(
                            VIEW_NAME,
                            PROPERTY_NAME,
                            VIEW_PROPERTY,
                            it.horizToStart
                        )
                    }
                    if (it.horizToEnd != UNDEFINED) {
                        VIEW_PROPERTY = constraintSet.getConstraint(itemId).layout.startToEnd
                        PROPERTY_NAME = "startToEnd constraint"
                        assertStyle(
                            VIEW_NAME,
                            PROPERTY_NAME,
                            VIEW_PROPERTY,
                            it.horizToEnd
                        )
                    }
                } else {
                    if (it.horizToStart != UNDEFINED) {
                        VIEW_PROPERTY = constraintSet.getConstraint(itemId).layout.endToStart
                        PROPERTY_NAME = "endToStart constraint"
                        assertStyle(
                            VIEW_NAME,
                            PROPERTY_NAME,
                            VIEW_PROPERTY,
                            it.horizToStart
                        )
                    }
                    if (it.horizToEnd != UNDEFINED) {
                        VIEW_PROPERTY = constraintSet.getConstraint(itemId).layout.endToEnd
                        PROPERTY_NAME = "endToEnd constraint"
                        assertStyle(
                            VIEW_NAME,
                            PROPERTY_NAME,
                            VIEW_PROPERTY,
                            it.horizToEnd
                        )
                    }
                }
            }

            verticalConstraints?.let {
                if (it.isVerticalTop) {
                    if (it.verticalToTop != UNDEFINED) {
                        VIEW_PROPERTY = constraintSet.getConstraint(itemId).layout.topToTop
                        PROPERTY_NAME = "topToTop constraint"
                        assertStyle(
                            VIEW_NAME,
                            PROPERTY_NAME,
                            VIEW_PROPERTY,
                            it.verticalToTop
                        )
                    }
                    if (it.verticalToBottom != UNDEFINED) {
                        VIEW_PROPERTY = constraintSet.getConstraint(itemId).layout.topToBottom
                        PROPERTY_NAME = "topToBottom constraint"
                        assertStyle(
                            VIEW_NAME,
                            PROPERTY_NAME,
                            VIEW_PROPERTY,
                            it.verticalToBottom
                        )
                    }
                } else {
                    if (it.verticalToTop != UNDEFINED) {
                        VIEW_PROPERTY = constraintSet.getConstraint(itemId).layout.bottomToTop
                        PROPERTY_NAME = "bottomToTop constraint"
                        assertStyle(
                            VIEW_NAME,
                            PROPERTY_NAME,
                            VIEW_PROPERTY,
                            it.verticalToTop
                        )
                    }
                    if (it.verticalToBottom != UNDEFINED) {
                        VIEW_PROPERTY = constraintSet.getConstraint(itemId).layout.bottomToBottom
                        PROPERTY_NAME = "bottomToBottom constraint"
                        assertStyle(
                            VIEW_NAME,
                            PROPERTY_NAME,
                            VIEW_PROPERTY,
                            it.verticalToBottom
                        )
                    }
                }
            }
        }

    }

    private fun assertStyle(VIEW_NAME: String, PROPERTY_NAME: String, actual: Int, expected: Int) {
        try {
            Assert.assertEquals(
                "$TEST_TITLE Testing if $VIEW_NAME $PROPERTY_NAME is correct. Result: ",
                expected.toLong(),
                actual.toLong()
            )
        } catch (e: Exception) {
            Assert.fail("Exception while accessing $VIEW_NAME $PROPERTY_NAME in food item : $e")
        }
    }

    companion object {
        var UNDEFINED = -1
        const val TEST_TITLE = "FAILED TEST:"
        private const val ADD_TO_CART_TEXT = "ADD TO CART"
        private const val REMOVE_FROM_CART_TEXT = "REMOVE FROM CART"
    }
}