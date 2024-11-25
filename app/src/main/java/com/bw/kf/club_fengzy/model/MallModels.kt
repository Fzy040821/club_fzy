package com.bw.kf.club_fengzy.model


import android.view.View
import androidx.lifecycle.MutableLiveData
import com.bw.kf.club_fengzy.util.RandomPicProvider
import com.chad.library.adapter.base.entity.MultiItemEntity

import java.io.Serializable
import java.util.UUID
import kotlin.random.Random

data class MallMotorSeries(
    var id: Long = 0,
    var name: String? = null,
    var pic: String? = RandomPicProvider.pic(),
    var desc: String? = null,
    var child: List<MallMotorSeries>? = null,
    var checked: Boolean = false,
    var goodsId: Long = 0,
) : Serializable {
    fun ifSeries() = !child.isNullOrEmpty()

    private fun formatNameByLength(max: Int): String {
        val str = name ?: ""
        val sb = StringBuilder()
        var index = 0

        for (char in str) {
            if (index >= max) {
                sb.append("...")
                break
            }
            when {
                char.isLetter() -> {
                    sb.append(char)
                    if (char.isLowerCase() || char.isUpperCase()) {
                        index++
                    } else {
                        index += 2
                    }
                }
                char.isDigit() -> {
                    sb.append(char)
                    index++
                }
                else->{
                    sb.append(char)
                    index++
                }
            }
        }

        return sb.toString()
    }

    fun formatLongName(): String {
        return formatNameByLength(26)
    }

    fun formatShortName(): String {
        return formatNameByLength(10)
    }
}
