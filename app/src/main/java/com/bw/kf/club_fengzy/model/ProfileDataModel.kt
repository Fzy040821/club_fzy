package com.bw.kf.club_fengzy.model



enum class MotoCircleNavigationEnum(val navId: Long , val sort: Int , val title: String , val type: Int){
    HOT(10001 , 0 , "热门" , type = 1),
    TOPIC(10002 , 1 , "帖子" , type = 2),
    INFORMATION(10003 , 2 , "资讯" , type = 3),
    FOLLOW(10003 , 3 , "关注" , type = 4),
}

data class MotoCircleNavigationItem(
    val defaultStatus: Int? = null,
    val modelList: MutableList<ModelItem>? = null,
    val navigationId: String = "",
    var seriesId: String = "",
    val sort: Int,
    val status: Int,
    val title: String,
    val type: Int,
    var value: String,
)

data class ModelItem(
    var isSeries: Boolean = false,
    val modelId: String = "",
    val name: String = "-",
    var checked: Boolean = false,
    var last: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        return (other is ModelItem) && (this.modelId == other.modelId && this.name == other.name)
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + modelId.hashCode()
        return result
    }
}