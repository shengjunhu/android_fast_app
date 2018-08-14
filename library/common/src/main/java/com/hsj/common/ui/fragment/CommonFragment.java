package com.hsj.common.ui.fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/5/27 14:52
 * @Class:CommonFragment
 * @Description:普通Fragment、数据本地初始化
 */
public abstract class CommonFragment extends Fragment {

    public final String TAG = this.getClass().getSimpleName();

    /**
     * 弹出Toast
     *
     * @param message
     */
    public void showToast(@NonNull String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

}
