#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_loader_image_hsj_com_image_loader_ImageLoaderMainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
