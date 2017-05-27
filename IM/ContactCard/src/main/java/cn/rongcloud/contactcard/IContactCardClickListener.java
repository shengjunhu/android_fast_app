package cn.rongcloud.contactcard;

import android.view.View;

import cn.rongcloud.contactcard.message.ContactMessage;
import io.rong.imkit.model.UIMessage;

/**
 * Created by Beyond on 05/01/2017.
 */

public interface IContactCardClickListener {
    void onContactCardClick(View view, ContactMessage content);
}
