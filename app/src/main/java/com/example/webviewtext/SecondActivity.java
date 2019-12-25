package com.example.webviewtext;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

public class SecondActivity extends BasicActivity{
    WebView mWebview;
    WebSettings mWebSettings;
 //   TextView beginLoading,endLoading,loading,mtitle;
    TextView loading,mtitle;
    ProgressBar progressBar;
    Button button1;

    private IntentFilter intentFilter;
    private NetworkChangeReciver network;

    public static void actionStart(Context context,String data1,String data2){
        Intent intent=new Intent(context,SecondActivity.class);
        intent.putExtra("params1",data1);
        intent.putExtra("params2",data2);
//        Log.d("data1:",data1);
//        Log.d("data2:",data2);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        intentFilter =new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        network =new NetworkChangeReciver();
        registerReceiver(network,intentFilter);

        Toast.makeText(SecondActivity.this,"Welcome to baidu!",Toast.LENGTH_SHORT).show();

        mWebview = (WebView) findViewById(R.id.webView1);
//        beginLoading = (TextView) findViewById(R.id.text_beginLoading);
//        endLoading = (TextView) findViewById(R.id.text_endLoading);
        loading = (TextView) findViewById(R.id.text_Loading);
        mtitle = (TextView) findViewById(R.id.title_text);
        progressBar=findViewById(R.id.progress);

        mWebSettings = mWebview.getSettings();

        mWebview.loadUrl("http://www.baidu.com/");


        //设置不用系统浏览器打开,直接显示在当前Webview
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        //设置WebChromeClient类
        mWebview.setWebChromeClient(new WebChromeClient() {


            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                System.out.println("标题在这里");
                mtitle.setText(title);
            }


            //获取加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    String progress = newProgress + "%";
                    loading.setText(progress);
                    progressBar.setVisibility(View.VISIBLE);
                } else if (newProgress == 100) {
                    loading.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                }
            }
        });


        //设置WebViewClient类
//        mWebview.setWebViewClient(new WebViewClient() {
//            //设置加载前的函数
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                System.out.println("开始加载了");
//                beginLoading.setText("开始加载了");
//
//            }
//
//            //设置结束加载函数
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                endLoading.setText("结束加载了");
//
//            }
//        });
        button1=findViewById(R.id.title_back);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //点击返回上一页面而不是退出浏览器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebview.canGoBack()) {
            mWebview.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    //销毁Webview
    @Override
    protected void onDestroy() {
        if (mWebview != null) {
            mWebview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebview.clearHistory();

            ((ViewGroup) mWebview.getParent()).removeView(mWebview);
            mWebview.destroy();
            mWebview = null;
        }
        super.onDestroy();
        unregisterReceiver(network);
    }
    class NetworkChangeReciver extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            // TODO Auto-generated method stub
            Toast.makeText(arg0, "network changes", Toast.LENGTH_SHORT).show();

        }
    }
}
