package com.neusoft.listenertest.viewmodel.sdk.listener;

import java.util.List;

/**
 * @author : YangHaoYi on 2022/3/10.
 * Email  :  yanghaoyi@neusoft.com
 * Description :
 * Change : YangHaoYi on 2022/3/10.
 * Version : V 1.0
 */
public interface IFavoriteListener {

    void onAddFavoritePoiInfoResult(int errorCode, String cid);

    void onQueryResult(int errorCode, List<String> result);
}
