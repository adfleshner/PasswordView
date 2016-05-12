package com.flesh.passwordview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;

/**
 * Created by aaronfleshner on 3/26/16. again and this time it is a little better and working pretty good! :)
 */
public class PasswordView extends LinearLayout {

    private String TAG = "Password View Tag";
    private EditText passwordView;
    private CheckBox passwordCheckBox;
    private String text, hint = "";
    private boolean showPass = false, showPassHover = false;
    private Drawable passwordCheckBoxDrawable;

    public PasswordView(Context context) {
        super(context);
        init(context);
    }

    public PasswordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initAttrs(context, attrs);
    }

    public PasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initAttrs(context, attrs);
    }

    @TargetApi(21)
    public PasswordView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
        initAttrs(context, attrs);
    }

    public void init(Context context) {
        this.setOrientation(HORIZONTAL);
        passwordView = new EditText(context);
        passwordCheckBox = new CheckBox(context);
        passwordCheckBox.setButtonDrawable(R.drawable.pwv_drawable);
        passwordView.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        passwordView.setTypeface(Typeface.MONOSPACE);
        passwordView.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        passwordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // show password
                    showPassword();
                } else {
                    // hide password
                    hidePassword();
                }
            }


            private void showPassword() {
                Log.d(TAG, "Show Password");
                passwordView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                passwordView.setSelection(passwordView.getText().length());
            }

            private void hidePassword() {
                Log.d(TAG, "Hide Password");
                passwordView.setTransformationMethod(PasswordTransformationMethod.getInstance());
                passwordView.setSelection(passwordView.getText().length());
            }

        });
        //add each view to the LinearLayout.
        this.addView(passwordView);
        this.addView(passwordCheckBox);
        updateView();
    }

    //init attrs that are in the attrs.xml file.
    public void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.passwordView);
        try {
            //String for the hint of the editText
            hint = a.getString(R.styleable.passwordView_pwv_pass_hint);
            //String for the text of the editText
            text = a.getString(R.styleable.passwordView_pwv_pass_text);
            //Boolean of the Checkbox
            showPass = a.getBoolean(R.styleable.passwordView_pwv_show_pass, false);
            showPassHover = a.getBoolean(R.styleable.passwordView_pwv_show_pass_hover, false);
            passwordCheckBoxDrawable = a.getDrawable(R.styleable.passwordView_pwv_drawable);
        } catch (Exception e) {

        }

        a.recycle();
        //set attrs if they are set in the xml.
        //Set the text in the edit text
        passwordView.setText(text);
        //Set the hint in the edit text
        passwordView.setHint(hint);
        //put cursor at the end of the text.
        passwordView.setSelection(passwordView.getText().length());
        //set the check box to whatever showPass is ( default false )
        passwordCheckBox.setChecked(showPass);

        if (passwordCheckBoxDrawable != null) {
            passwordCheckBox.setButtonDrawable(passwordCheckBoxDrawable);
        }

        if (showPassHover) {
            passwordCheckBox.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        Log.d(TAG, "DOWN");
                        passwordCheckBox.setChecked(true);
                        return true;
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        Log.d(TAG, "UP");
                        passwordCheckBox.setChecked(false);
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        }

        //update that view!
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

    public void setShowPass(Boolean showPass) {
        this.showPass = showPass;
        passwordCheckBox.setChecked(showPass);
        updateView();
    }

    public void updateView() {
        this.invalidate();
        this.requestLayout();
    }


}