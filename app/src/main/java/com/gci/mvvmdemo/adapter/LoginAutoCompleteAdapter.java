package com.gci.mvvmdemo.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;


import com.gci.mvvmdemo.myWidget.LoginAutoCompleteEdittext;
import com.gci.mvvmdemo.model.LoginInfo;
import com.gci.mvvmdemo.R;

import java.util.ArrayList;

public class LoginAutoCompleteAdapter extends BaseAdapter implements Filterable {

	private Context context;

	private ArrayFilter mFilter;

	private ArrayList<LoginInfo> mAutoCompValues;// 所有的Item

	private ArrayList<String> mOriginalValues = new ArrayList<String>();// 所有的Item

	private ArrayList<String> mObjects = new ArrayList<String>();// 过滤后的item

	private final Object mLock = new Object();

	// private int maxMatch = 5;// 最多显示多少个选项,负数表示全部



	private LoginAutoCompleteEdittext mAutoComp;

	// 换肤所需的资源数组
	private int dropDownbg;
	private int pressColor;
	private int textColor;

	public LoginAutoCompleteAdapter(Context pContext, ArrayList<LoginInfo> pOriginalValues) {
		this.context = pContext;

		this.mAutoCompValues = pOriginalValues;
		for (LoginInfo model : mAutoCompValues) {
			mOriginalValues.add(model.getAccount());
			mObjects.add(model.getAccount());
		}


		dropDownbg = R.drawable.login_drop_2;

	}

	@Override
	public Filter getFilter() {
		if (mFilter == null) {
			mFilter = new ArrayFilter();
		}
		return mFilter;
	}

	public class ArrayFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence prefix) {
			FilterResults results = new FilterResults();

			if (prefix == null || prefix.length() == 0) {
				synchronized (mLock) {
					ArrayList<String> list = new ArrayList<String>(mOriginalValues);
					results.values = list;
					results.count = list.size();
					return results;
				}
			} else {
				String prefixString = prefix.toString().toLowerCase();
				final int count = mOriginalValues.size();
				final ArrayList<String> newValues = new ArrayList<String>(count);

				for (int i = 0; i < count; i++) {
					final String value = mOriginalValues.get(i);
					final String valueText = value.toLowerCase();

					// if (valueText.startsWith(prefixString)) {
					// // 源码 ,匹配开头
					// newValues.add(mOriginalValues.get(i));
					// }
					if (valueText.startsWith(prefixString) || valueText.contains(prefixString)) {
						// 源码 ,匹配开头
						newValues.add(mOriginalValues.get(i));
					}
					/*
					 * if (maxMatch > 0) { // 有数量限制 if (newValues.size() >
					 * maxMatch - 1) { break; } }
					 */
				}
				results.values = newValues;
				results.count = newValues.size();
			}

			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			mObjects = (ArrayList<String>) results.values;
			if (results.count > 0) {
				SharedPreferences userInfo = context.getSharedPreferences("user_info", 0);
				boolean mMenuFlag = userInfo.getBoolean("MenuFlag", false);
				if (mMenuFlag) {
					userInfo.edit().putBoolean("MenuFlag", false).commit();
				} else {
					notifyDataSetChanged();
				}
			} else {
				notifyDataSetInvalidated();
			}
		}
	}

	@Override
	public int getCount() {
		return mObjects.size();
	}

	@Override
	public Object getItem(int position) {
		// 此方法有误，尽量不要使用
		return mObjects.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		holder.tv.setText(mObjects.get(position));
		holder.iv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				deleteItem(position);
				// showDialog(position);
			}
		});


		return view;
	}

	private void deleteItem(int position) {

		// 从适配器删除
		mObjects.remove(position);
		mOriginalValues.remove(position);
		mAutoCompValues.remove(position);
		notifyDataSetChanged();
	}

	/*
	 * private void showDialog(final int position) {
	 * 
	 * String p_Title = context.getString(R.string.deleteItemTitle); String
	 * p_Message = context.getString(R.string.deleteItemMessage); new
	 * GciAlertDialog.Builder(context) .setTitle(p_Title) .setMessage(p_Message)
	 * .setPositiveButton(context.getString(R.string.confirm), new
	 * DialogInterface.OnClickListener() {
	 * 
	 * @Override public void onClick(DialogInterface dialog, int which) {
	 * deleteItem(position); } })
	 * .setNegativeButton(context.getString(R.string.cancel), null)
	 * .create().show(); }
	 */

	class ViewHolder {
		TextView tv;
		ImageView iv;
	}

	public LoginAutoCompleteEdittext getAutoComp() {
		return mAutoComp;
	}

	public void setAutoComp(LoginAutoCompleteEdittext pAutoComp) {
		this.mAutoComp = pAutoComp;
	}

}
