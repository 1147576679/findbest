/*
package com.example.zjy.shop;

import java.util.List;

import com.ksec.model.News;
import com.ksec.model.Notice;
import com.ksec.model.Personal;
import com.ksec.model.Shop;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ShopListAdapter extends BaseAdapter{
	private LayoutInflater layoutInflater;
	private Context context;
	private List<Shop> shopList;
	private int[] pictureArr={R.drawable.shop01,R.drawable.shop02,R.drawable.shop03,R.drawable.shop04,R.drawable.shop05,R.drawable.shop06,R.drawable.shop07};

	public ShopListAdapter(Context context, List<Shop> list) {
		layoutInflater=LayoutInflater.from(context);
		this.context=context;
		this.shopList=list;
		
	}

	@Override
	public int getCount() {
		if (shopList == null) {
			return 0;
		} else {
			return shopList.size();
		}
	}

	@Override
	public Object getItem(int arg0) {
		if (shopList != null) {
			return shopList.get(arg0);
		}
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		convertView=layoutInflater.inflate(R.layout.list_item_shop, null);
		holder=new ViewHolder();
	
		holder.img_shop=(ImageView)convertView.findViewById(R.id.img_shop);
		holder.txt_name=(TextView)convertView.findViewById(R.id.txt_name);
		holder.txt_address=(TextView)convertView.findViewById(R.id.txt_address);
		holder.txt_note=(TextView)convertView.findViewById(R.id.txt_note);
		
//		holder.img_shop.setBackgroundResource(pictureArr[shopList.get(position).getShopId()]);
		holder.txt_name.setText(shopList.get(position).getShopName());
		holder.txt_address.setText(shopList.get(position).getAddress());
		holder.txt_note.setText(shopList.get(position).getNote());
	
		return convertView;
	}
	
	private static class ViewHolder {
		private ImageView img_shop;
		private TextView txt_name;
		private TextView txt_address;
		private TextView txt_note;
	}
}
*/
