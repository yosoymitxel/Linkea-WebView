package com.mitxel.links.vistaweb;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity{
    private String urlLinkea = "http://www.linkea.cf";
    private WebView myWebView;
    private ProgressBar progressBar;
    private void mostratMensaje(String mensaje){
        Toast mensajeToast = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT);
        mensajeToast.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.progresiveBar);
        myWebView = (WebView)findViewById(R.id.linkeaWebView);

        WebSettings myWebSettings = myWebView.getSettings();
        myWebSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl(urlLinkea);
        myWebView.setWebViewClient(new WebViewClient());
        mostratMensaje("Bienvenido");
        myWebView.setWebChromeClient(new WebChromeClient());
        progressBar.setProgress(0);
        myWebView.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView webV, int Progress){
                progressBar.setProgress(Progress);
                setTitle("Por favor espere");
                if(Progress==100){
                    setTitle(webV.getTitle());
                    progressBar.setVisibility(webV.GONE);
                }else{
                    progressBar.setVisibility(webV.VISIBLE);
                }
                super.onProgressChanged(webV,Progress);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(myWebView.canGoBack()){
            myWebView.canGoBack();
        }else{
            super.onBackPressed();
        }
    }
}
