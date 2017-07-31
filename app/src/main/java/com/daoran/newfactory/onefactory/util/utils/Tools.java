package com.daoran.newfactory.onefactory.util.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.DisplayMetrics;

import org.apache.http.util.EncodingUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 各种类型转换
 * Created by djx on 16/1/18.
 */
public class Tools {
    /*
     * 距离转换
	 */
    public static String distance(Double distance) {
//        distance = distance / 1000;
//        DecimalFormat df = new DecimalFormat("######0.00");
//        return String.valueOf(df.format(distance)) + " km";
        distance = distance / 1000;
        if (distance < 0.01) {
            return "0.01km";
        } else {
            DecimalFormat df = new DecimalFormat("######0.00");
            return String.valueOf(df.format(distance)) + " km";
        }
    }

    public static String diatance(String distance) {
        try {
            double temp = Double.parseDouble(distance);
            DecimalFormat df = new DecimalFormat("######0.00");
            return String.valueOf(df.format(temp)) + " km";
        } catch (Exception e) {
            return "0 Km";
        }
    }

    /**
     * 年龄
     */
    public static int getage(String update_time) {
        try {
            long cur = System.currentTimeMillis();
            Long time = Long.valueOf(update_time);
            Calendar cNow = Calendar.getInstance();
            Calendar c = Calendar.getInstance();
            c.setTime(new Date(time));
            int cd = c.get(Calendar.DATE);
            int cnd = cNow.get(Calendar.DATE);
            int cm = c.get(Calendar.MONTH);
            int cnm = cNow.get(Calendar.MONTH);
            int cy = c.get(Calendar.YEAR);
            int cny = cNow.get(Calendar.YEAR);
            if (time * 10 < cur) {
                time *= 1000;
            }

            int y = (cny - cy);
            return y;
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 时间戳转日期
     */
    public static CharSequence getDate(String update_time) {
        try {
            Long time = Long.valueOf(update_time);
            Calendar c = Calendar.getInstance();
            c.setTime(new Date(time));
            int cd = c.get(Calendar.DATE);
            int cm = c.get(Calendar.MONTH);
            int cy = c.get(Calendar.YEAR);
            String str = cy + "年" + cm + "月" + cd + "日";
            return str;

        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 时间戳转生日
     */
    public static CharSequence getBirthday(String update_time) {
        try {
            Long time = Long.valueOf(update_time);
            Calendar c = Calendar.getInstance();
            c.setTime(new Date(time));
            int cd = c.get(Calendar.DATE);
            int cm = c.get(Calendar.MONTH);
            int cy = c.get(Calendar.YEAR);
            String str = cy + "-" + cm + "-" + cd;
            return str;

        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 格式化时间
     */
    public static CharSequence formatTime(String update_time) {
        try {
            long cur = System.currentTimeMillis();
            Long time = Long.valueOf(update_time);
            Calendar cNow = Calendar.getInstance();
            Calendar c = Calendar.getInstance();
            c.setTime(new Date(time));
            int cd = c.get(Calendar.DATE);
            int cnd = cNow.get(Calendar.DATE);
            int cm = c.get(Calendar.MONTH);
            int cnm = cNow.get(Calendar.MONTH);
            if (time * 10 < cur) {
                time *= 1000;
            }

            long d = (cur - time);
            if (d < 1000 * 10) {
                return "刚刚";
            } else if (d < 1000 * 60) {
                return (int) (d / (1000)) + "秒前";
            } else if (d < 1000 * 60 * 60) {// 小于一小时
                return (int) (d / (1000 * 60)) + "分钟前";
            } else if (c.get(Calendar.DATE) == cNow.get(Calendar.DATE)) { // 小于一天
                SimpleDateFormat dateformatHour = new SimpleDateFormat("HH:mm");
                return dateformatHour.format(time);
            } else if ((cnd - cd == 1) & (cm == cnm)) {
                SimpleDateFormat dateformatDay = new SimpleDateFormat("HH:mm");
                return "昨天" + dateformatDay.format(time);
            } else if ((cnd - cd == 2) & (cm == cnm)) {
                SimpleDateFormat dateformatDay = new SimpleDateFormat("HH:mm");
                return "前天" + dateformatDay.format(time);
            } else if (c.get(Calendar.YEAR) == cNow.get(Calendar.YEAR)) {
                SimpleDateFormat dateformatDay = new SimpleDateFormat("MM-dd HH:mm");
                return dateformatDay.format(time);
            } else {
                SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd");
                String str = dateformat1.format(time);
                return str;
            }
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 格式化时间
     */
    public static CharSequence formatTimeYMD(String update_time) {
        try {
            if (judgeTimeYMD(update_time)) {
                return update_time;
            }
            long cur = System.currentTimeMillis();
            Long time = Long.valueOf(update_time);

            if (time * 10 < cur) {
                time *= 1000;
            }

            long d = (cur - time);
            SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd");
            String str = dateformat1.format(time);
            return str;
        } catch (Exception e) {
            return "";
        }

    }

    public static boolean judgeTimeYMD(String str) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = (Date) formatter.parse(str);
            return str.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 时间转成时间戳
     *
     * @param time
     * @return
     */
    public static long timeToDate(String time) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
            long unixTimestamp = date.getTime() / 1000;
            return unixTimestamp;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long currentLongTime() {
        return System.currentTimeMillis();
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        Activity activity = (Activity) context;
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;// 宽度height = dm.heightPixels ;//高度
    }

    /**
     * 检测是否 存在SD卡
     *
     * @return
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检测网络是否存在
     *
     * @return
     */
    public static boolean checkNetwork(final Activity mActivity) {
        ConnectivityManager conn = (ConnectivityManager) mActivity
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = conn.getActiveNetworkInfo();
        if (net != null && net.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * 将String类型的日期转换成calendar
     *
     * @param str
     * @return
     */
    public static Calendar stringToCalendar(String str) {
        Calendar calendar = new GregorianCalendar(Locale.CHINA);
        try {
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    /**
     * 写文件到 SD卡
     */
    public static void writeFileSdcard(String fileName, String message) {
        if (message.equals("[]")) {
            message = "";
        }
        try {
            // FileOutputStream fout = openFileOutput(fileName, MODE_PRIVATE);
            FileOutputStream fout = new FileOutputStream(fileName);
            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] bytes = message.getBytes();
            fout.write(bytes);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从SD卡读取信息
     */
    public static String readFileSdcard(String fileName) {
        String res = "";
        File file = new File(fileName);
        if (file.exists()) {
            try {
                FileInputStream fin = new FileInputStream(fileName);
                int length = fin.available();
                byte[] buffer = new byte[length];
                fin.read(buffer);
                res = EncodingUtils.getString(buffer, "UTF-8");
                fin.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (res.equals("")) {
                res = " ";
            }
        } else {
            res = " ";
        }
        return res;
    }

    /**
     * 判断String 是否有内容
     *
     * @param str
     * @return
     */
    public static boolean StringHasContent(String str) {
        if (null == str || "".equals(str) || "null".equals(str)
                || str.length() == 0) {
            return false;
        }
        return true;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        if (context == null) {
            return (int) dpValue;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        if (context == null) {
            return (int) pxValue;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        if (context == null) {
            return (int) pxValue;
        }
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        if (context == null) {
            return (int) spValue;
        }
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static String getCurrentDate(String pattern) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 判断是否手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNum(String mobiles) {
        Pattern p = Pattern
                .compile("[1][358]\\d{9}");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 给特殊字符编码
     *
     * @param txt
     * @return
     */
    public static String urlEncoder(String txt) {
        try {
            return URLEncoder.encode(txt, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 给特殊字符解码
     *
     * @param txt
     * @return
     */
    public static String urlDeCoder(String txt) {
        try {
            return URLDecoder.decode(txt, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
