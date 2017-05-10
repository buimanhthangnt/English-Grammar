package etspteam.android.englishgrammar.lesson;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import etspteam.android.englishgrammar.R;

public class GrammarFragment extends Fragment {

    public GrammarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_grammar, container, false);
        WebView webView = (WebView) v.findViewById(R.id.grammar_webview);
        //webView.loadData("ABC", "text/html", "UTF-8");
        webView.loadUrl("file:///android_asset/grammar/danhtu.html");
        return v;
    }

}
