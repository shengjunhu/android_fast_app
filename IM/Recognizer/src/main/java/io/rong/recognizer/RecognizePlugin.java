package io.rong.recognizer;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.text.Editable;

import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imkit.utilities.PermissionCheckUtil;

public class RecognizePlugin implements IPluginModule {

    @Override
    public Drawable obtainDrawable(Context context) {
        return context.getResources().getDrawable(R.drawable.rc_recognizer_voice_selector);
    }

    @Override
    public String obtainTitle(Context context) {
        return context.getString(R.string.rc_plugin_recognize);
    }

    @Override
    public void onClick(Fragment currentFragment, final RongExtension extension) {
        String[] permissions = {Manifest.permission.RECORD_AUDIO};
        if (!PermissionCheckUtil.requestPermissions(currentFragment, permissions)) {
            return;
        }
        Recognizer recognizerView = new Recognizer(extension.getContext());
        recognizerView.setResultCallBack(new IRecognizedResult() {
            @Override
            public void onResult(String data) {
                Editable editable = extension.getInputEditText().getText();
                String str = editable.toString() + data;
                extension.getInputEditText().setText(str);
                extension.getInputEditText().setSelection(str.length());
            }

            @Override
            public void onClearClick() {
                extension.getInputEditText().setText("");
            }
        });
        extension.addPluginPager(recognizerView);
        recognizerView.startRecognize();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
