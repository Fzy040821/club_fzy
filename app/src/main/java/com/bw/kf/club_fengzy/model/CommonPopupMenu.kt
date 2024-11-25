package com.bw.kf.club_fengzy.model

import kotlin.random.Random

object PopupMenuTags {
    const val TAG_OTHERS = "TAG_OTHERS"
    const val TAG_SERIES = "TAG_SERIES"
}

data class CommonPopupMenu(
    var id: Long = Random.nextLong(1, Long.MAX_VALUE),
    var name: String? = "",
    var tag: String? = "",
    var checked: Boolean = false,
    var last: Boolean = false,
    var series: MallMotorSeries? = null
)