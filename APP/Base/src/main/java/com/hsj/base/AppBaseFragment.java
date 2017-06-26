package com.hsj.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 14:52
 * @Class:AppBaseFragment
 * @Description:懒加载Fragment
 */
public abstract class AppBaseFragment extends Fragment implements View.OnClickListener{

    public String TAG = this.getClass().getSimpleName();

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        if(rootView == null){

            rootView = inflater.inflate(getLayoutId(), container, false);

            initUI();

            initToolBar();

            initData();
        }
        return rootView;
    }

    protected abstract int getLayoutId();

    protected abstract void initUI();

    protected abstract void initToolbar(Toolbar toolbar,TextView tv_left,TextView tv_center,TextView tv_right);

    protected abstract void initData();

    /**
     * 初始化Toolbar
     */
    protected void initToolBar() {
        Toolbar toolbar = findView(R.id.tb_base);
        TextView tv_left = findView(R.id.tv_left);
        TextView tv_center = findView(R.id.tv_center);
        EditText et_center = findView(R.id.et_center);
        TextView tv_right = findView(R.id.tv_right);

        initToolbar(toolbar,tv_left, tv_center,tv_right);
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

    protected void startDialog(){

    }

    protected void finishDialog(){

    }

    protected void startProgressBar(){

    }

    protected void finishProgressBar(){

    }

}
