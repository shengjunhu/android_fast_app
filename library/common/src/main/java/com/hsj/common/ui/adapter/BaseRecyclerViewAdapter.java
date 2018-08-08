package com.hsj.common.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @Company:南京荣之誉信息科技有限公司
 * @Author:HSJ
 * @E-mail:shengjunhu@foxmail.com
 * @Date:2017/12/8/10:33
 * @Version:XBS V2.0
 * @Class:BaseRecyclerViewAdapter
 * @Description:
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder> {

    public final String TAG = this.getClass().getName();

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(setItemView(), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return setData() == null ? 0 : setData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ViewHolder(View itemView) {
            super(itemView);
            bindItemView(itemView);
        }
    }

    /**
     * item点击
     *
     * @param t
     */
    public void itemViewOnClick(T t) {

    }

    /**
     * 设置item布局
     *
     * @return
     */
    protected abstract int setItemView();

    /**
     * 设置数据
     *
     * @return
     */
    protected abstract List<T> setData();

    /**
     * 返回item数据
     *
     * @param t
     */
    protected abstract void bindItemData(T t);

    /**
     * 返回itemView
     *
     * @param itemView
     */
    protected abstract void bindItemView(View itemView);

}
