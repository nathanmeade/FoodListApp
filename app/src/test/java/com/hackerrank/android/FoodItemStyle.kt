package com.hackerrank.android

class FoodItemStyle(
    var testMargins: IntArray?,
    var horizontalConstraints: TestHorizontalConstraints?,
    var verticalConstraints: TestVerticalConstraints?
)

internal class TestMargins(var left: Int, var right: Int, var top: Int, var bottom: Int)

class TestVerticalConstraints(
    var isVerticalTop: Boolean,
    var verticalToTop: Int,
    var verticalToBottom: Int
)

class TestHorizontalConstraints(
    var isHorizontalStart: Boolean,
    var horizToStart: Int,
    var horizToEnd: Int
)