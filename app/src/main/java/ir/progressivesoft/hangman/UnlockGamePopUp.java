package ir.progressivesoft.hangman;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class UnlockGamePopUp extends Dialog implements OnClickListener {
    int brojOsvojenihZvezdica;
    public Activity f19c;
    public Dialog f20d;
    public TextView dialogText;
    Editor editor;
    ImageButton getmore;
    String kategorijaPojma;
    public Button no;
    int noviBrojOsvojenihZvezdica;
    public TextView numberYourStars;
    SharedPreferences pref;
    ImageButton unlock;
    public Button yes;

    public UnlockGamePopUp(Activity a) {
        super(a);
        this.kategorijaPojma = BuildConfig.FLAVOR;
        this.f19c = a;
    }

    public void podaci(String kategorija) {
        this.kategorijaPojma = kategorija;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.unlockgamepopup);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.pref = getContext().getSharedPreferences("MyPref", 0);
        this.brojOsvojenihZvezdica = this.pref.getInt("brojZvezdica", 0);
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/CarterOne.ttf");
        this.dialogText = (TextView) findViewById(R.id.tvDialog);
        this.numberYourStars = (TextView) findViewById(R.id.tvNumStars);
        this.dialogText.setTypeface(tf, 1);
        this.dialogText.setTextColor(-1);
        this.dialogText.setText(this.kategorijaPojma);
        this.numberYourStars.setText("تعداد ستاره ها " + this.brojOsvojenihZvezdica);
        this.yes = (Button) findViewById(R.id.ibBackPop);
        this.no = (Button) findViewById(R.id.ibShopPop);
        this.unlock = (ImageButton) findViewById(R.id.ibUnlockPop);
        this.getmore = (ImageButton) findViewById(R.id.ibGetMorePop);
        this.yes.setOnClickListener(this);
        this.no.setOnClickListener(this);
        this.unlock.setOnClickListener(this);
        this.getmore.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibBackPop /*2131624118*/:
                dismiss();
                break;
            case R.id.ibShopPop /*2131624119*/:
//                getContext().startActivity(new Intent(getContext(), Store.class));
                break;
            case R.id.ibUnlockPop /*2131624121*/:
                Toast toast;
                if (this.brojOsvojenihZvezdica < 100) {
                    toast = Toast.makeText(getContext(), "ستاره هات کافی نیست! برای رفتن ستاره باید بیشتر بازی کنی یا به بازارچه سر بزنی!", Toast.LENGTH_SHORT);
                    toast.setGravity(17, 0, 0);
                    toast.show();
                    break;
                }
                this.noviBrojOsvojenihZvezdica = this.brojOsvojenihZvezdica - 100;
                this.editor = this.pref.edit();
                this.editor.putInt("Unlock" + this.kategorijaPojma, 1);
                this.editor.putInt("brojZvezdica", this.noviBrojOsvojenihZvezdica);
                this.editor.apply();
                toast = Toast.makeText(getContext(), "مرحله با موفقیت آزاد شد!", Toast.LENGTH_SHORT);
                toast.setGravity(17, 0, 0);
                toast.show();
                getContext().startActivity(new Intent(getContext(), Category.class));
                this.f19c.finish();
                break;
            case R.id.ibGetMorePop /*2131624122*/:
//                getContext().startActivity(new Intent(getContext(), Store.class));
                break;
        }
        dismiss();
    }

    public void onBackPressed() {
    }
}
