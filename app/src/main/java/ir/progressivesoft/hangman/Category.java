package ir.progressivesoft.hangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class Category extends AppCompatActivity {
    static String[] kategorije;
    CustomListAdapter adapter;
    int brojSkupljenihZvezdica;
    ArrayList kategorijeNiz;
    SharedPreferences pref;
    MediaPlayer press;
    TextView prikazBrojacaZvezdica;
    ListView prikazKategorija;
    ImageButton prodavnica;
    int statusKategorije;
    Typeface tf;
    boolean zvuk;

    private void openCategory(String openKategorija, int statusUnlocka) {
        if (statusUnlocka == 1) {
            if (Category.this.zvuk) {
                Category.this.press.start();
            }
            Intent zapocniIgricu = new Intent(Category.this.getApplicationContext(), Game.class);
            zapocniIgricu.putExtra("kategorija", openKategorija);
            Category.this.startActivity(zapocniIgricu);
            return;
        }
        UnlockGamePopUp cdd3 = new UnlockGamePopUp(Category.this);
        cdd3.podaci(openKategorija);
        cdd3.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        kategorije = new String[]{"حیوانات", "کشور ها", "خواننده ها", "فیلم ها", "بازیکنان فوتبال", "بازیگران", /*"Cities", "Celebrities",*/ "مشاهیر", /*"Sport", */"ماشین", "هری پاتر", "شغل ها", /*"Landmarks",*/ "کاراکتر", "کارتون ها", "برند ها"/*, "Games", "Marvel and DC", "Comics"*/};
        this.press = MediaPlayer.create(this, R.raw.press);
        this.prikazBrojacaZvezdica = (TextView) findViewById(R.id.tvStarsCounterCategory);
        this.prodavnica = (ImageButton) findViewById(R.id.ibGetToStore);
        this.tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "font/CarterOne.ttf");
        this.prikazKategorija = (ListView) findViewById(R.id.listView);
        this.prikazBrojacaZvezdica.setTypeface(this.tf, 1);
        this.prodavnica.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Category.this.startActivity(new Intent(Category.this.getApplicationContext(), Store.class));
            }
        });
        this.pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        this.brojSkupljenihZvezdica = this.pref.getInt("brojZvezdica", 0);
        this.prikazBrojacaZvezdica.setText(BuildConfig.FLAVOR + this.brojSkupljenihZvezdica);
        this.zvuk = this.pref.getBoolean("zvuk", true);
        this.kategorijeNiz = new ArrayList();
        for (Object add : kategorije) {
            this.kategorijeNiz.add(add);
        }
        this.adapter = new CustomListAdapter(this, this.kategorijeNiz, getApplicationContext());
        this.prikazKategorija.setAdapter(this.adapter);
        this.prikazKategorija.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = Category.kategorije[position];
                int i = -1;
                switch (str) {
                    case "فیلم ها":
                        openCategory(Category.kategorije[position], 1);
                        break;
                    case "هری پاتر":
                        Category.this.statusKategorije = Category.this.pref.getInt("Unlock" + "هری پاتر", 0);
                        openCategory("هری پاتر", Category.this.statusKategorije);
                        break;
                    case "کاراکتر":
                        Category.this.statusKategorije = Category.this.pref.getInt("Unlock" + "کاراکتر", 0);
                        openCategory("کاراکتر", Category.this.statusKategorije);
                        break;
                    case "کشور ها":
                        openCategory(Category.kategorije[position], 1);
                        break;
//                    case "Landmarks":
//                            i = 13;
//                            break;
                    case "مشاهیر":
                        openCategory(Category.kategorije[position], 1);
                        break;
//                    case "Celebrities":
//                        openCategory(Category.kategorije[position], 1);
//                        break;
                    case "ماشین":
                        Category.this.statusKategorije = Category.this.pref.getInt("Unlock" + "ماشین", 0);
                        openCategory("ماشین", Category.this.statusKategorije);
                        break;
                    case "شغل ها":
                        Category.this.statusKategorije = Category.this.pref.getInt("Unlock" + "شغل ها", 0);
                        openCategory("شغل ها", Category.this.statusKategorije);
                        break;
//                    case "Games":
//                            i = 17;
//                            break;
                    case "کارتون ها":
                        Category.this.statusKategorije = Category.this.pref.getInt("Unlock" + "کارتون ها", 0);
                        openCategory("کارتون ها", Category.this.statusKategorije);
                        break;
                    case "خواننده ها":
                        openCategory(Category.kategorije[position], 1);
                        break;
//                    case "Sport":
//                        openCategory(Category.kategorije[position], 1);
//                        break;
                    case "بازیکنان فوتبال":
                        openCategory("بازیکنان فوتبال", 1);
                        break;
//                    case "Marvel and DC":
//                            i = 18;
//                            break;
                    case "حیوانات":
                        openCategory(Category.kategorije[position], 1);
                            break;
                    case "بازیگران":
                        openCategory(Category.kategorije[position], 1);
                        break;
                    case "برند ها":
                        Category.this.statusKategorije = Category.this.pref.getInt("Unlock" + "برند ها", 0);
                        openCategory("برند ها", Category.this.statusKategorije);
                        break;
//                    case "Cities":
//                        openCategory(Category.kategorije[position], 1);
//                        break;
//                    case "Comics":
//                        Category.this.statusKategorije = Category.this.pref.getInt("Unlock" + Category.kategorije[position], 0);
//                        openCategory(Category.kategorije[position], Category.this.statusKategorije);
//                        break;
                }
//                switch(i) {
//                    case 13:
//                        Category.this.statusKategorije = Category.this.pref.getInt("Unlock" + Category.kategorije[position], 0);
//                        openCategory(Category.kategorije[position], Category.this.statusKategorije);
//                        break;
//                    case 17:
//                        Category.this.statusKategorije = Category.this.pref.getInt("Unlock" + Category.kategorije[position], 0);
//                        openCategory(Category.kategorije[position], Category.this.statusKategorije);
//                        break;
//                    case 18:
//                        Category.this.statusKategorije = Category.this.pref.getInt("Unlock" + Category.kategorije[position], 0);
//                        openCategory(Category.kategorije[position], Category.this.statusKategorije);
//                        break;
//                    default:
//                }
            }
        });
    }
    protected void onResume() {
        super.onResume();
        this.brojSkupljenihZvezdica = this.pref.getInt("brojZvezdica", 0);
        this.prikazBrojacaZvezdica.setText(BuildConfig.FLAVOR + this.brojSkupljenihZvezdica);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case 4 /*4*/:
                startActivity(new Intent(getApplicationContext(), Home.class));
                finish();
                return true;
            default:
                return false;
        }
    }

}
