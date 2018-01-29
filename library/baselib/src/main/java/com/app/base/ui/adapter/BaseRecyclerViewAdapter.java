package com.app.base.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.List;

/**
 * @Company:南京荣之誉信息科技有限公司
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/12/8/10:33
 * @Version:XBS V2.0
 * @Class:BaseRecyclerViewAdapter
 * @Description:
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder> {

    private Context context;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(setItemView(), parent, false));
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
     * 土司
     *
     * @param msg
     */
    public void showToast(@NonNull String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
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
