package com.catglo.tiblesimpleconnect;

import java.util.HashMap;

/**
 * Created by Marc Holder Kluver goblets@gmail.com on 2/4/14.
 */
public class GattAttributes {
    private static HashMap<String, String> attributes = new HashMap();
    public static String HEART_RATE_MEASUREMENT = "00002a37-0000-1000-8000-00805f9b34fb";
    public static String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";

    /******************
     * TI Sensor Tag  *
     ******************/
    public static String TI_SENSOR_TAG_Magnetometer_Service_UDID = "f000aa30-0451-4000-b000-000000000000";
    public static String TI_SENSOR_TAG_Magnetometer_Data_UDID    = "f000aa31-0451-4000-b000-000000000000";
    public static String TI_SENSOR_TAG_Magnetometer_Config_UDID  = "f000aa32-0451-4000-b000-000000000000";
    public static String TI_SENSOR_TAG_Magnetometer_Period_UDID  = "f000aa33-0451-4000-b000-000000000000";

    public static String TI_SENSOR_TAG_Humidity_Service_UDID       = "f000aa20-0451-4000-b000-000000000000";
    public static String TI_SENSOR_TAG_Humidity_Data_UDID          = "f000aa21-0451-4000-b000-000000000000";
    public static String TI_SENSOR_TAG_Humidity_Configuration_UDID = "f000aa22-0451-4000-b000-000000000000";

    public static String TI_SENSOR_TAG_BarometricPressure_Service_UDID      = "f000aa40-0451-4000-b000-000000000000";
    public static String TI_SENSOR_TAG_BarometricPressure_Data_UDID         = "f000aa41-0451-4000-b000-000000000000";
    public static String TI_SENSOR_TAG_BarometricPressure_Configuration_UDID= "f000aa42-0451-4000-b000-000000000000";
    public static String TI_SENSOR_TAG_BarometricPressure_Calibration_UDID  = "f000aa43-0451-4000-b000-000000000000";

    public static String TI_SENSOR_TAG_Gyroscope_Service_UDID = "f000aa50-0451-4000-b000-000000000000";

    /*

      UUID_IRT_SERV = fromString("f000aa00-0451-4000-b000-000000000000"),
      UUID_IRT_DATA = fromString("f000aa01-0451-4000-b000-000000000000"),
      UUID_IRT_CONF = fromString("f000aa02-0451-4000-b000-000000000000"), // 0: disable, 1: enable
      UUID_IRT_PERI = fromString("f000aa03-0451-4000-b000-000000000000"), // Period in tens of milliseconds

      UUID_ACC_SERV = fromString("f000aa10-0451-4000-b000-000000000000"),
      UUID_ACC_DATA = fromString("f000aa11-0451-4000-b000-000000000000"),
      UUID_ACC_CONF = fromString("f000aa12-0451-4000-b000-000000000000"), // 0: disable, 1: enable
      UUID_ACC_PERI = fromString("f000aa13-0451-4000-b000-000000000000"), // Period in tens of milliseconds

      UUID_HUM_SERV = fromString("f000aa20-0451-4000-b000-000000000000"),
      UUID_HUM_DATA = fromString("f000aa21-0451-4000-b000-000000000000"),
      UUID_HUM_CONF = fromString("f000aa22-0451-4000-b000-000000000000"), // 0: disable, 1: enable
      UUID_HUM_PERI = fromString("f000aa23-0451-4000-b000-000000000000"), // Period in tens of milliseconds

      UUID_MAG_SERV = fromString("f000aa30-0451-4000-b000-000000000000"),
      UUID_MAG_DATA = fromString("f000aa31-0451-4000-b000-000000000000"),
      UUID_MAG_CONF = fromString("f000aa32-0451-4000-b000-000000000000"), // 0: disable, 1: enable
      UUID_MAG_PERI = fromString("f000aa33-0451-4000-b000-000000000000"), // Period in tens of milliseconds

      UUID_BAR_SERV = fromString("f000aa40-0451-4000-b000-000000000000"),
      UUID_BAR_DATA = fromString("f000aa41-0451-4000-b000-000000000000"),
      UUID_BAR_CONF = fromString("f000aa42-0451-4000-b000-000000000000"), // 0: disable, 1: enable
      UUID_BAR_CALI = fromString("f000aa43-0451-4000-b000-000000000000"), // Calibration characteristic
      UUID_BAR_PERI = fromString("f000aa44-0451-4000-b000-000000000000"), // Period in tens of milliseconds

      UUID_GYR_SERV = fromString("f000aa50-0451-4000-b000-000000000000"),
      UUID_GYR_DATA = fromString("f000aa51-0451-4000-b000-000000000000"),
      UUID_GYR_CONF = fromString("f000aa52-0451-4000-b000-000000000000"), // 0: disable, bit 0: enable x, bit 1: enable y, bit 2: enable z
      UUID_GYR_PERI = fromString("f000aa53-0451-4000-b000-000000000000"), // Period in tens of milliseconds

      UUID_KEY_SERV = fromString("0000ffe0-0000-1000-8000-00805f9b34fb"),
      UUID_KEY_DATA = fromString("0000ffe1-0000-1000-8000-00805f9b34fb");


    I/MARC    ( 1302): Found bluetooth service 00001800-0000-1000-8000-00805f9b34fb with 5 characteristics
I/MARC    ( 1302):   Has charicteristic 00002a00-0000-1000-8000-00805f9b34fb
I/MARC    ( 1302):   Has charicteristic 00002a01-0000-1000-8000-00805f9b34fb
I/MARC    ( 1302):   Has charicteristic 00002a02-0000-1000-8000-00805f9b34fb
I/MARC    ( 1302):   Has charicteristic 00002a03-0000-1000-8000-00805f9b34fb
I/MARC    ( 1302):   Has charicteristic 00002a04-0000-1000-8000-00805f9b34fb
I/MARC    ( 1302): Found bluetooth service 00001801-0000-1000-8000-00805f9b34fb with 1 characteristics
I/MARC    ( 1302):   Has charicteristic 00002a05-0000-1000-8000-00805f9b34fb
I/MARC    ( 1302): Found bluetooth service 0000180a-0000-1000-8000-00805f9b34fb with 9 characteristics
I/MARC    ( 1302):   Has charicteristic 00002a23-0000-1000-8000-00805f9b34fb
I/MARC    ( 1302):   Has charicteristic 00002a24-0000-1000-8000-00805f9b34fb
I/MARC    ( 1302):   Has charicteristic 00002a25-0000-1000-8000-00805f9b34fb
I/MARC    ( 1302):   Has charicteristic 00002a26-0000-1000-8000-00805f9b34fb
I/MARC    ( 1302):   Has charicteristic 00002a27-0000-1000-8000-00805f9b34fb
I/MARC    ( 1302):   Has charicteristic 00002a28-0000-1000-8000-00805f9b34fb
I/MARC    ( 1302):   Has charicteristic 00002a29-0000-1000-8000-00805f9b34fb
I/MARC    ( 1302):   Has charicteristic 00002a2a-0000-1000-8000-00805f9b34fb
I/MARC    ( 1302):   Has charicteristic 00002a50-0000-1000-8000-00805f9b34fb
I/MARC    ( 1302): Found bluetooth service f000aa00-0451-4000-b000-000000000000 with 2 characteristics
I/MARC    ( 1302):   Has charicteristic f000aa01-0451-4000-b000-000000000000
I/MARC    ( 1302):   Has charicteristic f000aa02-0451-4000-b000-000000000000
I/MARC    ( 1302): Found bluetooth service f000aa10-0451-4000-b000-000000000000 with 3 characteristics
I/MARC    ( 1302):   Has charicteristic f000aa11-0451-4000-b000-000000000000
I/MARC    ( 1302):   Has charicteristic f000aa12-0451-4000-b000-000000000000
I/MARC    ( 1302):   Has charicteristic f000aa13-0451-4000-b000-000000000000
I/MARC    ( 1302): Found bluetooth service f000aa20-0451-4000-b000-000000000000 with 2 characteristics
I/MARC    ( 1302):   Has charicteristic f000aa21-0451-4000-b000-000000000000
I/MARC    ( 1302):   Has charicteristic f000aa22-0451-4000-b000-000000000000
I/MARC    ( 1302): Found bluetooth service f000aa30-0451-4000-b000-000000000000 with 3 characteristics
I/MARC    ( 1302):   Has charicteristic f000aa31-0451-4000-b000-000000000000
I/MARC    ( 1302):   Has charicteristic f000aa32-0451-4000-b000-000000000000
I/MARC    ( 1302):   Has charicteristic f000aa33-0451-4000-b000-000000000000
I/MARC    ( 1302): Found bluetooth service f000aa40-0451-4000-b000-000000000000 with 3 characteristics
I/MARC    ( 1302):   Has charicteristic f000aa41-0451-4000-b000-000000000000
I/MARC    ( 1302):   Has charicteristic f000aa42-0451-4000-b000-000000000000
I/MARC    ( 1302):   Has charicteristic f000aa43-0451-4000-b000-000000000000
I/MARC    ( 1302): Found bluetooth service f000aa50-0451-4000-b000-000000000000 with 2 characteristics
I/MARC    ( 1302):   Has charicteristic f000aa51-0451-4000-b000-000000000000
I/MARC    ( 1302):   Has charicteristic f000aa52-0451-4000-b000-000000000000
I/MARC    ( 1302): Found bluetooth service 0000ffe0-0000-1000-8000-00805f9b34fb with 1 characteristics
I/MARC    ( 1302):   Has charicteristic 0000ffe1-0000-1000-8000-00805f9b34fb
I/MARC    ( 1302): Found bluetooth service f000aa60-0451-4000-b000-000000000000 with 2 characteristics
I/MARC    ( 1302):   Has charicteristic f000aa61-0451-4000-b000-000000000000
I/MARC    ( 1302):   Has charicteristic f000aa62-0451-4000-b000-000000000000
I/MARC    ( 1302): Found bluetooth service f000ccc0-0451-4000-b000-000000000000 with 3 characteristics
I/MARC    ( 1302):   Has charicteristic f000ccc1-0451-4000-b000-000000000000
I/MARC    ( 1302):   Has charicteristic f000ccc2-0451-4000-b000-000000000000
I/MARC    ( 1302):   Has charicteristic f000ccc3-0451-4000-b000-000000000000
I/MARC    ( 1302): Found bluetooth service f000ffc0-0451-4000-b000-000000000000 with 2 characteristics
I/MARC    ( 1302):   Has charicteristic f000ffc1-0451-4000-b000-000000000000
I/MARC    ( 1302):   Has charicteristic f000ffc2-0451-4000-b000-000000000000
     */

    static {
        //Services.
        attributes.put("0000180d-0000-1000-8000-00805f9b34fb", "Heart Rate Service");
        attributes.put("0000180a-0000-1000-8000-00805f9b34fb", "Device Information Service");

        //Characteristics.
        attributes.put(HEART_RATE_MEASUREMENT, "Heart Rate Measurement");
        attributes.put(CLIENT_CHARACTERISTIC_CONFIG, "Manufacturer Name String");




    }

    public static String lookup(String uuid, String defaultName) {
        String name = attributes.get(uuid);
        return name == null ? defaultName : name;
    }
}
