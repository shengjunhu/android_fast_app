package com.hsj.common.ui.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/5/27 14:52
 * @Class:BaseLoadFragment
 * @Description:Fragment数据加载：
 * 1、加载中、2、加载成功、3、加载失败、4、网络故障
 */
public abstract class BaseLoadFragment extends CommonFragment {

    private boolean isVisible = false; //当前Fragment是否可见
    private boolean isInitView = false; //是否与View建立起映射关系
    private boolean isFirstLoad = true;  //是否是第一次加载数据
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {

            rootView = inflater.inflate(getLayoutId(), container, false);

            initUI(savedInstanceState);

            isInitView = true;
            loadData();
        }
        return rootView;
    }

    protected abstract int getLayoutId();

    protected abstract void initUI(Bundle savedInstanceState);

    protected abstract void initData();

    private void loadData() {
        if (!isFirstLoad || !isVisible || !isInitView) { // 不加载数据
            return;
        }
        initData();
        isFirstLoad = false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            isVisible = true;
            loadData();
        } else {
            isVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    /**
     * 查找View
     *
     * @param id  控件的id
     * @param <V> View类型
     * @return
     */
    protected <V extends View> V findView(@IdRes int id) {
        return  rootView.findViewById(id);
    }

}
