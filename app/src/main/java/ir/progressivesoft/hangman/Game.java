package ir.progressivesoft.hangman;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game extends AppCompatActivity implements OnClickListener, AnimationListener {
    Animation animFadein;
    boolean animacija;
    MediaPlayer applause;
    private AudioManager audio;
    Button bA;
    Button bB;
    Button bC;
    Button bD;
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
    MediaPlayer bell;
    int brojac;
    int brojacReklami;
    int brojacZvezdica;
    Editor editor;
    ImageView hangman;
    ImageButton hint;
    String hintRec;
    String izabranaKategorija;
    TextView kategorija;
    Context kontekst;
    int maskota;
    MediaPlayer no;
    int pogresno;
    SharedPreferences pref;
    TextView prikaz;
    String rastaviSlova;
    String rec;
    String[] recNiz;
    String[] recSlova;
    List<String> reciSlovaHint;
    String resenje;
    String sakrijRec;
    TextView starsCounter;
    String tempRastaviSlova;
    Typeface tf;
    int ukupnoReci;
    String unesenoSlovo;
    MediaPlayer yes;
    String zadataRec;
    int zvuk;
    boolean zvukApp;

    private void hintsLogic(boolean what, String slovo) {
        if (!what) {
            this.hintRec = this.hintRec.replace(slovo, BuildConfig.FLAVOR);
            this.recSlova = this.hintRec.split(BuildConfig.FLAVOR);
            this.reciSlovaHint = Arrays.asList(this.recSlova);
        } else if (this.brojacZvezdica < 5) {
            HintPopUp hintPop = new HintPopUp(this);
            hintPop.podaci("ستاره هات کافی نیست! میتونی به بازارچه سر بزنی یا بیشتر بازی کنی!");
            hintPop.show();
        } else {
            this.unesenoSlovo = (String) this.reciSlovaHint.get(new Random().nextInt(((this.reciSlovaHint.size() - 1) - 1) + 1) + 1);
            proveraUnesenogSlova();
            iskoriscenoDugme(this.unesenoSlovo);
            this.editor = this.pref.edit();
            this.brojacZvezdica -= 5;
            this.editor.putInt("brojZvezdica", this.brojacZvezdica);
            this.editor.apply();
            this.starsCounter.setText(BuildConfig.FLAVOR + this.brojacZvezdica);
            Toast hintMessage = Toast.makeText(getApplicationContext(), "راهنمایی: " + this.unesenoSlovo, Toast.LENGTH_LONG);
            hintMessage.setGravity(17, 0, 0);
            hintMessage.show();
        }
    }

    private void updateReciNaEkranu() {
        if (this.zadataRec.length() > 15) {
            this.rastaviSlova = this.sakrijRec.replaceFirst(" ", "\n");
            this.rastaviSlova = this.rastaviSlova.replace(BuildConfig.FLAVOR, " ").trim();
            this.resenje = this.zadataRec.replaceFirst(" ", "\n");
            this.resenje = this.resenje.replace(BuildConfig.FLAVOR, " ").trim();
        } else {
            this.rastaviSlova = this.sakrijRec.replace(BuildConfig.FLAVOR, " ").trim();
            this.resenje = this.zadataRec.replace(BuildConfig.FLAVOR, " ").trim();
        }
        if (this.tempRastaviSlova.equals(this.rastaviSlova)) {
            this.tempRastaviSlova = this.rastaviSlova;
            this.pogresno++;
            azurirajSliku();
        } else if (this.resenje.equals(this.rastaviSlova)) {
            if (this.zvukApp) {
                this.applause.start();
            }
            sacuvajBrojacKategoriju();
            EndGamePopUp cdd = new EndGamePopUp(this);
            cdd.podaci("آفرین!", this.zadataRec, this.izabranaKategorija, this.brojac, this.ukupnoReci, this.pogresno);
            cdd.setCanceledOnTouchOutside(false);
            cdd.show();
        } else {
            if (this.zvuk == 0) {
                this.zvuk++;
            } else if (this.zvukApp) {
                this.yes.start();
            }
            this.tempRastaviSlova = this.rastaviSlova;
        }
        this.prikaz.setText(this.rastaviSlova);
    }

    private void azurirajSliku() {
        if (this.pogresno == 1) {
            if (this.zvukApp) {
                this.no.start();
            }
            maskotaPromenaSlike(1);
        } else if (this.pogresno == 2) {
            if (this.zvukApp) {
                this.no.start();
            }
            maskotaPromenaSlike(2);
        } else if (this.pogresno == 3) {
            if (this.zvukApp) {
                this.no.start();
            }
            maskotaPromenaSlike(3);
        } else if (this.pogresno == 4) {
            if (this.zvukApp) {
                this.no.start();
            }
            maskotaPromenaSlike(4);
        } else if (this.pogresno == 5) {
            if (this.zvukApp) {
                this.no.start();
            }
            maskotaPromenaSlike(5);
        } else if (this.pogresno == 6) {
            if (this.zvukApp) {
                this.no.start();
            }
            maskotaPromenaSlike(6);
        } else if (this.pogresno >= 7) {
            maskotaPromenaSlike(7);
            sacuvajBrojacKategoriju();
            EndGamePopUp cdd = new EndGamePopUp(this);
            cdd.podaci("اشتباهه!", this.zadataRec, this.izabranaKategorija, this.brojac, this.ukupnoReci, this.pogresno);
            cdd.setCanceledOnTouchOutside(false);
            cdd.show();
            if (this.zvukApp) {
                this.bell.start();
            }
        }
    }

    private void maskotaPromenaSlike(int broj) {
        switch (broj) {
            case 1 /*1*/:
                if (this.maskota == 1) {
                    this.hangman.setImageResource(R.drawable.f1);
                } else if (this.maskota == 2) {
                    this.hangman.setImageResource(R.drawable.v1);
                } else if (this.maskota == 3) {
                    this.hangman.setImageResource(R.drawable.z1);
                } else if (this.maskota == 4) {
                    this.hangman.setImageResource(R.drawable.m1);
                } else {
                    this.hangman.setImageResource(R.drawable.r1);
                }
                break;
            case 2 /*2*/:
                if (this.maskota == 1) {
                    this.hangman.setImageResource(R.drawable.f2);
                } else if (this.maskota == 2) {
                    this.hangman.setImageResource(R.drawable.v2);
                } else if (this.maskota == 3) {
                    this.hangman.setImageResource(R.drawable.z2);
                } else if (this.maskota == 4) {
                    this.hangman.setImageResource(R.drawable.m2);
                } else {
                    this.hangman.setImageResource(R.drawable.r2);
                }
                break;
            case 3 /*3*/:
                if (this.maskota == 1) {
                    this.hangman.setImageResource(R.drawable.f3);
                } else if (this.maskota == 2) {
                    this.hangman.setImageResource(R.drawable.v3);
                } else if (this.maskota == 3) {
                    this.hangman.setImageResource(R.drawable.z3);
                } else if (this.maskota == 4) {
                    this.hangman.setImageResource(R.drawable.m3);
                } else {
                    this.hangman.setImageResource(R.drawable.r3);
                }
                break;
            case 4 /*4*/:
                if (this.maskota == 1) {
                    this.hangman.setImageResource(R.drawable.f4);
                } else if (this.maskota == 2) {
                    this.hangman.setImageResource(R.drawable.v4);
                } else if (this.maskota == 3) {
                    this.hangman.setImageResource(R.drawable.z4);
                } else if (this.maskota == 4) {
                    this.hangman.setImageResource(R.drawable.m4);
                } else {
                    this.hangman.setImageResource(R.drawable.r4);
                }
                break;
            case 5 /*5*/:
                if (this.maskota == 1) {
                    this.hangman.setImageResource(R.drawable.f5);
                } else if (this.maskota == 2) {
                    this.hangman.setImageResource(R.drawable.v5);
                } else if (this.maskota == 3) {
                    this.hangman.setImageResource(R.drawable.z5);
                } else if (this.maskota == 4) {
                    this.hangman.setImageResource(R.drawable.m5);
                } else {
                    this.hangman.setImageResource(R.drawable.r5);
                }
                break;
            case 6/*6*/:
                if (this.maskota == 1) {
                    this.hangman.setImageResource(R.drawable.f6);
                } else if (this.maskota == 2) {
                    this.hangman.setImageResource(R.drawable.v6);
                } else if (this.maskota == 3) {
                    this.hangman.setImageResource(R.drawable.z6);
                } else if (this.maskota == 4) {
                    this.hangman.setImageResource(R.drawable.m6);
                } else {
                    this.hangman.setImageResource(R.drawable.r6);
                }
                break;
            case 7 /*7*/:
                if (this.maskota == 1) {
                    this.hangman.setImageResource(R.drawable.f7);
                } else if (this.maskota == 2) {
                    this.hangman.setImageResource(R.drawable.v7);
                } else if (this.maskota == 3) {
                    this.hangman.setImageResource(R.drawable.z7);
                } else if (this.maskota == 4) {
                    this.hangman.setImageResource(R.drawable.m7);
                } else {
                    this.hangman.setImageResource(R.drawable.r7);
                }
                break;
            default:
                if (this.maskota == 1) {
                    this.hangman.setImageResource(R.drawable.f7);
                } else if (this.maskota == 2) {
                    this.hangman.setImageResource(R.drawable.v7);
                } else if (this.maskota == 3) {
                    this.hangman.setImageResource(R.drawable.z7);
                } else if (this.maskota == 4) {
                    this.hangman.setImageResource(R.drawable.m7);
                } else {
                    this.hangman.setImageResource(R.drawable.r7);
                }
                break;
        }
    }

    private void proveraUnesenogSlova() {
        int i = -1;
        while (true) {
            i = this.zadataRec.indexOf(this.unesenoSlovo, i + 1);
            if (i != -1) {
                this.sakrijRec = this.sakrijRec.substring(0, i) + this.unesenoSlovo + this.sakrijRec.substring(i + 1);
            } else {
                hintsLogic(false, this.unesenoSlovo);
                updateReciNaEkranu();
                return;
            }
        }
    }

    private void sacuvajBrojacKategoriju() {
        this.editor = this.pref.edit();
        this.editor.putInt("brojOdigranihIgrica", this.brojacReklami + 1);
        if (this.brojac != this.ukupnoReci - 1) {
            this.editor.putInt(this.izabranaKategorija, this.brojac + 1);
        } else {
            this.editor.putInt(this.izabranaKategorija, 0);
        }
        this.editor.apply();
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
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bQ /*2131624038*/:
                this.unesenoSlovo = "ض";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bW /*2131624039*/:
                this.unesenoSlovo = "ص";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bE /*2131624040*/:
                this.unesenoSlovo = "ث";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bR /*2131624041*/:
                this.unesenoSlovo = "ق";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bT /*2131624042*/:
                this.unesenoSlovo = "ف";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bY /*2131624043*/:
                this.unesenoSlovo = "غ";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bU /*2131624044*/:
                this.unesenoSlovo = "ع";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bI /*2131624045*/:
                this.unesenoSlovo = "ه";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bO /*2131624046*/:
                this.unesenoSlovo = "خ";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bP /*2131624047*/:
                this.unesenoSlovo = "ح";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bA /*2131624048*/:
                this.unesenoSlovo = "ش";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bS /*2131624049*/:
                this.unesenoSlovo = "س";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bD /*2131624050*/:
                this.unesenoSlovo = "ی";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bF /*2131624051*/:
                this.unesenoSlovo = "ب";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bG /*2131624052*/:
                this.unesenoSlovo = "ل";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bH /*2131624053*/:
                this.unesenoSlovo = "ا";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bJ /*2131624054*/:
                this.unesenoSlovo = "ت";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bK /*2131624055*/:
                this.unesenoSlovo = "ن";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bL /*2131624056*/:
                this.unesenoSlovo = "م";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bZ /*2131624058*/:
                this.unesenoSlovo = "ش";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bX /*2131624059*/:
                this.unesenoSlovo = "ط";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bC /*2131624060*/:
                this.unesenoSlovo = "ز";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bV /*2131624061*/:
                this.unesenoSlovo = "ر";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bB /*2131624062*/:
                this.unesenoSlovo = "ذ";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bN /*2131624063*/:
                this.unesenoSlovo = "د";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bM /*2131624064*/:
                this.unesenoSlovo = "ئ";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bJim /*2131624064*/:
                this.unesenoSlovo = "ج";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bCh/*2131624064*/:
                this.unesenoSlovo = "چ";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bKaf /*2131624064*/:
                this.unesenoSlovo = "ک";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bGaf /*2131624064*/:
                this.unesenoSlovo = "گ";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bVav /*2131624064*/:
                this.unesenoSlovo = "و";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bPe /*2131624064*/:
                this.unesenoSlovo = "پ";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            case R.id.bZh /*2131624064*/:
                this.unesenoSlovo = "ژ";
                proveraUnesenogSlova();
                iskoriscenoDugme(this.unesenoSlovo);
                break;
            default:
        }
    }

    public void iskoriscenoDugme(String iskorisceno) {
        switch (iskorisceno) {
            case "ش"/*65*/:
                if (iskorisceno.equals("ش")) {
                    this.bA.setBackgroundResource(R.drawable.button_kbin);
                    this.bA.setTextColor(Color.parseColor("#e0b97c"));
                    this.bA.setClickable(false);
                    break;
                }
                break;
            case "ذ"/*66*/:
                if (iskorisceno.equals("ذ")) {
                    this.bB.setBackgroundResource(R.drawable.button_kbin);
                    this.bB.setTextColor(Color.parseColor("#e0b97c"));
                    this.bB.setClickable(false);
                    break;
                }
                break;
            case "ز" /*67*/:
                if (iskorisceno.equals("ز")) {
                    this.bC.setBackgroundResource(R.drawable.button_kbin);
                    this.bC.setTextColor(Color.parseColor("#e0b97c"));
                    this.bC.setClickable(false);
                    break;
                }
                break;
            case "ی" /*68*/:
                if (iskorisceno.equals("ی")) {
                    this.bD.setBackgroundResource(R.drawable.button_kbin);
                    this.bD.setTextColor(Color.parseColor("#e0b97c"));
                    this.bD.setClickable(false);
                }
                break;
            case "ث" /*69*/:
                if (iskorisceno.equals("ث")) {
                    this.bE.setBackgroundResource(R.drawable.button_kbin);
                    this.bE.setTextColor(Color.parseColor("#e0b97c"));
                    this.bE.setClickable(false);
                    break;
                }
                break;
            case "ب" /*70*/:
                if (iskorisceno.equals("ب")) {
                    this.bF.setBackgroundResource(R.drawable.button_kbin);
                    this.bF.setTextColor(Color.parseColor("#e0b97c"));
                    this.bF.setClickable(false);
                    break;
                }
                break;
            case "ل" /*71*/:
                if (iskorisceno.equals("ل")) {
                    this.bG.setBackgroundResource(R.drawable.button_kbin);
                    this.bG.setTextColor(Color.parseColor("#e0b97c"));
                    this.bG.setClickable(false);
                    break;
                }
                break;
            case "ا" /*72*/:
                if (iskorisceno.equals("ا")) {
                    this.bH.setBackgroundResource(R.drawable.button_kbin);
                    this.bH.setTextColor(Color.parseColor("#e0b97c"));
                    this.bH.setClickable(false);
                    break;
                }
                break;
            case "ه" /*73*/:
                if (iskorisceno.equals("ه")) {
                    this.bI.setBackgroundResource(R.drawable.button_kbin);
                    this.bI.setTextColor(Color.parseColor("#e0b97c"));
                    this.bI.setClickable(false);
                    break;
                }
                break;
            case "ت" /*74*/:
                if (iskorisceno.equals("ت")) {
                    this.bJ.setBackgroundResource(R.drawable.button_kbin);
                    this.bJ.setTextColor(Color.parseColor("#e0b97c"));
                    this.bJ.setClickable(false);
                    break;
                }
                break;
            case "ن" /*75*/:
                if (iskorisceno.equals("ن")) {
                    this.bK.setBackgroundResource(R.drawable.button_kbin);
                    this.bK.setTextColor(Color.parseColor("#e0b97c"));
                    this.bK.setClickable(false);
                    break;
                }
                break;
            case "م" /*76*/:
                if (iskorisceno.equals("م")) {
                    this.bL.setBackgroundResource(R.drawable.button_kbin);
                    this.bL.setTextColor(Color.parseColor("#e0b97c"));
                    this.bL.setClickable(false);
                    break;
                }
                break;
            case "ئ" /*77*/:
                if (iskorisceno.equals("ئ")) {
                    this.bM.setBackgroundResource(R.drawable.button_kbin);
                    this.bM.setTextColor(Color.parseColor("#e0b97c"));
                    this.bM.setClickable(false);
                    break;
                }
                break;
            case "د" /*78*/:
                if (iskorisceno.equals("د")) {
                    this.bN.setBackgroundResource(R.drawable.button_kbin);
                    this.bN.setTextColor(Color.parseColor("#e0b97c"));
                    this.bN.setClickable(false);
                    break;
                }
                break;
            case "خ" /*79*/:
                if (iskorisceno.equals("خ")) {
                    this.bO.setBackgroundResource(R.drawable.button_kbin);
                    this.bO.setTextColor(Color.parseColor("#e0b97c"));
                    this.bO.setClickable(false);
                    break;
                }
                break;
            case "ح" /*80*/:
                if (iskorisceno.equals("ح")) {
                    this.bP.setBackgroundResource(R.drawable.button_kbin);
                    this.bP.setTextColor(Color.parseColor("#e0b97c"));
                    this.bP.setClickable(false);
                    break;
                }
                break;
            case "ض" /*81*/:
                if (iskorisceno.equals("ض")) {
                    this.bQ.setBackgroundResource(R.drawable.button_kbin);
                    this.bQ.setTextColor(Color.parseColor("#e0b97c"));
                    this.bQ.setClickable(false);
                    break;
                }
                break;
            case "ق" /*82*/:
                if (iskorisceno.equals("ق")) {
                    this.bR.setBackgroundResource(R.drawable.button_kbin);
                    this.bR.setTextColor(Color.parseColor("#e0b97c"));
                    this.bR.setClickable(false);
                    break;
                }
                break;
            case "س" /*83*/:
                if (iskorisceno.equals("س")) {
                    this.bS.setBackgroundResource(R.drawable.button_kbin);
                    this.bS.setTextColor(Color.parseColor("#e0b97c"));
                    this.bS.setClickable(false);
                    break;
                }
                break;
            case "ف" /*84*/:
                if (iskorisceno.equals("ف")) {
                    this.bT.setBackgroundResource(R.drawable.button_kbin);
                    this.bT.setTextColor(Color.parseColor("#e0b97c"));
                    this.bT.setClickable(false);
                    break;
                }
                break;
            case "ع" /*85*/:
                if (iskorisceno.equals("ع")) {
                    this.bU.setBackgroundResource(R.drawable.button_kbin);
                    this.bU.setTextColor(Color.parseColor("#e0b97c"));
                    this.bU.setClickable(false);
                    break;
                }
                break;
            case "ر" /*86*/:
                if (iskorisceno.equals("ر")) {
                    this.bV.setBackgroundResource(R.drawable.button_kbin);
                    this.bV.setTextColor(Color.parseColor("#e0b97c"));
                    this.bV.setClickable(false);
                    break;
                }
                break;
            case "ص" /*87*/:
                if (iskorisceno.equals("ص")) {
                    this.bW.setBackgroundResource(R.drawable.button_kbin);
                    this.bW.setTextColor(Color.parseColor("#e0b97c"));
                    this.bW.setClickable(false);
                    break;
                }
                break;
            case "ط" /*88*/:
                if (iskorisceno.equals("ط")) {
                    this.bX.setBackgroundResource(R.drawable.button_kbin);
                    this.bX.setTextColor(Color.parseColor("#e0b97c"));
                    this.bX.setClickable(false);
                }
                break;
            case "غ" /*89*/:
                if (iskorisceno.equals("غ")) {
                    this.bY.setBackgroundResource(R.drawable.button_kbin);
                    this.bY.setTextColor(Color.parseColor("#e0b97c"));
                    this.bY.setClickable(false);
                    break;
                }
                break;
            case "ظ" /*90*/:
                if (iskorisceno.equals("ظ")) {
                    this.bZ.setBackgroundResource(R.drawable.button_kbin);
                    this.bZ.setTextColor(Color.parseColor("#e0b97c"));
                    this.bZ.setClickable(false);
                    break;
                }
                break;
            case "ج" /*90*/:
                if (iskorisceno.equals("ج")) {
                    this.bJim.setBackgroundResource(R.drawable.button_kbin);
                    this.bJim.setTextColor(Color.parseColor("#e0b97c"));
                    this.bJim.setClickable(false);
                    break;
                }
                break;
            case "چ" /*90*/:
                if (iskorisceno.equals("چ")) {
                    this.bCh.setBackgroundResource(R.drawable.button_kbin);
                    this.bCh.setTextColor(Color.parseColor("#e0b97c"));
                    this.bCh.setClickable(false);
                    break;
                }
                break;
            case "ک" /*90*/:
                if (iskorisceno.equals("ک")) {
                    this.bKaf.setBackgroundResource(R.drawable.button_kbin);
                    this.bKaf.setTextColor(Color.parseColor("#e0b97c"));
                    this.bKaf.setClickable(false);
                    break;
                }
                break;
            case "گ" /*90*/:
                if (iskorisceno.equals("گ")) {
                    this.bGaf.setBackgroundResource(R.drawable.button_kbin);
                    this.bGaf.setTextColor(Color.parseColor("#e0b97c"));
                    this.bGaf.setClickable(false);
                    break;
                }
                break;
            case "و" /*90*/:
                if (iskorisceno.equals("و")) {
                    this.bVav.setBackgroundResource(R.drawable.button_kbin);
                    this.bVav.setTextColor(Color.parseColor("#e0b97c"));
                    this.bVav.setClickable(false);
                    break;
                }
                break;
            case "پ" /*90*/:
                if (iskorisceno.equals("پ")) {
                    this.bPe.setBackgroundResource(R.drawable.button_kbin);
                    this.bPe.setTextColor(Color.parseColor("#e0b97c"));
                    this.bPe.setClickable(false);
                    break;
                }
                break;
            case "ژ" /*90*/:
                if (iskorisceno.equals("ژ")) {
                    this.bZh.setBackgroundResource(R.drawable.button_kbin);
                    this.bZh.setTextColor(Color.parseColor("#e0b97c"));
                    this.bZh.setClickable(false);
                    break;
                }
                break;
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
        sacuvajBrojacKategoriju();
        BackGamePopUp cdd = new BackGamePopUp(this);
        cdd.podaci("مطمئنی میخوای از بازی\nخارج بشی؟");
        cdd.setCanceledOnTouchOutside(false);
        cdd.show();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case 4/*4*/:
                BackGamePU();
                return true;
            case 24 /*24*/:
                this.audio.adjustStreamVolume(3, 1, 1);
                return true;
            case 25 /*25*/:
                this.audio.adjustStreamVolume(3, -1, 1);
                return true;
            default:
                return false;
        }
    }

    private void vratiBrojacOdKategorije() {
        this.brojac = this.pref.getInt(this.izabranaKategorija, 0);
    }


    public Game() {
        this.sakrijRec = BuildConfig.FLAVOR;
        this.tempRastaviSlova = BuildConfig.FLAVOR;
        this.pogresno = 1;
        this.zvuk = 0;
        this.brojac = 0;
        this.brojacReklami = 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        forceRTLIfSupported(this);
        int i;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        this.zvukApp = this.pref.getBoolean("zvuk", true);
        this.starsCounter = (TextView) findViewById(R.id.tvStarsCounter);
        this.kategorija = (TextView) findViewById(R.id.tvCategory);
        this.prikaz = (TextView) findViewById(R.id.tvRec);
        this.audio = (AudioManager) getSystemService(AUDIO_SERVICE);
        this.hangman = (ImageView) findViewById(R.id.imageView);
        this.tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "font/CarterOne.ttf");
        this.hint = (ImageButton) findViewById(R.id.bHint);
        this.hint.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Game.this.hintsLogic(true, BuildConfig.FLAVOR);
            }
        });
        this.applause = MediaPlayer.create(this, R.raw.applause);
        this.bell = MediaPlayer.create(this, R.raw.bell);
        this.no = MediaPlayer.create(this, R.raw.no);
        this.yes = MediaPlayer.create(this, R.raw.yes);
        this.kontekst = getBaseContext();
        this.izabranaKategorija = getIntent().getStringExtra("kategorija");
        this.recNiz = CategoryDB.getCategoryTerms(this.kontekst, this.izabranaKategorija).split(";");
        this.ukupnoReci = this.recNiz.length;
        vratiBrojacOdKategorije();
        this.rec = this.recNiz[this.brojac];
        this.zadataRec = this.rec.toUpperCase();
        this.hintRec = this.zadataRec;
        this.hintRec = this.hintRec.replace(" ", BuildConfig.FLAVOR);
        this.recSlova = this.hintRec.split(BuildConfig.FLAVOR);
        this.reciSlovaHint = Arrays.asList(this.recSlova);
        this.kategorija.setText(this.izabranaKategorija);
        this.kategorija.setTypeface(this.tf, 1);
        this.brojacZvezdica = this.pref.getInt("brojZvezdica", 0);
        this.starsCounter.setText(BuildConfig.FLAVOR + this.brojacZvezdica);
        this.starsCounter.setTypeface(this.tf, 1);
        this.maskota = this.pref.getInt("maskota", 1);
        for (i = 0; i < this.zadataRec.length(); i++) {
            this.sakrijRec += "_";
        }
        i = -1;
        while (true) {
            i = this.zadataRec.indexOf(" ", i + 1);
            if (i == -1) {
                break;
            }
            this.sakrijRec = this.sakrijRec.substring(0, i) + " " + this.sakrijRec.substring(i + 1);
        }
        updateReciNaEkranu();
        inicijalizacijaTastature();
        this.animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        this.animFadein.setAnimationListener(this);
        this.animacija = this.pref.getBoolean("Animacija", false);
        if (this.animacija) {
            this.hangman.startAnimation(this.animFadein);
        }
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onPause() {
        super.onPause();
        this.bell.stop();
        this.applause.stop();
        this.yes.stop();
        this.no.release();
    }

    protected void onRestart() {
        super.onRestart();
    }

    protected void onResume() {
        super.onResume();
        this.applause = MediaPlayer.create(this, R.raw.applause);
        this.bell = MediaPlayer.create(this, R.raw.bell);
        this.no = MediaPlayer.create(this, R.raw.no);
        this.yes = MediaPlayer.create(this, R.raw.yes);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void forceRTLIfSupported(AppCompatActivity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            activity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }
}
