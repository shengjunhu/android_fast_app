/*
 *   Copyright (c) 2017.  HSJ
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.app.common.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2018/2/4/17:33
 * @Version:V1.0
 * @Class:IOUtils
 * @Description:文件操作类
 */
public class IOUtils {

    private IOUtils() {
    }

    /**
     * 缓冲字节
     */
    private static int BUFFER_SIZE = 8192;

    /**
     * 默认可用存储大小：200MB
     */
    private static final long DEFAULT_USABLE_SPACE = 200 * 1024 * 1024;

    /**
     * "/"符号
     */
    private static final String LINE_SEP = System.getProperty("line.separator");

    /**
     * 关闭 IO
     *
     * @param closeables closeables
     */
    public static void closeIO(Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

///////////////////////////////////////文件压缩//////////////////////////////////////////////////////

    /**
     * 压缩文件
     *
     * @param resFile  待压缩文件
     * @param rootPath 相对于压缩文件的路径
     * @param zos      压缩文件输出流
     * @param comment  压缩文件的注释
     * @return {@code true}: 压缩成功<br>{@code false}: 压缩失败
     * @throws IOException IO 错误时抛出
     */
    private static boolean zipFile(@NonNull File resFile,
                                   @NonNull String rootPath,
                                   @NonNull ZipOutputStream zos,
                                   @NonNull String comment) {
        rootPath = rootPath + (File.separator) + resFile.getName();
        if (resFile.isDirectory()) {
            File[] fileList = resFile.listFiles();
            if (fileList == null || fileList.length <= 0) {
                ZipEntry entry = new ZipEntry(rootPath + '/');
                entry.setComment(comment);
                try {
                    zos.putNextEntry(entry);
                    zos.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            } else {
                for (File file : fileList) {
                    if (!zipFile(file, rootPath, zos, comment)) return false;
                }
            }
        } else {
            InputStream is = null;
            try {
                is = new BufferedInputStream(new FileInputStream(resFile));
                ZipEntry entry = new ZipEntry(rootPath);
                entry.setComment(comment);
                zos.putNextEntry(entry);
                byte buffer[] = new byte[BUFFER_SIZE];
                int len;
                while ((len = is.read(buffer, 0, BUFFER_SIZE)) != -1) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                closeIO(is);
            }
        }
        return true;
    }

    /**
     * 批量压缩文件
     *
     * @param resFiles 待压缩文件集合
     * @param zipFile  压缩文件
     * @param comment  压缩文件的注释
     * @return {@code true}: 压缩成功<br>{@code false}: 压缩失败
     * @throws IOException IO错误时抛出
     */
    public static boolean zipFiles(@NonNull Collection<File> resFiles,
                                   @NonNull File zipFile,
                                   @NonNull String comment) {
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new FileOutputStream(zipFile));
            for (File resFile : resFiles) {
                if (!zipFile(resFile, "", zos, comment)) return false;
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            if (zos != null) {
                try {
                    zos.finish();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                closeIO(zos);
            }
            return false;
        }
    }

    /**
     * 解压带有关键字的文件
     *
     * @param zipFile 待解压文件
     * @param destDir 目标目录
     * @return 返回带有关键字的文件链表
     * @throws IOException IO 错误时抛出
     */
    public static List<File> unzipFile(@NonNull File zipFile, @NonNull File destDir) {
        List<File> files = new ArrayList<>();
        try {
            ZipFile zf = new ZipFile(zipFile);
            Enumeration<?> entries = zf.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();
                if (!unzipChildFile(destDir, files, zf, entry, entryName)) {
                    return files;
                }
            }
            return files;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检测是否有子文件要解压
     *
     * @param destDir
     * @param files
     * @param zf
     * @param entry
     * @param entryName
     * @return
     * @throws IOException
     */
    private static boolean unzipChildFile(File destDir,
                                          List<File> files,
                                          ZipFile zf,
                                          ZipEntry entry,
                                          String entryName) throws IOException {
        String filePath = destDir + File.separator + entryName;
        File file = new File(filePath);
        files.add(file);
        if (entry.isDirectory()) {
            if (!file.exists()) return false;
        } else {
            if (!file.exists()) return false;
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new BufferedInputStream(zf.getInputStream(entry));
                out = new BufferedOutputStream(new FileOutputStream(file));
                byte buffer[] = new byte[BUFFER_SIZE];
                int len;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
            } finally {
                closeIO(in, out);
            }
        }
        return true;
    }

///////////////////////////文件与字符串、字符数组、流之间相互操作//////////////////////////////////////////

    /**
     * 将输入流写入文件
     *
     * @param file   文件
     * @param is     输入流
     * @param append 是否追加在文件末
     * @return {@code true}: 写入成功<br>{@code false}: 写入失败
     */
    public static boolean writeFileFromIS(@NonNull File file,
                                          @NonNull InputStream is,
                                          boolean append) {
        if (!file.exists() || file.isDirectory()) return false;
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file, append));
            byte data[] = new byte[8192];
            int len;
            while ((len = is.read(data, 0, 8192)) != -1) {
                os.write(data, 0, len);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeIO(is, os);
        }
    }

    /**
     * 将字节数组写入文件
     *
     * @param file   文件
     * @param bytes  字节数组
     * @param append 是否追加在文件末
     * @return {@code true}: 写入成功<br>{@code false}: 写入失败
     */
    public static boolean writeFileFromBytesByStream(@NonNull File file,
                                                     @NonNull byte[] bytes,
                                                     boolean append) {
        if (!file.exists() || file.isDirectory()) return false;
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file, append));
            bos.write(bytes);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeIO(bos);
        }
    }

    /**
     * 将字节数组写入文件
     *
     * @param file    文件
     * @param bytes   字节数组
     * @param append  是否追加在文件末
     * @param isForce 是否写入文件
     * @return {@code true}: 写入成功<br>{@code false}: 写入失败
     */
    public static boolean writeFileFromBytesByChannel(@NonNull File file,
                                                      @NonNull byte[] bytes,
                                                      boolean append,
                                                      boolean isForce) {
        FileChannel fc = null;
        try {
            fc = new FileOutputStream(file, append).getChannel();
            fc.position(fc.size());
            fc.write(ByteBuffer.wrap(bytes));
            if (isForce) fc.force(true);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeIO(fc);
        }
    }

    /**
     * 将字符串写入文件
     *
     * @param file    文件
     * @param content 写入内容
     * @param append  是否追加在文件末
     * @return {@code true}: 写入成功<br>{@code false}: 写入失败
     */
    public static boolean writeFileFromString(@NonNull File file,
                                              @NonNull String content,
                                              boolean append) {
        if (!file.exists()) return false;
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file, append));
            bw.write(content);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeIO(bw);
        }
    }

    /**
     * 读取文件到字符串中
     *
     * @param file        文件
     * @param charsetName 编码格式
     * @return 字符串
     */
    public static String readFile2String(@NonNull File file, String charsetName) {
        if (!file.exists() || file.isDirectory()) return null;
        BufferedReader reader = null;
        try {
            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "utf-8"));

            String line;
            if ((line = reader.readLine()) != null) {
                sb.append(line);
                while ((line = reader.readLine()) != null) {
                    sb.append(LINE_SEP).append(line);
                }
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeIO(reader);
        }
    }

    /**
     * 读取文件到字节数组中
     *
     * @param file 文件
     * @return 字符数组
     */
    public static byte[] readFile2BytesByMap(@NonNull File file) {
        if (!file.exists() || file.isDirectory()) return null;
        FileChannel fc = null;
        try {
            fc = new RandomAccessFile(file, "r").getChannel();
            int size = (int) fc.size();
            MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, size).load();
            byte[] result = new byte[size];
            mbb.get(result, 0, size);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeIO(fc);
        }
    }

///////////////////////////图片与字符串、字符数组、流之间相互操作//////////////////////////////////////////

    /**
     * bitmap 转 byteArr
     *
     * @param bitmap bitmap 对象
     * @param format 格式
     * @return 字节数组
     */
    public static byte[] bitmap2Bytes(@NonNull Bitmap bitmap, Bitmap.CompressFormat format) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(format, 100, baos);
        return baos.toByteArray();
    }

    /**
     * byteArr 转 bitmap
     *
     * @param bytes 字节数组
     * @return bitmap
     */
    public static Bitmap bytes2Bitmap(@NonNull byte[] bytes) {
        return (bytes.length == 0) ? null : BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * drawable 转 bitmap
     *
     * @param drawable drawable 对象
     * @return bitmap
     */
    public static Bitmap drawable2Bitmap(@NonNull Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        Bitmap bitmap;
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1,
                    drawable.getOpacity() != PixelFormat.OPAQUE
                            ? Bitmap.Config.ARGB_8888
                            : Bitmap.Config.RGB_565);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(),
                    drawable.getOpacity() != PixelFormat.OPAQUE
                            ? Bitmap.Config.ARGB_8888
                            : Bitmap.Config.RGB_565);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * bitmap 转 drawable
     *
     * @param bitmap bitmap 对象
     * @return drawable
     */
    public static Drawable bitmap2Drawable(@NonNull Context context, @NonNull Bitmap bitmap) {
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    /**
     * drawable 转 byteArr
     *
     * @param drawable drawable 对象
     * @param format   格式
     * @return 字节数组
     */
    public static byte[] drawable2Bytes(@NonNull Drawable drawable, @NonNull Bitmap.CompressFormat format) {
        return bitmap2Bytes(drawable2Bitmap(drawable), format);
    }

    /**
     * view 转 bitmap
     *
     * @param view 视图
     * @return bitmap
     */
    public static Bitmap view2Bitmap(@NonNull View view) {
        Bitmap ret = Bitmap.createBitmap(view.getWidth(),
                view.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(ret);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return ret;
    }

    /**
     * 获取 bitmap
     *
     * @param file 文件
     * @return bitmap
     */
    public static Bitmap getBitmap(@NonNull File file) {
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }


    /**
     * 获取 bitmap
     *
     * @param is 输入流
     * @return bitmap
     */
    public static Bitmap getBitmap(@NonNull InputStream is) {
        return BitmapFactory.decodeStream(is);
    }

    /**
     * 获取 bitmap
     *
     * @param data   数据
     * @param offset 偏移量
     * @return bitmap
     */
    public static Bitmap getBitmap(@NonNull byte[] data, int offset) {
        if (data.length == 0) return null;
        return BitmapFactory.decodeByteArray(data, offset, data.length);
    }

    /**
     * 获取 bitmap
     *
     * @param resId 资源 id
     * @return bitmap
     */
    public static Bitmap getBitmap(@NonNull Context context, @DrawableRes  int resId) {
        Drawable drawable = ContextCompat.getDrawable(context, resId);
        if (drawable == null) {
            return null;
        } else {
            Canvas canvas = new Canvas();
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(),
                    Bitmap.Config.ARGB_8888);
            canvas.setBitmap(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return bitmap;
        }
    }

}
