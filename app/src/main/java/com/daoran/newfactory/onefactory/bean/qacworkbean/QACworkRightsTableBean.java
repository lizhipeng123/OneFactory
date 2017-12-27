package com.daoran.newfactory.onefactory.bean.qacworkbean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 创建时间：2017/11/23
 * 编写人：lizhipeng
 * 功能描述：
 */

public class QACworkRightsTableBean implements Parcelable {
    private int id;
    private String TableCode;//数据库表名
    private String TableTxt;//表名
    private String UserCode;//列名
    private int TableTypeID;//类别id
    private Object istransfer;
    private List<JsonTextBean> JsonText;

    protected QACworkRightsTableBean(Parcel in) {
        id = in.readInt();
        TableCode = in.readString();
        TableTxt = in.readString();
        UserCode = in.readString();
        TableTypeID = in.readInt();
    }

    public static final Creator<QACworkRightsTableBean> CREATOR = new Creator<QACworkRightsTableBean>() {
        @Override
        public QACworkRightsTableBean createFromParcel(Parcel in) {
            return new QACworkRightsTableBean(in);
        }

        @Override
        public QACworkRightsTableBean[] newArray(int size) {
            return new QACworkRightsTableBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableCode() {
        return TableCode;
    }

    public void setTableCode(String TableCode) {
        this.TableCode = TableCode;
    }

    public String getTableTxt() {
        return TableTxt;
    }

    public void setTableTxt(String TableTxt) {
        this.TableTxt = TableTxt;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String UserCode) {
        this.UserCode = UserCode;
    }

    public int getTableTypeID() {
        return TableTypeID;
    }

    public void setTableTypeID(int TableTypeID) {
        this.TableTypeID = TableTypeID;
    }

    public Object getIstransfer() {
        return istransfer;
    }

    public void setIstransfer(Object istransfer) {
        this.istransfer = istransfer;
    }

    public List<JsonTextBean> getJsonText() {
        return JsonText;
    }

    public void setJsonText(List<JsonTextBean> JsonText) {
        this.JsonText = JsonText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(TableCode);
        dest.writeString(TableTxt);
        dest.writeString(UserCode);
        dest.writeInt(TableTypeID);
    }

    public static class JsonTextBean implements Parcelable{
        /**
         * id : 0
         * pId : -1
         * name : pd_saleslist:查货跟踪表
         * isParent : true
         * checked : true
         * open : true
         * ColumnName : mid
         */

        private String id;
        private String pId;
        private String name;
        private boolean isParent;
        private boolean checked;
        private boolean open;
        private String ColumnName;

        protected JsonTextBean(Parcel in) {
            id = in.readString();
            pId = in.readString();
            name = in.readString();
            isParent = in.readByte() != 0;
            checked = in.readByte() != 0;
            open = in.readByte() != 0;
            ColumnName = in.readString();
        }

        public static final Creator<JsonTextBean> CREATOR = new Creator<JsonTextBean>() {
            @Override
            public JsonTextBean createFromParcel(Parcel in) {
                return new JsonTextBean(in);
            }

            @Override
            public JsonTextBean[] newArray(int size) {
                return new JsonTextBean[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPId() {
            return pId;
        }

        public void setPId(String pId) {
            this.pId = pId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isIsParent() {
            return isParent;
        }

        public void setIsParent(boolean isParent) {
            this.isParent = isParent;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public boolean isOpen() {
            return open;
        }

        public void setOpen(boolean open) {
            this.open = open;
        }

        public String getColumnName() {
            return ColumnName;
        }

        public void setColumnName(String ColumnName) {
            this.ColumnName = ColumnName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(pId);
            dest.writeString(name);
            dest.writeByte((byte) (isParent ? 1 : 0));
            dest.writeByte((byte) (checked ? 1 : 0));
            dest.writeByte((byte) (open ? 1 : 0));
            dest.writeString(ColumnName);
        }
    }
}
