package com.hackerrank.android

import android.os.Looper
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import com.google.common.truth.Truth
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows

@RunWith(RobolectricTestRunner::class)
class UiTests {
    private lateinit var activity: MainActivity
    private lateinit var rv: RecyclerView
    private var robot = ActivityRobots()

    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(MainActivity::class.java).create().resume().get()
        rv = activity.findViewById(R.id.food_list)
    }

    @Test
    fun testAdapterDecoration() {
        Truth.assertThat(rv.getItemDecorationAt(0)).isEqualTo(activity!!.decoration)
    }

    @Test
    fun validateButtonClickAddsToCartForFirstItem() {
        validateButtonClickAddsToCartForFoodItem(0)
    }

    @Test
    fun validateButtonClickAddsToCartForSecondItem() {
        validateButtonClickAddsToCartForFoodItem(1)
    }

    @Test
    fun validateButtonClickAddsToCartForThirdItem() {
        validateButtonClickAddsToCartForFoodItem(2)
    }

    @Test
    fun validateButtonClickAddsToCartForFourthItem() {
        validateButtonClickAddsToCartForFoodItem(3)
    }

    fun validateButtonClickAddsToCartForFoodItem(position: Int) {
        ActivityScenario.launch(MainActivity::class.java).onActivity { activity: MainActivity? ->
            robot.clickOnAddToCartForItem(position)
            robot.verifyButtonTextForItem(position, "REMOVE FROM CART")
            robot.verifyNotAddedToCartForItem(position, "ADD TO CART")
            robot.clickOnAddToCartForItem(position)
            robot.verifyButtonTextForItem(position, "ADD TO CART")
            robot.verifyNotAddedToCartForItem(position, "ADD TO CART")
        }
    }

    companion object {
        const val TEST_TITLE = "FAILED TEST:"
    }
}

private class ActivityRobots : BaseEspressoRobots() {
    fun clickOnAddToCartForItem(position: Int) {
        onView(ViewMatchers.withId(R.id.food_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                RecyclerViewItemActions.clickViewWithId(R.id.food_cart_button)
            )
        )
    }

    fun verifyButtonTextForItem(position: Int, btnText: String?) {
        onView(ViewMatchers.withId(R.id.food_list)).check(
            ViewAssertions.matches(
                AdaptersUtils.viewHolderChildHasText(position, R.id.food_cart_button, btnText)
            )
        )
    }

    fun verifyNotAddedToCartForItem(excludePosition: Int, btnText: String?) {
        onView(ViewMatchers.withId(R.id.food_list)).check(
            ViewAssertions.matches(
                AdaptersUtils.viewHolderChildrenMatches(
                    excludePosition,
                    R.id.food_cart_button,
                    ViewMatchers.withText(btnText),
                    true
                )
            )
        )
    }
}

internal abstract class BaseEspressoRobots {
    var rootMatcherOverride: Matcher<Root>? = null
    protected fun onView(matcher: Matcher<View?>?): ViewInteraction {
        val retVal = Espresso.onView(matcher)
        return if (rootMatcherOverride != null) {
            retVal.inRoot(rootMatcherOverride)
        } else retVal
    }
}

internal class RecyclerViewItemMatchers<T : View?>(
    private val position: Int,
    private val id: Int,
    private val matcher: Matcher<View>
) :
    BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
    override fun matchesSafely(view: RecyclerView): Boolean {
        val itemView = view.findViewHolderForAdapterPosition(position)!!.itemView
        val targetView = itemView.findViewById<View>(id)
        return matcher.matches(targetView)
    }

    override fun describeTo(description: Description) {}
}

internal class RecyclerViewItemsMatchers<T : View>(
    private val excludedPosition: Int,
    private val id: Int,
    private val matcher: Matcher<View>,
    private val match: Boolean
) :
    BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
    override fun matchesSafely(view: RecyclerView): Boolean {
        val itemCount = view.adapter!!.itemCount
        for (i in 0 until itemCount) {
            if (i != excludedPosition) {
                view.scrollToPosition(i)
                Shadows.shadowOf(Looper.getMainLooper()).idle()
                val itemView = view.findViewHolderForAdapterPosition(i)?.itemView
                val targetView = itemView?.findViewById<View>(id)
                if (matcher.matches(targetView) != match) return false
            }
        }
        return true
    }

    override fun describeTo(description: Description) {}
}

internal object AdaptersUtils {
    fun viewHolderChildrenMatches(
        position: Int,
        id: Int,
        matcher: Matcher<View>
    ): BoundedMatcher<View, RecyclerView> {
        return RecyclerViewItemMatchers<View>(position, id, matcher)
    }

    fun viewHolderChildrenMatches(
        excludePosition: Int,
        id: Int,
        matcher: Matcher<View>,
        match: Boolean
    ): BoundedMatcher<View, RecyclerView> {
        return RecyclerViewItemsMatchers<View>(excludePosition, id, matcher, match)
    }

    fun viewHolderChildHasText(
        position: Int,
        id: Int,
        text: String?
    ): BoundedMatcher<View, RecyclerView> {
        return RecyclerViewItemMatchers<View>(position, id, ViewMatchers.withText(text))
    }
}

internal object RecyclerViewItemActions {
    fun clickViewWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return ""
            }

            override fun perform(uiController: UiController, view: View) {
                view.findViewById<View>(id).performClick()
            }
        }
    }
}