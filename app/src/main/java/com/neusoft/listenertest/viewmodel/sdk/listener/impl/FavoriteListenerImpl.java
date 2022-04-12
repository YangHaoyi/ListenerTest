package com.neusoft.listenertest.viewmodel.sdk.listener.impl;

import com.neusoft.listenertest.viewmodel.sdk.listener.IFavoriteListener;
import com.neusoft.listenertest.viewmodel.listener.OnGetDataListener;

import java.util.List;

/**
 * @author : YangHaoYi on 2022/3/10.
 * Email  :  yanghaoyi@neusoft.com
 * Description :
 * Change : YangHaoYi on 2022/3/10.
 * Version : V 1.0
 */
public class FavoriteListenerImpl implements IFavoriteListener {

    private OnGetDataListener<String> keywordsListener;
    private OnGetDataListener<String> aroundSearchListener;


    public void onKeywordSearchResultUpdate(String mockText) {
        if (null != keywordsListener) {
            keywordsListener.success(mockText);
        }
    }


    public void onAroundSearchResultUpdate(String mockText) {
        if (null != aroundSearchListener) {
            aroundSearchListener.success(mockText);
        }
    }


    public void setKeywordsListener(OnGetDataListener<String> keywordsListener) {
        this.keywordsListener = keywordsListener;
    }

    public void setAroundSearchListener(OnGetDataListener<String> aroundSearchListener) {
        this.aroundSearchListener = aroundSearchListener;
    }

    @Override
    public void onAddFavoritePoiInfoResult(int errorCode, String cid) {

    }

    @Override
    public void onQueryResult(int errorCode, List<String> result) {

    }
}
