package com.bestmafen.hexkeyboard;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

public class KeyboardUtil {
	private KeyboardView mKeyboardView;
	private Keyboard mKeyboard;// 键盘
	private EditText edt;

	public KeyboardUtil(Activity activity, Context contexxt, EditText edt) {
		this.edt = edt;
		mKeyboard = new Keyboard(contexxt, R.xml.keyboard_16jinzhi);
		mKeyboardView = (KeyboardView) activity
				.findViewById(R.id.keyboard_view);
		mKeyboardView.setKeyboard(mKeyboard);
		mKeyboardView.setEnabled(true);
		mKeyboardView.setPreviewEnabled(true);
		mKeyboardView.setOnKeyboardActionListener(listener);
	}

	private OnKeyboardActionListener listener = new OnKeyboardActionListener() {
		@Override
		public void swipeUp() {
		}

		@Override
		public void swipeRight() {
		}

		@Override
		public void swipeLeft() {
		}

		@Override
		public void swipeDown() {
		}

		@Override
		public void onText(CharSequence text) {
		}

		@Override
		public void onRelease(int primaryCode) {
		}

		@Override
		public void onPress(int primaryCode) {
		}

		@Override
		public void onKey(int primaryCode, int[] keyCodes) {
			Editable editable = edt.getText();
			int start = edt.getSelectionStart();
			// 按完成，隐藏键盘
			if (primaryCode == Keyboard.KEYCODE_CANCEL) {
				hideKeyboard();
				// 按回退，删除一个字符
			} else if (primaryCode == Keyboard.KEYCODE_DELETE) {
				if (editable != null && editable.length() > 0) {
					if (start > 0) {
						editable.delete(start - 1, start);
					}
				}
			} else {
				editable.insert(start, Character.toString((char) primaryCode));
			}
		}
	};

	/**
	 * 弹出键盘
	 */
	public void showKeyboard() {
		int visibility = mKeyboardView.getVisibility();
		if (visibility == View.GONE || visibility == View.INVISIBLE) {
			mKeyboardView.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 隐藏键盘
	 */
	public void hideKeyboard() {
		int visibility = mKeyboardView.getVisibility();
		if (visibility == View.VISIBLE) {
			mKeyboardView.setVisibility(View.INVISIBLE);
		}
	}
}
