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

package com.hsj.main.ui.adapter;

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
