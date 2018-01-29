package com.app.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/**
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/6/22/19:42
 * @Class:TabApdater
 * @Description:
 */
public class TabFragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> tabFragmentList;
    private List<String> tabNameList;

    public TabFragmentAdapter(FragmentManager fm, List<Fragment> tabFragmentList, List<String> tabNameList) {
        super(fm);
        this.tabFragmentList = tabFragmentList;
        this.tabNameList = tabNameList;
    }

    @Override
    public Fragment getItem(int position) {
        return tabFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return tabFragmentList == null ? 0 : tabFragmentList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return super.isViewFromObject(view, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNameList == null ? "" : tabNameList.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public float getPageWidth(int position) {
        return super.getPageWidth(position);
    }
}
