package com.neusoft.listenertest.viewmodel.sdk;

import com.neusoft.listenertest.viewmodel.sdk.listener.IFavoriteListener;
import com.neusoft.listenertest.viewmodel.sdk.listener.impl.FavoriteListenerImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : YangHaoYi on 2022/3/10.
 * Email  :  yanghaoyi@neusoft.com
 * Description :
 * Change : YangHaoYi on 2022/3/10.
 * Version : V 1.0
 */
public class SdkManager {

    /** SDK 收藏监听 常驻  **/
    private IFavoriteListener favoriteListener;

    public void registerFavoriteListener(IFavoriteListener favoriteListener){
        this.favoriteListener = favoriteListener;
    }

    public void addFavorite(String favoriteInfo){

    }

    public void queryFavorite(){

    }

    public void mockAddResult(String mockText){
        favoriteListener.onAddFavoritePoiInfoResult(0,mockText);
    }

    public void mockQueryResult(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        favoriteListener.onQueryResult(0,list);
    }

    public void unRegisterListener(){
        this.favoriteListener = null;
    }

    public static SdkManager getInstance(){
        return InstanceHolder.INSTANCE;
    }
    public static final class InstanceHolder{
        public static final SdkManager INSTANCE = new SdkManager();
    }
}
