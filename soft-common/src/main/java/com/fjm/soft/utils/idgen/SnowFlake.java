package com.fjm.soft.utils.idgen;

import static java.lang.System.currentTimeMillis;

/**
 * @Author: fongjinming
 * @CreateTime: 2019-08-14 14:44
 * @Description:
 */
public class SnowFlake {

    /**
     * 起始的时间戳
     */
    private final static long START_STMP = 1480166465631L;

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 12; //序列号占用的位数
    private final static long MACHINE_BIT = 5;   //机器标识占用的位数
    private final static long DATACENTER_BIT = 5;//数据中心占用的位数

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     * <p>
     * MACHINE_LEFT:机器ID向左移12位
     * DATACENTER_LEFT:数据标识id向左移17位(12+5)
     * TIMESTMP_LEFT:时间截向左移22位(5+5+12)
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    /**
     * 数据中心
     */
    private long datacenterId;
    /**
     * 机器标识
     */
    private long machineId;
    /**
     * 序列号
     */
    private long sequence = 0L;
    /**
     * 上一次时间戳
     */
    private long lastStmp = -1L;

    /**
     * 唯一实例.
     */
    private static SnowFlake instance;

    /**
     * 获取SnowFlake实例（单例模式）.
     *
     * @return SnowFlake
     */
    public static SnowFlake getInstance() {
        if (instance == null) {
            throw new RuntimeException("SnowFlake并未在应用程序初始化");
        }

        return instance;
    }

    /**
     * 直接获取分布式id.
     *
     * @return id
     */
    public static long createId() {
        return getInstance().nextId();
    }

    /**
     * 构造器.
     *
     * @param datacenterId 数据中心
     * @param machineId    机器标识
     */
    public SnowFlake(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStmp = timestampOffsetGen();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStmp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = tilNextTimestamp(lastStmp);
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
                | datacenterId << DATACENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    /**
     * 获取下一个时间戳（sequence溢出的情况下需要处理）.
     *
     * @param lastTimestamp 上一个时间戳
     * @return 下一个时间戳
     */
    private long tilNextTimestamp(final long lastTimestamp) {
        long timestamp = timestampOffsetGen();

        while (timestamp <= lastTimestamp) {
            timestamp = timestampOffsetGen();
        }

        return timestamp;
    }

    /**
     * 获取当前时间戳（直接减掉基准时间戳）.
     *
     * @return 时间戳
     */
    private long timestampOffsetGen() {
        return currentTimeMillis() - START_STMP;
    }

}
