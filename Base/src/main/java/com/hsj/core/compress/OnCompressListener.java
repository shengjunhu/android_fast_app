package com.hsj.core.compress;

import java.io.File;

public interface OnCompressListener {

    void onStart();

    void onSuccess(File file);

    void onError(String errorMsg);

}
