package com.daoran.newfactory.onefactory.util.file.save;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.widget.Toast;

import com.daoran.newfactory.onefactory.bean.qacworkbean.QACworkPageDataBean;
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
 * 查货跟踪表保存excel文件
 * Created by lizhipeng on 2017/6/27.
 */

public class CommodityExcelUtil {
    //内存地址
    public static String root = Environment.getExternalStorageDirectory()
            .getPath();

    public static void writeExcel(
            final Context context,
            final List<QACworkPageDataBean.DataBean> exportOrder,
            String fileName) throws Exception {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED) && getAvailableStorage()
                > 1000000) {
            Toast.makeText(context, "SD卡不可用", Toast.LENGTH_LONG).show();
            return;
        }
        String[] title = {"款号", "客户", "跟单","生产主管","主管评分","封样资料接收时间"
                ,"大货资料接收时间","出货时间","制单数量","需要特别备注的情况",
                "预计产前报告时间","开产前会时间","产前会报告", "大货面料情况",
                "大货辅料情况","大货特殊工艺情况","特殊工艺特别备注","实裁数",
                "上线日期","下线日期","加工厂","预计早期时间","自查早期时间",
                "早查报告","预计中期时间","自查中期时间","中期报告","预计尾期时间",
                "自查尾期时间","尾查报告","客查中期时间","客查尾期时间",
                "成品包装开始日期","装箱数量","QC特别备注","离厂日期","查货批次",
                "后道","业务员确认客查日期","尾查预查","巡检中查", "QA首扎",
                "QA首扎件数","QA首扎日"};
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
                    QACworkPageDataBean.DataBean order = exportOrder.get(i);
                    Label orderNum = new Label(0, i + 1, order.getItem());//款号
                    Label ctmtxt = new Label(1, i + 1, order.getCtmtxt());//客户
                    Label prddocumentaryLabel = new Label(2, i + 1, order.getPrddocumentary());//跟单
                    Label prdmasterLabel = new Label(3, i + 1, order.getPrdmaster());//生产主管
                    Label QCMasterScoreLabel = new Label(4, i + 1, order.getQCMasterScore());//主管评分
                    Label sealedrevLabel = new Label(5, i + 1, order.getSealedrev());//封样资料接收时间
                    Label docbackLabel = new Label(6, i + 1, order.getDocback());//大货资料接收时间
                    Label lcdatLabel = new Label(7, i + 1, order.getLcdat());//出货时间
                    Label TaskqtyLabel = new Label(8, i + 1, order.getTaskqty());//制单数量
                    Label preMemoLabel = new Label(9, i + 1, order.getPreMemo());//需要特别备注的情况
                    Label predocdtLabel = new Label(10, i + 1, order.getPredocdt());//预计产前报告时间
                    Label predtLabel = new Label(11, i + 1, order.getPredt());//开产前会时间
                    Label predocLabel = new Label(12, i + 1, order.getPredoc());//产前会报告
                    Label fabricsokLabel = new Label(13, i + 1, order.getFabricsok());//大货面料情况
                    Label accessoriesokLabel = new Label(14, i + 1, order.getAccessoriesok());//大货辅料情况
                    Label spcproDecLabel = new Label(15, i + 1, order.getSpcproDec());//大货特殊工艺情况
                    Label spcproMemoLabel = new Label(16, i + 1, order.getSpcproMemo());//特殊工艺特别备注
                    Label cutqtyLabel = new Label(17, i + 1, order.getCutqty());//实裁数
                    Label sewFdtLabel = new Label(18, i + 1, order.getSewFdt());//上线日期
                    Label sewMdtLabel = new Label(19, i + 1, order.getSewMdt());//下线日期
                    Label subfactoryLabel = new Label(20, i + 1, order.getSubfactory());//加工厂
                    Label prebdtLabel = new Label(21, i + 1, order.getPrebdt());//预计早期时间
                    Label QCbdtLabel = new Label(22, i + 1, order.getQCbdt());//自查早期时间
                    Label QCbdtDocLabel = new Label(23, i + 1, order.getQCbdtDoc());//早查报告
                    Label premdtLabel = new Label(24, i + 1, order.getPremdt());//预计中期时间
                    Label QCmdtLabel = new Label(25, i + 1, order.getQCmdt());//自查中期时间
                    Label QCmdtDocLabel = new Label(26, i + 1, order.getQCmdtDoc());//中期报告
                    Label preedtLabel = new Label(27, i + 1, order.getPreedt());//预计尾期时间
                    Label QCMedtLabel = new Label(28, i + 1, order.getQCMedt());//自查尾期时间
                    Label QCedtDocLabel = new Label(29, i + 1, order.getQCedtDoc());//尾查报告
                    Label fctmdtLabel = new Label(30, i + 1, order.getFctmdt());//客查中期时间
                    Label fctedtLabel = new Label(31, i + 1, order.getFctedt());//客查尾期时间
                    Label packbdatLabel = new Label(32, i + 1, order.getPackbdat());//成品包装开始日期
                    Label packqty2Label = new Label(33, i + 1, order.getPackqty2());//装箱数量
                    Label QCMemoLabel = new Label(34, i + 1, order.getQCMemo());//QC特别备注
                    Label factlcdatLabel = new Label(35, i + 1, order.getFactlcdat());//离厂日期
                    Label batchidLabel = new Label(36, i + 1, order.getBatchid());//查货批次
                    Label ourAfterLabel = new Label(37, i + 1, order.getOurAfter());//后道
                    Label ctmchkdtLabel = new Label(38, i + 1, order.getCtmchkdt());//业务员确认客查日期
                    Label IPQCPedtLabel = new Label(39, i + 1, order.getIPQCPedt());//尾查预查
                    Label IPQCmdtLabel = new Label(40, i + 1, order.getIPQCmdt());//巡检中查
                    Label QAnameLabel = new Label(41, i + 1, order.getQAname());//QA首扎
                    Label QAScoreLabel = new Label(42, i + 1, order.getQAScore());//QA首扎件数
                    Label QAMemoLabel = new Label(43, i + 1, order.getQAMemo());//QA首扎日期
                    try {
                        sheet.addCell(orderNum);
                        sheet.addCell(ctmtxt);
                        sheet.addCell(prddocumentaryLabel);
                        sheet.addCell(prdmasterLabel);
                        sheet.addCell(QCMasterScoreLabel);
                        sheet.addCell(sealedrevLabel);
                        sheet.addCell(docbackLabel);
                        sheet.addCell(lcdatLabel);
                        sheet.addCell(TaskqtyLabel);
                        sheet.addCell(preMemoLabel);
                        sheet.addCell(predocdtLabel);
                        sheet.addCell(predtLabel);
                        sheet.addCell(predocLabel);
                        sheet.addCell(fabricsokLabel);
                        sheet.addCell(accessoriesokLabel);
                        sheet.addCell(spcproDecLabel);
                        sheet.addCell(spcproMemoLabel);
                        sheet.addCell(cutqtyLabel);
                        sheet.addCell(sewFdtLabel);
                        sheet.addCell(sewMdtLabel);
                        sheet.addCell(subfactoryLabel);
                        sheet.addCell(prebdtLabel);
                        sheet.addCell(QCbdtLabel);
                        sheet.addCell(QCbdtDocLabel);
                        sheet.addCell(premdtLabel);
                        sheet.addCell(QCmdtLabel);
                        sheet.addCell(QCmdtDocLabel);
                        sheet.addCell(preedtLabel);
                        sheet.addCell(QCMedtLabel);
                        sheet.addCell(QCedtDocLabel);
                        sheet.addCell(fctmdtLabel);
                        sheet.addCell(fctedtLabel);
                        sheet.addCell(packbdatLabel);
                        sheet.addCell(packqty2Label);
                        sheet.addCell(QCMemoLabel);
                        sheet.addCell(factlcdatLabel);
                        sheet.addCell(batchidLabel);
                        sheet.addCell(ourAfterLabel);
                        sheet.addCell(ctmchkdtLabel);
                        sheet.addCell(IPQCPedtLabel);
                        sheet.addCell(IPQCmdtLabel);
                        sheet.addCell(QAnameLabel);
                        sheet.addCell(QAScoreLabel);
                        sheet.addCell(QAMemoLabel);
                    } catch (WriteException e) {
                        ToastUtils.ShowToastMessage("写入失败",context);
                        e.printStackTrace();
                    }
                }
                ToastUtils.ShowToastMessage("写入成功",context);
                // 写入数据
                try {
                    ToastUtils.ShowToastMessage("写入成功，请在设置中Excel文件中查看",
                            context);
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
