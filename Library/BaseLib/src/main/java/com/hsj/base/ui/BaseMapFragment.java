package com.hsj.base.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.os.CancellationSignal;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.hsj.base.core.BaseApp;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/08/06 22:24
 * @Class:BaseMapFragment
 * @Description:地图Fragment
 */
public abstract class BaseMapFragment extends Fragment implements CancellationSignal.OnCancelListener {

    public String TAG = this.getClass().getSimpleName();

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView == null){

            rootView = inflater.inflate(getLayoutId(), container, false);

            initUI(savedInstanceState);

            initData();
        }
        return rootView;
    }

    protected abstract int getLayoutId();

    protected abstract void initUI(Bundle savedInstanceState);

    protected abstract void initData();

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        System.gc();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseApp.getRefWatcher().watch(this);
    }

    /**
     * 查找View
     *
     * @param id  控件的id
     * @param <V> View类型
     * @return
     */
    protected <V extends View> V findView(@IdRes int id) {
        return (V) rootView.findViewById(id);
    }

    /**
     * 弹出Toast
     *
     * @param message
     */
    public void showToast(@NonNull String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * 加载提示对话框
     * @param hint
     */
    protected void startDialog(String hint){

    }

    protected void stopDialog(){

    }

}

