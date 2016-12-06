package com.hiquanta.scaffold.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hiquanta.scaffold.R;
import com.hiquanta.scaffold.internal.di.components.UserComponent;
import com.hiquanta.scaffold.model.UserModel;
import com.hiquanta.scaffold.presenter.UserListPresenter;
import com.hiquanta.scaffold.view.UserListView;
import com.hiquanta.scaffold.view.adapter.UsersAdapter;
import com.hiquanta.scaffold.view.adapter.UsersLayoutManager;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hiquanta on 2016/9/26.
 */

public class UserListFragment extends BaseListFragment implements UserListView {
    private static final int TOTAL_COUNTER = 18;

    private int mCurrentCounter = 0;

    private boolean isErr;
    private boolean mLoadMoreEndGone = false;

    public interface UserListListener {
        void onUserClicked(final UserModel userModel);
    }

    @Inject
    UserListPresenter userListPresenter;
    @Inject
    UsersAdapter usersAdapter;



    @BindView(R.id.from)
    TextView from;


    private UserListListener userListListener;

    public UserListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof UserListListener) {
            this.userListListener = (UserListListener) activity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(UserComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
//        fragmentView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//               // setSwipeRefreshLoadingState();
//                swipeLayout.setRefreshing(true);
//                onRefresh();
//            }
//        },5000);
        return fragmentView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.userListPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadUserList();
        }
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        usersAdapter.setEnableLoadMore(false);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                loadUserList();
                swipeLayout.setRefreshing(false);
                usersAdapter.setEnableLoadMore(true);
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        super.onLoadMoreRequested();
        swipeLayout.setEnabled(false);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (mCurrentCounter >= TOTAL_COUNTER) {
//                    mQuickAdapter.loadMoreEnd();//default visible
                    usersAdapter.loadMoreEnd(mLoadMoreEndGone);//true is gone,false is visible
                } else {
                    if (isErr) {
                     // usersAdapter.addData(DataServer.getSampleData(PAGE_SIZE));
                        mCurrentCounter = usersAdapter.getData().size();
                        usersAdapter.loadMoreComplete();
                    } else {
                        isErr = true;
                        Toast.makeText(getActivity(), R.string.exception_message_no_connection, Toast.LENGTH_LONG).show();
                        usersAdapter.loadMoreFail();

                    }
                }
                usersAdapter.loadMoreEnd(true);
                swipeLayout.setEnabled(true);
            }
        });
    }
    private void setSwipeLayoutEnable(boolean isEnable){
        swipeLayout.setEnabled(isEnable);

    }
    private void setLoadMoreEnable(boolean isEnable){
        usersAdapter.setEnableLoadMore(isEnable);
    }
    private void setmLoadMoreEndGone(boolean isGone){
        usersAdapter.loadMoreEnd(isGone);
    }
    private void onLoadMoreFail(){
        usersAdapter.loadMoreFail();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.userListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.userListPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rv_users.setAdapter(null);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.userListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.userListListener = null;
    }

    @Override
    public void renderUserList(Collection<UserModel> userModelCollection) {
        if (userModelCollection != null) {
            this.usersAdapter.setUsersCollection(userModelCollection);

        }
    }

    @Override
    public void viewUser(UserModel userModel) {
        if (this.userListListener != null) {
            this.userListListener.onUserClicked(userModel);
        }
    }

    @Override
    public void dataFrom(String from) {
        this.from.setText(from);
    }

    @Override
    public void showLoading() {
        swipeLayout.setEnabled(false);
        this.rl_progress.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        swipeLayout.setEnabled(true);
        this.rl_progress.setVisibility(View.GONE);

    }

    @Override
    public void showRetry() {
        swipeLayout.setEnabled(false);
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        swipeLayout.setEnabled(true);
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }

    private void setupRecyclerView() {
        this.swipeLayout.setOnRefreshListener(this);
        this.usersAdapter.setOnItemClickListener(onItemClickListener);
        this.usersAdapter.setOnLoadMoreListener(this);
        this.usersAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        this.rv_users.setLayoutManager(new UsersLayoutManager(context()));
        this.rv_users.setAdapter(usersAdapter);
    }

    private void loadUserList() {
        this.userListPresenter.initialize();
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        UserListFragment.this.loadUserList();
    }

    private UsersAdapter.OnItemClickListener onItemClickListener =
            new UsersAdapter.OnItemClickListener() {
                @Override
                public void onUserItemClicked(UserModel userModel) {
                    if (UserListFragment.this.userListPresenter != null && userModel != null) {
                        UserListFragment.this.userListPresenter.onUserClicked(userModel);
                    }
                }
            };
}
