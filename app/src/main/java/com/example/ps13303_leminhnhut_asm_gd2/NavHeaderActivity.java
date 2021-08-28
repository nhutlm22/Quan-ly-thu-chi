//package com.example.ps13303_leminhnhut_asm_gd2;
//
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.bumptech.glide.Glide;
//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//
//public class NavHeaderActivity extends AppCompatActivity {
//    ImageView imageView;
//    TextView textView;
//    Button btnDangXuat;
//    GoogleSignInClient mGoogleSignInClient;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.nav_header);
//
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//
//        imageView = findViewById(R.id.ivNavHeader);
//        textView = findViewById(R.id.tvNavHeader);
//        btnDangXuat = findViewById(R.id.btnDangXuat);
//
//        btnDangXuat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.btnDangXuat:
//                        signOut();
//                        break;
//                }
//            }
//        });
//
//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
//        if (acct != null) {
//            String personName = acct.getDisplayName();
//            Uri personPhoto = acct.getPhotoUrl();
//
//            textView.setText(personName);
//            Glide.with(this).load(String.valueOf(personPhoto)).into(imageView);
//
//    }}
//
//    private void signOut() {
//        mGoogleSignInClient.signOut()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(NavHeaderActivity.this, "Đăng xuất thành công!", Toast.LENGTH_LONG).show();
//                        finish();
//                    }
//                });
//    }
//
//}