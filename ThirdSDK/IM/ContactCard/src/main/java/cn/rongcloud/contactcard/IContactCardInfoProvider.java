package cn.rongcloud.contactcard;

import java.util.List;

import io.rong.imlib.model.UserInfo;

/**
 * Created by Beyond on 29/12/2016.
 */

public interface IContactCardInfoProvider {

    interface IContactCardInfoCallback {
        void getContactCardInfoCallback(List<? extends UserInfo> list);
    }

    void getContactCardInfoProvider(IContactCardInfoCallback contactInfoCallback);

}
