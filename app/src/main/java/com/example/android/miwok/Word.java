package com.example.android.miwok;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by marci on 21.11.2017.
 */

public class Word {


    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public String getDefaultTranslation() {
        return defaultTranslation;
    }

    public boolean isHasImage() {
        return hasImage;
    }

    public int getResourceImageid() {return resourceImageid;}

    public int getResourceAudioId() {return resourceAudioId;}


    @Override
    public String toString() {
        return "Word{" +
                "miwokTranslation='" + miwokTranslation + '\'' +
                ", defaultTranslation='" + defaultTranslation + '\'' +
                ", resourceAudioId=" + resourceAudioId +
                ", hasImage=" + hasImage +
                ", resourceImageid=" + resourceImageid +
                '}';
    }

    private String miwokTranslation;
    private  String defaultTranslation;
    private int resourceAudioId;
    private boolean hasImage;
    private int resourceImageid;

    public Word(String defaultTranslation, String miwokTranslation, int resourceImageid, int resourceAudioId)
    {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.resourceImageid = resourceImageid;
        this.resourceAudioId = resourceAudioId;
        hasImage = true;

    }

    public Word(String defaultTranslation, String miwokTranslation, int resourceAudioId)
    {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.resourceAudioId=resourceAudioId;
        hasImage = false;



    }





}
