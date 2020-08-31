package ir.progressivesoft.hangman;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Store extends AppCompatActivity implements OnClickListener {
    static final int RC_REQUEST = 10001;
    static final String SKU_STARS100 = "stars100";
    static final String SKU_STARS1000 = "stars1000";
    static final String SKU_STARS1500 = "stars1500";
    static final String SKU_STARS300 = "stars300";
    static final String SKU_STARS500 = "stars500";
    static final String TAG = "Hangman";
    int brojKupljenihZvezdica;
    int brojOsvojenihZvezdica;
    ImageButton buy100;
    ImageButton buy1000;
    ImageButton buy1500;
    ImageButton buy300;
    ImageButton buy500;
    Editor editor;
    SharedPreferences pref;
    TextView prikazBrZvezdica;
    Typeface tf;
    Toast customtoast;


    public Store() {
        this.brojKupljenihZvezdica = 0;
        this.brojOsvojenihZvezdica = 0;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.store);
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        brojOsvojenihZvezdica = pref.getInt("brojZvezdica", 0);
        prikazBrZvezdica = (TextView) findViewById(R.id.tvStoreDisplayStars);
        tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "font/CarterOne.ttf");
        prikazBrZvezdica.setTypeface(tf, 1);
        buy100 = (ImageButton) findViewById(R.id.ibBuy100);
        buy300 = (ImageButton) findViewById(R.id.ibBuy300);
        buy500 = (ImageButton) findViewById(R.id.ibBuy500);
        buy1000 = (ImageButton) findViewById(R.id.ibBuy1000);
        buy1500 = (ImageButton) findViewById(R.id.ibBuy1500);
        buy100.setOnClickListener(this);
        buy300.setOnClickListener(this);
        buy500.setOnClickListener(this);
        buy1000.setOnClickListener(this);
        buy1500.setOnClickListener(this);
        editor = pref.edit();
        loadData();

        Context context = getApplicationContext();
        LayoutInflater inflater=getLayoutInflater();

        View custom_toast =inflater.inflate(R.layout.toast_custom, null);
        customtoast=new Toast(context);
        customtoast.setView(custom_toast);
        customtoast.setDuration(Toast.LENGTH_LONG);
    }


    public void updateUi() {
        this.brojOsvojenihZvezdica = this.pref.getInt("brojZvezdica", 0);
        this.prikazBrZvezdica.setText(BuildConfig.FLAVOR + this.brojOsvojenihZvezdica);
    }

    void loadData() {
        this.brojOsvojenihZvezdica = this.pref.getInt("brojZvezdica", 0);
        this.prikazBrZvezdica.setText(BuildConfig.FLAVOR + this.brojOsvojenihZvezdica);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibBuy100:
                editor.putInt("brojZvezdica", brojOsvojenihZvezdica + 100);
                editor.commit();
                customtoast.show();
                break;
            case R.id.ibBuy300:
                editor.putInt("brojZvezdica", brojOsvojenihZvezdica+ 300);
                editor.commit();
                customtoast.show();
                break;
            case R.id.ibBuy500:
                editor.putInt("brojZvezdica", brojOsvojenihZvezdica+ 500);
                editor.commit();
                customtoast.show();
                break;
            case R.id.ibBuy1000:
                editor.putInt("brojZvezdica", brojOsvojenihZvezdica+ 1000);
                editor.commit();
                customtoast.show();
                break;
            case R.id.ibBuy1500:
                editor.putInt("brojZvezdica", brojOsvojenihZvezdica+ 1500);
                editor.commit();
                customtoast.show();
                break;
            default:
        }
        updateUi();
    }

    protected void onResume() {
        super.onResume();
        loadData();
    }

    public void onBackPressed() {
        finish();
    }
}
