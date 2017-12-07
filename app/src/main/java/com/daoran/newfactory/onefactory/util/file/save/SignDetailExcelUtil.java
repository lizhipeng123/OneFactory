package com.daoran.newfactory.onefactory.util.file.save;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.widget.Toast;


import com.daoran.newfactory.onefactory.bean.signbean.SignDailyBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Created by lizhipeng on 2017/7/18.
 */

public class SignDetailExcelUtil {
    //内存地址
    public static String root = Environment.getExternalStorageDirectory()
            .getPath();

    public static void writeExcel(
            final Context context,
            final List<SignDailyBean.DataBean> exportOrder,
            String fileName) throws Exception {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED) && getAvailableStorage()
                > 1000000) {
            Toast.makeText(context, "SD卡不可用", Toast.LENGTH_LONG).show();
            return;
        }

        String[] title = {"编号", "制单人", "班次", "签到时间", "签到地点", "备注"};
        File file;
        File appDir = new File(Environment.getExternalStorageDirectory(), "DfAPP");
        File dir = new File(context.getExternalFilesDir(null).getPath());
        file = new File(dir, fileName + ".xls");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 创建Excel工作表
        final WritableWorkbook wwb;
        OutputStream os = new FileOutputStream(file);
        wwb = Workbook.createWorkbook(os);
        // 添加第一个工作表并设置第一个Sheet的名字
        final WritableSheet sheet = wwb.createSheet("签到查询表", 0);
        Label label;
        for (int i = 0; i < title.length; i++) {
            // Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
            // 在Label对象的子对象中指明单元格的位置和内容
            label = new Label(i, 0, title[i], getHeader());
            // 将定义好的单元格添加到工作表中
            sheet.addCell(label);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < exportOrder.size(); i++) {
                    SignDailyBean.DataBean order = exportOrder.get(i);
                    Label orderNum = new Label(0, i + 1, order.getCode());
                    Label restaurant = new Label(1, i + 1, order.getRecorder());
                    Label regedittyp = new Label(2, i + 1, order.getRegedittyp());
                    Label recordat = new Label(3, i + 1, order.getRecordat());
                    Label recordplace = new Label(4, i + 1, order.getRecordplace());
                    Label memo = new Label(5, i + 1, order.getMemo());
                    try {
                        sheet.addCell(orderNum);
                        sheet.addCell(restaurant);
                        sheet.addCell(regedittyp);
                        sheet.addCell(recordat);
                        sheet.addCell(recordplace);
                        sheet.addCell(memo);
                    } catch (WriteException e) {
                        Toast.makeText(context, "写入失败。。。。。",
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
                // 写入数据
                try {
                    wwb.write();
                    // 关闭文件
                    Toast.makeText(context, "写入成功。。。。。",
                            Toast.LENGTH_LONG).show();
                    wwb.close();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    /**
     * 设置表格样式
     * @return
     */
    public static WritableCellFormat getHeader() {
        WritableFont font = new WritableFont(WritableFont.TIMES, 10,
                WritableFont.BOLD);// 定义字体
        try {
            font.setColour(Colour.BLUE);// 蓝色字体
        } catch (WriteException e1) {
            e1.printStackTrace();
        }
        WritableCellFormat format = new WritableCellFormat(font);
        try {
            format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
            // format.setBorder(Border.ALL, BorderLineStyle.THIN,
            // Colour.BLACK);// 黑色边框
            // format.setBackground(Colour.YELLOW);// 黄色背景
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }


    /**
     * 获取SD可用容量
     */
    private static long getAvailableStorage() {
        StatFs statFs = new StatFs(root);
        long blockSize = statFs.getBlockSize();
        long availableBlocks = statFs.getAvailableBlocks();
        long availableSize = blockSize * availableBlocks;
        // Formatter.formatFileSize(context, availableSize);
        return availableSize;
    }
}
