package com.neusoft.listenertest.model;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : YangHaoYi on 2022/1/21.
 * Email  :  yanghaoyi@neusoft.com
 * Description :
 * Change : YangHaoYi on 2022/1/21.
 * Version : V 1.0
 */
public class FavoriteConstants {

    public static final int OVER_MAX_COUNT = -1;

    @IntDef({ErrorCode.SUCCESS, ErrorCode.FAILED, ErrorCode.TIMEOUT, ErrorCode.UNKNOW, ErrorCode.EXCEPTION1, ErrorCode.EXCEPTION2, ErrorCode.ERRORNOTWORKWELL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorCode {
        /** 成功 **/
        int SUCCESS = 0;
        /** 失败 **/
        int FAILED = 1;
        /** 超时 **/
        int TIMEOUT = 2;
        /** 未知 **/
        int UNKNOW = 3;
        int EXCEPTION1 = 4;
        int EXCEPTION2 = 5;
        int ERRORNOTWORKWELL = 6;
    }


    @IntDef({FavoriteListenerType.ADD, FavoriteListenerType.QUERY, FavoriteListenerType.CLEAR, FavoriteListenerType.REMOVE, FavoriteListenerType.GET_SAVE_STATUS })
    @Retention(RetentionPolicy.SOURCE)
    public @interface FavoriteListenerType {
        /** 添加 **/
        int ADD = 0;
        /** 查询 **/
        int QUERY = 1;
        /** 清空 **/
        int CLEAR = 2;
        /** 移除 **/
        int REMOVE = 3;
        /** 获取保存状态 **/
        int GET_SAVE_STATUS = 4;
    }

}
