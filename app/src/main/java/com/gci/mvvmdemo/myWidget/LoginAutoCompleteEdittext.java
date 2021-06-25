package com.gci.mvvmdemo.myWidget;

import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.RelativeLayout;

import com.gci.mvvmdemo.R;
import com.gci.mvvmdemo.adapter.LoginAutoCompleteAdapter;


public class LoginAutoCompleteEdittext extends RelativeLayout {
	private Context context;

	private AutoCompleteTextView mAutoCompleteTextView;

	public LoginAutoCompleteEdittext(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initVarible();
		initViews();
	}

	private void initVarible() {
	}

	private void initViews() {
		LayoutInflater.from(context).inflate(R.layout.loginautoedit, this, true);
		mAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.user_name);
		float width = getResources().getDisplayMetrics().widthPixels; // 获得屏幕宽度

		float i = 0.55f;
		if (width <= 480) {
			i = 0.5f;
		}

//		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT ,
//				RelativeLayout.LayoutParams.WRAP_CONTENT);
//		mAutoCompleteTextView = new AutoCompleteTextView(context);
//		mAutoCompleteTextView.setThreshold(1);
//		mAutoCompleteTextView.setImeOptions(EditorInfo.IME_ACTION_NEXT);
//		mAutoCompleteTextView.setLayoutParams(params);
//		mAutoCompleteTextView.setGravity(TEXT_ALIGNMENT_TEXT_START);
//		// 设置最大文本50字符
//		mAutoCompleteTextView.setFilters(new InputFilter[] { new InputFilter.LengthFilter(50) });
//		// mAutoCompleteTextView.setPadding(10, 10, 0, 0);
//		mAutoCompleteTextView.setSingleLine(true);
//		mAutoCompleteTextView.setBackgroundDrawable(null);
//		if (width <= 480) {
//			mAutoCompleteTextView.setPadding(14, 25, 0, 0);
//		} else {
//			mAutoCompleteTextView.setPadding(14, 17, 0, 0);
//		}

		mAutoCompleteTextView.setDropDownBackgroundResource(R.drawable.login_drop_2);

		float dropSize = Float.parseFloat(context.getString(R.string.login_username_width));
		// 列表的长度
		i = 0.675f * dropSize;
		if (width <= 480) {
			i = 0.475f * dropSize;
		}
		mAutoCompleteTextView.setDropDownWidth((int) (width * i));
		// 列表的相对位置往下
		mAutoCompleteTextView.setDropDownVerticalOffset(13);
		mAutoCompleteTextView.setHint("R.string.login_username_hint");

//		mAutoCompleteTextView.setBackgroundColor(context.getResources().getColor(R.color.black));

//		// 下拉的图标
//		RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(100, RelativeLayout.LayoutParams.MATCH_PARENT);
//		p.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//		p.bottomMargin = 20;
//		ImageView iv = new ImageView(context);
//		iv.setLayoutParams(p);
//		iv.setScaleType(ScaleType.CENTER);
//		//iv.setBackgroundResource(R.drawable.login_select);
//		iv.setImageResource(R.drawable.login_select);
//		if (width <= 480) {
//			iv.setPadding(0, 10, 0, 0);
//		} else {
//			iv.setPadding(0, 17, 0, 0);
//		}
//		iv.setClickable(true);
//		iv.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				LoginAutoCompleteAdapter adapter = getAdapter();
//
//				if (Build.VERSION.SDK_INT < 15) {
//					Filter filter = adapter.getFilter();
//					// 显示全部
//					filter.filter("");
//				}
//				mAutoCompleteTextView.showDropDown();
//			}
//		});
//
//		this.addView(mAutoCompleteTextView);
//		this.addView(iv);
	}

	public void showDropDown(){
		LoginAutoCompleteAdapter adapter = getAdapter();
		if (Build.VERSION.SDK_INT < 15) {
			Filter filter = adapter.getFilter();
			// 显示全部
			filter.filter("");
		}
		mAutoCompleteTextView.showDropDown();
	}

	public void setSelection(int index) {
		mAutoCompleteTextView.setSelection(index);
	}

	public void setHint(String resid) {
		mAutoCompleteTextView.setHint(resid);
	}

	public Editable getText() {
		return mAutoCompleteTextView.getText();
	}

	public void setText(String text) {
		mAutoCompleteTextView.setText(text);
	}

	public void dismissDropDown() {
		mAutoCompleteTextView.dismissDropDown();
	}

	public boolean isPopupShowing() {
		return mAutoCompleteTextView.isPopupShowing();
	}

	public void setAdapter(LoginAutoCompleteAdapter adapter) {
		mAutoCompleteTextView.setAdapter(adapter);
	}

	public LoginAutoCompleteAdapter getAdapter() {
		return (LoginAutoCompleteAdapter) mAutoCompleteTextView.getAdapter();
	}

	public void setThreshold(int threshold) {
		mAutoCompleteTextView.setThreshold(threshold);
	}

	public AutoCompleteTextView getAutoCompleteTextView() {
		return mAutoCompleteTextView;
	}

}
