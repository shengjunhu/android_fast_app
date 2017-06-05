package io.rong.callkit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.rong.calllib.RongCallCommon;
import io.rong.imkit.RongContext;
import io.rong.imkit.widget.AsyncImageView;
import io.rong.imlib.model.UserInfo;

/**
 * Created by weiqinxiao on 16/3/15.
 */
public class CallSelectMemberActivity extends Activity {

    ArrayList<String> selectedMember;
    TextView txtvStart;
    ListAdapter mAdapter;
    ListView mList;
    RongCallCommon.CallMediaType mMediaType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.rc_voip_activity_select_member);
        RongContext.getInstance().getEventBus().register(this);

        txtvStart = (TextView) findViewById(R.id.rc_btn_ok);
        txtvStart.setEnabled(false);
        txtvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putStringArrayListExtra("invited", selectedMember);
                setResult(RESULT_OK, intent);
                CallSelectMemberActivity.this.finish();
            }
        });
        findViewById(R.id.rc_btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                CallSelectMemberActivity.this.finish();
            }
        });

        selectedMember = new ArrayList<>();

        Intent intent = getIntent();
        int type = intent.getIntExtra("mediaType", RongCallCommon.CallMediaType.VIDEO.getValue());
        mMediaType = RongCallCommon.CallMediaType.valueOf(type);
        final ArrayList<String> invitedMembers = intent.getStringArrayListExtra("invitedMembers");
        ArrayList<String> allMembers = intent.getStringArrayListExtra("allMembers");
        String groupId = intent.getStringExtra("groupId");
        RongCallKit.GroupMembersProvider provider = RongCallKit.getGroupMemberProvider();
        if (groupId != null && allMembers == null && provider != null) {
            allMembers = provider.getMemberList(groupId, new RongCallKit.OnGroupMembersResult() {
                @Override
                public void onGotMemberList(ArrayList<String> members) {
                    if (mAdapter != null) {
                        if (members != null && members.size() > 0) {
                            mAdapter.setAllMembers(members);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if (allMembers == null) {
            allMembers = invitedMembers;
        }

        mList = (ListView) findViewById(R.id.rc_listview_select_member);
        if (invitedMembers != null && invitedMembers.size() > 0) {
            mAdapter = new ListAdapter(allMembers, invitedMembers);
            mList.setAdapter(mAdapter);
            mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    View v = view.findViewById(R.id.rc_checkbox);
                    String userId = (String) v.getTag();
                    if (!invitedMembers.contains(userId)) {
                        if (mMediaType.equals(RongCallCommon.CallMediaType.VIDEO)
                                && !v.isSelected() && selectedMember.size() + invitedMembers.size() >= 9) {
                            Toast.makeText(CallSelectMemberActivity.this, "您最多只能选择9人", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (selectedMember.contains(userId)) {
                            selectedMember.remove(userId);
                        }

                        v.setSelected(!v.isSelected());
                        if (v.isSelected()) {
                            selectedMember.add(userId);
                        }

                        if (selectedMember.size() > 0) {
                            txtvStart.setEnabled(true);
                            txtvStart.setTextColor(getResources().getColor(R.color.rc_voip_check_enable));
                        } else {
                            txtvStart.setEnabled(false);
                            txtvStart.setTextColor(getResources().getColor(R.color.rc_voip_check_disable));
                        }
                    }
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        RongContext.getInstance().getEventBus().unregister(this);
        super.onDestroy();
    }

    class ListAdapter extends BaseAdapter {
        List<String> allMembers;
        List<String> invitedMembers;

        public ListAdapter(List<String> allMembers, List<String> invitedMembers) {
            this.allMembers = allMembers;
            this.invitedMembers = invitedMembers;
        }

        public void setAllMembers(List<String> allMembers) {
            this.allMembers = allMembers;
        }

        @Override
        public int getCount() {
            return allMembers.size();
        }

        @Override
        public Object getItem(int position) {
            return allMembers.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(CallSelectMemberActivity.this).inflate(R.layout.rc_voip_listitem_select_member, null);
                holder.checkbox = (ImageView) convertView.findViewById(R.id.rc_checkbox);
                holder.portrait = (AsyncImageView) convertView.findViewById(R.id.rc_user_portrait);
                holder.name = (TextView) convertView.findViewById(R.id.rc_user_name);
                convertView.setTag(holder);
            }

            holder = (ViewHolder)convertView.getTag();
            holder.checkbox.setTag(allMembers.get(position));
            if (invitedMembers.contains(allMembers.get(position))) {
                holder.checkbox.setClickable(false);
                holder.checkbox.setEnabled(false);
                holder.checkbox.setImageResource(R.drawable.rc_voip_icon_checkbox_checked);
            } else {
                if (selectedMember.contains(allMembers.get(position))) {
                    holder.checkbox.setImageResource(R.drawable.rc_voip_checkbox);
                    holder.checkbox.setSelected(true);
                } else {
                    holder.checkbox.setImageResource(R.drawable.rc_voip_checkbox);
                    holder.checkbox.setSelected(false);
                }
                holder.checkbox.setClickable(true);
                holder.checkbox.setEnabled(true);
            }

            UserInfo userInfo = RongContext.getInstance().getUserInfoFromCache(allMembers.get(position));
            if (userInfo != null) {
                holder.name.setText(userInfo.getName());
                holder.portrait.setAvatar(userInfo.getPortraitUri());
            } else {
                holder.name.setText(allMembers.get(position));
                holder.portrait.setAvatar(null);
            }
            return convertView;
        }
    }

    public void onEventMainThread(UserInfo userInfo) {
        if (mList != null) {
            int first = mList.getFirstVisiblePosition() - mList.getHeaderViewsCount();
            int last = mList.getLastVisiblePosition() - mList.getHeaderViewsCount();

            int index = first - 1;

            while (++index <= last && index >= 0 && index < mAdapter.getCount()) {
                if (mAdapter.getItem(index).equals(userInfo.getUserId())) {
                    mAdapter.getView(index, mList.getChildAt(index - mList.getFirstVisiblePosition() + mList.getHeaderViewsCount()), mList);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    class ViewHolder {
        ImageView checkbox;
        AsyncImageView portrait;
        TextView name;
    }
}
