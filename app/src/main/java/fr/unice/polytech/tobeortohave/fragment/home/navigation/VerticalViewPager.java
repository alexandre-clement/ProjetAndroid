package fr.unice.polytech.tobeortohave.fragment.home.navigation;

import android.content.Context;
import android.graphics.Matrix;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * @author Alexandre Clement
 *         Created the 10/05/2017.
 */

public class VerticalViewPager extends ViewPager
{
    public VerticalViewPager(Context context)
    {
        super(context);
        init();
    }

    public VerticalViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    private void init()
    {
        setPageTransformer(true, new VerticalPageTransformer());
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    private MotionEvent swapXY(MotionEvent ev, float angle)
    {
        Matrix matrix = new Matrix();
        int x = getPaddingLeft() + getWidth() / 2;
        int y = getPaddingTop() + getHeight() / 2;
        matrix.setRotate(angle, x, y);
        ev.transform(matrix);
        return ev;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event)
    {
        final boolean interceptTouchEvent = super.onInterceptTouchEvent(swapXY(event, -90));
        swapXY(event, 90);
        return interceptTouchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        final boolean b = super.onTouchEvent(swapXY(ev, -90));
        swapXY(ev, 90);
        return b;
    }

    private class VerticalPageTransformer implements PageTransformer
    {
        @Override
        public void transformPage(View view, float position)
        {
            if (position < -1)
                view.setAlpha(0);
            else if (position <= 1)
            {
                view.setAlpha(1);
                view.setTranslationX(view.getWidth() * -position);
                view.setTranslationY(position * view.getHeight());
            }
            else
                view.setAlpha(0);
        }
    }

}
