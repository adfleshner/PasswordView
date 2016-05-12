# PasswordView
This is a simple password view that allows user to pull in an editText with password input type that is changeable by a simple click of a checkbox.

Usage:
```xml
    <com.flesh.passwordview.PasswordView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/passwordView"/>
```

```java
PasswordView passwordView;
passwordView = (PasswordView) findViewById(R.id.passwordView);

//To get the editText with in the PasswordView like this.
EditText text = passwordView.getPasswordView();

//To get the Checkbox with in the PasswordView like this.
Checkbox showPass = passwordView.getPasswordCheckBox();
```
