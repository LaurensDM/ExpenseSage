package com.example.expensesage.ui.utils

/**
 * Enum class that is used to store the type of navigation
 *
 */
enum class NavigationType {
    BOTTOM_NAVIGATION,
    NAVIGATION_RAIL,
}

/**
 * list that is used to store all enums
 */
val screens = listOf(
    Navigations.Start,
    Navigations.Expenses,
    Navigations.Owed,
    Navigations.Due,
    Navigations.Summary,
)