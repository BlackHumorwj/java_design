package com.example.aac_mvvm.room;

import com.example.aac_mvvm.room.db.DBManager;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * @time 2019/12/2 20:28
 * @desc
 */
//表名
@Entity(tableName = DBManager.Table.orders)
public class Order {

    public static long id = 10001;

    @PrimaryKey
    @ColumnInfo(name = "order_id")
    public long orderId;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "owner_name")
    public String ownerName;
    @ColumnInfo(name = "owner_phone")
    public String ownerPhone;

    @Ignore
    public String ignoreText;


    @Embedded
    public OwnerAddress ownerAddress;

    public static Order newInstance() {
        final Order order = new Order();
        order.orderId = id;
        id++;
        order.address = "杭州西湖";
        order.ownerName = "李四";
        return order;
    }
}
