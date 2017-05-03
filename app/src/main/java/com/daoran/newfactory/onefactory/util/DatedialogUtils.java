package com.daoran.newfactory.onefactory.util;

/**
 * Created by lizhipeng on 2017/4/20.
 */

public class DatedialogUtils {
    //获取某年某月的总天数
    public static int getNumberOfDays(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (isRunYear(year)) {
                    return 29;
                } else {
                    return 28;
                }
        }
        return 100;
    }

    //判断是否闰年
    private static boolean isRunYear(int year) {
        return (year % 4 != 0) || (year % 100 == 0) && (year % 400 != 0);
    }
}
