package com.bw.kf.club_fengzy.ui.base.state;

/**
 * @项目名称 UILoader
 * @类名 UIState
 * @创建时间 2021/12/11 15:16
 * @作者 钟阳
 * @描述 UI状态
 * <p>
 * NONE 空状态
 * LOADING 加载中
 * NETWORK_ERROR 网络错误
 * EMPTY 空数据
 * SUCCESS 成功状态
 */
public enum UIState {

    NONE, LOADING, NETWORK_ERROR, EMPTY, SUCCESS, REFRESH,
    // 2024年01月14日 - 未登录
    UN_LOGIN,
    // 2024年01月14日 - 关注人员为空
    NO_FOLLOW
}
