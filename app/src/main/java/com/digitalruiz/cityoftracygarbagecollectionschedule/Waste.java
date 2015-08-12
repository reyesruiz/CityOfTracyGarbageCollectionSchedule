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
    private int imageid;



    //Context mContext;
    //public Waste(Context context)
   // {
       // mContext = context;
    //}
    //private static final int recycle = R.string.recycle;
    //private static final int yard = R.string.yard;
    //private static final int garbage = R.string.garbage;
    //private static final int descRecycle = R.string.descRecycle;
    //private static final int descYard = R.string.descYard;
    //private static final int descGarbage = R.string.descGarbage;


    public static final Waste[] wastes = {
            new Waste(R.string.recycle, R.string.descRecycle, R.drawable.recycling_cart),
            new Waste(R.string.yard, R.string.descYard, R.drawable.yard_waste_cart),
            new Waste(R.string.garbage, R.string.descGarbage, R.drawable.garbage_cart),
    };

    private Waste(Integer name, Integer description, Integer imageid) {
        this.name = name;
        this.description = description;
        this.imageid = imageid;
    }

    public Integer getName() {
        return name;
    }

    public Integer getDescription() {
        return description;
    }

    public Integer getImageID(){
        return imageid;
    }




}
