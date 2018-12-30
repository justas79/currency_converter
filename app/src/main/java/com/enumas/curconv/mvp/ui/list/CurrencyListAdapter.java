package com.enumas.curconv.mvp.ui.list;


import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.curencyconv.enumas.currencyconverter.R;
import com.enumas.curconv.mvp.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter used in Currency List recycler view
 */
public class CurrencyListAdapter extends RecyclerView.Adapter<CurrencyListAdapter.ListItemViewHolder> {

    /**
     *  List of items displayed in recycler view
     */
    List<CurrencyListItemModel> list;


    /**
     * Item click interface for handling click event on activity
     */
    public interface CurrencyListClickListener {
        void onItemClick(View v, int position);
    }

    private final CurrencyListClickListener onClickListener;

    public CurrencyListAdapter(List<CurrencyListItemModel> list, CurrencyListClickListener listClickListener) {
        this.list = list;
        this.onClickListener = listClickListener;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.currency_list_row, viewGroup, false);
        ListItemViewHolder listItemViewHolder = new ListItemViewHolder(itemView);
        itemView.setOnClickListener(v -> onClickListener.onItemClick(v, listItemViewHolder.getAdapterPosition()));
        return listItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder listItemViewHolder, int i) {
        listItemViewHolder.itemCode.setText(list.get(i).getCode());
        listItemViewHolder.itemRate.setText(list.get(i).getRate());
        int res = getResourceByCurrency(list.get(i).getCode(), listItemViewHolder.itemFlag.getContext());
        Picasso.get().load(res).into(listItemViewHolder.itemFlag);
    }

    /**
     * Returns resource id gy given currency code. Resource id points to flag to display.
     * If resource (flag) not found - returns blank resource
     */
    private int getResourceByCurrency(String code, Context context) {
        Resources resources = context.getResources();

        String countryFlagCode = Constants.IATA_CURR_MAP.get(code);
        if (countryFlagCode != null) {
            int drawable = resources.getIdentifier(countryFlagCode.toLowerCase(), "drawable", context.getPackageName());
            if (drawable != 0) {
                return drawable;
            }
        }
        return R.drawable.blank;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    /**
     * View Holder for Currency List
     */
    public static class ListItemViewHolder extends RecyclerView.ViewHolder  {
        public TextView itemCode;
        public TextView itemRate;
        public ImageView itemFlag;
        public ListItemViewHolder(View itemView) {
            super(itemView);
            itemCode = itemView.findViewById(R.id.textview_currency_code);
            itemRate = itemView.findViewById(R.id.textview_currency_rate);
            itemFlag = itemView.findViewById(R.id.imgview_flag);
        }
    }



    public void setData(List<CurrencyListItemModel> items) {
        this.list = items;
    }
}
