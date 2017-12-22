package com.daoran.newfactory.onefactory.adapter.pocontrastadpter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.LinearLayout;

import com.daoran.newfactory.onefactory.R;
import com.daoran.newfactory.onefactory.bean.pocontrastbean.PoContrastEntyBean;
import com.daoran.newfactory.onefactory.util.Http.sharedparams.SPUtils;

import java.util.List;

/**
 * Created by luoqf on 2017/12/4.
 * 生产单物料审核表适配器
 */

public class PoContrastAdapter extends BaseAdapter{
private Context context;//创建上下文对象
private List<PoContrastEntyBean.DataBean> dataBeen;//生产单物料审核表实体集合
private SPUtils spUtils;
private SharedPreferences sp;//临时存储

public PoContrastAdapter(Context context, List<PoContrastEntyBean.DataBean> dataBeen) {
        this.context = context;
        this.dataBeen = dataBeen;
        }

    @Override
    public int  getCount() {
        return dataBeen.size();
    }

    @Override
    public PoContrastEntyBean.DataBean getItem(int position) {
        return dataBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pocontrast_data, null);
            holder.tvPoContrastMtrcol = (TextView) convertView.findViewById(R.id.tvPoContrastMtrcol);
            holder.tvPoContrastMtrstatus = (TextView) convertView.findViewById(R.id.tvPoContrastMtrstatus);
            holder.tvPoContrastPoqty = (TextView) convertView.findViewById(R.id.tvPoContrastPoqty);
            holder.tvPoContrastPpric = (TextView) convertView.findViewById(R.id.tvPoContrastPpric);
            holder.tvPoContrastSealedrev = (TextView) convertView.findViewById(R.id.tvPoContrastSealedrev);
            holder.tvPoContrastProcpric = (TextView) convertView.findViewById(R.id.tvPoContrastProcpric);
            holder.tvPoContrastPrepric = (TextView) convertView.findViewById(R.id.tvPoContrastPrepric);
            holder.tvPoContrastPurprofit = (TextView) convertView.findViewById(R.id.tvPoContrastPurprofit);
            holder.tvPoContrastMtrprocpric = (TextView) convertView.findViewById(R.id.tvPoContrastMtrprocpric);
            holder.tvPoContrastTranslatememo = (TextView) convertView.findViewById(R.id.tvPoContrastTranslatememo);
            holder.tvPoContrastMemo = (TextView) convertView.findViewById(R.id.tvPoContrastMemo);
            holder.tvPoContrastRecordat = (TextView) convertView.findViewById(R.id.tvPoContrastRecordat);
            holder.tvPoContrastLcdat = (TextView) convertView.findViewById(R.id.tvPoContrastLcdat);
            holder.tvPoContrastRrdat = (TextView) convertView.findViewById(R.id.tvPoContrastRrdat);
            holder.tvPoContrastFsaler = (TextView) convertView.findViewById(R.id.tvPoContrastFsaler);
            holder.tvPoContrastPurchaser = (TextView) convertView.findViewById(R.id.tvPoContrastPurchaser);
            holder.tvPoContrastSupplier = (TextView) convertView.findViewById(R.id.tvPoContrastSupplier);
            holder.tvPoContrastWgh = (TextView) convertView.findViewById(R.id.tvPoContrastWgh);
            holder.tvPoContrastMdl = (TextView) convertView.findViewById(R.id.tvPoContrastMdl);
            holder.tvPoContrastDetailmdl = (TextView) convertView.findViewById(R.id.tvPoContrastDetailmdl);
            holder.tvPoContrastElement = (TextView) convertView.findViewById(R.id.tvPoContrastElement);
            holder.tvPoContrastWht = (TextView) convertView.findViewById(R.id.tvPoContrastWht);
            holder.tvPoContrastCatgroy = (TextView) convertView.findViewById(R.id.tvPoContrastCatgroy);
            holder.tvPoContrastPart = (TextView) convertView.findViewById(R.id.tvPoContrastPart);
            spUtils.put(context, "strposition", position);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvPoContrastMtrcol.setText(getItem(position).getMtrcol());
        holder.tvPoContrastMtrstatus.setText(getItem(position).getMtrstatus());
        holder.tvPoContrastPoqty.setText(getItem(position).getPoqty());
        holder.tvPoContrastPpric.setText(getItem(position).getPpric());
        holder.tvPoContrastProcpric.setText(getItem(position).getProcpric());
        holder.tvPoContrastPrepric.setText(getItem(position).getPrepric());
        holder.tvPoContrastMtrprocpric.setText(getItem(position).getMtrprocpric());
        holder.tvPoContrastTranslatememo.setText(getItem(position).getTranslatememo());
        holder.tvPoContrastMemo.setText(getItem(position).getMemo());
        holder.tvPoContrastRecordat.setText(getItem(position).getRecordat());
        holder.tvPoContrastLcdat.setText(getItem(position).getLcdat());
        holder.tvPoContrastRrdat.setText(getItem(position).getRrdat());
        holder.tvPoContrastFsaler.setText(getItem(position).getFsaler());
        holder.tvPoContrastPurchaser.setText(getItem(position).getPurchaser());
        holder.tvPoContrastSupplier.setText(getItem(position).getSupplier());
        holder.tvPoContrastWgh.setText(getItem(position).getWgh());
        holder.tvPoContrastMdl.setText(getItem(position).getMdl());
        holder.tvPoContrastDetailmdl.setText(getItem(position).getDetailmdl());
        holder.tvPoContrastElement.setText(getItem(position).getElement());
        holder.tvPoContrastWht.setText(getItem(position).getWht());
        holder.tvPoContrastCatgroy.setText(getItem(position).getCatgroy());
        holder.tvPoContrastPart.setText(getItem(position).getPart());
        return  convertView;
    }

    class ViewHolder {
         LinearLayout linPocontrastcontent;
         TextView tvPoContrastMtrcol, tvPoContrastMtrstatus
        , tvPoContrastPoqty, tvPoContrastPpric,tvPoContrastSealedrev,tvPoContrastProcpric, tvPoContrastPrepric
        , tvPoContrastPurprofit,tvPoContrastMtrprocpric,tvPoContrastTranslatememo,tvPoContrastMemo,tvPoContrastRecordat
        ,tvPoContrastLcdat, tvPoContrastRrdat, tvPoContrastFsaler,tvPoContrastPurchaser, tvPoContrastSupplier,
         tvPoContrastWgh, tvPoContrastMdl, tvPoContrastDetailmdl, tvPoContrastElement, tvPoContrastWht, tvPoContrastCatgroy,
         tvPoContrastPart;
    }
}
