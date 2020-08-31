package ir.progressivesoft.hangman;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    Context context;
    private LayoutInflater inflater;
    private List<String> kategorije;
    SharedPreferences pref;
    int statusKategorije;
    Typeface tf;

    public CustomListAdapter(Activity activity, List<String> kategorije, Context context) {
        this.pref = context.getSharedPreferences("MyPref", 0);
        this.activity = activity;
        this.kategorije = kategorije;
        this.context = context;
    }

    public Object getItem(int location) {
        return this.kategorije.get(location);
    }

    public int getCount() {
        return this.kategorije.size();
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        this.tf = Typeface.createFromAsset(this.context.getAssets(), "font/CarterOne.ttf");
        if (this.inflater == null) {
            this.inflater = (LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.categorybutton, null);
        }
        TextView imeKategorije = (TextView) convertView.findViewById(R.id.tvImeKategorije);
        RelativeLayout pozadina = (RelativeLayout) convertView.findViewById(R.id.rlBackground);
        ImageView cena = (ImageView) convertView.findViewById(R.id.ivDisplayPrice);
        String nazivKategorije = (String) this.kategorije.get(position);
        stanjeKategorija((String) this.kategorije.get(position));
        imeKategorije.setTypeface(tf);
        imeKategorije.setText(nazivKategorije);
        if (this.statusKategorije == 0) {
            pozadina.setBackgroundResource(R.drawable.button_category_bw);
            cena.setVisibility(View.VISIBLE);
        } else {
            pozadina.setBackgroundResource(R.drawable.button_category);
            cena.setVisibility(View.GONE);
        }
        return convertView;
    }

    private void stanjeKategorija(String str) {
        switch (str) {
            case "فیلم ها":
                this.statusKategorije = 1;
                break;
            case "هری پاتر":
                this.statusKategorije = this.pref.getInt("Unlock" + str, 0);
                break;
            case "کاراکتر":
                this.statusKategorije = this.pref.getInt("Unlock" + str, 0);
                break;
            case "کشور ها":
                this.statusKategorije = 1;
                break;
            case "Landmarks":
                this.statusKategorije = this.pref.getInt("Unlock" + str, 0);
                break;
            case "مشاهیر":
                this.statusKategorije = 1;
                break;
            case "Celebrities":
                this.statusKategorije = 1;
                break;
            case "ماشین":
                this.statusKategorije = this.pref.getInt("Unlock" + str, 0);
                break;
            case "شغل ها":
                this.statusKategorije = this.pref.getInt("Unlock" + str, 0);
                break;
            case "Games":
                this.statusKategorije = this.pref.getInt("Unlock" + str, 0);
                break;
            case "کارتون ها":
                this.statusKategorije = this.pref.getInt("Unlock" + str, 0);
                break;
            case "خواننده ها":
                this.statusKategorije = 1;
                break;
            case "Sport":
                this.statusKategorije = 1;
                break;
            case "بازیکنان فوتبال":
                this.statusKategorije = 1;
                break;
            case "Marvel and DC":
                this.statusKategorije = this.pref.getInt("Unlock" + str, 0);
                break;
            case "حیوانات":
                this.statusKategorije = 1;
                break;
            case "بازیگران":
                this.statusKategorije = 1;
                break;
            case "برند ها":
                this.statusKategorije = this.pref.getInt("Unlock" + str, 0);
                break;
            case "Cities":
                this.statusKategorije = 1;
                break;
            case "Comics":
                this.statusKategorije = this.pref.getInt("Unlock" + str, 0);
                break;
        }
    }
}
