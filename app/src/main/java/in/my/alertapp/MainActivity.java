package in.my.alertapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    EditText email, password;
    TextView registerTxt;
    Button login;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerTxt = findViewById(R.id.register);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate(email.getText().toString(), password.getText().toString());
                startSignIn();
            }
        });

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()!=null){
                    Intent intent1 = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent1);
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    private void startSignIn() {
        String emailtxt = email.getText().toString();
        String passwordtxt = password.getText().toString();

        mAuth.signInWithEmailAndPassword(emailtxt,passwordtxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "SigIn Problem", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void Validate(String emailtxt, String passtxt) {
        if (emailtxt.isEmpty() || passtxt.isEmpty()) {
            if (emailtxt.isEmpty()) {
                email.setError("Fields cannot be empty");
                email.setText("");
            } else {
                password.setError("Fields cannot be empty");
                password.setText("");
            }
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailtxt).matches()) {
            email.setError("Please Enter Valid email");
            email.setText("");
        } else {
//            checkFirebaseForEmail();
        }
    }


    public void registerTxt(View v){
        Intent intent1 = new Intent(this,register.class);
        startActivity(intent1);
    }
    //    private void checkFirebaseForEmail() {
//        databaseReference.child("Users").child("id").child("email").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(!dataSnapshot.exists()){
//                    Toast.makeText(MainActivity.this, "User does not exits Please register", Toast.LENGTH_SHORT).show();
//                    progressBar.setVisibility(View.GONE);
//                }
//            }
}


