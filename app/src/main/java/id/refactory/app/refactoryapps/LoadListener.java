package id.refactory.app.refactoryapps;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by massam on 14/10/17.
 */

public class LoadListener{
    @JavascriptInterface
    public void processHTML(String html){
        Log.e("Result HTML", html);
    }
}
