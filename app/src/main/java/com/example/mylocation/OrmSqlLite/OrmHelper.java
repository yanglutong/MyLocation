package com.example.mylocation.OrmSqlLite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.mylocation.OrmSqlLite.Bean.Conmmunit01Bean;
import com.example.mylocation.OrmSqlLite.Bean.GuijiViewBean;
import com.example.mylocation.OrmSqlLite.Bean.GuijiViewBeanjizhan;
import com.example.mylocation.OrmSqlLite.Bean.PinConfigBean;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Created by Administrator on 2018/3/22 0022.
 */

public class OrmHelper extends OrmLiteSqliteOpenHelper {
    public static final String DB_NAME = "1607.db";
    private static final int DB_VERSION = 1;

    public OrmHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        //建表,和Gson类似，第二个参数即是业务实体类
        try {
            try {
                TableUtils.createTable(connectionSource, PinConfigBean.class);
                TableUtils.createTable(connectionSource, Conmmunit01Bean.class);
                TableUtils.createTable(connectionSource, GuijiViewBeanjizhan.class);
                TableUtils.createTable(connectionSource, GuijiViewBean.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
