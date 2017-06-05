package cn.rongcloud.contactcard.message;

import android.os.Parcel;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.rong.common.ParcelUtils;
import io.rong.common.RLog;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;

/**
 * Created by Beyond on 2016/12/5.
 */

@MessageTag(value = "RC:CardMsg", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class ContactMessage extends MessageContent {
    private static final String TAG = "ContactMessage";

    private String id;
    private String name;
    private String imgUrl;

    public ContactMessage() {

    }

    public ContactMessage (String id, String name, String imgUrl) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public static ContactMessage obtain(String id, String title, String imgUrl) { // 应考虑增加昵称的设置
        return new ContactMessage(id, title, imgUrl);
    }

    public static final Creator<ContactMessage> CREATOR = new Creator<ContactMessage>() {
        @Override
        public ContactMessage createFromParcel(Parcel source) {
            return new ContactMessage(source);
        }

        @Override
        public ContactMessage[] newArray(int size) {
            return new ContactMessage[size];
        }
    };

    @Override
    public byte[] encode() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("userId", getId()); // 这里的id（联系人）不同于下边userinfo中的id(发送信息者)
            jsonObject.put("name", getEmotion(getName()));
            jsonObject.put("portraitUri", getImgUrl());
            if (getJSONUserInfo() != null)
                jsonObject.putOpt("user", getJSONUserInfo());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        try {
            return jsonObject.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ContactMessage(byte[] data) {
        String jsonStr = null;
        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);

            if (jsonObj.has("userId"))
                setId(jsonObj.optString("userId"));
            if (jsonObj.has("name"))
                setName(jsonObj.optString("name"));
            if (jsonObj.has("portraitUri"))
                setImgUrl(jsonObj.optString("portraitUri"));
            if (jsonObj.has("user"))
                setUserInfo(parseJsonToUserInfo(jsonObj.getJSONObject("user")));
        } catch (JSONException e) {
            RLog.e(TAG, "JSONException " + e.getMessage());
        }
    }

    public ContactMessage(Parcel in) {
        id = in.readString();
        name = in.readString();
        imgUrl = in.readString();
        setUserInfo(ParcelUtils.readFromParcel(in, UserInfo.class));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(imgUrl);
        ParcelUtils.writeToParcel(dest, getUserInfo());
    }

    private String getEmotion(String content) {

        Pattern pattern = Pattern.compile("\\[/u([0-9A-Fa-f]+)\\]");
        Matcher matcher = pattern.matcher(content);

        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            int inthex = Integer.parseInt(matcher.group(1), 16);
            matcher.appendReplacement(sb, String.valueOf(Character.toChars(inthex)));
        }

        matcher.appendTail(sb);

        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
