package com.hiquanta.scaffold.view.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hiquanta.scaffold.R;
import com.hiquanta.scaffold.internal.di.PerActivity;
import com.hiquanta.scaffold.model.UserModel;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by hiquanta on 2016/10/15.
 */
@PerActivity
public class UsersAdapter extends BaseQuickAdapter<UserModel> {
    public interface OnItemClickListener {
        void onUserItemClicked(UserModel userModel);
    }

    private List<UserModel> usersCollection;


    private UsersAdapter.OnItemClickListener onItemClickListener;


    @Inject
    public UsersAdapter(Context context, List<UserModel> data) {
        super(R.layout.row_user, data);
        this.usersCollection = data;

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final UserModel userModel) {
        baseViewHolder.setText(R.id.title, userModel.getFullName());
        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onUserItemClicked(userModel);
                }
            }
        });
    }

    public void setUsersCollection(Collection<UserModel> usersCollection) {
        this.validateUsersCollection(usersCollection);
        this.usersCollection = (List<UserModel>) usersCollection;
        this.setNewData(this.usersCollection);
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(UsersAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validateUsersCollection(Collection<UserModel> usersCollection) {
        if (usersCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }
}
