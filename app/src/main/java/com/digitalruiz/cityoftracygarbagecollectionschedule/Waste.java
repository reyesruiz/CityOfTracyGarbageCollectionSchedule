package com.digitalruiz.cityoftracygarbagecollectionschedule;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;

/**
 * Created by reyes on 8/11/15.
 */
public class Waste {
    private int name;
   // private String description;
    //private String recycle;


    Context mContext;
    public Waste(Context context)
    {
        mContext = context;
    }
    private static final int recycle = R.string.recycle;
    private static final int yard = R.string.yard;
    private static final int garbage = R.string.garbage;


    public static final Waste[] wastes = {
            new Waste(recycle),
            new Waste(yard),
            new Waste(garbage)
    };

    private Waste(Integer name) {
        this.name = name;

    }

    public Integer getName() {
        return name;
    }





}
