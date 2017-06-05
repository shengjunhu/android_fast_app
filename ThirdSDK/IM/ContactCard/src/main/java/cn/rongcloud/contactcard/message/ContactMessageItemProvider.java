package cn.rongcloud.contactcard.message;

import android.content.Context;
import android.content.res.Resources;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.rongcloud.contactcard.IContactCardClickListener;
import cn.rongcloud.contactcard.R;
import io.rong.common.RLog;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.emoticon.AndroidEmoji;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.utilities.OptionsPopupDialog;
import io.rong.imkit.widget.AsyncImageView;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;

/**
 * Created by Beyond on 2016/12/5.
 */

@ProviderTag(messageContent = ContactMessage.class, showProgress = false, showReadState = true, showSummaryWithName = false)
public class ContactMessageItemProvider extends IContainerItemProvider.MessageProvider<ContactMessage> {
    private final static String TAG = "ContactMessageItemProvider";
    private IContactCardClickListener iContactCardClickListener;

    public ContactMessageItemProvider(IContactCardClickListener iContactCardClickListener) {
        this.iContactCardClickListener = iContactCardClickListener;
    }

    private static class ViewHolder {
        AsyncImageView mImage;
        TextView mName;
        LinearLayout mLayout;
    }

    @Override
    public View newView(Context context, ViewGroup group) {
        View view = LayoutInflater.from(context).inflate(R.layout.rc_message_contact_card, null);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.mImage = (AsyncImageView) view.findViewById(R.id.rc_img);
        viewHolder.mName = (TextView) view.findViewById(R.id.rc_name);
        viewHolder.mLayout = (LinearLayout) view.findViewById(R.id.rc_layout);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View v, int position, ContactMessage content, UIMessage message) {
        ViewHolder viewHolder = (ViewHolder) v.getTag();
        if (!TextUtils.isEmpty(content.getImgUrl())) {
            viewHolder.mImage.setAvatar(content.getImgUrl(), R.drawable.rc_default_portrait);
        } else {
            if (RongUserInfoManager.getInstance().getUserInfo(content.getId()) != null)
                viewHolder.mImage.setAvatar(RongUserInfoManager.getInstance().getUserInfo(content.getId()).getPortraitUri());
        }

        SpannableStringBuilder spannable = new SpannableStringBuilder(content.getName());
        AndroidEmoji.ensure(spannable);
        viewHolder.mName.setText(spannable);

        if (message.getMessageDirection() == Message.MessageDirection.RECEIVE)
            viewHolder.mLayout.setBackgroundResource(R.drawable.rc_ic_bubble_left_file);
        else
            viewHolder.mLayout.setBackgroundResource(R.drawable.rc_ic_bubble_right_file);
    }

    @Override
    public Spannable getContentSummary(ContactMessage contactMessage) {
        if (contactMessage != null && contactMessage.getUserInfo() != null && contactMessage.getUserInfo().getUserId() != null && contactMessage.getName() != null) {
            if (contactMessage.getUserInfo().getUserId().equals(RongIM.getInstance().getCurrentUserId())) {
                String str_RecommendClause = RongContext.getInstance().getResources().getString(R.string.rc_recommend_clause_to_others);
                return new SpannableString(String.format(str_RecommendClause, contactMessage.getName()));
            } else {
                String str_RecommendClause = RongContext.getInstance().getResources().getString(R.string.rc_recommend_clause_to_me);
                return new SpannableString(String.format(str_RecommendClause, contactMessage.getUserInfo().getName(), contactMessage.getName()));
            }
        } else {
            return new SpannableString("[" + R.string.rc_plugins_contact + "]");
        }
    }

    @Override
    public void onItemClick(View view, int position, ContactMessage content, UIMessage message) {
        if (iContactCardClickListener != null) {
            iContactCardClickListener.onContactCardClick(view, content);
        }
    }

    @Override
    public void onItemLongClick(final View view, int position, ContactMessage content, final UIMessage message) {
        String[] items;

        long deltaTime = RongIM.getInstance().getDeltaTime();
        long normalTime = System.currentTimeMillis() - deltaTime;
        boolean enableMessageRecall = false;
        int messageRecallInterval = -1;

        try {
            enableMessageRecall = RongContext.getInstance().getResources().getBoolean(R.bool.rc_enable_message_recall);
            messageRecallInterval = RongContext.getInstance().getResources().getInteger(R.integer.rc_message_recall_interval);
        } catch (Resources.NotFoundException e) {
            RLog.e(TAG, "rc_message_recall_interval not configure in rc_config.xml");
            e.printStackTrace();
        }
        if (message.getSentStatus().equals(Message.SentStatus.SENDING) && message.getProgress() < 100) {
            return;
        }
        if (message.getSentStatus().equals(Message.SentStatus.SENT)
                && enableMessageRecall
                && (normalTime - message.getSentTime()) <= messageRecallInterval * 1000
                && message.getSenderUserId().equals(RongIM.getInstance().getCurrentUserId())
                && !message.getConversationType().equals(Conversation.ConversationType.CUSTOMER_SERVICE)
                && !message.getConversationType().equals(Conversation.ConversationType.APP_PUBLIC_SERVICE)
                && !message.getConversationType().equals(Conversation.ConversationType.PUBLIC_SERVICE)
                && !message.getConversationType().equals(Conversation.ConversationType.SYSTEM)
                && !message.getConversationType().equals(Conversation.ConversationType.CHATROOM)) {
            items = new String[]{view.getContext().getResources().getString(R.string.rc_dialog_item_message_delete), view.getContext().getResources().getString(R.string.rc_dialog_item_message_recall)};
        } else {
            items = new String[]{view.getContext().getResources().getString(R.string.rc_dialog_item_message_delete)};
        }

        OptionsPopupDialog.newInstance(view.getContext(), items).setOptionsPopupDialogListener(new OptionsPopupDialog.OnOptionsItemClickedListener() {
            @Override
            public void onOptionsItemClicked(int which) {
                if (which == 0) {
                    RongIM.getInstance().cancelSendMediaMessage(message.getMessage(), null);
                    RongIM.getInstance().deleteMessages(new int[]{message.getMessageId()}, null);
                } else if (which == 1) {
                    RongIM.getInstance().recallMessage(message.getMessage(), getPushContent(view.getContext(), message));
                }
            }
        }).show();
    }
}
