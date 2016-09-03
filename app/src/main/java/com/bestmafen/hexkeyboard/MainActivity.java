package com.bestmafen.hexkeyboard;

import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private KeyboardView mKeyKeyboardView;
    private UUIDEditText edt_uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mKeyKeyboardView = (KeyboardView) findViewById(R.id.keyboard_view);
        edt_uuid = (UUIDEditText) findViewById(R.id.edt_uuid);
        edt_uuid.setInputType(InputType.TYPE_NULL);// 不会弹出系统键盘
        edt_uuid.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                new KeyboardUtil(MainActivity.this, MainActivity.this, edt_uuid)
                        .showKeyboard();
                return false;
            }
        });
    }
}
