/*
package com.example.zjy.shop;

import java.util.List;

import com.ksec.model.News;
import com.ksec.model.Notice;
import com.ksec.model.Personal;
import com.ksec.model.ShopType;
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

public class ShopTypeListAdapter extends BaseAdapter{
	private LayoutInflater layoutInflater;
	private Context context;
	private List<ShopType> typeList;
	private int[] pictureArr={R.drawable.shop_type0,R.drawable.shop_type1,R.drawable.shop_type2,R.drawable.shop_type3,R.drawable.shop_type4,R.drawable.shop_type5,R.drawable.shop_type6,R.drawable.shop_type7,R.drawable.shop_type8,R.drawable.shop_type5,R.drawable.shop_type2, R.drawable.shop_type2,R.drawable.shop_type3,R.drawable.shop_type4,R.drawable.shop_type5,R.drawable.shop_type6,R.drawable.shop_type7,R.drawable.shop_type8,R.drawable.shop_type5,R.drawable.shop_type2};

	public ShopTypeListAdapter(Context context, List<ShopType> list) {
		layoutInflater=LayoutInflater.from(context);
		this.context=context;
		this.typeList=list;
	}

	@Override
	public int getCount() {
		if (typeList == null) {
			return 0;
		} else {
			return typeList.size();
		}
	}

	@Override
	public Object getItem(int arg0) {
		if (typeList != null) {
			return typeList.get(arg0);
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
		convertView=layoutInflater.inflate(R.layout.list_item_shoptype, null);
		holder=new ViewHolder();
	
		holder.img_type=(ImageView)convertView.findViewById(R.id.img_type);
		holder.txt_type=(TextView)convertView.findViewById(R.id.txt_type);
		
		holder.img_type.setBackgroundResource(pictureArr[typeList.get(position).getTypeId()]);
		holder.txt_type.setText(typeList.get(position).getTypeName());
		return convertView;
	}
	
	private static class ViewHolder {
		private ImageView img_type;
		private TextView txt_type;
	}
}
*/
