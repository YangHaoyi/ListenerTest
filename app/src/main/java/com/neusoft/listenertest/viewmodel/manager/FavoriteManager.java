package com.neusoft.listenertest.viewmodel.manager;

import com.neusoft.listenertest.model.FavoriteConstants;
import com.neusoft.listenertest.viewmodel.listener.OnGetDataListener;
import com.neusoft.listenertest.viewmodel.sdk.SdkManager;
import com.neusoft.listenertest.viewmodel.sdk.listener.IFavoriteListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : YangHaoYi on 2022/3/10.
 * Email  :  yanghaoyi@neusoft.com
 * Description :test
 * Change : YangHaoYi on 2022/3/10.
 * Version : V 1.0
 */
public class FavoriteManager {

    /** 添加 收藏点回调监听 **/
    private OnGetDataListener<String> addFavoriteListener;
    /** 查询 收藏点回调监听 **/
    private List<OnGetDataListener<List<String>>> queryFavoriteList = new ArrayList<>();

    private FavoriteManager() {
        initListener();
    }

    /** 初始化SDK监听 **/
    private void initListener(){
        SdkManager.getInstance().registerFavoriteListener(new IFavoriteListener() {
            @Override
            public void onAddFavoritePoiInfoResult(int errorCode, String cid) {
                if (null != addFavoriteListener) {
                    addFavoriteListener.success(cid);
                }
            }

            @Override
            public void onQueryResult(int errorCode, List<String> result) {
                for (OnGetDataListener<List<String>> listener:queryFavoriteList){
                    listener.success(result);
                }
            }
        });
    }

    /**
     * 添加收藏点
     * @param favoriteInfo 收藏信息
     * @param addFavoriteListener 添加收藏点 页面监听
     *  **/
    public void addFavorite(String favoriteInfo,OnGetDataListener<String> addFavoriteListener){
        this.addFavoriteListener = addFavoriteListener;
        SdkManager.getInstance().addFavorite(favoriteInfo);
    }

    /**
     * 查询收藏点
     * @param queryFavoriteListener 查询收藏点 页面监听
     *  **/
    public void queryFavorite(OnGetDataListener<List<String>> queryFavoriteListener){
        queryFavoriteList.add(queryFavoriteListener);
        SdkManager.getInstance().queryFavorite();
    }

    public void removeQueryListener(OnGetDataListener<List<String>> listener){
        queryFavoriteList.remove(listener);
    }

    public void unRegisterListener(@FavoriteConstants.FavoriteListenerType int type){
        //打断页面与FavoriteManager单例的联系
        switch (type){
            case FavoriteConstants.FavoriteListenerType.ADD:
                addFavoriteListener = null;
                break;
            default:
                break;
        }
    }

    public static FavoriteManager getInstance(){
        return InstanceHolder.INSTANCE;
    }

    public static final class InstanceHolder{
        public static final FavoriteManager INSTANCE = new FavoriteManager();
    }
}
