package com.digitalruiz.cityoftracygarbagecollectionschedule;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;

/**
 * Created by reyes on 8/11/15.
 */
public class Waste {
    private int name;
    private int description;



    Context mContext;
    public Waste(Context context)
    {
        mContext = context;
    }
    private static final int recycle = R.string.recycle;
    private static final int yard = R.string.yard;
    private static final int garbage = R.string.garbage;
    private static final int descRecycle = R.string.descRecycle;
    private static final int descYard = R.string.descYard;
    private static final int descGarbage = R.string.descGarbage;


    public static final Waste[] wastes = {
            new Waste(recycle, descRecycle),
            new Waste(yard, descYard),
            new Waste(garbage, descGarbage),
    };

    private Waste(Integer name, Integer description) {
        this.name = name;
        this.description = description;
    }

    public Integer getName() {
        return name;
    }

    public Integer getDescription() {
        return description;
    }





}
