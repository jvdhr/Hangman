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
import android.widget.ImageView;
import android.widget.TextView;

public class EndGamePopUp extends Dialog implements OnClickListener {
    int brojOsvojenihZvezdica;
    int brojacVrednost;
    int brojacZvezdi;
    public Activity f9c;
    public Dialog f10d;
    public TextView dialogText;
    Editor editor;
    String kategorijaPojma;
    public Button no;
    String poruka;
    SharedPreferences pref;
    ImageButton saznajVise;
    String tacnaRec;
    public TextView word;
    public Button yes;
    ImageView zvezde;

    public EndGamePopUp(Activity a) {
        super(a);
        this.poruka = "آفرین";
        this.tacnaRec = "کلمه";
        this.kategorijaPojma = BuildConfig.FLAVOR;
        this.brojacVrednost = 0;
        this.f9c = a;
    }

    public void podaci(String krajnjaPoruka, String zadataRec, String kategorija, int brojac, int ukupno, int pogresno) {
        this.poruka = krajnjaPoruka;
        this.tacnaRec = zadataRec;
        this.brojacZvezdi = pogresno;
        this.kategorijaPojma = kategorija;
        if (brojac == ukupno - 1) {
            this.brojacVrednost = 0;
        } else {
            this.brojacVrednost = brojac + 1;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.endgamepopup);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.pref = getContext().getSharedPreferences("MyPref", 0);
        this.brojOsvojenihZvezdica = this.pref.getInt("brojZvezdica", 0);
        this.saznajVise = (ImageButton) findViewById(R.id.ibFindOutMore);
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/CarterOne.ttf");
        this.dialogText = (TextView) findViewById(R.id.tvDialog);
        this.zvezde = (ImageView) findViewById(R.id.ivStars);
        this.word = (TextView) findViewById(R.id.tvWord);
        this.word.setText("کلمه: " + this.tacnaRec);
        this.editor = this.pref.edit();
        if (this.brojacZvezdi == 7) {
            this.zvezde.setImageResource(R.drawable.stars_0);
            this.editor.putInt("brojZvezdica", this.brojOsvojenihZvezdica + 0);
        } else if (this.brojacZvezdi == 6 || this.brojacZvezdi == 5) {
            this.zvezde.setImageResource(R.drawable.stars_1);
            this.editor.putInt("brojZvezdica", this.brojOsvojenihZvezdica + 1);
        } else if (this.brojacZvezdi == 4 || this.brojacZvezdi == 3) {
            this.zvezde.setImageResource(R.drawable.stars_2);
            this.editor.putInt("brojZvezdica", this.brojOsvojenihZvezdica + 2);
        } else if (this.brojacZvezdi == 2 || this.brojacZvezdi == 1 || this.brojacZvezdi == 0) {
            this.zvezde.setImageResource(R.drawable.stars_3);
            this.editor.putInt("brojZvezdica", this.brojOsvojenihZvezdica + 3);
        } else {
            this.zvezde.setImageResource(R.drawable.stars_0);
            this.editor.putInt("brojZvezdica", this.brojOsvojenihZvezdica + 0);
        }
        this.editor.apply();
        this.dialogText.setTypeface(tf, 1);
        this.dialogText.setTextColor(-1);
        this.dialogText.setText(this.poruka);
        this.yes = (Button) findViewById(R.id.btn_yes);
        this.no = (Button) findViewById(R.id.btn_no);
        this.yes.setOnClickListener(this);
        this.no.setOnClickListener(this);
        this.saznajVise.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes /*2131624022*/:
                this.f9c.finish();
                break;
            case R.id.btn_no /*2131624023*/:
                Intent intent = new Intent(getContext(), Game.class);
                intent.putExtra("kategorija", this.kategorijaPojma);
                getContext().startActivity(intent);
                this.f9c.finish();
                break;
            case R.id.ibFindOutMore /*2131624072*/:
                Intent findOut = new Intent(getContext(), FindOutMore.class);
                findOut.putExtra("kategorija", this.kategorijaPojma);
                findOut.putExtra("tacnaRec", this.tacnaRec);
                getContext().startActivity(findOut);
                this.f9c.finish();
                break;
        }
        dismiss();
    }

    public void onBackPressed() {
    }
}
