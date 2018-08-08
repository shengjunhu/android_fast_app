package com.hsj.image.loader.glide.recyclerview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/10/9/16:08
 * @Class:RecyclerToListViewScrollListener
 * @Description:RecyclerView滑动监听
 */
public final class RecyclerToListViewScrollListener extends RecyclerView.OnScrollListener {

    public static final int UNKNOWN_SCROLL_STATE = Integer.MIN_VALUE;
    private final AbsListView.OnScrollListener scrollListener;
    private int lastFirstVisible = -1;
    private int lastVisibleCount = -1;
    private int lastItemCount    = -1;

    public RecyclerToListViewScrollListener(AbsListView.OnScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        int listViewState;
        switch (newState) {
            case RecyclerView.SCROLL_STATE_DRAGGING:
                listViewState = ListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL;
                break;
            case RecyclerView.SCROLL_STATE_IDLE:
                listViewState = ListView.OnScrollListener.SCROLL_STATE_IDLE;
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                listViewState = ListView.OnScrollListener.SCROLL_STATE_FLING;
                break;
            default:
                listViewState = UNKNOWN_SCROLL_STATE;
        }
        scrollListener.onScrollStateChanged(null /*view*/, listViewState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        int firstVisible = layoutManager.findFirstVisibleItemPosition();
        int visibleCount = Math.abs(firstVisible - layoutManager.findLastVisibleItemPosition());
        int itemCount = recyclerView.getAdapter().getItemCount();

        if (firstVisible != lastFirstVisible || visibleCount != lastVisibleCount || itemCount != lastItemCount) {
            scrollListener.onScroll(null, firstVisible, visibleCount, itemCount);
            lastFirstVisible = firstVisible;
            lastVisibleCount = visibleCount;
            lastItemCount = itemCount;
        }
    }

}
