package com.example.CvHandler.model;

import java.util.Random;

public enum ACTIVITY_TYPE {
    EXPERIENCE_PROFESSIONNELLE,
    FORMATION,
    PROJET,
    STAGE,
    BENEVOLAT,
    AUTRE;
    private static final Random PRNG = new Random();
    public static ACTIVITY_TYPE randomNatureActivity()  {
        ACTIVITY_TYPE[] natureActivities = values();
        return natureActivities[PRNG.nextInt(natureActivities.length)];
    }
}
