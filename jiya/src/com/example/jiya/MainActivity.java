package com.example.jiya;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author wuliao
 * @version 1.0
 * 挤压动画的实现
 */
public class MainActivity extends Activity implements OnScrollListener{
	private TextView mTextView;
	private ListView mListView;
	private LayoutInflater inflate;
	private LinearLayout mLinearLayout;
	private ArrayList<String> mStrs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	private void initView() {
		inflate=LayoutInflater.from(this);
		mStrs=new ArrayList<String>();
		addData();
		mListView=(ListView) findViewById(R.id.listview);
		mLinearLayout=(LinearLayout) findViewById(R.id.title);
		mTextView=(TextView) findViewById(R.id.tv_title);
		mListView.setAdapter(new MyAdapter());
		mListView.setOnScrollListener(this);
	}
	
	private void addData() {
		for(int i=0;i<20;i++){
			mStrs.add("hehehahagaga"+i);
		}
	}
	
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return mStrs.size();
		}

		@Override
		public Object getItem(int position) {

			return mStrs.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			String str=mStrs.get(position);
			ViewHolder viewHolder;
			if(convertView==null){
				viewHolder=new ViewHolder();
				convertView=inflate.inflate(R.layout.item_listview, null);
				viewHolder.tv=(TextView) convertView.findViewById(R.id.tv_view);
				viewHolder.iv=(ImageView) convertView.findViewById(R.id.iv_view);
				convertView.setTag(viewHolder);
			}else{
				viewHolder=(ViewHolder) convertView.getTag();
			}
			viewHolder.tv.setText(str);
			return convertView;
		}
		
		
	}
	private static class ViewHolder{
		public TextView tv;
		public ImageView iv;
	}
	
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		//课件第二个
		int top=0;
		//获取要替代的view
		View childView=view.getChildAt(1);
		int height=mLinearLayout.getHeight();
		mTextView.setText(mStrs.get(firstVisibleItem));
		if(childView!=null){	
			top=childView.getTop();
			//获取MarginLayoutParams设置margin属性
			MarginLayoutParams params = (MarginLayoutParams) mLinearLayout.getLayoutParams();
			if(top<height){
				int mar=top-height;
				//mLinearLayout.setPadding(0,mar,0,0);
				params.topMargin = (int) mar;
				mLinearLayout.setLayoutParams(params);
			}else{
				params.topMargin = 0;
				mLinearLayout.setLayoutParams(params);
				//mLinearLayout.setPadding(0,0,0,0);
			}
		}
	}

}
