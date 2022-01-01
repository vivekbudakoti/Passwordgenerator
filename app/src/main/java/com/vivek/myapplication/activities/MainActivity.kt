package com.vivek.myapplication.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.vivek.myapplication.R


class MainActivity : AppCompatActivity() {

    companion object{
        private const val RC_SIGN_IN = 101
    }

    private lateinit var mAuth : FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient
    private lateinit var btn_login : Button


    var loginid = false

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Shared prefernce Reading value of loggedin to check
        val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        loginid = sh.getBoolean("loggedin",false)


        if(loginid == true){
            startActivity(Intent(this, Passgen_screen::class.java))
            finishAffinity()
        }

        setContentView(R.layout.activity_main)

        btn_login = findViewById(R.id.btn_signin)

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        mAuth = FirebaseAuth.getInstance()

        btn_login.setOnClickListener {
           // startActivity(Intent(this, Passgen_screen::class.java))
            signIn()
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("Sign in activity", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this,"${e}",Toast.LENGTH_LONG).show()
                Log.w("Sign in activity", "Google sign in failed", e)
            }
        }
    }

    @SuppressLint("WrongConstant")
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Sign in activity", "signInWithCredential:success")
                    val user = mAuth.currentUser
                    val name = user?.displayName
                    val id = user?.uid

                    //Sharedprefernce task for saving the loggedin status

                    val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)

                    val myEdit = sharedPreferences.edit()
                    myEdit.putBoolean("loggedin", true)
                    myEdit.putString("username",name)
                    myEdit.putString("userId", id.toString())
                    myEdit.commit()


                    Toast.makeText(this,"You have Successfully logged in",Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, Register::class.java))

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"Login failed. Try again!",Toast.LENGTH_LONG).show()
                    Log.w("Sign in activity", "signInWithCredential:failure", task.exception)

                }
            }
    }

}