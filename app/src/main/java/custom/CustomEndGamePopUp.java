package custom;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import ir.progressivesoft.hangman.Home;
import ir.progressivesoft.hangman.R;

public class CustomEndGamePopUp extends Dialog implements OnClickListener {
    int brojacZvezdi;
    public Activity f21c;
    public Dialog f22d;
    public TextView dialogText;
    public Button no;
    String poruka;
    String tacnaRec;
    public TextView word;
    public Button yes;
    ImageView zvezde;
    ImageButton findout;

    public CustomEndGamePopUp(Activity a) {
        super(a);
        this.poruka = "آفرین";
        this.tacnaRec = "کلمه";
        this.f21c = a;
    }

    public void podaci(String krajnjaPoruka, String zadataRec, int pogresno) {
        this.poruka = krajnjaPoruka;
        this.tacnaRec = zadataRec;
        this.brojacZvezdi = pogresno;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.endgamepopup);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/CarterOne.ttf");
        this.dialogText = (TextView) findViewById(R.id.tvDialog);
        this.zvezde = (ImageView) findViewById(R.id.ivStars);
        findout = (ImageButton) findViewById(R.id.ibFindOutMore);
        findout.setVisibility(View.INVISIBLE);
        this.word = (TextView) findViewById(R.id.tvWord);
        this.word.setText(this.tacnaRec);
        if (this.brojacZvezdi == 7) {
            this.zvezde.setImageResource(R.drawable.stars_0);
        } else if (this.brojacZvezdi == 6 || this.brojacZvezdi == 5) {
            this.zvezde.setImageResource(R.drawable.stars_1);
        } else if (this.brojacZvezdi == 4 || this.brojacZvezdi == 3) {
            this.zvezde.setImageResource(R.drawable.stars_2);
        } else if (this.brojacZvezdi == 2 || this.brojacZvezdi == 1 || this.brojacZvezdi == 0) {
            this.zvezde.setImageResource(R.drawable.stars_3);
        } else {
            this.zvezde.setImageResource(R.drawable.stars_0);
        }
        this.dialogText.setTypeface(tf, 1);
        this.dialogText.setTextColor(-1);
        this.dialogText.setText(this.poruka);
        this.yes = (Button) findViewById(R.id.btn_yes);
        this.no = (Button) findViewById(R.id.btn_no);
        this.yes.setOnClickListener(this);
        this.no.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
//                getContext().startActivity(new Intent(getContext(), CustomType.class));
                this.f21c.finish();
                break;
            case R.id.btn_no:
                getContext().startActivity(new Intent(getContext(), CustomType.class));
                this.f21c.finish();
                break;
        }
        dismiss();
    }
}
