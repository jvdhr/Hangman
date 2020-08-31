package ir.progressivesoft.hangman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import custom.CustomType;

public class Home extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {
    boolean animacija;
    ImageView animation;
    int besplatneZvezdiceUkupno;
    String data;
    Editor editor;
    ImageView info;
    Animation ljuljanje;
    ImageView logo;
    int maskota;
    ImageView mute;
    boolean opcije;
    MediaPlayer ping;
    ImageButton player1;
    ImageButton player2;
    String[] podaci;
    SharedPreferences pref;
    MediaPlayer press;
    ImageView settings;
    Animation shakeLong;
    Animation shaking;
    int startCounter;
    ImageButton store;
    boolean zvuk;

    private void proveriMaskotu() {
        if (this.maskota == 1) {
            this.logo.setImageResource(R.drawable.f);
        } else if (this.maskota == 2) {
            this.logo.setImageResource(R.drawable.v);
        } else if (this.maskota == 3) {
            this.logo.setImageResource(R.drawable.z);
        } else if (this.maskota == 4) {
            this.logo.setImageResource(R.drawable.m);
        } else {
            this.logo.setImageResource(R.drawable.r);
        }
    }

    private void opcijeSlider(boolean opcije) {
        if (opcije) {
            this.mute.setVisibility(View.VISIBLE);
            this.animation.setVisibility(View.VISIBLE);
            findViewById(R.id.llSetings).setBackgroundResource(R.drawable.new_bg_button);
            return;
        }
        this.mute.setVisibility(View.INVISIBLE);
        this.animation.setVisibility(View.INVISIBLE);
        findViewById(R.id.llSetings).setBackgroundColor(0);
    }

    public void onAnimationEnd(Animation animation) {
        if (animation == this.ljuljanje) {
            this.logo.startAnimation(this.ljuljanje);
        }
        if (animation != this.shaking) {
        }
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void BackGamePU() {
        BackGamePopUp cdd2 = new BackGamePopUp(this);
        cdd2.podaci("مطمئنی میخوای از بازی\nخارج بشی؟");
        cdd2.setCanceledOnTouchOutside(false);
        cdd2.show();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.pref = getApplicationContext().getSharedPreferences("MyPref", 0);
            this.editor = this.pref.edit();
            this.editor.putBoolean("info", false);
            this.editor.apply();
        this.press = MediaPlayer.create(this, R.raw.press);
        this.player1 = (ImageButton) findViewById(R.id.ib1Player);
        this.player2 = (ImageButton) findViewById(R.id.ib2Player);
        this.store = (ImageButton) findViewById(R.id.ibOptions);
        this.settings = (ImageView) findViewById(R.id.ivSettings);
        this.mute = (ImageView) findViewById(R.id.ivMute);
        this.animation = (ImageView) findViewById(R.id.ivAnimation);
        info = (ImageView) findViewById(R.id.ivInfo);
        this.logo = (ImageView) findViewById(R.id.ivLogo);
        this.ljuljanje = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        this.shaking = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        this.shakeLong = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_fast);
        this.ljuljanje.setAnimationListener(this);
        this.shaking.setAnimationListener(this);
        this.shakeLong.setAnimationListener(this);
        this.player1.setOnClickListener(this);
        this.player2.setOnClickListener(this);
        this.store.setOnClickListener(this);
        this.settings.setOnClickListener(this);
        this.mute.setOnClickListener(this);
        this.animation.setOnClickListener(this);
        this.opcije = this.pref.getBoolean("opcije", true);
        opcijeSlider(this.opcije);
        this.animacija = this.pref.getBoolean("Animacija", false);
        this.maskota = this.pref.getInt("maskota", 1);
        proveriMaskotu();
        if (this.animacija) {
            this.logo.startAnimation(this.ljuljanje);
            this.animation.setBackgroundResource(R.drawable.new_btns_animation);
        }
        this.zvuk = this.pref.getBoolean("zvuk", true);
        if (this.zvuk) {
            this.mute.setBackgroundResource(R.drawable.new_btns_music);
        } else {
            this.mute.setBackgroundResource(R.drawable.new_btns_musicoff);
        }
        this.startCounter = this.pref.getInt("startCounter", 0);
        this.animation.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                boolean z = true;
                Home.this.animacija = Home.this.pref.getBoolean("Animacija", false);
                Toast toast;
                if (Home.this.animacija) {
                    toast = Toast.makeText(Home.this.getApplicationContext(), "انیمیشن غیرفعال شد\nمصرف باتری کمتر", Toast.LENGTH_SHORT);
                    toast.setGravity(17, 0, 0);
                    toast.show();
                    Home.this.logo.clearAnimation();
                    Home.this.finish();
                    Home.this.startActivity(Home.this.getIntent());
                } else {
                    toast = Toast.makeText(Home.this.getApplicationContext(), "انیمیشن فعال شد\nمصرف باتری بیشتر", Toast.LENGTH_SHORT);
                    toast.setGravity(17, 0, 0);
                    toast.show();
                    Home.this.logo.startAnimation(Home.this.ljuljanje);
                    Home.this.finish();
                    Home.this.startActivity(Home.this.getIntent());
                }
                Home.this.editor = Home.this.pref.edit();
                Editor editor = Home.this.editor;
                String str = "Animacija";
                if (Home.this.animacija) {
                    z = false;
                }
                editor.putBoolean(str, z);
                Home.this.editor.apply();
                return false;
            }
        });
    }

    public void onClick(View v) {
        boolean z = true;
        boolean z2 = false;
        Toast toast;
        switch (v.getId()) {
            case R.id.ivSettings /*2131624086*/:
                this.opcije = this.pref.getBoolean("opcije", true);
                if (this.opcije) {
                    if (this.opcije) {
                        z = false;
                    }
                    opcijeSlider(z);
                    this.editor = this.pref.edit();
                    this.editor.putBoolean("opcije", false);
                    this.editor.apply();
                    return;
                }
                if (!this.opcije) {
                    z2 = true;
                }
                opcijeSlider(z2);
                this.editor = this.pref.edit();
                this.editor.putBoolean("opcije", true);
                this.editor.apply();
                break;
            case R.id.ivMute /*2131624088*/:
                this.zvuk = this.pref.getBoolean("zvuk", true);
                if (this.zvuk) {
                    this.mute.setBackgroundResource(R.drawable.new_btns_musicoff);
                    this.zvuk = false;
                    this.editor = this.pref.edit();
                    this.editor.putBoolean("zvuk", false);
                    this.editor.apply();
                    return;
                }
                this.mute.setBackgroundResource(R.drawable.new_btns_music);
                this.zvuk = true;
                this.editor = this.pref.edit();
                this.editor.putBoolean("zvuk", true);
                this.editor.apply();
                break;
            case R.id.ivAnimation /*2131624090*/:
                if (this.maskota >= 4) {
                    this.maskota = 0;
                    toast = Toast.makeText(getApplicationContext(), "برای فعالسازی یا غیرفعالسازی انیمشین این دکمه رو نگه دار", Toast.LENGTH_SHORT);
                    toast.setGravity(17, 0, 0);
                    toast.show();
                } else {
                    this.maskota++;
                }
                proveriMaskotu();
                this.editor = this.pref.edit();
                this.editor.putInt("maskota", this.maskota);
                this.editor.apply();
                break;
            case R.id.ib1Player /*2131624091*/:
                this.player1.startAnimation(this.shaking);
                if (this.zvuk) {
                    this.press.start();
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Home.this.startActivity(new Intent(Home.this.getApplicationContext(), Category.class));
                        Home.this.finish();
                    }
                }, 400);
                break;
            case R.id.ib2Player /*2131624092*/:
                this.player2.startAnimation(this.shaking);
                if (this.zvuk) {
                    this.press.start();
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Home.this.startActivity(new Intent(Home.this.getApplicationContext(), CustomType.class));
                    }
                }, 400);
                break;
            case R.id.ibOptions /*2131624093*/:
                this.store.startAnimation(this.shaking);
                if (this.zvuk) {
                    this.press.start();
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Home.this.startActivity(new Intent(Home.this.getApplicationContext(), Store.class));
                    }
                }, 400);
                break;
            default:
        }
    }
}
