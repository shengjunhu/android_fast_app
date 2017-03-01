package com.hsj.basetool.helper;

import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptHelper {

    /**
     * 字节数组转16进制字符串
     *
     * @param b
     * @return
     */
    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    /**
     * 字节转16进制字符串
     *
     * @param b
     * @return
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * MD5编码
     *
     * @param origin
     * @param charsetname-->UTF-8
     * @return
     * @throws Exception
     */
    public static String MD5Encode(String origin, String charsetname) throws Exception {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception exception) {
            throw new Exception(exception);
        }
        return resultString;
    }

    /**
     * AES加密
     *
     * @param sSrc
     * @param sKey
     * @return
     * @throws Exception
     */
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        sKey = MD5Encode(sKey, "utf8").substring(8, 8 + 16);
        if (sKey == null) {
            return null;
        }
        if (sKey.length() != 16) {
            return null;
        }
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        String sKeyr = new StringBuffer(sKey).reverse().toString();
        IvParameterSpec iv = new IvParameterSpec(sKeyr.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());
        return Base64.encodeToString(encrypted, Base64.URL_SAFE);
    }

    /**
     * AES解密
     *
     * @param sSrc
     * @param sKey
     * @return
     * @throws Exception
     */
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            sKey = MD5Encode(sKey, "utf8").substring(8, 8 + 16);
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            String sKeyr = new StringBuffer(sKey).reverse().toString();
            IvParameterSpec iv = new IvParameterSpec(sKeyr.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.decode(sSrc, Base64.URL_SAFE);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    /**
     * Base64加密
     *
     * @param str
     * @return
     * @throws IOException
     */
    public static String Base(String str) throws IOException {
        return Base64.encodeToString(str.getBytes(),Base64.DEFAULT);
    }

    /**
     * 自定义加密
     *
     * @param text
     * @param key
     * @return
     * @throws Exception
     */
    public static String sign(String text, String key)throws Exception {
        text += key;
        return MD5Encode(text,"UTF-8");
    }

    /**
     * 获取文件MD5加密值
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static String getMd5ByFile(File file) throws FileNotFoundException {
        String value = null;
        FileInputStream in = new FileInputStream(file);
        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    /**
     * 16进制码
     */
    private static final String hexDigits[] = {
            "0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f" };

}
