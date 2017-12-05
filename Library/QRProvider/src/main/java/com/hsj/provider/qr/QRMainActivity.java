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

package com.hsj.provider.qr;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hsj.base.ui.BaseActivity;

public class QRMainActivity extends BaseActivity {

    private ImageView iv_qr;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qr_main;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        findView(R.id.btn_scan_qr).setOnClickListener(this);
        findView(R.id.btn_create_qr).setOnClickListener(this);
        iv_qr = findView(R.id.iv_qr);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_scan_qr:

                break;
            case R.id.btn_create_qr:
                createQrCode();
                break;
            default:
                break;
        }
    }

    /**
     * 创建二维码
     */
    private void createQrCode() {
        String qrText = "创建二维码";
        //int qrImageId = R.drawable.ic_launcher;
    }

}
