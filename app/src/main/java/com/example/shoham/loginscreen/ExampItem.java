package com.example.shoham.loginscreen;

/**
 * Created by shoham on 1/18/2018.
 */

public class ExampItem {
    private int mImageResource;
    private String mText1;
    private String mText2;

    public ExampItem(int imageResource,String text1, String text2){
        mImageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
    }

    public int getImageResoure(){
        return mImageResource;
    }
    public String getmText1(){
    return mText1;
    }

    public String getmText2(){
    return mText2;
    }

}
