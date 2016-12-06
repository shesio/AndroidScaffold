package com.hiquanta.scaffold.view.fragment;

import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hiquanta.scaffold.R;

import butterknife.BindView;

/**
 * Created by hiquanta on 2016/10/19.
 */

public  class BaseListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{
    private static final int PAGE_SIZE = 6;
    @BindView(R.id.rv_users)
    RecyclerView rv_users;
    @BindView(R.id.rl_progress)
    RelativeLayout rl_progress;
    @BindView(R.id.rl_retry)
    RelativeLayout rl_retry;
    @BindView(R.id.bt_retry)
    Button bt_retry;

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }
    /**
     * 设置顶部正在加载的状态
     */
    private void setSwipeRefreshLoadingState() {
        if (swipeLayout != null) {
            swipeLayout.setRefreshing(true);
            // 防止多次重复刷新
            swipeLayout.setEnabled(false);
        }
    }
    /**
     * 设置顶部加载完毕的状态
     */
    private void setSwipeRefreshLoadedState() {
        if (swipeLayout != null) {
            swipeLayout.setRefreshing(false);
            swipeLayout.setEnabled(true);
        }
    }
}
