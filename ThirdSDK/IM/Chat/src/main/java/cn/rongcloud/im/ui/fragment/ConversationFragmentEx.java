package cn.rongcloud.im.ui.fragment;

import android.content.Intent;
import cn.rongcloud.im.ui.activity.ReadReceiptDetailActivity;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.model.Conversation;

/**
 *  会话 Fragment 继承自ConversationFragment
 *  onResendItemClick: 重发按钮点击事件. 如果返回 false,走默认流程,如果返回 true,走自定义流程
 *  onReadReceiptStateClick: 已读回执详情的点击事件.
 *  如果不需要重写 onResendItemClick 和 onReadReceiptStateClick ,可以不必定义此类,直接集成 ConversationFragment 就可以了
 *  Created by Yuejunhong on 2016/10/10.
 */
public class ConversationFragmentEx extends ConversationFragment {
    @Override
    public boolean onResendItemClick(io.rong.imlib.model.Message message) {
        return false;
    }

    @Override
    public void onReadReceiptStateClick(io.rong.imlib.model.Message message) {
        if (message.getConversationType() == Conversation.ConversationType.GROUP) { //目前只适配了群组会话
            Intent intent = new Intent(getActivity(), ReadReceiptDetailActivity.class);
            intent.putExtra("message", message);
            getActivity().startActivity(intent);
        }
    }

    public void onWarningDialog(String msg) {
        String typeStr = getUri().getLastPathSegment();
        if (!typeStr.equals("chatroom")) {
            super.onWarningDialog(msg);
        }
    }
}
