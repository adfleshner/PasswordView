package com.flesh.passwordView.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;

/**
 * Created by aaronfleshner on 11/17/15.
 */
public class PasswordView extends LinearLayout {

    private EditText passwordView;
    private CheckBox passwordCheckBox;
    private String text, hint = "";

    public PasswordView(Context context) {
        super(context);
        init(context);
    }

    public PasswordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initAtts(context, attrs);
    }

    public PasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initAtts(context, attrs);
    }

    @TargetApi(21)
    public PasswordView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
        initAtts(context, attrs);
    }

    public void init(Context context) {
        passwordView = new EditText(context);
        passwordCheckBox = new CheckBox(context);
        passwordView.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        passwordView.setTypeface(Typeface.MONOSPACE);
        passwordView.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwordView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        passwordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // show password
                    passwordView.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    passwordView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

    public void initAtts(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.passwordView);
        try {
            hint = a.getString(R.styleable.passwordView_hint);
            text = a.getString(R.styleable.passwordView_text);
        } catch (Exception e) {

        }

        a.recycle();
        passwordView.setText(text);
        passwordView.setHint(hint);
        updateView();
    }

    public CheckBox getPasswordCheckBox() {
        return passwordCheckBox;
    }

    public EditText getPasswordView() {
        return passwordView;
    }

    public void setPasswordHint(String hint) {
        this.hint = hint;
        passwordView.setHint(hint);
        updateView();
    }

    public void setPasswordText(String text) {
        this.text = text;
        passwordView.setText(text);
        updateView();
    }

    public void updateView() {
        this.invalidate();
        this.requestLayout();
    }
}
