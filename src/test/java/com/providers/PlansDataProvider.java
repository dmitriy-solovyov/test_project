package com.providers;

import com.utils.Plan;
import org.testng.annotations.DataProvider;


public class PlansDataProvider {

    @DataProvider(name = "plan")
    public static Object[][] getPlan(){
        return new Object[][] {
                {new Plan("30дней останется", "320Кбит/сек (макс.)", "300руб. в месяц")},
                {new Plan("30дней останется", "416Кбит/сек (макс.)", "350руб. в месяц")},
                {new Plan("30дней останется", "512Кбит/сек (макс.)", "400руб. в месяц")},
                {new Plan("30дней останется", "640Кбит/сек (макс.)", "450руб. в месяц")},
                {new Plan("30дней останется", "768Кбит/сек (макс.)", "500руб. в месяц")},
                {new Plan("30дней останется", "896Кбит/сек (макс.)", "550руб. в месяц")},
                {new Plan("30дней останется", "1.0Мбит/сек (макс.)", "600руб. в месяц")},
                {new Plan("30дней останется", "1.3Мбит/сек (макс.)", "650руб. в месяц")},
                {new Plan("30дней останется", "1.7Мбит/сек (макс.)", "700руб. в месяц")},
                {new Plan("30дней останется", "2.1Мбит/сек (макс.)", "750руб. в месяц")},
                {new Plan("30дней останется", "3.1Мбит/сек (макс.)", "800руб. в месяц")},
                {new Plan("30дней останется", "4.1Мбит/сек (макс.)", "850руб. в месяц")},
                {new Plan("30дней останется", "5.0Мбит/сек (макс.)", "900руб. в месяц")},
                {new Plan("30дней останется", "5.7Мбит/сек (макс.)", "950руб. в месяц")},
                {new Plan("30дней останется", "6.4Мбит/сек (макс.)", "1000руб. в месяц")},
                {new Plan("30дней останется", "7.1Мбит/сек (макс.)", "1050руб. в месяц")},
                {new Plan("30дней останется", "7.8Мбит/сек (макс.)", "1100руб. в месяц")},
                {new Plan("30дней останется", "8.5Мбит/сек (макс.)", "1150руб. в месяц")},
                {new Plan("30дней останется", "9.2Мбит/сек (макс.)", "1200руб. в месяц")},
                {new Plan("30дней останется", "10.0Мбит/сек (макс.)", "1250руб. в месяц")},
                {new Plan("30дней останется", "12.0Мбит/сек (макс.)", "1300руб. в месяц")},
                {new Plan("30дней останется", "15.0Мбит/сек (макс.)", "1350руб. в месяц")},
                {new Plan("30дней останется", "МаксМбит/сек (макс.)", "1400руб. в месяц")},
        };
    }

}