package com.neusoft.listenertest.viewmodel;

import androidx.lifecycle.ViewModel;

import com.neusoft.listenertest.model.SingleLiveEvent;
import com.neusoft.listenertest.viewmodel.listener.OnGetDataListener;
import com.neusoft.listenertest.viewmodel.manager.FavoriteManager;

import java.util.List;

/**
 * @author : YangHaoYi on 2022/3/11.
 * Email  :  yanghaoyi@neusoft.com
 * Description :
 * Change : YangHaoYi on 2022/3/11.
 * Version : V 1.0
 */
public class FavoriteViewModel extends ViewModel {

    private SingleLiveEvent<List<String>> favoriteList = new SingleLiveEvent<>();
    private SingleLiveEvent<String> addCid = new SingleLiveEvent<>();
    private OnGetDataListener<List<String>> queryListener= new OnGetDataListener<List<String>>() {
        @Override
        public void success(List<String> response) {
            favoriteList.postValue(response);
        }

        @Override
        public void fail(int code, List<String> response) {

        }
    };


    public void queryFavorite(){
        FavoriteManager.getInstance().queryFavorite(queryListener);
    }

    public void addFavorite(){
        FavoriteProvider.getInstance().addFavorite("111", new OnGetDataListener<String>() {
            @Override
            public void success(String response) {
                addCid.postValue(response);
            }

            @Override
            public void fail(int code, String response) {

            }
        });
    }


    public void removeQueryListener(){
        FavoriteManager.getInstance().removeQueryListener(queryListener);
    }


    public SingleLiveEvent<List<String>> getFavoriteList() {
        return favoriteList;
    }

    public SingleLiveEvent<String> getAddCid() {
        return addCid;
    }
}
