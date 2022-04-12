package com.neusoft.listenertest.viewmodel;

import static com.neusoft.listenertest.model.FavoriteConstants.OVER_MAX_COUNT;

import com.neusoft.listenertest.model.FavoriteConstants;
import com.neusoft.listenertest.viewmodel.listener.OnGetDataListener;
import com.neusoft.listenertest.viewmodel.manager.FavoriteManager;

import java.util.List;

/**
 * @author : YangHaoYi on 2022/3/10.
 * Email  :  yanghaoyi@neusoft.com
 * Description :
 * Change : YangHaoYi on 2022/3/10.
 * Version : V 1.0
 */
public class FavoriteProvider {

    private String favoriteInfo;
    private OnGetDataListener<String> addFavoriteListener;
    private OnGetDataListener<List<String>> queryByAddListener = new OnGetDataListener<List<String>>() {
        @Override
        public void success(List<String> response) {
            if (null != response && response.size() < 5) {
                if (null != addFavoriteListener) {
                    FavoriteManager.getInstance().addFavorite(favoriteInfo,addFavoriteListener);
                }
            }else {
                if (null != addFavoriteListener) {
                    addFavoriteListener.fail(OVER_MAX_COUNT, null);
                }
            }
        }

        @Override
        public void fail(int code, List<String> response) {

        }
    };

    public void queryFavorite(OnGetDataListener<List<String>> queryFavoriteListener){
        FavoriteManager.getInstance().queryFavorite(queryFavoriteListener);
    }

    /**
     * 执行关键字检索
     * @param favoriteInfo 收藏信息
     * @param addFavoriteListener 添加收藏页面监听
     * **/
    public void addFavorite(String favoriteInfo,OnGetDataListener<String> addFavoriteListener){
        this.favoriteInfo = favoriteInfo;
        this.addFavoriteListener = addFavoriteListener;
        FavoriteManager.getInstance().queryFavorite(queryByAddListener);
    }

    public void unRegisterListener(@FavoriteConstants.FavoriteListenerType int type){
        FavoriteManager.getInstance().removeQueryListener(queryByAddListener);
        queryByAddListener = null;
        FavoriteManager.getInstance().unRegisterListener(type);
        addFavoriteListener = null;
    }

    public static FavoriteProvider getInstance(){
        return InstanceHolder.INSTANCE;
    }
    public static final class InstanceHolder{
        public static final FavoriteProvider INSTANCE = new FavoriteProvider();
    }
}
