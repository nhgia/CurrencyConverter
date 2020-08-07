package com.nhgia.currencyconverter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class HistoryListAdapter  extends BaseAdapter {

    private List<Country> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public HistoryListAdapter(Context aContext,  List<Country> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("DefaultLocale")
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_history_item, null);
            holder = new ViewHolder();
            holder.flagView = (ImageView) convertView.findViewById(R.id.imageView_source);
            holder.currencyView = (TextView) convertView.findViewById(R.id.textHistCur1);
            holder.moneyView = (TextView) convertView.findViewById(R.id.textHistVal1);
            holder.flagView2 = (ImageView) convertView.findViewById(R.id.imageView_destination);
            holder.currencyView2 = (TextView) convertView.findViewById(R.id.textHistCur2);
            holder.moneyView2 = (TextView) convertView.findViewById(R.id.textHistVal2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Country country = this.listData.get(position);
        holder.currencyView.setText("Vietnamese Dong");
        holder.moneyView.setText(country.getCurrentValue());
        int vnImgId = this.getMipmapResIdByName("vn");
        holder.flagView.setImageResource(vnImgId);

        holder.currencyView2.setText(country.getCurrency());
        double money = Double.parseDouble(country.getCurrentValue()) * country.getConvertRate();
        holder.moneyView2.setText(String.format("%f", money));

        int imageId = this.getMipmapResIdByName(country.getFlagName());

        holder.flagView2.setImageResource(imageId);

        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    public void updateList(List<Country> listData) {
        this.listData.clear();
        this.listData.addAll(listData);
    }

    static class ViewHolder {
        ImageView flagView;
        TextView currencyView;
        TextView moneyView;
        ImageView flagView2;
        TextView currencyView2;
        TextView moneyView2;
    }

}