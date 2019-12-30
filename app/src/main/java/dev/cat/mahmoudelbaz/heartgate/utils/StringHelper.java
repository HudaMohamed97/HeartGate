package dev.cat.mahmoudelbaz.heartgate.utils;

import android.text.Html;

public class StringHelper {

    public String formateHtmlString(String htmlText) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return String.valueOf(Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY));
        } else {
            return Html.fromHtml(htmlText).toString();
        }
    }
}
