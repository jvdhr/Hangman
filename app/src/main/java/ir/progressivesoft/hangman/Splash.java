package ir.progressivesoft.hangman;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Splash extends AppCompatActivity {
    static String izmesaniPojmovi;
    static InputStream pojmoviInputStream;
    int besplatneZvezdiceUkupno;
    Calendar calendar;
    int day;
    Editor editor;
    Intent intent;
    String jezik;
    String[] kategorije;
    String[] kategorijeSR;
    String lang;
    int lastDay;
    SharedPreferences pref;
    Boolean prvoUcitavanje;
    int startCounter;

    private void pokusajDaPromesasKategorije(int i) {
        String str = this.kategorije[i];
        Object obj = -1;
        switch (str.hashCode()) {
            case -1984392349:
                if (str.equals("Movies")) {
                    obj = 3;
                    break;
                }
                break;
            case -1462237332:
                if (str.equals("Harry Potter")) {
                    obj = 11;
                    break;
                }
                break;
            case -1056078198:
                if (str.equals("Characters")) {
                    obj = 14;
                    break;
                }
                break;
            case -938362220:
                if (str.equals("Countries")) {
                    obj = 1;
                    break;
                }
                break;
            case -865936357:
                if (str.equals("Landmarks")) {
                    obj = 13;
                    break;
                }
                break;
            case -426060349:
                if (str.equals("Influental People")) {
                    obj = 8;
                    break;
                }
                break;
            case -365752927:
                if (str.equals("Celebrities")) {
                    obj = 7;
                    break;
                }
                break;
            case 2092863:
                if (str.equals("Cars")) {
                    obj = 10;
                    break;
                }
                break;
            case 2314358:
                if (str.equals("Jobs")) {
                    obj = 12;
                    break;
                }
                break;
            case 68567713:
                if (str.equals("Games")) {
                    obj = 17;
                    break;
                }
                break;
            case 71987941:
                if (str.equals("Cartoons")) {
                    obj = 15;
                    break;
                }
                break;
            case 74710533:
                if (str.equals("Music")) {
                    obj = 2;
                    break;
                }
                break;
            case 80099156:
                if (str.equals("Sport")) {
                    obj = 9;
                    break;
                }
                break;
            case 698998676:
                if (str.equals("Players")) {
                    obj = 4;
                    break;
                }
                break;
            case 703766793:
                if (str.equals("Marvel and DC")) {
                    obj = 18;
                    break;
                }
                break;
            case 807717335:
                if (str.equals("Animals")) {
                    obj = 0;
                    break;
                }
                break;
            case 1955889470:
                if (str.equals("Actors")) {
                    obj = 5;
                    break;
                }
                break;
            case 1997804012:
                if (str.equals("Brands")) {
                    obj = 16;
                    break;
                }
                break;
            case 2018682729:
                if (str.equals("Cities")) {
                    obj = 6;
                    break;
                }
                break;
            case 2024015256:
                if (str.equals("Comics")) {
                    obj = 19;
                    break;
                }
                break;
        }
        if (obj.equals(0)) {
            pojmoviInputStream = getApplicationContext().getResources().openRawResource(R.raw.c1_animals);

        } else if (obj.equals(1)) {
            pojmoviInputStream = getApplicationContext().getResources().openRawResource(R.raw.c2_countries);

        } else if (obj.equals(2)) {
            pojmoviInputStream = getApplicationContext().getResources().openRawResource(R.raw.c3_music);

        } else if (obj.equals(3)) {
            pojmoviInputStream = getApplicationContext().getResources().openRawResource(R.raw.c4_movies);

        } else if (obj.equals(4)) {
            pojmoviInputStream = getApplicationContext().getResources().openRawResource(R.raw.c5_players);

        } else if (obj.equals(5)) {
            pojmoviInputStream = getApplicationContext().getResources().openRawResource(R.raw.c6_actors);

        } else if (obj.equals(6)) {
            pojmoviInputStream = getApplicationContext().getResources().openRawResource(R.raw.c7_cities);

        } else if (obj.equals(7)) {
            pojmoviInputStream = getApplicationContext().getResources().openRawResource(R.raw.c8_celebrities);

        } else if (obj.equals(8)) {
            pojmoviInputStream = getApplicationContext().getResources().openRawResource(R.raw.c9_influentialpeople);

        } else if (obj.equals(9)) {
            pojmoviInputStream = getApplicationContext().getResources().openRawResource(R.raw.c10_sport);

        } else if (obj.equals(10)) {
            pojmoviInputStream = getApplicationContext().getResources().openRawResource(R.raw.c11_cars);

        } else if (obj.equals(11)) {
            pojmoviInputStream = getApplicationContext().getResources().openRawResource(R.raw.c12_harrypotter);

        } else if (obj.equals(12)) {
            pojmoviInputStream = getApplicationContext().getResources().openRawResource(R.raw.c13_jobs);

        } else if (obj.equals(13)) {
            pojmoviInputStream = getApplicationContext().getResources().openRawResource(R.raw.c14_landmarks);

        } else if (obj.equals(14)) {
            pojmoviInputStream = getApplicationContext().getResources().openRawResource(R.raw.c15_moviecharacters);

        } else if (obj.equals(15)) {
            pojmoviInputStream = getApplicationContext().getResources().openRawResource(R.raw.c16_cartoons);

        } else if (obj.equals(16)) {
            pojmoviInputStream = getApplicationContext().getResources().openRawResource(R.raw.c17_brands);

        } else if (obj.equals(17)) {
            pojmoviInputStream = getApplicationContext().getResources().openRawResource(R.raw.c18_games);

        } else if (obj.equals(18)) {
            pojmoviInputStream = getApplicationContext().getResources().openRawResource(R.raw.c19_marveldc);

        } else if (obj.equals(19)) {
            pojmoviInputStream = getApplicationContext().getResources().openRawResource(R.raw.c20_comics);

        }
        List<String> listaReci = Arrays.asList(readTextFile(pojmoviInputStream).split(";"));
        Collections.shuffle(listaReci);
        izmesaniPojmovi = listaReci.toString();
        izmesaniPojmovi = izmesaniPojmovi.replace(", ", ";");
        izmesaniPojmovi = izmesaniPojmovi.replace("[", BuildConfig.FLAVOR);
        izmesaniPojmovi = izmesaniPojmovi.replace("]", BuildConfig.FLAVOR);
        WriteData(this.kategorije[i], izmesaniPojmovi);
    }

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buf = new byte[AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT];
        while (true) {
            try {
                int len = inputStream.read(buf);
                if (len == -1) {
                    break;
                }
                outputStream.write(buf, 0, len);
            } catch (IOException e) {
            }
        }
        try {
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }

    public void WriteData(String imeKategorije, String pojmovi) {
        try {
            OutputStreamWriter outputWriter = new OutputStreamWriter(openFileOutput(imeKategorije + ".txt", 0));
            outputWriter.write(pojmovi);
            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void freeStarsNewDay() {
        if (this.lastDay != this.day) {
            this.besplatneZvezdiceUkupno = this.pref.getInt("brojZvezdica", 0);
            Toast.makeText(getApplicationContext(), "15 ستاره رایگان روزانه دریافت کردید", Toast.LENGTH_SHORT).show();
            this.editor = this.pref.edit();
            this.editor.putInt("brojZvezdica", this.besplatneZvezdiceUkupno + 15);
            this.editor.putInt("freeDayStars", this.day);
            this.editor.apply();
            return;
        }
        Toast.makeText(getApplicationContext(), "فردا هم بازی کنید تا ستاره های رایگان بیشتری بگیرید.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        this.prvoUcitavanje = Boolean.valueOf(this.pref.getBoolean("shuffle", true));
        this.startCounter = this.pref.getInt("startCounter", 0);
        this.calendar = Calendar.getInstance();
        this.day = this.calendar.get(Calendar.DAY_OF_MONTH); //7
        this.lastDay = this.pref.getInt("freeDayStars", 100);
        this.editor = this.pref.edit();
        this.editor.putInt("startCounter", this.startCounter + 1);
        this.editor.apply();
        this.lang = this.pref.getString("izabraniJezik", "none");
        if (this.lang.equals("none")) {
            this.lang = Locale.getDefault().getLanguage();
        }
        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        switch (am.getRingerMode()) {
            case 0 /*0*/:
                am.setStreamVolume(3, 0, 0);
                break;
            case 1 /*1*/:
                am.setStreamVolume(3, 0, 0);
                break;
            case 2 /*2*/:
                if (am.getStreamVolume(3) == 0) {
                    am.setStreamVolume(3, (int) (((float) am.getStreamMaxVolume(3)) * 0.3f), 0);
                    break;
                }
                break;
        }
        if (this.prvoUcitavanje.booleanValue()) {
            int i;
            this.kategorije = new String[]{"Animals", "Countries", "Music", "Movies", "Players", "Actors", "Cities", "Celebrities", "Influental People", "Sport", "Cars", "Harry Potter", "Jobs", "Landmarks", "Characters", "Cartoons", "Brands", "Games", "Marvel and DC", "Comics"};
            for (i = 0; i < this.kategorije.length; i++) {
                try {
                    pokusajDaPromesasKategorije(i);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error" + e, Toast.LENGTH_LONG).show();
                }
            }
            this.editor = this.pref.edit();
            this.editor.putBoolean("shuffle", false);
            this.editor.putInt("brojZvezdica", 150);
            this.editor.apply();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Splash.this.freeStarsNewDay();
                Splash.this.intent = new Intent(Splash.this.getApplicationContext(), Home.class);
                Splash.this.startActivity(Splash.this.intent);
                Splash.this.finish();
            }
        }, 3000);
    }
}
