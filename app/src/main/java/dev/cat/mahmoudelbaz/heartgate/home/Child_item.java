package dev.cat.mahmoudelbaz.heartgate.home;

import android.text.Spanned;

/**
 * Created by mahmoudelbaz on 7/10/17.
 */

public class Child_item {

    private final String nameId;
    private final Spanned Html;
    private int backgroundColorResId;

    public Child_item(String nameId, int backgroundColorResId) {
        this.nameId = nameId;
        this.backgroundColorResId = backgroundColorResId;
        this.Html = null;
    }

    public Spanned getHtml() {
        return Html;
    }

    public String getNameId() {
        return nameId;
    }

    public int getBackgroundColorResId() {
        return backgroundColorResId;
    }

}
