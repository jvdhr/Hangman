package ir.progressivesoft.hangman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toast;

public class FindOutMore extends AppCompatActivity {
    ImageButton back;
    String kategorijaPojma;
    SharedPreferences pref;
    ImageButton reload;
    String tacnaRec;
    WebView webView;

    /* renamed from: com.klikapp.hangman.FindOutMore.1 */
    class C03191 implements OnClickListener {
        C03191() {
        }

        public void onClick(View v) {
            FindOutMore.this.backButton();
        }
    }

    /* renamed from: com.klikapp.hangman.FindOutMore.2 */
    class C03202 implements OnClickListener {
        C03202() {
        }

        public void onClick(View v) {
            if (FindOutMore.this.isNetworkConnected()) {
                Toast.makeText(FindOutMore.this.getApplicationContext(), "Please wait!", Toast.LENGTH_SHORT).show();
                FindOutMore.this.webView.loadUrl("https://fa.wikipedia.org/wiki/" + FindOutMore.this.tacnaRec.toLowerCase());
                return;
            }
            Toast.makeText(FindOutMore.this.getApplicationContext(), "Error: Please turn on internet and click on reload button!", Toast.LENGTH_SHORT).show();
        }
    }

    /* renamed from: com.klikapp.hangman.FindOutMore.3 */
    class C03213 extends WebViewClient {
        C03213() {
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return false;
        }
    }

    /* renamed from: com.klikapp.hangman.FindOutMore.4 */
    class C03224 extends WebViewClient {
        C03224() {
        }

        public void onPageFinished(WebView view, String url) {
            FindOutMore.this.webView.scrollTo(0, 0);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.findoutmore);
        this.pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        this.back = (ImageButton) findViewById(R.id.ibBackFindOut);
        this.reload = (ImageButton) findViewById(R.id.ibReload);
        this.back.setOnClickListener(new C03191());
        this.reload.setOnClickListener(new C03202());
        Intent intent = getIntent();
        this.kategorijaPojma = intent.getStringExtra("kategorija");
        this.tacnaRec = intent.getStringExtra("tacnaRec");
        this.webView = (WebView) findViewById(R.id.webView);
        this.webView.getSettings().setJavaScriptEnabled(true);
        if (isNetworkConnected()) {
            Toast.makeText(getApplicationContext(), "Please wait!", Toast.LENGTH_SHORT).show();
            this.webView.loadUrl("https://fa.wikipedia.org/wiki/" + this.tacnaRec.toLowerCase());
        } else {
            Toast.makeText(getApplicationContext(), "Error: Please turn on internet and click on reload button!", Toast.LENGTH_SHORT).show();
        }
        this.webView.setWebViewClient(new C03213());
        this.webView.setWebViewClient(new C03224());
    }

    private void backButton() {
        Intent intent = new Intent(getApplicationContext(), Game.class);
        intent.putExtra("kategorija", this.kategorijaPojma);
        startActivity(intent);
        finish();
    }

    public void onBackPressed() {
        backButton();
    }

    private boolean isNetworkConnected() {
        if (((ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE)).getActiveNetworkInfo() == null) {
            return false;
        }
        return true;
    }

    protected void onPause() {
        super.onPause();
        if (VERSION.SDK_INT >= 11) {
            this.webView.onPause();
        } else {
            finish();
        }
    }
}
