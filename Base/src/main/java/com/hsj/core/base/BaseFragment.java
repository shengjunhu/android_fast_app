package com.hsj.core.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * @Company:北京****科技有限公司
 * @Author:HSJ
 * @Version:FastAndroid V1.0
 * @Date:2017/2/21 12:54
 * @E-mail:mr.ajun@foxmail.com
 * @Class:BaseFragment
 * @Description:项目Fragment基类
 */
public abstract class BaseFragment extends Fragment {

    private String TAG = "BaseFragment";

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

    /**
     * Toast
     * @param msg
     */
    public void showToast(@NonNull String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 判断字符串为空
     *
     * @param str
     * @return false 为（null、""）
     */
    public boolean isEmpty(@Nullable String str){
        return TextUtils.isEmpty(str);
    }

    /**
     * 判断对象是否为null
     * @param obj
     * @return
     */
    public boolean isNull(@Nullable Object obj){
        return null == obj ? true:false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
