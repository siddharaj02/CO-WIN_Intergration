package com.profilecreator.ui.dashboard


object DashboardContent {

    var DASHBOARD_ITEM_MAP: Map<Int, String>
    val DASHBOARD_ITEM_LIST: MutableList<Int> = ArrayList()


    var DASHBOARD_MAP: Map<Int, String>
    val DASHBOARD_LIST: MutableList<Int> = ArrayList()

    init {
        DASHBOARD_ITEM_MAP = mapOf(
            11 to "Personal Information",
            12 to "Education details",
            13 to "Company details",
            14 to "Project details",
            15 to "Other Information"
        )
        for (key in DASHBOARD_ITEM_MAP.keys) {
            addAccommodationItem(key)
        }

        DASHBOARD_MAP = mapOf(
            1 to "DashboardItem"
        )
        for (key in DASHBOARD_MAP.keys) {
            addDashboardItem(key)
        }
    }

    private fun addAccommodationItem(item: Int) {
        DASHBOARD_ITEM_LIST.add(item)
    }

    private fun addDashboardItem(item: Int) {
        DASHBOARD_LIST.add(item)
    }
}
