package com.rz.varnishview.introviewpager;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by Rz Rasel on 2017-12-17.
 */
/*
String string = "#FFFF0000";
int color = Integer.parseInt(string.replaceFirst("^#",""), 16);
*/
public class PagerSkipNextButton extends RelativeLayout {
    //private OnClickListener onClickListener;
    private ViewPager viewPagerInstruction;
    private int totalViewPagerPage = 0;
    private int currentViewPagerSelectedPage = 0;
    private String btnTextSkip = "";
    private int btnBorderStyleSkip;
    private int btnTextColorSkip = Color.parseColor("#000000");
    private int btnTextSizeSkip = 2;

    private String btnTextNext = "";
    private int btnBorderStyleNext;
    private int btnTextColorNext = Color.parseColor("#000000");
    private int btnTextSizeNext = 2;

    private String btnTextGo = "";
    private int btnBorderStyleGo;
    private int btnTextColorGo = Color.parseColor("#000000");
    private int btnTextSizeGo = 2;
    private Button sysBtnPagerViewSkip = null;
    private Button sysBtnPagerViewNext = null;
    private Button sysBtnPagerViewGo = null;


    private int drawableRes;
    private Drawable drawable = null;
    //@android:style/Widget.Button
    //int DEF_STYLE = R.attr.borderlessButtonStyle;
    int DEF_STYLE = android.R.style.Widget_Material_ButtonBar;

    public PagerSkipNextButton(Context context) {
        super(context);
        init(null, 0, 0);
    }

    public PagerSkipNextButton(Context argContext, AttributeSet argAttrs) {
        super(argContext, argAttrs);
        init(argAttrs, 0, 0);
    }

    public PagerSkipNextButton(Context argContext, AttributeSet argAttrs, int argDefStyleAttr) {
        super(argContext, argAttrs, argDefStyleAttr);
        init(argAttrs, argDefStyleAttr, 0);
    }

    @TargetApi(21)
    public PagerSkipNextButton(Context argContext, AttributeSet argAttrs, int argDefStyleAttr, int argDefStyleRes) {
        super(argContext, argAttrs, argDefStyleAttr, argDefStyleRes);
        init(argAttrs, argDefStyleAttr, argDefStyleRes);
    }

    private void init(AttributeSet argAttrs, int argDefStyleAttr, int argDefStyleRes) {
        TypedArray typedArray = getContext().obtainStyledAttributes(argAttrs, R.styleable.ViewPagerSkipNext, argDefStyleAttr, argDefStyleRes);
        btnTextSkip = typedArray.getString(R.styleable.ViewPagerSkipNext_btnTextSkip);
        btnBorderStyleSkip = typedArray.getResourceId(R.styleable.ViewPagerSkipNext_btnBorderStyleSkip, 0);
        btnTextColorSkip = typedArray.getColor(R.styleable.ViewPagerSkipNext_btnTextColorSkip, Color.BLACK);
        btnTextSizeSkip = typedArray.getDimensionPixelSize(R.styleable.ViewPagerSkipNext_btnTextSizeSkip, btnTextSizeSkip);

        btnTextNext = typedArray.getString(R.styleable.ViewPagerSkipNext_btnTextNext);
        btnBorderStyleNext = typedArray.getResourceId(R.styleable.ViewPagerSkipNext_btnBorderStyleNext, 0);
        btnTextColorNext = typedArray.getColor(R.styleable.ViewPagerSkipNext_btnTextColorNext, Color.BLACK);
        btnTextSizeNext = typedArray.getDimensionPixelSize(R.styleable.ViewPagerSkipNext_btnTextSizeNext, btnTextSizeNext);

        btnTextGo = typedArray.getString(R.styleable.ViewPagerSkipNext_btnTextGo);
        btnBorderStyleGo = typedArray.getResourceId(R.styleable.ViewPagerSkipNext_btnBorderStyleGo, 0);
        btnTextColorGo = typedArray.getColor(R.styleable.ViewPagerSkipNext_btnTextColorGo, Color.BLACK);
        btnTextSizeGo = typedArray.getDimensionPixelSize(R.styleable.ViewPagerSkipNext_btnTextSizeGo, btnTextSizeNext);



        /*btnTheme = typedArray.getResourceId(R.styleable.MultiButton_btnStyle, DEF_STYLE);
        drawableRes = typedArray.getResourceId(R.styleable.MultiButton_myicon, 0);
        drawable = getResources().getDrawable(drawableRes);*/
        typedArray.recycle();
        //private ImageView createItem()
        sysBtnPagerViewSkip = onCreateButton(R.id.sysBtnPagerViewSkip, btnBorderStyleSkip, RelativeLayout.ALIGN_PARENT_LEFT);
        sysBtnPagerViewSkip.setTextColor(btnTextColorSkip);
        sysBtnPagerViewSkip.setText(btnTextSkip);
        sysBtnPagerViewSkip.setTextSize(btnTextSizeSkip);
        addView(sysBtnPagerViewSkip);
        //onSetProperty(sysBtnPagerViewSkip);
        sysBtnPagerViewNext = onCreateButton(R.id.sysBtnPagerViewNext, btnBorderStyleNext, RelativeLayout.ALIGN_PARENT_RIGHT);
        sysBtnPagerViewNext.setTextColor(btnTextColorNext);
        sysBtnPagerViewNext.setText(btnTextNext);
        sysBtnPagerViewNext.setTextSize(btnTextSizeNext);
        addView(sysBtnPagerViewNext);
        //reflectParametersInView();
        sysBtnPagerViewGo = onCreateButton(R.id.sysBtnPagerViewGo, btnBorderStyleGo, RelativeLayout.ALIGN_PARENT_RIGHT);
        sysBtnPagerViewGo.setTextColor(btnTextColorGo);
        sysBtnPagerViewGo.setText(btnTextGo);
        sysBtnPagerViewGo.setTextSize(btnTextSizeGo);
        sysBtnPagerViewGo.setVisibility(GONE);
        addView(sysBtnPagerViewGo);
        if (onClickListener != null) {
            sysBtnPagerViewSkip.setOnClickListener(onClickListener);
            sysBtnPagerViewNext.setOnClickListener(onClickListener);
            sysBtnPagerViewGo.setOnClickListener(onClickListener);
        }
    }

    private void reflectParametersInView() {
        removeAllViews();
        /*ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );*/
        /*Button firstBtn = new Button(getContext(), null, btnTheme);
        //RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)index.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams = null;
        layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        firstBtn.setLayoutParams(layoutParams);
        firstBtn.setText("first");
        addView(firstBtn);
        Button secondBtn = new Button(getContext(), null, btnTheme);
        layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        secondBtn.setLayoutParams(layoutParams);
        secondBtn.setText("second");
        addView(secondBtn);
        //button.setTextAppearance(context, R.style.MyTextStyle);
        //ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), R.attr.borderlessButtonStyle);
        //Button lastBtn = new Button(getContext());
        //R.attr.btnStyle
        Button lastBtn = new Button(getContext(), null, btnTheme);
        layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lastBtn.setLayoutParams(layoutParams);
        lastBtn.setText("last");
        lastBtn.setCompoundDrawablesWithIntrinsicBounds(drawable, null, drawable, null);
        //lastBtn.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        addView(lastBtn);
        //Button b = new Button(new ContextThemeWrapper(this, R.attr.borderlessButtonStyle), null, 0);
        //style="?android:attr/borderlessButtonStyle"
        //how to set button borderlessButtonStyle programmatically in android
        //https://stackoverflow.com/questions/11422220/android-how-to-programmatically-set-the-button-style-in-a-linearlayout

        //https://gist.github.com/romannurik/7026222
        //R.id.your_button_id*/
    }

    private void onSetProperty(Button argBtn) {
        argBtn.setText(btnTextSkip);
        //argBtn.setGravity(Gravity.CENTER_HORIZONTAL);

        /*RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) argBtn.getLayoutParams();
        //layoutParams.bottomMargin += 20;
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        argBtn.setLayoutParams(layoutParams);*/
    }

    private Button onCreateButton(int argId, int argBorderStyle, int argAlignParent) {
        Button retButton = null;
        LayoutParams layoutParams = null;
        if (argBorderStyle > 0) {
            retButton = new Button(getContext(), null, argBorderStyle);
        } else {
            retButton = new Button(getContext());
        }
        layoutParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.addRule(argAlignParent);
        //layoutParams.setPadding(left, top, right, bottom);
        retButton.setLayoutParams(layoutParams);
        retButton.setId(argId);
        retButton.setText(null);
        retButton.setPadding(48, 24, 48, 24);
        //retButton.setBackgroundColor(Color.parseColor("#ff0000"));
        retButton.setBackgroundResource(R.drawable.button_border);
        return retButton;
        //https://stackoverflow.com/questions/10779115/how-to-set-padding-or-margin-to-linear-layout
        //https://stackoverflow.com/questions/9685658/add-padding-on-view-programmatically
    }

    public void setupWithViewPager(@NonNull ViewPager argViewPager) {
        //setPageCount(viewPager.getAdapter().getCount());
        //viewPager.addOnPageChangeListener(new ViewPagerIndicator.OnPageChangeListener());
        viewPagerInstruction = argViewPager;
        totalViewPagerPage = viewPagerInstruction.getAdapter().getCount();
        currentViewPagerSelectedPage = viewPagerInstruction.getCurrentItem();
        viewPagerInstruction.addOnPageChangeListener(new OnPageChangeListener());
    }

    public void setOnClickListener(OnClickListener argOnClickListener) {
        onClickListener = argOnClickListener;
        if (onClickListener != null) {
            sysBtnPagerViewSkip.setOnClickListener(onClickListener);
            sysBtnPagerViewNext.setOnClickListener(onClickListener);
            sysBtnPagerViewGo.setOnClickListener(onClickListener);
        }
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View argView) {
            int btnId = argView.getId();
            /*switch (btnId) {
                case R.id.sysBtnPagerViewSkip:
                    break;
            }*/
            if (btnId == R.id.sysBtnPagerViewSkip) {
            } else if (btnId == R.id.sysBtnPagerViewNext) {
                currentViewPagerSelectedPage++;
                onSetButtonShowHide(currentViewPagerSelectedPage);
                viewPagerInstruction.setCurrentItem(currentViewPagerSelectedPage);
            }
        }
    };

    public void addOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        //mListener = listener;
    }

    private void onSetButtonShowHide(int ageCurrentSelectedPage) {
        currentViewPagerSelectedPage = ageCurrentSelectedPage;
        if (currentViewPagerSelectedPage >= totalViewPagerPage - 1) {
            if (sysBtnPagerViewSkip.getVisibility() != View.GONE) {
                onSetTransition(sysBtnPagerViewSkip, 1000, 50, 250);
            }
            if (sysBtnPagerViewNext.getVisibility() != View.GONE) {
                onSetTransition(sysBtnPagerViewNext, 1000, 50, 250);
            }
            if (sysBtnPagerViewNext.getVisibility() != View.VISIBLE) {
                onSetTransition(sysBtnPagerViewGo, 250, -50, -250);
            }
            sysBtnPagerViewSkip.setVisibility(GONE);
            sysBtnPagerViewNext.setVisibility(GONE);
            sysBtnPagerViewGo.setVisibility(VISIBLE);
            //onSetTransition(sysBtnPagerViewGo, 1000, 50, 250);
        } else {
            if (sysBtnPagerViewSkip.getVisibility() != View.VISIBLE) {
                onSetTransition(sysBtnPagerViewSkip, 250, -50, -250);
            }
            if (sysBtnPagerViewNext.getVisibility() != View.VISIBLE) {
                onSetTransition(sysBtnPagerViewNext, 250, -50, -250);
            }
            if (sysBtnPagerViewNext.getVisibility() != View.GONE && sysBtnPagerViewNext.getVisibility() != View.VISIBLE) {
                onSetTransition(sysBtnPagerViewGo, 1000, 50, 250);
            }
            sysBtnPagerViewSkip.setVisibility(VISIBLE);
            sysBtnPagerViewNext.setVisibility(VISIBLE);
            sysBtnPagerViewGo.setVisibility(GONE);
        }
    }

    private void onSetTransition(View argView, int argDuration, int argAmountToMoveRight, int argAmountToMoveDown) {
        int fromXDelta = 0, toXDelta = 0;
        int fromYDelta = 0, toYDelta = 0;
        fromXDelta = -1 * argAmountToMoveRight;
        if (argAmountToMoveRight >= 0) {
            fromXDelta = 0;
            toXDelta = argAmountToMoveRight;
        }
        fromYDelta = -1 * argAmountToMoveDown;
        if (argAmountToMoveDown >= 0) {
            fromYDelta = 0;
            toYDelta = argAmountToMoveDown;
        }
        TranslateAnimation translateAnimation;
        translateAnimation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        translateAnimation.setDuration(argDuration);
        translateAnimation.setFillAfter(false);
        argView.startAnimation(translateAnimation);
    }

    private class OnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int argPosition, float argPositionOffset, int argPositionOffsetPixels) {
            /*if (mListener != null) {
                mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }*/
        }

        @Override
        public void onPageSelected(int agrPosition) {
            onSetButtonShowHide(agrPosition);
            /*if (mListener != null) {
                mListener.onPageSelected(position);
            }*/
        }

        @Override
        public void onPageScrollStateChanged(int argState) {
            /*if (mListener != null) {
                mListener.onPageScrollStateChanged(state);
            }*/
        }
    }
}
/*
Definition-1-three button
- skip button - text only
- next button (>) - image/icon only
- go button - text only
Definition-2
- icon (Left, right, not)
Definition-3
- [x] style - borderless
- [x] text color
- text style Typeface.NORMAL
- [x] text size
- [x] text
- [x] button id


int ordinal = a.getInt(R.styleable.IconView_icon, 0);
Format format = Format.fromId(a.getInt(R.styleable.IconView_icon, 0)));
private enum Format {
    enum_name_one(0), enum_name_n(666);
    int id;

    Format(int id) {
        this.id = id;
    }

    static Format fromId(int id) {
        for (Format f : values()) {
            if (f.id == id) return f;
        }
        throw new IllegalArgumentException();
    }
}

https://gist.github.com/laaptu/ee2d30c46e14743172cb
*/