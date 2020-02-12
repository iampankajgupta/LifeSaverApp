package in.my.alertapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

public class register extends AppCompatActivity {
    EditText email,password,userName,aadharNumber;
    Button upload,register;
    ImageView image;
    private FirebaseAuth mAuth;
    String aadharNumberTxt,emailTxt,passwordTxt,userNameTxt;
    boolean aadharResult;
    Bitmap bitmap;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        userName = findViewById(R.id.userName);
        aadharNumber = findViewById(R.id.aadharNumber);
        upload = findViewById(R.id.upload);
        register = findViewById(R.id.register);
        image = findViewById(R.id.image);
        emailTxt = email.getText().toString();
        passwordTxt = password.getText().toString();

        aadharNumberTxt = aadharNumber.getText().toString();
        aadharResult = Veroheff.validateVerhoeff(aadharNumberTxt);


        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        firebaseAuth = FirebaseAuth.getInstance();

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                uploadImage();
        }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aadharNumberTxt.isEmpty() || passwordTxt.isEmpty() || userNameTxt.isEmpty() || emailTxt.isEmpty()){
                    Toast.makeText(register.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches()){
                    email.setError("Please Enter Valid email");
                    email.setText("");
                } else if (aadharResult==false){
                    aadharNumber.setError("Please Enter Valid Aadhar Number");
                }else{

/*                    mAuth.createUserWithEmailAndPassword(emailTxt,passwordTxt)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                }
                            });*/







//                   Intent intent1 = new Intent(register.this,HomeActivity.class);
//                    ByteArrayOutputStream _bs = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, _bs);
//                    intent1.putExtra("byteArray", _bs.toByteArray());
//                    startActivity(intent1);

//                    write to receiver Activity
//                    if(getIntent().hasExtra("byteArray")) {
//                        ImageView _imv= new ImageView(this);
//                        Bitmap _bitmap = BitmapFactory.decodeByteArray(
//                                getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);
//                        _imv.setImageBitmap(_bitmap);
//                    }
                }
            }
        });

    }

/*    private void uploadImage() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            image.setImageURI(imageUri);
    }
    }*/
}
