package com.neusoft.listenertest.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.neusoft.listenertest.R;
import com.neusoft.listenertest.model.FavoriteConstants;
import com.neusoft.listenertest.viewmodel.FavoriteViewModel;
import com.neusoft.listenertest.viewmodel.listener.OnGetDataListener;
import com.neusoft.listenertest.viewmodel.manager.FavoriteManager;
import com.neusoft.listenertest.viewmodel.FavoriteProvider;
import com.neusoft.listenertest.viewmodel.sdk.SdkManager;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvMessage;
    private TextView tvQueryMessage;
    private EditText edMockText;
    private TextView tvAddListener;
    private TextView tvGetFavorite;
    private TextView tvRemoveListener;
    private TextView tvRemoveQueryListener;
    private TextView tvSendGetEvent;
    private TextView tvSendAddEvent;

    private FavoriteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initView();
        initObserve();
        initEvent();
    }

    private void initView() {
        tvMessage = findViewById(R.id.tvMessage);
        tvQueryMessage = findViewById(R.id.tvQueryMessage);
        edMockText = findViewById(R.id.edMockText);
        tvAddListener = findViewById(R.id.tvAddListener);
        tvGetFavorite = findViewById(R.id.tvGetFavorite);
        tvRemoveListener = findViewById(R.id.tvRemoveListener);
        tvSendGetEvent = findViewById(R.id.tvSendGetEvent);
        tvSendAddEvent = findViewById(R.id.tvSendAddEvent);
        tvRemoveQueryListener = findViewById(R.id.tvRemoveQueryListener);
    }


    private void initObserve(){
        viewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        viewModel.getFavoriteList().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                tvQueryMessage.setText(strings.get(0));
            }
        });
        viewModel.getAddCid().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvMessage.setText(s);
            }
        });
    }


    private void initEvent() {
        tvAddListener.setOnClickListener(this);
        tvRemoveListener.setOnClickListener(this);
        tvSendGetEvent.setOnClickListener(this);
        tvSendAddEvent.setOnClickListener(this);
        tvGetFavorite.setOnClickListener(this);
        tvRemoveQueryListener.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvAddListener:
                viewModel.addFavorite();
                break;
            case R.id.tvGetFavorite:
                viewModel.queryFavorite();
                break;
            case R.id.tvSendGetEvent:
                SdkManager.getInstance().mockQueryResult();
                break;
            case R.id.tvSendAddEvent:
                if (!TextUtils.isEmpty(edMockText.getText().toString())) {
                    SdkManager.getInstance().mockAddResult(edMockText.getText().toString());
                }
                break;
            case R.id.tvRemoveListener:
                FavoriteProvider.getInstance().unRegisterListener(FavoriteConstants.FavoriteListenerType.ADD);
                break;
            case R.id.tvRemoveQueryListener:
                viewModel.removeQueryListener();
                break;
            default:
                break;
        }
    }
}