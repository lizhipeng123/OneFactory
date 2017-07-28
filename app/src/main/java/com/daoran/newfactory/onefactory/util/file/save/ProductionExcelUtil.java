package com.daoran.newfactory.onefactory.util.file.save;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.widget.Toast;

import com.daoran.newfactory.onefactory.bean.ProducationDetailBean;
import com.daoran.newfactory.onefactory.util.exception.ToastUtils;

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
 * 生产日报表保存excel
 * Created by lizhipeng on 2017/6/27.
 */

public class ProductionExcelUtil {
    //内存地址
    public static String root = Environment.getExternalStorageDirectory()
            .getPath();

    public static void writeExcel(
            final Context context,
            final List<ProducationDetailBean.DataBean> exportOrder,
            String fileName) throws Exception {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED) && getAvailableStorage()
                > 1000000) {
            Toast.makeText(context, "SD卡不可用", Toast.LENGTH_LONG).show();
            return;
        }
        String[] title = {"款号", "跟单", "工厂","部门/组别","工序","组别人数"
        ,"制单数","花色","任务数","尺码","实裁数","上月完工","总完工数",
                "结余数量","状态","年","月","1日","2日","3日","4日","5日","6日","7日"
                ,"8日","9日","10日","11日","12日","13日","14日","15日",
                "16日","17日","18日","19日","20日","21日","22日","23日","24日",
                "25日","26日","27日","28日","29日","30日","31日","备注","制单人"
                ,"制单时间"};
        File file;
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
        final WritableSheet sheet = wwb.createSheet("生产日报表", 0);
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
                    ProducationDetailBean.DataBean order = exportOrder.get(i);
                    Label orderNum = new Label(0, i + 1, order.getItem());//款号
                    Label restaurant = new Label(1, i + 1, order.getPrddocumentary());//跟单
                    Label nameLabel = new Label(2, i + 1, order.getSubfactory());//工厂
                    Label subfactoryTeamsLabel = new Label(3, i + 1, order.getSubfactoryTeams());//部门/组别
                    Label workingProcedureLabel = new Label(4, i + 1, order.getWorkingProcedure());//工序
                    Label workersLabel = new Label(5, i + 1, order.getWorkers());//组别人数
                    Label PqtyLabel = new Label(6, i + 1, order.getPqty());//制单数
                    Label prodcolLabel = new Label(7, i + 1, order.getProdcol());//花色
                    Label TaskqtyLabel = new Label(8, i + 1, order.getTaskqty());//任务数
                    Label mdlLabel = new Label(9, i + 1, order.getMdl());//尺码
                    Label factcutqtyLabel = new Label(10, i + 1, order.getFactcutqty());//实裁数
                    Label LastMonQtyLabel = new Label(11, i + 1, order.getLastMonQty());//上月完工
                    Label SumCompletedQtyLabel = new Label(12, i + 1, order.getSumCompletedQty());//总完工数
                    Label LeftQtyLabel = new Label(13, i + 1, order.getLeftQty());//结余数量
                    Label PrdstatusLabel = new Label(14, i + 1, order.getPrdstatus());//状态
                    Label yearLabel = new Label(15, i + 1, order.getYear());//年
                    Label monthLabel = new Label(16, i + 1, order.getMonth());//月
                    Label day1Label = new Label(17, i + 1, order.getDay1());
                    Label Day2Label = new Label(18, i + 1, order.getDay2());
                    Label Day3Label = new Label(19, i + 1, order.getDay3());
                    Label Day4Label = new Label(20, i + 1, order.getDay4());
                    Label Day5Label = new Label(21, i + 1, order.getDay5());
                    Label Day6Label = new Label(22, i + 1, order.getDay6());
                    Label Day7Label = new Label(23, i + 1, order.getDay7());
                    Label Day8Label = new Label(24, i + 1, order.getDay8());
                    Label Day9Label = new Label(25, i + 1, order.getDay9());
                    Label Day10Label = new Label(26, i + 1, order.getDay10());
                    Label Day11Label = new Label(27, i + 1, order.getDay11());
                    Label Day12Label = new Label(28, i + 1, order.getDay12());
                    Label Day13Label = new Label(29, i + 1, order.getDay13());
                    Label Day14Label = new Label(30, i + 1, order.getDay14());
                    Label Day15Label = new Label(31, i + 1, order.getDay15());
                    Label Day16Label = new Label(32, i + 1, order.getDay16());
                    Label Day17Label = new Label(33, i + 1, order.getDay17());
                    Label Day18Label = new Label(34, i + 1, order.getDay18());
                    Label Day19Label = new Label(35, i + 1, order.getDay19());
                    Label Day20Label = new Label(36, i + 1, order.getDay20());
                    Label Day21Label = new Label(37, i + 1, order.getDay21());
                    Label Day22Label = new Label(38, i + 1, order.getDay22());
                    Label Day23Label = new Label(39, i + 1, order.getDay23());
                    Label Day24Label = new Label(40, i + 1, order.getDay24());
                    Label Day25Label = new Label(41, i + 1, order.getDay25());
                    Label Day26Label = new Label(42, i + 1, order.getDay26());
                    Label Day27Label = new Label(43, i + 1, order.getDay27());
                    Label Day28Label = new Label(44, i + 1, order.getDay28());
                    Label Day29Label = new Label(45, i + 1, order.getDay29());
                    Label Day30Label = new Label(46, i + 1, order.getDay30());
                    Label Day31Label = new Label(47, i + 1, order.getDay31());
                    Label MemoLabel = new Label(48, i + 1, order.getMemo());
                    Label RecorderLabel = new Label(49, i + 1, order.getRecorder());
                    Label RecordatLabel = new Label(50, i + 1, order.getRecordat());
                    try {
                        sheet.addCell(orderNum);
                        sheet.addCell(restaurant);
                        sheet.addCell(nameLabel);
                        sheet.addCell(subfactoryTeamsLabel);
                        sheet.addCell(workingProcedureLabel);
                        sheet.addCell(workersLabel);
                        sheet.addCell(PqtyLabel);
                        sheet.addCell(prodcolLabel);
                        sheet.addCell(TaskqtyLabel);
                        sheet.addCell(mdlLabel);
                        sheet.addCell(factcutqtyLabel);
                        sheet.addCell(LastMonQtyLabel);
                        sheet.addCell(SumCompletedQtyLabel);
                        sheet.addCell(LeftQtyLabel);
                        sheet.addCell(PrdstatusLabel);
                        sheet.addCell(yearLabel);
                        sheet.addCell(monthLabel);
                        sheet.addCell(day1Label);
                        sheet.addCell(Day2Label);
                        sheet.addCell(Day3Label);
                        sheet.addCell(Day4Label);
                        sheet.addCell(Day5Label);
                        sheet.addCell(Day6Label);
                        sheet.addCell(Day7Label);
                        sheet.addCell(Day8Label);
                        sheet.addCell(Day9Label);
                        sheet.addCell(Day10Label);
                        sheet.addCell(Day11Label);
                        sheet.addCell(Day12Label);
                        sheet.addCell(Day13Label);
                        sheet.addCell(Day14Label);
                        sheet.addCell(Day15Label);
                        sheet.addCell(Day16Label);
                        sheet.addCell(Day17Label);
                        sheet.addCell(Day18Label);
                        sheet.addCell(Day19Label);
                        sheet.addCell(Day20Label);
                        sheet.addCell(Day21Label);
                        sheet.addCell(Day22Label);
                        sheet.addCell(Day23Label);
                        sheet.addCell(Day24Label);
                        sheet.addCell(Day25Label);
                        sheet.addCell(Day26Label);
                        sheet.addCell(Day27Label);
                        sheet.addCell(Day28Label);
                        sheet.addCell(Day29Label);
                        sheet.addCell(Day30Label);
                        sheet.addCell(Day31Label);
                        sheet.addCell(MemoLabel);
                        sheet.addCell(RecorderLabel);
                        sheet.addCell(RecordatLabel);
                    } catch (WriteException e) {
                        ToastUtils.ShowToastMessage("写入失败",context);
                        e.printStackTrace();
                    }
                }
                ToastUtils.ShowToastMessage("写入成功",context);
                // 写入数据
                try {
                    wwb.write();
                    // 关闭文件
                    wwb.close();
                } catch (IOException e) {
                    ToastUtils.ShowToastMessage("写入失败",context);
                    e.printStackTrace();
                } catch (WriteException e) {
                    ToastUtils.ShowToastMessage("写入失败",context);
                    e.printStackTrace();
                }

            }
        }).start();
    }

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
