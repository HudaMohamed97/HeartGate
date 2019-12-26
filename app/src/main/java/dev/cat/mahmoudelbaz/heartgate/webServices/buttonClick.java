package dev.cat.mahmoudelbaz.heartgate.webServices;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

class buttonClick extends AppCompatButton {

    public buttonClick(Context context) {
        super(context);
    }

    public buttonClick(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;

            case MotionEvent.ACTION_UP:
                performClick();
                return true;
        }
        return false;
    }


    @Override
    public boolean performClick() {
        return true;
    }
}

