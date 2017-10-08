package com.example.moham.mvp_rx_dagg_ret1.mvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.moham.mvp_rx_dagg_ret1.R;
import com.example.moham.mvp_rx_dagg_ret1.mvp.presenter.fetch_friends.MainActivityPresenter;
import com.example.moham.mvp_rx_dagg_ret1.mvp.view.I_FriendsListView;

/**
 * Created by moham on 10/1/2017.
 */

public class RcycleAdapter extends RecyclerView.Adapter<RcycleAdapter.VHolder> {
    private MainActivityPresenter presenter;

    public RcycleAdapter(MainActivityPresenter presenter){
        this.presenter=presenter;
    }

    @Override
    public VHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_element, parent,false);
        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(final VHolder holder, final int position) {
        presenter.onBindView(holder,position);
        holder.myItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onItemClicked((Button) view,position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return presenter.listSize();
    }

    public static class VHolder extends RecyclerView.ViewHolder implements I_FriendsListView {
       public Button myItem;
         VHolder(View itemView) {
            super(itemView);
            myItem=itemView.findViewById(R.id.row_element_btn_id);
        }

        @Override
        public void setItemText(String name) {
            myItem.setText(name);
        }

        @Override
        public void setselected(boolean selected) {
            myItem.setSelected(selected);
        }
    }
}
