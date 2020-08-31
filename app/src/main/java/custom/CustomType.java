package custom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ir.progressivesoft.hangman.BackGamePopUp;
import ir.progressivesoft.hangman.BuildConfig;
import ir.progressivesoft.hangman.R;

public class CustomType extends AppCompatActivity implements OnClickListener, AnimationListener {
    Animation animFadein;
    boolean animacija;
    Button bA;
    Button bB;
    Button bC;
    Button bD;
    Button bDel;
    Button bE;
    Button bF;
    Button bG;
    Button bH;
    Button bI;
    Button bJ;
    Button bK;
    Button bL;
    Button bM;
    Button bN;
    Button bO;
    Button bP;
    Button bQ;
    Button bR;
    Button bS;
    Button bSpace;
    Button bT;
    Button bU;
    Button bV;
    Button bW;
    Button bX;
    Button bY;
    Button bZ;


    Button bJim;
    Button bCh;
    Button bKaf;
    Button bGaf;
    Button bVav;
    Button bPe;
    Button bZh;

    int brojOsvojenihZvezdica;
    Editor editor;
    ImageView hangman;
    TextView nastavi;
    TextView player;
    int playerBrojac;
    int playerNext;
    int playerNow;
    SharedPreferences pref;
    Typeface tf;
    String unesenaRec;
    String unesenoSlovo;
    EditText unos;
    boolean xdaPromocija;
    Toast toast;

    public CustomType() {
        this.unesenaRec = BuildConfig.FLAVOR;
        this.brojOsvojenihZvezdica = 0;
        this.xdaPromocija = true;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customtype);
        this.pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        this.tf = Typeface.createFromAsset(getAssets(), "font/CarterOne.ttf");
        inicijalizacijaTastature();
        this.hangman = (ImageView) findViewById(R.id.imageView);
        this.animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        this.animFadein.setAnimationListener(this);
        this.animacija = this.pref.getBoolean("Animacija", false);
        if (this.animacija) {
            this.hangman.startAnimation(this.animFadein);
        }
        this.unos = (EditText) findViewById(R.id.etUnos);
        this.unos.setKeyListener(null);
        this.nastavi = (TextView) findViewById(R.id.tvContinue);
        this.nastavi.setTypeface(this.tf, 1);
        this.nastavi.setOnClickListener(this);
        this.player = (TextView) findViewById(R.id.tvPlayer);
        this.player.setTypeface(this.tf, 1);
        this.xdaPromocija = this.pref.getBoolean("xdaPromocija", true);
        this.editor = this.pref.edit();
        this.playerBrojac = this.pref.getInt("playerBrojac", 1);
        if (this.playerBrojac == 2) {
            this.playerNow = 2;
            this.playerNext = 1;
            this.player.setText("بازیکن " + this.playerNow);
            this.editor.putInt("playerBrojac", this.playerNext);
            this.editor.commit();
        } else {
            this.playerNow = 1;
            this.playerNext = 2;
            this.player.setText("بازیکن " + this.playerNow);
            this.editor.putInt("playerBrojac", this.playerNext);
            this.editor.commit();
        }
        Toast toast = Toast.makeText(getApplicationContext(), "بازیکن " + this.playerNow + ": کلمه رو وارد کن", Toast.LENGTH_LONG);
        toast.setGravity(17, 0, 0);
        toast.show();
    }

    private void unesenaRecMetod() {
        this.unesenaRec += this.unesenoSlovo;
        this.unos.setText(this.unesenaRec);
    }

    private void inicijalizacijaTastature() {
        this.bQ = (Button) findViewById(R.id.bQ);
        this.bW = (Button) findViewById(R.id.bW);
        this.bE = (Button) findViewById(R.id.bE);
        this.bR = (Button) findViewById(R.id.bR);
        this.bT = (Button) findViewById(R.id.bT);
        this.bY = (Button) findViewById(R.id.bY);
        this.bU = (Button) findViewById(R.id.bU);
        this.bI = (Button) findViewById(R.id.bI);
        this.bO = (Button) findViewById(R.id.bO);
        this.bP = (Button) findViewById(R.id.bP);
        this.bA = (Button) findViewById(R.id.bA);
        this.bS = (Button) findViewById(R.id.bS);
        this.bD = (Button) findViewById(R.id.bD);
        this.bF = (Button) findViewById(R.id.bF);
        this.bG = (Button) findViewById(R.id.bG);
        this.bH = (Button) findViewById(R.id.bH);
        this.bJ = (Button) findViewById(R.id.bJ);
        this.bK = (Button) findViewById(R.id.bK);
        this.bL = (Button) findViewById(R.id.bL);
        this.bZ = (Button) findViewById(R.id.bZ);
        this.bX = (Button) findViewById(R.id.bX);
        this.bC = (Button) findViewById(R.id.bC);
        this.bV = (Button) findViewById(R.id.bV);
        this.bB = (Button) findViewById(R.id.bB);
        this.bN = (Button) findViewById(R.id.bN);
        this.bM = (Button) findViewById(R.id.bM);
        bJim = (Button) findViewById(R.id.bJim);
        bCh = (Button) findViewById(R.id.bCh);
        bKaf = (Button) findViewById(R.id.bKaf);
        bGaf = (Button) findViewById(R.id.bGaf);
        bVav = (Button) findViewById(R.id.bVav);
        bPe = (Button) findViewById(R.id.bPe);
        bZh = (Button) findViewById(R.id.bZh);
        this.bDel = (Button) findViewById(R.id.bDel);
        this.bSpace = (Button) findViewById(R.id.bSpace);
        this.bQ.setOnClickListener(this);
        this.bW.setOnClickListener(this);
        this.bE.setOnClickListener(this);
        this.bR.setOnClickListener(this);
        this.bT.setOnClickListener(this);
        this.bY.setOnClickListener(this);
        this.bU.setOnClickListener(this);
        this.bI.setOnClickListener(this);
        this.bO.setOnClickListener(this);
        this.bP.setOnClickListener(this);
        this.bA.setOnClickListener(this);
        this.bS.setOnClickListener(this);
        this.bD.setOnClickListener(this);
        this.bF.setOnClickListener(this);
        this.bG.setOnClickListener(this);
        this.bH.setOnClickListener(this);
        this.bJ.setOnClickListener(this);
        this.bK.setOnClickListener(this);
        this.bL.setOnClickListener(this);
        this.bZ.setOnClickListener(this);
        this.bX.setOnClickListener(this);
        this.bC.setOnClickListener(this);
        this.bV.setOnClickListener(this);
        this.bB.setOnClickListener(this);
        this.bN.setOnClickListener(this);
        this.bM.setOnClickListener(this);
        bJim.setOnClickListener(this);
        bCh.setOnClickListener(this);
        bKaf.setOnClickListener(this);
        bGaf.setOnClickListener(this);
        bVav.setOnClickListener(this);
        bPe.setOnClickListener(this);
        bZh.setOnClickListener(this);
        this.bDel.setOnClickListener(this);
        this.bSpace.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bQ /*2131624038*/:
                this.unesenoSlovo = "ض";
                unesenaRecMetod();
                break;
            case R.id.bW /*2131624039*/:
                this.unesenoSlovo = "ص";
                unesenaRecMetod();
                break;
            case R.id.bE /*2131624040*/:
                this.unesenoSlovo = "ث";
                unesenaRecMetod();
                break;
            case R.id.bR /*2131624041*/:
                this.unesenoSlovo = "ق";
                unesenaRecMetod();
                break;
            case R.id.bT /*2131624042*/:
                this.unesenoSlovo = "ف";
                unesenaRecMetod();
                break;
            case R.id.bY /*2131624043*/:
                this.unesenoSlovo = "غ";
                unesenaRecMetod();
                break;
            case R.id.bU /*2131624044*/:
                this.unesenoSlovo = "ع";
                unesenaRecMetod();
                break;
            case R.id.bI /*2131624045*/:
                this.unesenoSlovo = "ه";
                unesenaRecMetod();
                break;
            case R.id.bO /*2131624046*/:
                this.unesenoSlovo = "خ";
                unesenaRecMetod();
                break;
            case R.id.bP /*2131624047*/:
                this.unesenoSlovo = "ح";
                unesenaRecMetod();
                break;
            case R.id.bA /*2131624048*/:
                this.unesenoSlovo = "ش";
                unesenaRecMetod();
                break;
            case R.id.bS /*2131624049*/:
                this.unesenoSlovo = "س";
                unesenaRecMetod();
                break;
            case R.id.bD /*2131624050*/:
                this.unesenoSlovo = "ی";
                unesenaRecMetod();
                break;
            case R.id.bF /*2131624051*/:
                this.unesenoSlovo = "ب";
                unesenaRecMetod();
                break;
            case R.id.bG /*2131624052*/:
                this.unesenoSlovo = "ل";
                unesenaRecMetod();
                break;
            case R.id.bH /*2131624053*/:
                this.unesenoSlovo = "ا";
                unesenaRecMetod();
                break;
            case R.id.bJ /*2131624054*/:
                this.unesenoSlovo = "ت";
                unesenaRecMetod();
                break;
            case R.id.bK /*2131624055*/:
                this.unesenoSlovo = "ن";
                unesenaRecMetod();
                break;
            case R.id.bL /*2131624056*/:
                this.unesenoSlovo = "م";
                unesenaRecMetod();
                break;
            case R.id.bDel /*2131624057*/:
                if (this.unesenaRec.length() > 0) {
                    this.unesenaRec = this.unesenaRec.substring(0, this.unesenaRec.length() - 1);
                    this.unos.setText(this.unesenaRec);
                }
                break;
            case R.id.bZ /*2131624058*/:
                this.unesenoSlovo = "ظ";
                unesenaRecMetod();
                break;
            case R.id.bX /*2131624059*/:
                this.unesenoSlovo = "ط";
                unesenaRecMetod();
                break;
            case R.id.bC /*2131624060*/:
                this.unesenoSlovo = "ز";
                unesenaRecMetod();
                break;
            case R.id.bV /*2131624061*/:
                this.unesenoSlovo = "ر";
                unesenaRecMetod();
                break;
            case R.id.bB /*2131624062*/:
                this.unesenoSlovo = "ذ";
                unesenaRecMetod();
                break;
            case R.id.bN /*2131624063*/:
                this.unesenoSlovo = "د";
                unesenaRecMetod();
                break;
            case R.id.bM /*2131624064*/:
                this.unesenoSlovo = "ئ";
                unesenaRecMetod();
                break;
            case R.id.bSpace /*2131624065*/:
                this.unesenoSlovo = " ";
                unesenaRecMetod();
                break;
            case R.id.bJim /*2131624065*/:
                this.unesenoSlovo = "ج";
                unesenaRecMetod();
                break;
            case R.id.bCh /*2131624065*/:
                this.unesenoSlovo = "چ";
                unesenaRecMetod();
                break;
            case R.id.bKaf /*2131624065*/:
                this.unesenoSlovo = "ک";
                unesenaRecMetod();
                break;
            case R.id.bGaf /*2131624065*/:
                this.unesenoSlovo = "گ";
                unesenaRecMetod();
                break;
            case R.id.bVav /*2131624065*/:
                this.unesenoSlovo = "و";
                unesenaRecMetod();
                break;
            case R.id.bPe /*2131624065*/:
                this.unesenoSlovo = "پ";
                unesenaRecMetod();
                break;
            case R.id.bZh /*2131624065*/:
                this.unesenoSlovo = "ژ";
                unesenaRecMetod();
                break;
            case R.id.tvContinue /*2131624066*/:
                if (this.unesenaRec.length() > 0) {
                    Intent proslediRecIgrici = new Intent(getApplicationContext(), CustomGame.class);
                    proslediRecIgrici.putExtra("ukucanarec", this.unesenaRec);
                    proslediRecIgrici.putExtra("igrac", this.playerNext);
                    startActivity(proslediRecIgrici);
                    this.unesenaRec = BuildConfig.FLAVOR;
                    toast = Toast.makeText(getApplicationContext(), "بازیکن " + this.playerNext + ": کلمه رو حدس بزن", Toast.LENGTH_LONG);
                    toast.setGravity(17, 0, 0);
                    toast.show();
                    finish();
                } else {
                    toast = Toast.makeText(getApplicationContext(), "برای شروع اول باید یه کلمه وارد کنی", Toast.LENGTH_LONG);
                    toast.setGravity(17, 0, 0);
                    toast.show();
                }
                break;
            default:
        }
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        if (animation == this.animFadein) {
            this.hangman.startAnimation(this.animFadein);
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void BackGamePU() {
        BackGamePopUp cdd = new BackGamePopUp(this);
        cdd.podaci("مطمئنی میخوای از بازی\nخارج بشی؟");
        cdd.setCanceledOnTouchOutside(false);
        cdd.show();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case 4 /*4*/:
                BackGamePU();
                return true;
            default:
                return false;
        }
    }
}
