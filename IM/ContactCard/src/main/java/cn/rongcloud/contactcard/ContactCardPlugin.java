package cn.rongcloud.contactcard;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import cn.rongcloud.contactcard.activities.ContactListActivity;
import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imlib.model.Conversation;

/**
 * Created by Beyond on 2016/11/14.
 */

public class ContactCardPlugin implements IPluginModule {

    private static final int REQUEST_CONTACT = 55;

    public ContactCardPlugin() {
    }

    @Override
    public Drawable obtainDrawable(Context context) {
        return ContextCompat.getDrawable(context, R.drawable.rc_contact_plugin_icon);
    }

    @Override
    public String obtainTitle(Context context) {
        return context.getString(R.string.rc_plugins_contact);
    }

    @Override
    public void onClick(Fragment currentFragment, RongExtension extension) {
        Conversation.ConversationType conversationType = extension.getConversationType();
        String targetId = extension.getTargetId();
        Intent intent = new Intent(currentFragment.getActivity(), ContactListActivity.class);
        intent.putExtra("conversationType", conversationType);
        intent.putExtra("targetId", targetId);
        extension.startActivityForPluginResult(intent, REQUEST_CONTACT, this);
        extension.collapseExtension();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
