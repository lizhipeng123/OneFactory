package com.daoran.newfactory.onefactory.util.utils;

import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 创建时间：2017/11/15
 * 编写人：lizhipeng
 * 功能描述：生产日报逻辑判断
 */

public class ProductionUtil {
    int lastmont, day1, day2, day3, day4, day5, day6, day7, day8, day9,
            day10, day11, day12, day13, day14, day15, day16, day17, day18,
            day19, day20, day21, day22, day23, day24, day25, day26, day27,
            day28, day29, day30, day31;

    /*计算总完工数*/
    public String CountMonth(String lastmonth, String dayone, String daytwo,
                             String dayThree, String dayfore, String dayfive,
                             String daysix, String daySeven, String dayEight,
                             String dayNine, String dayTen, String dayEleven,
                             String dayTwelve, String dayThirteen, String dayFourteen,
                             String dayFifteen, String daySixteen, String daySeventeen,
                             String dayEighteen, String dayNineteen, String dayTwenty,
                             String dayTwentyOne, String dayTwentyTwo, String dayTwentyThree,
                             String dayTwentyFore, String dayTwentyFive, String dayTwentySix,
                             String dayTwentySeven, String dayTwentyEight, String dayTwentyNine,
                             String dayThirty, String dayThirtyOne) {

        if (lastmonth.equals("")) {
            lastmont = 0;
        } else {
            lastmont = Integer.parseInt(lastmonth);
        }
        if (dayone.equals("")) {
            day1 = 0;
        } else {
            day1 = Integer.parseInt(dayone);
        }
        if (daytwo.equals("")) {
            day2 = 0;
        } else {
            day2 = Integer.parseInt(daytwo);
        }
        if (dayThree.equals("")) {
            day3 = 0;
        } else {
            day3 = Integer.parseInt(dayThree);
        }
        if (dayfore.equals("")) {
            day4 = 0;
        } else {
            day4 = Integer.parseInt(dayfore);
        }
        if (dayfive.equals("")) {
            day5 = 0;
        } else {
            day5 = Integer.parseInt(dayfive);
        }
        if (daysix.equals("")) {
            day6 = 0;
        } else {
            day6 = Integer.parseInt(daysix);
        }
        if (daySeven.equals("")) {
            day7 = 0;
        } else {
            day7 = Integer.parseInt(daySeven);
        }
        if (dayEight.equals("")) {
            day8 = 0;
        } else {
            day8 = Integer.parseInt(dayEight);
        }
        if (dayNine.equals("")) {
            day9 = 0;
        } else {
            day9 = Integer.parseInt(dayNine);
        }
        if (dayTen.equals("")) {
            day10 = 0;
        } else {
            day10 = Integer.parseInt(dayTen);
        }
        if (dayEleven.equals("")) {
            day11 = 0;
        } else {
            day11 = Integer.parseInt(dayEleven);
        }
        if (dayTwelve.equals("")) {
            day12 = 0;
        } else {
            day12 = Integer.parseInt(dayTwelve);
        }
        if (dayThirteen.equals("")) {
            day13 = 0;
        } else {
            day13 = Integer.parseInt(dayThirteen);
        }
        if (dayFourteen.equals("")) {
            day14 = 0;
        } else {
            day14 = Integer.parseInt(dayFourteen);
        }
        if (dayFifteen.equals("")) {
            day15 = 0;
        } else {
            day15 = Integer.parseInt(dayFifteen);
        }
        if (daySixteen.equals("")) {
            day16 = 0;
        } else {
            day16 = Integer.parseInt(daySixteen);
        }
        if (daySeventeen.equals("")) {
            day17 = 0;
        } else {
            day17 = Integer.parseInt(daySeventeen);
        }
        if (dayEighteen.equals("")) {
            day18 = 0;
        } else {
            day18 = Integer.parseInt(dayEighteen);
        }
        if (dayNineteen.equals("")) {
            day19 = 0;
        } else {
            day19 = Integer.parseInt(dayNineteen);
        }
        if (dayTwenty.equals("")) {
            day20 = 0;
        } else {
            day20 = Integer.parseInt(dayTwenty);
        }
        if (dayTwentyOne.equals("")) {
            day21 = 0;
        } else {
            day21 = Integer.parseInt(dayTwentyOne);
        }
        if (dayTwentyTwo.equals("")) {
            day22 = 0;
        } else {
            day22 = Integer.parseInt(dayTwentyTwo);
        }
        if (dayTwentyThree.equals("")) {
            day23 = 0;
        } else {
            day23 = Integer.parseInt(dayTwentyThree);
        }
        if (dayTwentyFore.equals("")) {
            day24 = 0;
        } else {
            day24 = Integer.parseInt(dayTwentyFore);
        }
        if (dayTwentyFive.equals("")) {
            day25 = 0;
        } else {
            day25 = Integer.parseInt(dayTwentyFive);
        }
        if (dayTwentySix.equals("")) {
            day26 = 0;
        } else {
            day26 = Integer.parseInt(dayTwentySix);
        }
        if (dayTwentySeven.equals("")) {
            day27 = 0;
        } else {
            day27 = Integer.parseInt(dayTwentySeven);
        }
        if (dayTwentyEight.equals("")) {
            day28 = 0;
        } else {
            day28 = Integer.parseInt(dayTwentyEight);
        }
        if (dayTwentyNine.equals("")) {
            day29 = 0;
        } else {
            day29 = Integer.parseInt(dayTwentyNine);
        }
        if (dayThirty.equals("")) {
            day30 = 0;
        } else {
            day30 = Integer.parseInt(dayThirty);
        }
        if (dayThirtyOne.equals("")) {
            day31 = 0;
        } else {
            day31 = Integer.parseInt(dayThirtyOne);
        }
        /**
         * 计算总完工数（每月数量相加）
         */
        int count = lastmont + day1 + day2 + day3 + day4 + day5 + day6 + day7 + day8 + day9
                + day10 + day11 + day12 + day13 + day14 + day15 + day16 + day17 + day18
                + day19 + day20 + day21 + day22 + day23 + day24 + day25 + day26 + day27 + day28
                + day29 + day30 + day31;
        String countmonth = String.valueOf(count);
        return countmonth;
    }

    /*如果制单人不是登录人，设置为不可填写*/
    public void TextNullEnabled(TextView tvProDocumentary, TextView tvProFactory,
                                TextView tvProDepartment, TextView tvProProcedure,
                                EditText editTexOthers, TextView tvProSingularSystem,
                                TextView tvProColor, EditText editTexTaskNumber,
                                TextView tvProSize, TextView tvProClippingNumber,
                                EditText editTexCompletedLastMonth, TextView tvProTotalCompletion,
                                TextView tvProBalanceAmount, TextView tvProState,
                                TextView tvProYear, TextView tvProMonth, EditText editTexOneDay,
                                EditText editTexTwoDay, EditText editTexThreeDay,
                                EditText editTexForeDay, EditText editTexFiveDay,
                                EditText editTexSixDay, EditText editTexSevenDay,
                                EditText editTexEightDay, EditText editTexNineDay,
                                EditText editTexTenDay, EditText editTexElevenDay,
                                EditText editTexTwelveDay, EditText editTexThirteenDay,
                                EditText editTexFourteenDay, EditText editTexFifteenDay,
                                EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                EditText editTexEighteenDay, EditText editTexNineteenDay,
                                EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                EditText editTexThirtyDay, EditText editTexThirtyOneDay,
                                EditText editTexRemarks, TextView tvProRecorder,
                                TextView tvProRecordat) {
        tvProDocumentary.setEnabled(false);
        tvProFactory.setEnabled(false);
        tvProDepartment.setEnabled(false);
        tvProProcedure.setEnabled(false);
        editTexOthers.setEnabled(false);
        editTexOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        tvProSingularSystem.setEnabled(false);
        tvProColor.setEnabled(true);
        editTexTaskNumber.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTaskNumber.getTag() instanceof TextWatcher) {
            editTexTaskNumber.removeTextChangedListener((TextWatcher) editTexTaskNumber.getTag());
        }
        tvProSize.setEnabled(true);
        tvProClippingNumber.setEnabled(false);
        editTexCompletedLastMonth.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexCompletedLastMonth.getTag() instanceof TextWatcher) {
            editTexCompletedLastMonth.removeTextChangedListener((TextWatcher) editTexCompletedLastMonth.getTag());
        }

        tvProTotalCompletion.setEnabled(false);
        tvProBalanceAmount.setEnabled(false);
        tvProState.setEnabled(false);
        tvProYear.setEnabled(false);
        tvProMonth.setEnabled(false);
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }

        editTexRemarks.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexRemarks.getTag() instanceof TextWatcher) {
            editTexRemarks.removeTextChangedListener((TextWatcher) editTexRemarks.getTag());
        }

        tvProRecorder.setEnabled(false);
        tvProRecordat.setEnabled(false);
    }

    /*如果制单人是登录人,设置为可填写状态*/
    public void TextRecorderEnabled(TextView tvProDocumentary, TextView tvProFactory,
                                    TextView tvProDepartment, TextView tvProProcedure,
                                    EditText editTexOthers, TextView tvProSingularSystem,
                                    TextView tvProColor, EditText editTexTaskNumber,
                                    TextView tvProSize, TextView tvProClippingNumber,
                                    EditText editTexCompletedLastMonth, TextView tvProTotalCompletion,
                                    TextView tvProBalanceAmount, TextView tvProState,
                                    TextView tvProYear, TextView tvProMonth, EditText editTexOneDay,
                                    EditText editTexTwoDay, EditText editTexThreeDay,
                                    EditText editTexForeDay, EditText editTexFiveDay,
                                    EditText editTexSixDay, EditText editTexSevenDay,
                                    EditText editTexEightDay, EditText editTexNineDay,
                                    EditText editTexTenDay, EditText editTexElevenDay,
                                    EditText editTexTwelveDay, EditText editTexThirteenDay,
                                    EditText editTexFourteenDay, EditText editTexFifteenDay,
                                    EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                    EditText editTexEighteenDay, EditText editTexNineteenDay,
                                    EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                    EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                    EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                    EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                    EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                    EditText editTexThirtyDay, EditText editTexThirtyOneDay,
                                    EditText editTexRemarks, TextView tvProRecorder,
                                    TextView tvProRecordat) {
        tvProDocumentary.setEnabled(true);
        tvProFactory.setEnabled(true);
        tvProDepartment.setEnabled(true);
        tvProProcedure.setEnabled(false);
        editTexOthers.setEnabled(true);
        /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOthers.getTag() instanceof TextWatcher) {
            editTexOthers.removeTextChangedListener((TextWatcher) editTexOthers.getTag());
        }

        tvProSingularSystem.setEnabled(true);
        tvProColor.setEnabled(true);
        editTexTaskNumber.setEnabled(true);
        /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTaskNumber.getTag() instanceof TextWatcher) {
            editTexTaskNumber.removeTextChangedListener((TextWatcher) editTexTaskNumber.getTag());
        }

        tvProSize.setEnabled(true);
        tvProClippingNumber.setEnabled(true);
        editTexCompletedLastMonth.setEnabled(true);
         /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexCompletedLastMonth.getTag() instanceof TextWatcher) {
            editTexCompletedLastMonth.removeTextChangedListener((TextWatcher) editTexCompletedLastMonth.getTag());
        }

        tvProTotalCompletion.setEnabled(true);
        tvProBalanceAmount.setEnabled(true);
        tvProState.setEnabled(true);
        tvProYear.setEnabled(true);
        tvProMonth.setEnabled(true);
        editTexOneDay.setEnabled(true);
        /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(true);
        /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(true);
        /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(true);
        /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(true);
        /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(true);
        /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(true);
        /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(true);
        /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(true);
        /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(true);
        /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }

        editTexRemarks.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexRemarks.getTag() instanceof TextWatcher) {
            editTexRemarks.removeTextChangedListener((TextWatcher) editTexRemarks.getTag());
        }

        tvProRecorder.setEnabled(true);
        tvProRecordat.setEnabled(true);
    }

    /*裁床情况下可填1日当日的数量*/
    public void TextOneEnabled(EditText editTexOneDay,
                               EditText editTexTwoDay, EditText editTexThreeDay,
                               EditText editTexForeDay, EditText editTexFiveDay,
                               EditText editTexSixDay, EditText editTexSevenDay,
                               EditText editTexEightDay, EditText editTexNineDay,
                               EditText editTexTenDay, EditText editTexElevenDay,
                               EditText editTexTwelveDay, EditText editTexThirteenDay,
                               EditText editTexFourteenDay, EditText editTexFifteenDay,
                               EditText editTexSixteenDay, EditText editTexSeventeenDay,
                               EditText editTexEighteenDay, EditText editTexNineteenDay,
                               EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                               EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                               EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                               EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                               EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                               EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填2日当日的数量*/
    public void TextTwoEnabled(EditText editTexOneDay,
                               EditText editTexTwoDay, EditText editTexThreeDay,
                               EditText editTexForeDay, EditText editTexFiveDay,
                               EditText editTexSixDay, EditText editTexSevenDay,
                               EditText editTexEightDay, EditText editTexNineDay,
                               EditText editTexTenDay, EditText editTexElevenDay,
                               EditText editTexTwelveDay, EditText editTexThirteenDay,
                               EditText editTexFourteenDay, EditText editTexFifteenDay,
                               EditText editTexSixteenDay, EditText editTexSeventeenDay,
                               EditText editTexEighteenDay, EditText editTexNineteenDay,
                               EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                               EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                               EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                               EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                               EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                               EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填3日当日的数量*/
    public void TextThreeEnabled(EditText editTexOneDay,
                                 EditText editTexTwoDay, EditText editTexThreeDay,
                                 EditText editTexForeDay, EditText editTexFiveDay,
                                 EditText editTexSixDay, EditText editTexSevenDay,
                                 EditText editTexEightDay, EditText editTexNineDay,
                                 EditText editTexTenDay, EditText editTexElevenDay,
                                 EditText editTexTwelveDay, EditText editTexThirteenDay,
                                 EditText editTexFourteenDay, EditText editTexFifteenDay,
                                 EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                 EditText editTexEighteenDay, EditText editTexNineteenDay,
                                 EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                 EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                 EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                 EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                 EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                 EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填4日当日的数量*/
    public void TextForeEnabled(EditText editTexOneDay,
                                EditText editTexTwoDay, EditText editTexThreeDay,
                                EditText editTexForeDay, EditText editTexFiveDay,
                                EditText editTexSixDay, EditText editTexSevenDay,
                                EditText editTexEightDay, EditText editTexNineDay,
                                EditText editTexTenDay, EditText editTexElevenDay,
                                EditText editTexTwelveDay, EditText editTexThirteenDay,
                                EditText editTexFourteenDay, EditText editTexFifteenDay,
                                EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                EditText editTexEighteenDay, EditText editTexNineteenDay,
                                EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填5日当日的数量*/
    public void TextFiveEnabled(EditText editTexOneDay,
                                EditText editTexTwoDay, EditText editTexThreeDay,
                                EditText editTexForeDay, EditText editTexFiveDay,
                                EditText editTexSixDay, EditText editTexSevenDay,
                                EditText editTexEightDay, EditText editTexNineDay,
                                EditText editTexTenDay, EditText editTexElevenDay,
                                EditText editTexTwelveDay, EditText editTexThirteenDay,
                                EditText editTexFourteenDay, EditText editTexFifteenDay,
                                EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                EditText editTexEighteenDay, EditText editTexNineteenDay,
                                EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填6日当日的数量*/
    public void TextSixEnabled(EditText editTexOneDay,
                               EditText editTexTwoDay, EditText editTexThreeDay,
                               EditText editTexForeDay, EditText editTexFiveDay,
                               EditText editTexSixDay, EditText editTexSevenDay,
                               EditText editTexEightDay, EditText editTexNineDay,
                               EditText editTexTenDay, EditText editTexElevenDay,
                               EditText editTexTwelveDay, EditText editTexThirteenDay,
                               EditText editTexFourteenDay, EditText editTexFifteenDay,
                               EditText editTexSixteenDay, EditText editTexSeventeenDay,
                               EditText editTexEighteenDay, EditText editTexNineteenDay,
                               EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                               EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                               EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                               EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                               EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                               EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填7日当日的数量*/
    public void TextSevenEnabled(EditText editTexOneDay,
                                 EditText editTexTwoDay, EditText editTexThreeDay,
                                 EditText editTexForeDay, EditText editTexFiveDay,
                                 EditText editTexSixDay, EditText editTexSevenDay,
                                 EditText editTexEightDay, EditText editTexNineDay,
                                 EditText editTexTenDay, EditText editTexElevenDay,
                                 EditText editTexTwelveDay, EditText editTexThirteenDay,
                                 EditText editTexFourteenDay, EditText editTexFifteenDay,
                                 EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                 EditText editTexEighteenDay, EditText editTexNineteenDay,
                                 EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                 EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                 EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                 EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                 EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                 EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填8日当日的数量*/
    public void TextEightEnabled(EditText editTexOneDay,
                                 EditText editTexTwoDay, EditText editTexThreeDay,
                                 EditText editTexForeDay, EditText editTexFiveDay,
                                 EditText editTexSixDay, EditText editTexSevenDay,
                                 EditText editTexEightDay, EditText editTexNineDay,
                                 EditText editTexTenDay, EditText editTexElevenDay,
                                 EditText editTexTwelveDay, EditText editTexThirteenDay,
                                 EditText editTexFourteenDay, EditText editTexFifteenDay,
                                 EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                 EditText editTexEighteenDay, EditText editTexNineteenDay,
                                 EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                 EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                 EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                 EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                 EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                 EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填9日当日的数量*/
    public void TextNineEnabled(EditText editTexOneDay,
                                EditText editTexTwoDay, EditText editTexThreeDay,
                                EditText editTexForeDay, EditText editTexFiveDay,
                                EditText editTexSixDay, EditText editTexSevenDay,
                                EditText editTexEightDay, EditText editTexNineDay,
                                EditText editTexTenDay, EditText editTexElevenDay,
                                EditText editTexTwelveDay, EditText editTexThirteenDay,
                                EditText editTexFourteenDay, EditText editTexFifteenDay,
                                EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                EditText editTexEighteenDay, EditText editTexNineteenDay,
                                EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填10日当日的数量*/
    public void TextTenEnabled(EditText editTexOneDay,
                               EditText editTexTwoDay, EditText editTexThreeDay,
                               EditText editTexForeDay, EditText editTexFiveDay,
                               EditText editTexSixDay, EditText editTexSevenDay,
                               EditText editTexEightDay, EditText editTexNineDay,
                               EditText editTexTenDay, EditText editTexElevenDay,
                               EditText editTexTwelveDay, EditText editTexThirteenDay,
                               EditText editTexFourteenDay, EditText editTexFifteenDay,
                               EditText editTexSixteenDay, EditText editTexSeventeenDay,
                               EditText editTexEighteenDay, EditText editTexNineteenDay,
                               EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                               EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                               EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                               EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                               EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                               EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填11日当日的数量*/
    public void TextElevenEnabled(EditText editTexOneDay,
                                  EditText editTexTwoDay, EditText editTexThreeDay,
                                  EditText editTexForeDay, EditText editTexFiveDay,
                                  EditText editTexSixDay, EditText editTexSevenDay,
                                  EditText editTexEightDay, EditText editTexNineDay,
                                  EditText editTexTenDay, EditText editTexElevenDay,
                                  EditText editTexTwelveDay, EditText editTexThirteenDay,
                                  EditText editTexFourteenDay, EditText editTexFifteenDay,
                                  EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                  EditText editTexEighteenDay, EditText editTexNineteenDay,
                                  EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                  EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                  EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                  EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                  EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                  EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填12日当日的数量*/
    public void TextTwelveEnabled(EditText editTexOneDay,
                                  EditText editTexTwoDay, EditText editTexThreeDay,
                                  EditText editTexForeDay, EditText editTexFiveDay,
                                  EditText editTexSixDay, EditText editTexSevenDay,
                                  EditText editTexEightDay, EditText editTexNineDay,
                                  EditText editTexTenDay, EditText editTexElevenDay,
                                  EditText editTexTwelveDay, EditText editTexThirteenDay,
                                  EditText editTexFourteenDay, EditText editTexFifteenDay,
                                  EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                  EditText editTexEighteenDay, EditText editTexNineteenDay,
                                  EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                  EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                  EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                  EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                  EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                  EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填13日当日的数量*/
    public void TextThirteenEnabled(EditText editTexOneDay,
                                    EditText editTexTwoDay, EditText editTexThreeDay,
                                    EditText editTexForeDay, EditText editTexFiveDay,
                                    EditText editTexSixDay, EditText editTexSevenDay,
                                    EditText editTexEightDay, EditText editTexNineDay,
                                    EditText editTexTenDay, EditText editTexElevenDay,
                                    EditText editTexTwelveDay, EditText editTexThirteenDay,
                                    EditText editTexFourteenDay, EditText editTexFifteenDay,
                                    EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                    EditText editTexEighteenDay, EditText editTexNineteenDay,
                                    EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                    EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                    EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                    EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                    EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                    EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填14日当日的数量*/
    public void TextFourteenEnabled(EditText editTexOneDay,
                                    EditText editTexTwoDay, EditText editTexThreeDay,
                                    EditText editTexForeDay, EditText editTexFiveDay,
                                    EditText editTexSixDay, EditText editTexSevenDay,
                                    EditText editTexEightDay, EditText editTexNineDay,
                                    EditText editTexTenDay, EditText editTexElevenDay,
                                    EditText editTexTwelveDay, EditText editTexThirteenDay,
                                    EditText editTexFourteenDay, EditText editTexFifteenDay,
                                    EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                    EditText editTexEighteenDay, EditText editTexNineteenDay,
                                    EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                    EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                    EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                    EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                    EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                    EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填15日当日的数量*/
    public void TextFifteenEnabled(EditText editTexOneDay,
                                   EditText editTexTwoDay, EditText editTexThreeDay,
                                   EditText editTexForeDay, EditText editTexFiveDay,
                                   EditText editTexSixDay, EditText editTexSevenDay,
                                   EditText editTexEightDay, EditText editTexNineDay,
                                   EditText editTexTenDay, EditText editTexElevenDay,
                                   EditText editTexTwelveDay, EditText editTexThirteenDay,
                                   EditText editTexFourteenDay, EditText editTexFifteenDay,
                                   EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                   EditText editTexEighteenDay, EditText editTexNineteenDay,
                                   EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                   EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                   EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                   EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                   EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                   EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填16日当日的数量*/
    public void TextSixteenEnabled(EditText editTexOneDay,
                                   EditText editTexTwoDay, EditText editTexThreeDay,
                                   EditText editTexForeDay, EditText editTexFiveDay,
                                   EditText editTexSixDay, EditText editTexSevenDay,
                                   EditText editTexEightDay, EditText editTexNineDay,
                                   EditText editTexTenDay, EditText editTexElevenDay,
                                   EditText editTexTwelveDay, EditText editTexThirteenDay,
                                   EditText editTexFourteenDay, EditText editTexFifteenDay,
                                   EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                   EditText editTexEighteenDay, EditText editTexNineteenDay,
                                   EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                   EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                   EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                   EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                   EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                   EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填17日当日的数量*/
    public void TextSeventeenEnabled(EditText editTexOneDay,
                                     EditText editTexTwoDay, EditText editTexThreeDay,
                                     EditText editTexForeDay, EditText editTexFiveDay,
                                     EditText editTexSixDay, EditText editTexSevenDay,
                                     EditText editTexEightDay, EditText editTexNineDay,
                                     EditText editTexTenDay, EditText editTexElevenDay,
                                     EditText editTexTwelveDay, EditText editTexThirteenDay,
                                     EditText editTexFourteenDay, EditText editTexFifteenDay,
                                     EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                     EditText editTexEighteenDay, EditText editTexNineteenDay,
                                     EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                     EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                     EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                     EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                     EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                     EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填18日当日的数量*/
    public void TextEighteenEnabled(EditText editTexOneDay,
                                    EditText editTexTwoDay, EditText editTexThreeDay,
                                    EditText editTexForeDay, EditText editTexFiveDay,
                                    EditText editTexSixDay, EditText editTexSevenDay,
                                    EditText editTexEightDay, EditText editTexNineDay,
                                    EditText editTexTenDay, EditText editTexElevenDay,
                                    EditText editTexTwelveDay, EditText editTexThirteenDay,
                                    EditText editTexFourteenDay, EditText editTexFifteenDay,
                                    EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                    EditText editTexEighteenDay, EditText editTexNineteenDay,
                                    EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                    EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                    EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                    EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                    EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                    EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填19日当日的数量*/
    public void TextNineteenEnabled(EditText editTexOneDay,
                                    EditText editTexTwoDay, EditText editTexThreeDay,
                                    EditText editTexForeDay, EditText editTexFiveDay,
                                    EditText editTexSixDay, EditText editTexSevenDay,
                                    EditText editTexEightDay, EditText editTexNineDay,
                                    EditText editTexTenDay, EditText editTexElevenDay,
                                    EditText editTexTwelveDay, EditText editTexThirteenDay,
                                    EditText editTexFourteenDay, EditText editTexFifteenDay,
                                    EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                    EditText editTexEighteenDay, EditText editTexNineteenDay,
                                    EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                    EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                    EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                    EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                    EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                    EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填20日当日的数量*/
    public void TextTwentyEnabled(EditText editTexOneDay,
                                  EditText editTexTwoDay, EditText editTexThreeDay,
                                  EditText editTexForeDay, EditText editTexFiveDay,
                                  EditText editTexSixDay, EditText editTexSevenDay,
                                  EditText editTexEightDay, EditText editTexNineDay,
                                  EditText editTexTenDay, EditText editTexElevenDay,
                                  EditText editTexTwelveDay, EditText editTexThirteenDay,
                                  EditText editTexFourteenDay, EditText editTexFifteenDay,
                                  EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                  EditText editTexEighteenDay, EditText editTexNineteenDay,
                                  EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                  EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                  EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                  EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                  EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                  EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填21日当日的数量*/
    public void TextTwentyOneEnabled(EditText editTexOneDay,
                                     EditText editTexTwoDay, EditText editTexThreeDay,
                                     EditText editTexForeDay, EditText editTexFiveDay,
                                     EditText editTexSixDay, EditText editTexSevenDay,
                                     EditText editTexEightDay, EditText editTexNineDay,
                                     EditText editTexTenDay, EditText editTexElevenDay,
                                     EditText editTexTwelveDay, EditText editTexThirteenDay,
                                     EditText editTexFourteenDay, EditText editTexFifteenDay,
                                     EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                     EditText editTexEighteenDay, EditText editTexNineteenDay,
                                     EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                     EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                     EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                     EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                     EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                     EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填22日当日的数量*/
    public void TextTwentyTwoEnabled(EditText editTexOneDay,
                                     EditText editTexTwoDay, EditText editTexThreeDay,
                                     EditText editTexForeDay, EditText editTexFiveDay,
                                     EditText editTexSixDay, EditText editTexSevenDay,
                                     EditText editTexEightDay, EditText editTexNineDay,
                                     EditText editTexTenDay, EditText editTexElevenDay,
                                     EditText editTexTwelveDay, EditText editTexThirteenDay,
                                     EditText editTexFourteenDay, EditText editTexFifteenDay,
                                     EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                     EditText editTexEighteenDay, EditText editTexNineteenDay,
                                     EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                     EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                     EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                     EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                     EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                     EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填23日当日的数量*/
    public void TextTwentyThreeEnabled(EditText editTexOneDay,
                                       EditText editTexTwoDay, EditText editTexThreeDay,
                                       EditText editTexForeDay, EditText editTexFiveDay,
                                       EditText editTexSixDay, EditText editTexSevenDay,
                                       EditText editTexEightDay, EditText editTexNineDay,
                                       EditText editTexTenDay, EditText editTexElevenDay,
                                       EditText editTexTwelveDay, EditText editTexThirteenDay,
                                       EditText editTexFourteenDay, EditText editTexFifteenDay,
                                       EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                       EditText editTexEighteenDay, EditText editTexNineteenDay,
                                       EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                       EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                       EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                       EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                       EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                       EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填24日当日的数量*/
    public void TextTwentyForeEnabled(EditText editTexOneDay,
                                      EditText editTexTwoDay, EditText editTexThreeDay,
                                      EditText editTexForeDay, EditText editTexFiveDay,
                                      EditText editTexSixDay, EditText editTexSevenDay,
                                      EditText editTexEightDay, EditText editTexNineDay,
                                      EditText editTexTenDay, EditText editTexElevenDay,
                                      EditText editTexTwelveDay, EditText editTexThirteenDay,
                                      EditText editTexFourteenDay, EditText editTexFifteenDay,
                                      EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                      EditText editTexEighteenDay, EditText editTexNineteenDay,
                                      EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                      EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                      EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                      EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                      EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                      EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填25日当日的数量*/
    public void TextTwentyFiveEnabled(EditText editTexOneDay,
                                      EditText editTexTwoDay, EditText editTexThreeDay,
                                      EditText editTexForeDay, EditText editTexFiveDay,
                                      EditText editTexSixDay, EditText editTexSevenDay,
                                      EditText editTexEightDay, EditText editTexNineDay,
                                      EditText editTexTenDay, EditText editTexElevenDay,
                                      EditText editTexTwelveDay, EditText editTexThirteenDay,
                                      EditText editTexFourteenDay, EditText editTexFifteenDay,
                                      EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                      EditText editTexEighteenDay, EditText editTexNineteenDay,
                                      EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                      EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                      EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                      EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                      EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                      EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填26日当日的数量*/
    public void TextTwentySixEnabled(EditText editTexOneDay,
                                     EditText editTexTwoDay, EditText editTexThreeDay,
                                     EditText editTexForeDay, EditText editTexFiveDay,
                                     EditText editTexSixDay, EditText editTexSevenDay,
                                     EditText editTexEightDay, EditText editTexNineDay,
                                     EditText editTexTenDay, EditText editTexElevenDay,
                                     EditText editTexTwelveDay, EditText editTexThirteenDay,
                                     EditText editTexFourteenDay, EditText editTexFifteenDay,
                                     EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                     EditText editTexEighteenDay, EditText editTexNineteenDay,
                                     EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                     EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                     EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                     EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                     EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                     EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填27日当日的数量*/
    public void TextTwentySevenEnabled(EditText editTexOneDay,
                                       EditText editTexTwoDay, EditText editTexThreeDay,
                                       EditText editTexForeDay, EditText editTexFiveDay,
                                       EditText editTexSixDay, EditText editTexSevenDay,
                                       EditText editTexEightDay, EditText editTexNineDay,
                                       EditText editTexTenDay, EditText editTexElevenDay,
                                       EditText editTexTwelveDay, EditText editTexThirteenDay,
                                       EditText editTexFourteenDay, EditText editTexFifteenDay,
                                       EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                       EditText editTexEighteenDay, EditText editTexNineteenDay,
                                       EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                       EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                       EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                       EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                       EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                       EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填28日当日的数量*/
    public void TextTwentyEightEnabled(EditText editTexOneDay,
                                       EditText editTexTwoDay, EditText editTexThreeDay,
                                       EditText editTexForeDay, EditText editTexFiveDay,
                                       EditText editTexSixDay, EditText editTexSevenDay,
                                       EditText editTexEightDay, EditText editTexNineDay,
                                       EditText editTexTenDay, EditText editTexElevenDay,
                                       EditText editTexTwelveDay, EditText editTexThirteenDay,
                                       EditText editTexFourteenDay, EditText editTexFifteenDay,
                                       EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                       EditText editTexEighteenDay, EditText editTexNineteenDay,
                                       EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                       EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                       EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                       EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                       EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                       EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填29日当日的数量*/
    public void TextTwentyNineEnabled(EditText editTexOneDay,
                                      EditText editTexTwoDay, EditText editTexThreeDay,
                                      EditText editTexForeDay, EditText editTexFiveDay,
                                      EditText editTexSixDay, EditText editTexSevenDay,
                                      EditText editTexEightDay, EditText editTexNineDay,
                                      EditText editTexTenDay, EditText editTexElevenDay,
                                      EditText editTexTwelveDay, EditText editTexThirteenDay,
                                      EditText editTexFourteenDay, EditText editTexFifteenDay,
                                      EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                      EditText editTexEighteenDay, EditText editTexNineteenDay,
                                      EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                      EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                      EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                      EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                      EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                      EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填30日当日的数量*/
    public void TextThirtyEnabled(EditText editTexOneDay,
                                  EditText editTexTwoDay, EditText editTexThreeDay,
                                  EditText editTexForeDay, EditText editTexFiveDay,
                                  EditText editTexSixDay, EditText editTexSevenDay,
                                  EditText editTexEightDay, EditText editTexNineDay,
                                  EditText editTexTenDay, EditText editTexElevenDay,
                                  EditText editTexTwelveDay, EditText editTexThirteenDay,
                                  EditText editTexFourteenDay, EditText editTexFifteenDay,
                                  EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                  EditText editTexEighteenDay, EditText editTexNineteenDay,
                                  EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                  EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                  EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                  EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                  EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                  EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }

    /*裁床情况下可填31日当日的数量*/
    public void TextThirtyOneEnabled(EditText editTexOneDay,
                                     EditText editTexTwoDay, EditText editTexThreeDay,
                                     EditText editTexForeDay, EditText editTexFiveDay,
                                     EditText editTexSixDay, EditText editTexSevenDay,
                                     EditText editTexEightDay, EditText editTexNineDay,
                                     EditText editTexTenDay, EditText editTexElevenDay,
                                     EditText editTexTwelveDay, EditText editTexThirteenDay,
                                     EditText editTexFourteenDay, EditText editTexFifteenDay,
                                     EditText editTexSixteenDay, EditText editTexSeventeenDay,
                                     EditText editTexEighteenDay, EditText editTexNineteenDay,
                                     EditText editTexTwentyDay, EditText editTexTwentyOneDay,
                                     EditText editTexTwentyTwoDay, EditText editTexTwentyThreeDay,
                                     EditText editTexTwentyForeDay, EditText editTexTwentyFiveDay,
                                     EditText editTexTwentySixDay, EditText editTexTwentySevenDay,
                                     EditText editTexTwentyEightDay, EditText editTexTwentyNineDay,
                                     EditText editTexThirtyDay, EditText editTexThirtyOneDay) {
        editTexOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexOneDay.getTag() instanceof TextWatcher) {
            editTexOneDay.removeTextChangedListener((TextWatcher) editTexOneDay.getTag());
        }

        editTexTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwoDay.getTag() instanceof TextWatcher) {
            editTexTwoDay.removeTextChangedListener((TextWatcher) editTexTwoDay.getTag());
        }

        editTexThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThreeDay.getTag() instanceof TextWatcher) {
            editTexThreeDay.removeTextChangedListener((TextWatcher) editTexThreeDay.getTag());
        }

        editTexForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexForeDay.getTag() instanceof TextWatcher) {
            editTexForeDay.removeTextChangedListener((TextWatcher) editTexForeDay.getTag());
        }

        editTexFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFiveDay.getTag() instanceof TextWatcher) {
            editTexFiveDay.removeTextChangedListener((TextWatcher) editTexFiveDay.getTag());
        }

        editTexSixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixDay.getTag() instanceof TextWatcher) {
            editTexSixDay.removeTextChangedListener((TextWatcher) editTexSixDay.getTag());
        }

        editTexSevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSevenDay.getTag() instanceof TextWatcher) {
            editTexSevenDay.removeTextChangedListener((TextWatcher) editTexSevenDay.getTag());
        }

        editTexEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEightDay.getTag() instanceof TextWatcher) {
            editTexEightDay.removeTextChangedListener((TextWatcher) editTexEightDay.getTag());
        }

        editTexNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineDay.getTag() instanceof TextWatcher) {
            editTexNineDay.removeTextChangedListener((TextWatcher) editTexNineDay.getTag());
        }

        editTexTenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTenDay.getTag() instanceof TextWatcher) {
            editTexTenDay.removeTextChangedListener((TextWatcher) editTexTenDay.getTag());
        }

        editTexElevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexElevenDay.getTag() instanceof TextWatcher) {
            editTexElevenDay.removeTextChangedListener((TextWatcher) editTexElevenDay.getTag());
        }

        editTexTwelveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwelveDay.getTag() instanceof TextWatcher) {
            editTexTwelveDay.removeTextChangedListener((TextWatcher) editTexTwelveDay.getTag());
        }

        editTexThirteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirteenDay.getTag() instanceof TextWatcher) {
            editTexThirteenDay.removeTextChangedListener((TextWatcher) editTexThirteenDay.getTag());
        }

        editTexFourteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFourteenDay.getTag() instanceof TextWatcher) {
            editTexFourteenDay.removeTextChangedListener((TextWatcher) editTexFourteenDay.getTag());
        }

        editTexFifteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexFifteenDay.getTag() instanceof TextWatcher) {
            editTexFifteenDay.removeTextChangedListener((TextWatcher) editTexFifteenDay.getTag());
        }

        editTexSixteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSixteenDay.getTag() instanceof TextWatcher) {
            editTexSixteenDay.removeTextChangedListener((TextWatcher) editTexSixteenDay.getTag());
        }

        editTexSeventeenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexSeventeenDay.getTag() instanceof TextWatcher) {
            editTexSeventeenDay.removeTextChangedListener((TextWatcher) editTexSeventeenDay.getTag());
        }

        editTexEighteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexEighteenDay.getTag() instanceof TextWatcher) {
            editTexEighteenDay.removeTextChangedListener((TextWatcher) editTexEighteenDay.getTag());
        }

        editTexNineteenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexNineteenDay.getTag() instanceof TextWatcher) {
            editTexNineteenDay.removeTextChangedListener((TextWatcher) editTexNineteenDay.getTag());
        }

        editTexTwentyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyDay.getTag() instanceof TextWatcher) {
            editTexTwentyDay.removeTextChangedListener((TextWatcher) editTexTwentyDay.getTag());
        }

        editTexTwentyOneDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyOneDay.getTag() instanceof TextWatcher) {
            editTexTwentyOneDay.removeTextChangedListener((TextWatcher) editTexTwentyOneDay.getTag());
        }

        editTexTwentyTwoDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyTwoDay.getTag() instanceof TextWatcher) {
            editTexTwentyTwoDay.removeTextChangedListener((TextWatcher) editTexTwentyTwoDay.getTag());
        }

        editTexTwentyThreeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyThreeDay.getTag() instanceof TextWatcher) {
            editTexTwentyThreeDay.removeTextChangedListener((TextWatcher) editTexTwentyThreeDay.getTag());
        }

        editTexTwentyForeDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyForeDay.getTag() instanceof TextWatcher) {
            editTexTwentyForeDay.removeTextChangedListener((TextWatcher) editTexTwentyForeDay.getTag());
        }

        editTexTwentyFiveDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyFiveDay.getTag() instanceof TextWatcher) {
            editTexTwentyFiveDay.removeTextChangedListener((TextWatcher) editTexTwentyFiveDay.getTag());
        }

        editTexTwentySixDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySixDay.getTag() instanceof TextWatcher) {
            editTexTwentySixDay.removeTextChangedListener((TextWatcher) editTexTwentySixDay.getTag());
        }

        editTexTwentySevenDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentySevenDay.getTag() instanceof TextWatcher) {
            editTexTwentySevenDay.removeTextChangedListener((TextWatcher) editTexTwentySevenDay.getTag());
        }

        editTexTwentyEightDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyEightDay.getTag() instanceof TextWatcher) {
            editTexTwentyEightDay.removeTextChangedListener((TextWatcher) editTexTwentyEightDay.getTag());
        }

        editTexTwentyNineDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexTwentyNineDay.getTag() instanceof TextWatcher) {
            editTexTwentyNineDay.removeTextChangedListener((TextWatcher) editTexTwentyNineDay.getTag());
        }

        editTexThirtyDay.setEnabled(false);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyDay.getTag() instanceof TextWatcher) {
            editTexThirtyDay.removeTextChangedListener((TextWatcher) editTexThirtyDay.getTag());
        }

        editTexThirtyOneDay.setEnabled(true);
            /*根据tag移除此前的监听事件，否则会造成数据丢失，错乱的问题*/
        if (editTexThirtyOneDay.getTag() instanceof TextWatcher) {
            editTexThirtyOneDay.removeTextChangedListener((TextWatcher) editTexThirtyOneDay.getTag());
        }
    }
}
