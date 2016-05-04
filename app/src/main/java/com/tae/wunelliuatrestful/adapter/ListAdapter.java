package com.tae.wunelliuatrestful.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tae.wunelliuatrestful.R;
import com.tae.wunelliuatrestful.model.Route;

import java.util.List;

/**
 * Created by Eduardo on 04/05/2016.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private List<Route> routes;
    private Context context;
    private ItemClickListener listener;

    public ListAdapter(List<Route> routes, Context context) {
        this.routes = routes;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.route_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Route route = routes.get(position);
        holder.txtRouteTitle.setText(route.getStartLocality());
        holder.txtRouteTitleEnd.setText(route.getEndLocality());
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtRouteTitle, txtRouteTitleEnd;

        public ViewHolder(View itemView) {
            super(itemView);
            txtRouteTitle = (TextView) itemView.findViewById(R.id.txtRouteTitle);
            txtRouteTitleEnd = (TextView) itemView.findViewById(R.id.txtRouteTitleEnd);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.clickOnListItem(routes.get(getAdapterPosition()));
        }
    }
}
