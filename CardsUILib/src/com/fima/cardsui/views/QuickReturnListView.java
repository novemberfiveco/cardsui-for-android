package com.fima.cardsui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.appstrakt.android.core.view.AppstraktListView;

public class QuickReturnListView extends AppstraktListView {

    private int mItemCount;
    private int mItemOffsetY[];
    private boolean scrollIsComputed = false;
    private int mHeight;

    public QuickReturnListView(Context context) {
        super(context);
    }

    public QuickReturnListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getListHeight() {
        return mHeight;
    }

    public void computeScrollY() {
        mHeight = 0;
        try {
            mItemCount = getAdapter().getCount();
            if (mItemOffsetY == null) {
                mItemOffsetY = new int[mItemCount];
            }
            for (int i = 0; i < mItemCount; ++i) {
                View view = getAdapter().getView(i, null, this);
                view.measure(
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                mItemOffsetY[i] = mHeight;
                mHeight += view.getMeasuredHeight();
            }
            scrollIsComputed = true;
        } catch (NullPointerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean scrollYIsComputed() {
        return scrollIsComputed;
    }

    public int getComputedScrollY() {
        int pos, nScrollY, nItemY;
        View view = null;
        pos = getFirstVisiblePosition();
        view = getChildAt(0);
        if (view != null && mItemOffsetY != null && mItemOffsetY.length > pos) {
            nItemY = view.getTop();
            nScrollY = mItemOffsetY[pos] - nItemY;
            return nScrollY;
        }
        return 0;
    }

    public int computeVerticalScrollOffset() {
        return super.computeVerticalScrollOffset();
    }

}