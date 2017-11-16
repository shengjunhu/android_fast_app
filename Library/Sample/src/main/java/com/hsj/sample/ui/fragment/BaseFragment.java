package com.hsj.sample.ui.fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * @Company:南京荣之誉信息科技有限公司
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/11/16/10:35
 * @Version:XBS V2.0
 * @Class:BaseFragment
 * @Description:
 */
public class BaseFragment extends Fragment {

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
