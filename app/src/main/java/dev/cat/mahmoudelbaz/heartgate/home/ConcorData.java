package dev.cat.mahmoudelbaz.heartgate.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import dev.cat.mahmoudelbaz.heartgate.R;
import dev.cat.mahmoudelbaz.heartgate.utils.StringHelper;

public class ConcorData extends AppCompatActivity {
    private StringHelper stringHelper;
    private String textContent;
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.concor_description_view);
        Bundle bundle = getIntent().getExtras();
        textContent = bundle.getString("contentText");
        stringHelper = new StringHelper();
        setViews();

    }

    private void setViews() {
        TextView textView = findViewById(R.id.content);
        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new MyBrowser());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(textContent);
    }


}

class MyBrowser extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}