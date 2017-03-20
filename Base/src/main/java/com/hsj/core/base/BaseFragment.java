package com.hsj.core.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Company     :  北京****科技有限公司
 * @Author      :  HSJ
 * @Version     :  Framework V1.0
 * @Date        :  2017/2/21 12:54
 * @E-mail      :  mr.ajun@foxmail.com
 * @Class       :  BaseFragment
 * @Description :  项目Fragment基类
 */
public class BaseFragment extends Fragment {

    private final String TAG = "BaseFragment";

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
