package com.app.base.ui.fragment;

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
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/5/27 14:52
 * @Class:BaseLoadFragment
 * @Description:Fragment数据加载：
 * 1、加载中、2、加载成功、3、加载失败、4、网络故障
 */
public abstract class BaseLoadFragment extends Fragment implements View.OnClickListener{

    public String TAG = this.getClass().getSimpleName();

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
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
    }

    /**
     * 检测内存泄露
     */
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
     * 判断字符串不为 null和 ""
     * @param str
     * @return
     */
    protected boolean notNull(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * TextView、EditText设置文本
     * @param str
     * @return
     */
    protected String checkStr(String str) {
        if (notNull(str)) {
            return str;
        } else {
            return "";
        }
    }

    /**
     * TextView、EditText设置文本
     * @param num
     * @return
     */
    protected String checkStr(Number num) {
        if (num == null) {
            return "";
        } else {
            return String.valueOf(num);
        }
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
     * 启动加载提示
     */
    protected void startDialog(String hint){

    }

    /**
     * 停止加载提示
     */
    protected void stopDialog(){

    }

}
