package com.example.task;

import static android.Manifest.permission.CALL_PHONE;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.Manifest.permission.CALL_PHONE;

import com.example.task.Model.Result;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserList extends AppCompatActivity {
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.Address)
    TextView address;
    @BindView(R.id.phone)
    TextView phone;
    ArrayList<Result> results = new ArrayList<>();
    Result result2;
    private static final int REQUEST_PHONE_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ButterKnife.bind(this);
        InternetConnetion();
        Intent intent = getIntent();
        result2 = (Result) intent.getSerializableExtra("valuess");
        String values[] = result2.getName().split("_");
        String names=values[0];
        if (results != null) {
            String value = result2.getEmail();
            name.setText(names.toLowerCase(Locale.ROOT) +result2.getName().replace(names,""));
            email.setText(value.toLowerCase(Locale.ROOT));
            address.setText(result2.getDdress());
            phone.setText(result2.getPhoneno());
        }
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://mail.google.com/mail/u/0/#inbox";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                String phoneNumber = "1234567890";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + result2.getPhoneno()));
                startActivity(intent);


            }
        });

    }
    void InternetConnetion(){
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        boolean connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
        if(connected){
            Toast.makeText(this, "Network Connected", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Network Connected Error", Toast.LENGTH_SHORT).show();
        }
    }
}