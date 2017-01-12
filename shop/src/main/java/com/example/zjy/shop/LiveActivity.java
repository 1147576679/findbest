/*
package com.ksec.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.zjy.shop.R;
import com.example.zjy.shop.ShopTypeListAdapter;
import com.httputils.okhttp.OkHttpUtils;
import com.httputils.okhttp.callback.StringCallback;
import com.ksec.adapter.ShopListAdapter
import com.ksec.constant.Constant;
import com.ksec.model.Shop;
import com.ksec.model.ShopType;
import com.ksec.shop.ShopDetailsActivity;
import com.ksec.utils.JSONUtils;
import com.ksec.utils.MyImgScroll;
import com.ksec.utils.MyToast;
import com.ksec.utils.NetworkChecker;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class LiveActivity extends Activity implements ListView.OnScrollListener, OnItemClickListener{
	private MyImgScroll viewpager;
	private List<View> listImg;
	private LinearLayout layDot;
	private FrameLayout lay_banner;
	//商品类型展示的listView
	private ListView typeListView;
	//商品类型
	private List<ShopType> typeList=new ArrayList<ShopType>();
	private ShopTypeListAdapter typeListAdapter;

	//商品详情的listview
	private ListView shopListView;
	private List<Shop> shopList = new ArrayList<Shop>();;
	private ShopListAdapter shopListAdapter;
	private boolean isFirst=false;
	private boolean ishide=false;

	//	private int type_page = 1;
	private int shop_page = 0;
	private ProgressDialog progressDialog = null;
	private boolean isLoading = false;
	private boolean loadEnd = false;
	private boolean isNetworkOk;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_live);

		lay_banner=(FrameLayout)findViewById(R.id.lay_banner);
		typeListView=(ListView)findViewById(R.id.typeListView);



		typeListAdapter=new ShopTypeListAdapter(LiveActivity.this,typeList);
		typeListView.setAdapter(typeListAdapter);

		*/
/**
		 * shoptype的点击监听
		 * @param arg0
		 *//*

		typeListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
									long arg3) {
				// TODO Auto-generated method stub
				Log.d("print", "--->点击了"  + arg2 );
				String str = null;
				for (int i = 0; i < typeList.size(); i++) {

					switch (arg2) {
						case 0:
							str = "";
							break;
						default:
							str = "&shopType=" +  typeList.get(arg2).getTypeId();
							break;

					}
				}
				shopList = new ArrayList<Shop>();
				shop_page = 0;
				showShopList(str);
			}
		});

		shopListView=(ListView)findViewById(R.id.shopListView);
		shopListAdapter=new ShopListAdapter(LiveActivity.this,shopList);
		shopListView.setAdapter(shopListAdapter);

		shopListView.setOnItemClickListener(this);
		shopListView.setOnScrollListener(this);
		//	shopListView.setOnTouchListener(list_touch_listener);


		InitViewPager();
		showShopTypeList();
		showShopList("");
	}

	*/
/**
	 * viewpager
	 *//*

	private void InitViewPager() {
		layDot=(LinearLayout)findViewById(R.id.vb);
		viewpager=(MyImgScroll)findViewById(R.id.viewpager);
		listImg=new ArrayList<View>();

		ImageView imageView = new ImageView(this);
		imageView.setBackgroundResource(R.drawable.ad01);
		listImg.add(imageView);

		imageView = new ImageView(this);
		imageView.setBackgroundResource(R.drawable.ad02);
		listImg.add(imageView);

		imageView = new ImageView(this);
		imageView.setBackgroundResource(R.drawable.ad03);
		listImg.add(imageView);

		viewpager.start(this, listImg, 5000, layDot, R.layout.ad_bottom_item,R.id.ad_item_v, R.drawable.dot_focused, R.drawable.dot_normal);
	}

	*/
/**
	 * 商家分类(全部商家、 商店超市、 家电维修、 家政服务……)
	 *//*

	private void showShopTypeList(){
		ShopType type=new ShopType();
		type.setTypeId(0);
		type.setTypeName("全部商家");
		typeList.add(type);

		type=new ShopType();
		type.setTypeId(1);
		type.setTypeName("商店超市");
		typeList.add(type);

		type=new ShopType();
		type.setTypeId(2);
		type.setTypeName("美食餐馆");
		typeList.add(type);

		type=new ShopType();
		type.setTypeId(3);
		type.setTypeName("家电维修");
		typeList.add(type);

		type=new ShopType();
		type.setTypeId(4);
		type.setTypeName("家政服务");
		typeList.add(type);

		type=new ShopType();
		type.setTypeId(5);
		type.setTypeName("美容美发");
		typeList.add(type);

		type=new ShopType();
		type.setTypeId(6);
		type.setTypeName("中介服务");
		typeList.add(type);

		type=new ShopType();
		type.setTypeName("教育培训");
		typeList.add(type);

		type=new ShopType();
		type.setTypeId(7);
		type.setTypeName("图书杂志");
		typeList.add(type);



	}

	private void showShopList(String str){
		shop_page++;
		isLoading = true;
		String url0 = Constant.Pre_URL + Constant.SHOP_LIST_URL + str;
		String shop_url = String.format(url0, shop_page);

		//执行网络请求
		isNetworkOk=NetworkChecker.isNetworkConnected(this);
		if(shop_page==1) showProgressDialog();
		Log.d("print", "商家列表url:" + shop_url);
		if (isNetworkOk) {
			OkHttpUtils.get().url(shop_url).build().execute(new StringCallback() {

				@Override
				public void onResponse(String arg0, int arg1) {
					// TODO Auto-generated method stub
//					Log.d("print", "商家列表：" + arg0);

					List<Shop> shopsList = JSONUtils.getShopListByJson(arg0);
//					Log.d("print", "商家" + shopsList);
					for (int i = 0; i < shopsList.size(); i++) {
						Log.d("print", "商家" + shopsList.get(i));
					}
					if(progressDialog!=null) progressDialog.dismiss();
					resultProcess(shopsList);
				}

				@Override
				public void onError(Call arg0, Exception arg1, int arg2) {
					// TODO Auto-generated method stub

				}
			});
		} else {
			MyToast.showToast(this, "网络连接失败，请检查网络！");
		}


	}

	private void resultProcess(List<Shop> result){
		isLoading=false;
		if(result==null || result.size()<1){
			MyToast.showToast(getApplicationContext(), "没有更多的数据！");
			loadEnd=true;
			return;
		}

		if(result.size()<10) loadEnd=true;

		for (int i = 0; i < result.size(); i++) {
			Log.d("print", "result:" + result.get(i));
		}
		shopList.addAll(result);
		for (int i = 0; i < shopList.size(); i++) {
			Log.d("print", "商家2" + shopList.get(i));
		}
		shopListAdapter.notifyDataSetChanged();
	}

	private void showProgressDialog() {
		progressDialog = ProgressDialog.show(this, null, "正在加载...", true, true);
	}



	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent=new Intent();
		intent.setClass(LiveActivity.this, ShopDetailsActivity.class);
		intent.putExtra("shopId", shopList.get(arg2).getShopId());
		startActivity(intent);
	}


	private int mListViewFirstItem = 0;
	//listView中第一项的在屏幕中的位置
	private int mScreenY = 0;
	//是否向上滚动
	private boolean mIsScrollToUp = false;

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		*/
/*if(firstVisibleItem==0) {
			isFirst=true;
			Log.e("!!", "000000000000000000000000000000000000000000");
		}
		else
			isFirst=false;

		 if(shopListView.getChildCount()>0)
	        {
	            boolean isScrollToUp = false;
	            View childAt = shopListView.getChildAt(firstVisibleItem);
	            int[] location = new int[2];
	            childAt.getLocationOnScreen(location);
	            Log.d("onScroll", "firstVisibleItem= "+firstVisibleItem+" , y="+location[1]);

	            if(firstVisibleItem!=mListViewFirstItem)
	            {
	                if(firstVisibleItem>mListViewFirstItem)
	                {
	                	hideTop(true);
	                    Log.e("--->", "向上滑动");
	                    isScrollToUp = true;
	                }else{
	                    Log.e("--->", "向下滑动");
	                    isScrollToUp = false;
	                    hideTop(false);
	                }
	                mListViewFirstItem = firstVisibleItem;
	                mScreenY = location[1];
	            }else{
	                if(mScreenY>location[1])
	                {
	                	hideTop(true);
	                    Log.i("--->", "->向上滑动");
	                    isScrollToUp = true;
	                }
	                else if(mScreenY<location[1])
	                {
	                    Log.i("--->", "->向下滑动");
	                    isScrollToUp = false;
	                    hideTop(false);
	                }
	                mScreenY = location[1];
	            }
	             
	            if(mIsScrollToUp!=isScrollToUp)
	            {
	               // onScrollDirectionChanged(mIsScrollToUp);
	            }
	             
	        }*//*


	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		*/
/*if(view.getLastVisiblePosition()==view.getCount()-1) {
			if(!isLoading && !loadEnd){
				String str = null;
				for (int i = 0; i < typeList.size(); i++) {
					if (i == 0) {
						str = "";
					} else {
						str = "&shopType=" +  typeList.get(i).getTypeId();
					}
				}
				showShopList(str);
			}
		}*//*

	}


	float mFirstY,mCurrentY;

	float mTouchSlop=50;
	public View.OnTouchListener list_touch_listener=new View.OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					mFirstY = event.getY();
					Log.e("1111", "d---------"+mFirstY);
					if(isFirst && ishide) {
						lay_banner.setVisibility(View.VISIBLE);
						ishide=false;
					}
					break;
				case MotionEvent.ACTION_MOVE:
				*/
/*
				 mCurrentY = event.getY();
				 if (mCurrentY - mFirstY > mTouchSlop) {
				
					 if(isFirst && ishide) {
						 lay_banner.setVisibility(View.VISIBLE);
						 ishide=false;
					 }
					 Log.e("1111", "d");
				 }
				 else if (mFirstY - mCurrentY > mTouchSlop) {
					 Log.e("2222", "u");
					 if(!ishide){
						 lay_banner.setVisibility(View.GONE);
						 ishide=true;
					 }
	
				 }*//*

					break;
				case MotionEvent.ACTION_UP:
					mFirstY = event.getY();
					Log.e("1111", "u---------------------------"+mFirstY);
					if(!ishide){
						lay_banner.setVisibility(View.GONE);
						ishide=true;
					}
					break;
			}


			return false;
		}
	};


	private void hideTop(boolean isup){
		if(isup){
			if(!ishide){
				lay_banner.setVisibility(View.GONE);
				ishide=true;
			}
		}
		else{
			if(isFirst && ishide) {
				lay_banner.setVisibility(View.VISIBLE);
				ishide=false;
			}
		}
	}
*/
/*
	private void showHideTitleBar(boolean b) {
		if (mAnimatorTitle != null && mAnimatorTitle.isRunning()) { 
			mAnimatorTitle.cancel(); 
		} 
		
		if (mAnimatorContent != null && mAnimatorContent.isRunning()) { 
			mAnimatorContent.cancel(); 
		} 
		
		if (b) { 
			mAnimatorTitle = ObjectAnimator.ofFloat(mTitle, "translationY", mTitle.getTranslationY(), 0); 
			mAnimatorContent = ObjectAnimator.ofFloat(mListView, "translationY", mListView.getTranslationY(), getResources().getDimension(R.dimen.title_height)); 
		} 
		else { 
			mAnimatorTitle = ObjectAnimator.ofFloat(mTitle, "translationY", mTitle.getTranslationY(), -mTitle.getHeight()); 
			mAnimatorContent = ObjectAnimator.ofFloat(mListView, "translationY", mListView.getTranslationY(),0); 
		}
		
		mAnimatorTitle.start(); 
		mAnimatorContent.start(); 

		
	}
	*//*




}
*/
