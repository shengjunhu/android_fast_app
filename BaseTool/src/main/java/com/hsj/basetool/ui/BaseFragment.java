package com.hsj.basetool.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Company: 南京荣之誉信息科技有限公司
 * @Author: HSJ
 * @E-mail: mr.ajun@foxmail.com
 * @Date: 2017/2/24 10:57
 * @Version: XBS V2.0
 * @Class: BaseFragment
 * @Description:
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
