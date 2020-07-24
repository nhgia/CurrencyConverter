package com.nhgia.currencyconverter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CustomListAdapter2 extends BaseAdapter {

    private List<Country> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter2(Context aContext, List<Country> listData) {
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

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        if (position < getCount() - 1) {
            return 0;
        }
        else {
            return 1;
        }
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        if (type == 0) {
            CustomListAdapter.ViewHolder holder;
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.list_item, parent, false);
                holder = new CustomListAdapter.ViewHolder();
                holder.flagView = (ImageView) convertView.findViewById(R.id.imageView_flag);
                holder.countryNameView = (TextView) convertView.findViewById(R.id.textView2);
                holder.currencyView = (TextView) convertView.findViewById(R.id.textView4);
                holder.moneyView = (TextView) convertView.findViewById(R.id.textView3);
                convertView.setTag(holder);
            } else {
                holder = (CustomListAdapter.ViewHolder) convertView.getTag();
            }

            Country country = this.listData.get(position);
            holder.countryNameView.setText(country.getCountryName());
            holder.currencyView.setText(country.getCurrency());
            double money = Double.parseDouble(country.getCurrentValue()) * country.getConvertRate();
            holder.moneyView.setText(String.format("%f", money));

            int imageId = this.getMipmapResIdByName(country.getFlagName());

            holder.flagView.setImageResource(imageId);
        }
        else {
            ViewHolder2 holder;
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.add_btn_item, parent, false);
                holder = new ViewHolder2();
                holder.imgBtn =(ImageButton) convertView.findViewById(R.id.imageButton);
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder2) convertView.getTag();
            }

            holder.imgBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //Toast.makeText(context, "New intent", Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(context, AddCurrenciesActivity.class);
                    String value = myIntent.getStringExtra("addCurrencyActivity");
                    myIntent.putExtra("addCurrencyActivity",value); //Optional parameters
                    context.startActivity(myIntent);
                }
            });
        }

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
        TextView countryNameView;
        TextView currencyView;
    }

    static class ViewHolder2 {
        ImageButton imgBtn;
    }

}