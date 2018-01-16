/*******************************************************************************
 * Copyright (c) 2017.   ShengJunHu
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 ******************************************************************************/

package com.hsj.base.ui.fragment;

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
 * @Date:2017/7/25 12:50
 * @Class:BaseWebFragment
 * @Description:web加载基类
 */
public abstract class BaseWebFragment extends Fragment implements CancellationSignal.OnCancelListener {

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
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 刷新数据
     * @param isRefresh
     */
    protected void refreshData(boolean isRefresh){
        if(isRefresh)initData();
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

    /**
     * 停止加载对话框
     */
    protected void stopDialog(){

    }

}
