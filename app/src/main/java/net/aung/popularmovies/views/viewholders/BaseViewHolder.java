package net.aung.popularmovies.views.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by aung on 12/15/15.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }
}
