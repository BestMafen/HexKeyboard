package com.bestmafen.hexkeyboard;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * 为自定义的EditText用来显示UUID的特定格式( xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx )
 * 
 * @author xiaokai
 *
 */
public class UUIDEditText extends EditText {

	private boolean isRun = false;

	public UUIDEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setBankCardTypeOn();
	}

	public UUIDEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		setBankCardTypeOn();
	}

	public UUIDEditText(Context context) {
		super(context);
		setBankCardTypeOn();
	}

	public void setBankCardTypeOn() {
		UUIDEditText.this.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// 以下4句代码是为了防止在onTextChanged方法种调用setText()，又会触发onTextChanged，导致程序报错
				if (isRun) {
					isRun = false;
					return;
				}
				isRun = true;

				String text = s.toString().replace("-", "");
				int length = text.length();

				StringBuilder tem = new StringBuilder(text);

				if (length > 20) {
					tem.insert(8, "-").insert(13, "-").insert(18, "-")
							.insert(23, "-");
				} else if (length > 16) {
					tem.insert(8, "-").insert(13, "-").insert(18, "-");
				} else if (length > 12) {
					tem.insert(8, "-").insert(13, "-");
				} else if (length > 8) {
					tem.insert(8, "-");
				}
				String d = tem.toString();

				int i = getSelectionStart();
				// Log.i("TAG", "selection=" + i + ",count=" + count);
				setText(d);
				// 减少
				if (count == 0) {
					if (i == 9 || i == 14 || i == 19 || i == 24) {
						setSelection(i - 1);
					} else {
						setSelection(i);
					}
					// 增多
				} else {
					// 长度为1时，移到最后
					if (d.length() == 1) {
						setSelection(1);
					} else {
						if (i == 9 || i == 14 || i == 19 || i == 24) {
							setSelection(i + 1);
						} else {
							setSelection(i);
						}
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}
}
