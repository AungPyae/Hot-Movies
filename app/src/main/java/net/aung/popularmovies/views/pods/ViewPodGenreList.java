package net.aung.popularmovies.views.pods;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.aung.popularmovies.R;
import net.aung.popularmovies.data.vos.GenreVO;
import net.aung.popularmovies.views.components.textview.SmallDashUnderlineTextView;

import java.util.List;

/**
 * Created by aung on 12/16/15.
 */
public class ViewPodGenreList extends LinearLayout {

    public ViewPodGenreList(Context context) {
        super(context);
    }

    public ViewPodGenreList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPodGenreList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setGenreList(List<GenreVO> genreList) {
        if(genreList == null)
            return;

        for (int position = 0; position < genreList.size(); position++) {
            TextView tv = new SmallDashUnderlineTextView(getContext());
            tv.setText(genreList.get(position).getName());
            tv.setPadding((int) getContext().getResources().getDimension(R.dimen.margin_small), 0, 0, 0);
            addView(tv);

            if (position < genreList.size() - 1) {
                TextView tvSeparator = new TextView(getContext());
                tvSeparator.setTextSize(14);
                tvSeparator.setTextColor(getContext().getResources().getColor(android.R.color.white));
                tvSeparator.setText(" | ");
                addView(tvSeparator);
            }
        }
    }
}