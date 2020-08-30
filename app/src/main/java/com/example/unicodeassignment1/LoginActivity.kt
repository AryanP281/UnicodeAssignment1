package com.example.unicodeassignment1

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.view.View
import android.widget.*

class LoginActivity : AppCompatActivity()
{
    private val USERNAME_BUNDLE_KEY : String = "USERNAME";
    private val PASSWORD_BUNDLE_KEY : String = "PASSWORD";

    private val submitClickListener : View.OnClickListener = object:View.OnClickListener
    {
        override fun onClick(v :View?)
        {
            //Checking if input is valid
            val views : Array<View?>? = validInput(); //Getting an array containing the views. If the entered credentials are invalid, the array will be null
            if(views == null)
                Toast.makeText(this@LoginActivity, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            else
            {
                val id : String = (views[0] as EditText).text.toString(); //Getting the entered id
                val password : String = (views[1] as EditText).text.toString(); //Getting the entered password

                //Saving the id and password
                val sharedPref : SharedPreferences = getPreferences(Context.MODE_PRIVATE);
                val prefEditor : SharedPreferences.Editor = sharedPref.edit();
                prefEditor.putString(USERNAME_BUNDLE_KEY, id); //Saving the id
                prefEditor.putString(PASSWORD_BUNDLE_KEY, password); //Saving the password
                prefEditor.apply(); //Saving the changes

                Toast.makeText(this@LoginActivity, "Welcome $id !", Toast.LENGTH_SHORT).show();
            }
        }

        private fun validInput() : Array<View?>?
        {
            val views : Array<View?>? = arrayOf<View?>(null, null); //The array containing the views

            //Checking if id is valid
            val idField : EditText = findViewById(R.id.login_id) as EditText;
            val id : String = idField.text.toString().trim(); //Getting the entered id with the leading and trailing whitespace removed
            if(id.length == 0) return null;
            else views?.set(0, idField); //Adding the id field to the views array

            //Checking if the password is valid
            val passwordField : EditText = findViewById(R.id.login_password) as EditText;
            val password : String = passwordField.text.toString().trim(); //Getting the entered password with the leading and trailing whitespace removed
            if(password.length == 0) return null;
            else views?.set(1, passwordField); //Adding the password field to the views array

            return views;
        }

    }; //Handles submit button clicks

    private val showPasswordTouchListener : View.OnTouchListener = object:View.OnTouchListener
    {
        override fun onTouch(v: View?, event: MotionEvent?): Boolean
        {
            when (event?.action)
            {
                MotionEvent.ACTION_DOWN -> findViewById<EditText>(R.id.login_password).setInputType(InputType.TYPE_CLASS_TEXT); //Making the password visible
                MotionEvent.ACTION_UP ->
                {
                    val passwordField: EditText = findViewById(R.id.login_password) as EditText;
                    passwordField.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD); //Hiding the password
                    passwordField.setSelection(passwordField.text.length); //Moving the cursor to the end
                };
            }

            return true;
        }
    }; //Toggles password visibility

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Setting the action bar title
        supportActionBar?.setTitle("Login");

        //Setting the click listener for the submit button
        findViewById<Button>(R.id.login_submit_btn).setOnClickListener(submitClickListener);

        //Setting the touch listener for the show password view
        findViewById<ImageButton>(R.id.login_show_password).setOnTouchListener(showPasswordTouchListener);

        //Restoring entered info
        if(savedInstanceState != null)
        {
            findViewById<EditText>(R.id.login_id).setText(savedInstanceState.getString(USERNAME_BUNDLE_KEY));
            findViewById<EditText>(R.id.login_password).setText(savedInstanceState.getString(PASSWORD_BUNDLE_KEY));
        }
    }

    override fun onSaveInstanceState(outState: Bundle)
    {
        super.onSaveInstanceState(outState)

        //Adding the entered password and username to the bundle
        outState.putString(USERNAME_BUNDLE_KEY, findViewById<EditText>(R.id.login_id).text.toString());
        outState.putString(PASSWORD_BUNDLE_KEY, findViewById<EditText>(R.id.login_password).text.toString());
    }
}