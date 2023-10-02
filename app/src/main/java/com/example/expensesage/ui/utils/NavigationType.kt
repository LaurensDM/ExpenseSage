package com.example.expensesage.ui.utils

enum class NavigationType {
    BOTTOM_NAVIGATION,
    NAVIGATION_RAIL,
    PERMANENT_NAVIGATION_DRAWER
}

val screens = listOf(
    Navigations.Start,
    Navigations.Expenses,
    Navigations.Owed,
    Navigations.Due,
    Navigations.Summary,
)