package com.atlaspharmacy.atlaspharmacy.promotions.domain;

import com.atlaspharmacy.atlaspharmacy.promotions.service.impl.PromotionService;
import com.atlaspharmacy.atlaspharmacy.schedule.domain.valueobjects.Period;

import java.util.Date;

public class PromotionsData {

    public static Promotion createPromotion1(){
        Promotion promotion=new Promotion();
        promotion.setDescription("Povodom osmog marta svim zenama -10%.");
        promotion.setActivePeriod(new Period(new Date("08/03/2021"),new Date("10/03/2021")));
        return promotion;
    }

    public static Promotion createPromotion2(){
        Promotion promotion=new Promotion();
        promotion.setDescription("Bioderma -30%.");
        promotion.setActivePeriod(new Period(new Date("08/07/2021"),new Date("13/07/2021")));
        return promotion;
    }
}
