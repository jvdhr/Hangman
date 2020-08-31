package ir.progressivesoft.hangman;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HintPopUp extends Dialog implements OnClickListener {
    public Activity f11c;
    public Dialog f12d;
    TextView dialogBack;
    public Button no;
    String poruka;
    public Button yes;

    public HintPopUp(Activity a) {
        super(a);
        this.poruka = BuildConfig.FLAVOR;
        this.f11c = a;
    }

    public void podaci(String krajnjaPoruka) {
        this.poruka = krajnjaPoruka;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.hintpopup);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/CarterOne.ttf");
        this.dialogBack = (TextView) findViewById(R.id.tvDialogBack);
        this.yes = (Button) findViewById(R.id.btn_yes);
        this.no = (Button) findViewById(R.id.btn_no);
        this.yes.setOnClickListener(this);
        this.no.setOnClickListener(this);
        this.dialogBack.setText(this.poruka);
        this.dialogBack.setTextColor(-1);
        this.dialogBack.setTypeface(tf, 1);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes /*2131624022*/:
                dismiss();
                break;
            case R.id.btn_no /*2131624023*/:
//                getContext().startActivity(new Intent(getContext(), Store.class));
                break;
        }
        dismiss();
    }
}
