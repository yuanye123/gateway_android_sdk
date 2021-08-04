#联网sdk使用介绍
导入依赖implementation 'com.tuya.smart:tuyasmart-gw_networking_sdk:1.0.41'

工程的gradle需做如下配置：
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://maven-other.tuya.com/repository/maven-releases/'}
    }
}

注意使用此sdk需要开启续写权限
主调接口
IoTGatewaySDKManager getInstance()
功能说明
获取SDK管理器实例。
参数说明
无
返回值
SDK管理器实例

int IotGatewayStart(Context context, Config config, GwAttachAttr[] attachAttrs)
功能说明
启动网关设备。
参数说明
参数名称

说明



context

context of app



config

是IoTGatewaySDKManager的内部类，需进行实例化，参考Config



attachAttrs

对应联网SDK的GW_ATTACH_ATTR_T结构体



返回值
返回值

说明



0

操作成功



错误码

失败返回错误码



int startFwDownload()
功能说明
当有新版本固件通知到用户时，用户通过此函数启动固件下载。固件下载完整路径需已在Config对象中填充传递。
参数说明
无
返回值
返回值

说明



0

操作成功



错误码

失败返回错误码



int getEngineerMode()
与 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的tuya_iot_get_engineer_mode接口对应。

int gwUnactive()
与 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的tuya_iot_gw_unactive接口对应。

int gwEngineerCheck(String engineerPath)
与 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的tuya_iot_gw_engineer_check接口对应。回调为IoTAppCallbacks中的onGwEngrToNormalFinish

int homeSecurityInfoSet(String mode, String nodeId, String delay)
与 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的tuya_iot_home_secruity_info_set接口对应。

AlarmInfo homeSecurityAlarmInfoGet()
与 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的tuya_iot_home_secruity_get_alarm_info接口对应。结果通过AlarmInfo类对象返回值返回。请参考下面AlarmInfo说明。

int netModeReport(int mode)
与 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的tuya_iot_net_mode_report接口对应。

int freshDeviceHeartbeat(String devId)
与 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的tuya_iot_fresh_dev_hb接口对应。

int sendDP(String devId, int dpId, int type, Object val)
说明：
     * 发送dp事件
     * @param id dp id
     * @param type 类型 DPEvent.Type
     * DPEvent.Type.PROP_BOOL   boolean
     * DPEvent.Type.PROP_VALUE  int
     * DPEvent.Type.PROP_STR    string
     * DPEvent.Type.PROP_ENUM   int
     * DPEvent.Type.PROP_RAW    byte[]
     * @param val 值
     * @return


int sendDPWithTimeStamp(String devId, int dpId, int type, Object val, int timestamp)
说明：    
     * 发送dp事件带时间戳
     *
     * @param id   dp id
     * @param type 类型 DPEvent.Type
     * @param val  值
     * @param timestamp 时间戳 单位秒
     * @return


int sendDP(String devId, DPEvent... events)
说明：
     * 发送多个dp事件
     *
     * @param events 多个dp类型
     * @return

int sendDPWithTimeStamp(String devId, DPEvent... events)
说明：
     * 发送多个dp事件带时间戳（时间戳需要赋值在DPEvent.timestamp）
     *
     * @param events 多个dp类型
     * @return

以上4个发送dp相关接口也可参看： 「GitHub - TuyaInc/tuya_smart_android_device_iot_sdk」- https://github.com/TuyaInc/tuya_smart_android_device_iot_sdk 
中的一些说明。


int setDeviceHeartbeatTimeout(String devId, long timeout)
与 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的tuya_iot_set_dev_hb_timeout接口对应。

int sysManageHeartbeatInit()
与 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的tuya_iot_sys_mag_hb_init接口对应。回调通过IoTAppCallbacks接口中的onDevHeartbeatSend完成

int gwUnbindDevice(String id)
与 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的tuya_iot_gw_unbind_dev接口对应。

int gwSubDeviceUpdate(String id, String version)
与 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的tuya_iot_gw_subdevice_update接口对应

int deviceOnlineStatusUpdate(String devId, int online)
与 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的tuya_iot_dev_online_stat_update接口对应。

int gwBindDevice(int type, int detailTypeDefine, String id, String productKey, String version)
与 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的tuya_iot_gw_bind_dev接口对应。

int deviceUpgradeProgressReport(int percent, String devId, byte type)
与 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的tuya_iot_dev_upgd_progress_rept接口对应。

int deviceUpgradeResultReport(String devId, byte type, int result)
与 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的tuya_iot_dev_upgd_result_report接口对应。

int refuseUpgrade(String devId)
与 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的tuya_iot_refuse_upgrade接口对应。

int gwVersionUpgrade(byte type, String verion) 
与 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的tuya_iot_gw_version_update接口对应。

int homeSecuritySyncAlarmStatus(byte alarmStatus)
与 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的tuya_iot_home_secruity_alarm_status接口对应。

int setGwEngrLogPath(String path)
工程模式日志存储路径设置。

int setGwAppLogPath(String path)
设置 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的gw_app_log_path_cb回调接口的path。

回调接口
回调接口分为4大类：IoTAppCallbacks、IoTCallbacks、IoTGwCallbacks、UpgradeEventCallback
IoTAppCallbacks
对应 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的TY_IOT_APP_CBS_S结构体。
用户实现此接口后，通过void setIoTAppCallbacks(IoTAppCallbacks callbacks)接口注册到管理器中。
额外接口说明：
void onGwEngrToNormalFinish(String path);
对应 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的ENGR_TO_NORMAL_FINISH_CB回调。
void onDevHeartbeatSend(String dev_id);
对应 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的DEV_HEARTBEAT_SEND_CB
回调。
int onEngrGwScePanel(String dev_id, ScePanel scePanel, int btn_num);
对应 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的gw_sce_panel_cb
回调。

IoTCallbacks
对应 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的TY_IOT_CBS_S结构体。
用户实现此接口后，通过void setIoTCallbacks(IoTCallbacks callbacks)接口注册到管理器中。
说明：
1. 升级相关接口无需关注，已挪到UpgradeEventCallback中维护。
2. 3个dp回调接口统一通过onDpEvent接口回调回来。
3. 另外，加入了几个额外接口，如下：
int onNetworkStatus(int status); // GW外网状态变动回调
status值：
/* offline in LAN.  user wired callback <tuya_hal_wired_station_conn> return <false> */
#define GB_STAT_LAN_UNCONN 0

/* online in LAN, offline in WAN.
   user wired callback <tuya_hal_wired_station_conn> return <true> but mqtt is offline
*/
#define GB_STAT_LAN_CONN 1

/* online in WAN.
   user wired callback <tuya_hal_wired_station_conn> return <true> and mqtt is online
*/
#define GB_STAT_CLOUD_CONN 2



/* 工程模式使用 */
int onEngrGwSetChannel(int channel); // 对应gw_set_channel_cb
int onEngrGwGetChannel(int channel); // 对应gw_get_channel_cb
int onEngrGwGetLog(int lengthLimit); // 对应gw_get_log_cb
int onEngrGwSyncConfig(); // 对应gw_sync_config_cb
int onEngrGwEngineerFinish(); // 对应gw_engineer_finish_cb


IoTGwCallbacks
对应 「网关联网 SDK-文档中心-涂鸦开发者」- https://developer.tuya.com/cn/docs/iot/smart-product-solution/product-solutiongateway/gateway-link-sdk-access-solution/tuya-gateway-link-sdk-development-manual?id=K9ducoah42rl2 文档中的TY_IOT_GW_CBS_S结构体。
用户实现此接口后，通过void setIoTGwCallbacks(IoTGwCallbacks callbacks)接口注册到管理器中。
说明：
无

UpgradeEventCallback
升级事件回调单独设立了一个回调接口。需要升级的时候注册回调就行。
接口说明：
/**
 * sdk 接收到后端的升级推送的时候，会触发此接口 附带升级信息
 * @param version
 */
void onUpgradeInfo(String version);

/**
 * 升级文件开始下载
 */
void onUpgradeDownloadStart();

/**
 * 升级文件下载进度
 */
void onUpgradeDownloadUpdate(int progress);

/**
 * sdk 下载升级文件下载完成触发此接口
 */
void upgradeFileDownloadFinished(boolean success);

/**
 * 升级失败
 * @param msg 错误信息
 */
void onUpgradeFail(String msg);
辅助类
Config类
重点关注项及说明：
public final static class Config {
    //存储路径，注意长度，底层为64个char字符长度的数组，还追加了一个文件名，也就是说路径要比64位还要短。
    public String mPath;
    //固件key
    public String mFirmwareKey;

    //uuid和authkey成对出现，而且设备唯一
    public String mUUID;
    public String mAuthKey;

    //固件版本号 & 包名
    public String mVersion;
    public String mPackageName;

    //网关是否具有设备的属性
    public boolean mGwAsDev;

    // 是否工程模式
    public boolean mEngrMode;
...

AlarmInfo类
public class AlarmInfo {
    public String alarm_mode; // 32byte max
    public byte alarm_status;
    public byte enable_countdown_status;
...

GwAttachAttr类
public class GwAttachAttr {

    /* tp value list: */
    public static final byte DEV_BLE_SNGL = 1;           //BLE固件
    public static final byte DEV_ZB_SNGL = 3;            //ZigBee固件
    public static final byte DEV_NM_NOT_ATH_SNGL = 9;    //MCU固件

    public byte tp;
    public String ver;
...

ChCode类
public class ChCode {

    public static final int CH_CODE_LMT = 15;
    public static final int CH_SN_LMT = 20;
    public static final int CH_REPORT_CODE_LMT = 20;
    public static final int CH_MANU_ID_LMT = 10;
    public static final int CH_VERSION_LMT = 10;
    public static final int CH_ENCRYPT_KEY_LMT = 20;

    public String ch_name;
    public String ch_code;
    public String ch_sn;
    public String ch_report_code;
    public String ch_manu_id;
    public String ch_version;
    public String ch_encrype_key;
...

DP事件类：
/**
 * DP点类
 */
public class DPEvent {

    public class Type {
        //Boolean
        public static final int PROP_BOOL = 0;
        //Integer
        public static final int PROP_VALUE = 1;
        //String
        public static final int PROP_STR = 2;
        //Integer
        public static final int PROP_ENUM = 3;
        //Integer
        public static final int PROP_BITMAP = 4;
        //RAW
        public static final int PROP_RAW = 5;
    }

    public int dpid;        // dp id
    public short type;        // dp type
    public Object value;     // dp value
    /**
     * 发生的时间戳(单位秒)
     */
    public int timestamp;  // dp happen time. if 0, mean now


混淆注意：
-keep class com.tuya.smart.gateway.gw_networking_sdk.** {*;}
