package com.neusoft.listenertest.viewmodel.listener;

/**
 * @author : YangHaoYi on 2021/11/8.
 * Email  :  yanghaoyi@neusoft.com
 * Description :test
 * Change : YangHaoYi on 2021/11/8.
 * Version : V 1.0
 */
public interface OnGetDataListener<T> {

    /**
     * 获取数据成功
     * @param response 请求数据
     *  **/
    void success(T response);

    /**
     * 获取数据失败
     * @param code 错误码
     * @param response 请求数据
     *  **/
    void fail(int code, T response);
}
