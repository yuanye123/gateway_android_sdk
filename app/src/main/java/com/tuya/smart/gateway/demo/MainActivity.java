package com.tuya.smart.gateway.demo;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
//import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.nio.charset.Charset;
import java.util.List;

import com.tuya.smart.gateway.gw_networking_sdk.ChCode;
import com.tuya.smart.gateway.gw_networking_sdk.DPEvent;
import com.tuya.smart.gateway.gw_networking_sdk.DPQuery;
import com.tuya.smart.gateway.gw_networking_sdk.DevDescIf;
import com.tuya.smart.gateway.gw_networking_sdk.GwAttachAttr;
import com.tuya.smart.gateway.gw_networking_sdk.IoTAppCallbacks;
import com.tuya.smart.gateway.gw_networking_sdk.IoTCallbacks;
import com.tuya.smart.gateway.gw_networking_sdk.IoTGatewaySDKManager;
import com.tuya.smart.gateway.gw_networking_sdk.Log;
import com.tuya.smart.gateway.gw_networking_sdk.IoTGwCallbacks;
import com.tuya.smart.gateway.gw_networking_sdk.IoTJNIApi;
import com.tuya.smart.gateway.gw_networking_sdk.ScePanel;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pub.devrel.easypermissions.EasyPermissions;

import static android.util.Base64.DEFAULT;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private static final String TAG = "MainActivity";

    private final int PERMISSION_CODE = 123;

    Disposable httpdisposable;

    private String[] requiredPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    IoTGatewaySDKManager ioTGatewaySDKManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.get_engr_mode).setOnClickListener(this::onClick);
        findViewById(R.id.reset).setOnClickListener(this::onClick);
        findViewById(R.id.dp_send).setOnClickListener(this::onClick);
        findViewById(R.id.sync_net_mode).setOnClickListener(this::onClick);
        findViewById(R.id.set_secruity_mode).setOnClickListener(this::onClick);
        findViewById(R.id.get_secruity_mode).setOnClickListener(this::onClick);
        findViewById(R.id.sync_alarm_status).setOnClickListener(this::onClick);
        findViewById(R.id.sub_device_heartbeat_man).setOnClickListener(this::onClick);
        findViewById(R.id.update_subdevice_onoffline_status).setOnClickListener(this::onClick);

        findViewById(R.id.traversal_device).setOnClickListener(this::onClick);

        findViewById(R.id.native_start).setOnClickListener(this::onClick);
        findViewById(R.id.native_stop).setOnClickListener(this::onClick);

        if (!EasyPermissions.hasPermissions(this, requiredPermissions)) {
            EasyPermissions.requestPermissions(this, "?????????????????????????????????", PERMISSION_CODE, requiredPermissions);
        } else {
            Toast.makeText(this,"???????????? ??? " + Manifest.permission.WRITE_EXTERNAL_STORAGE, Toast.LENGTH_SHORT).show();
            initSDK();
        }
    }

    private void initSDK() {

        Log.init(this, "/sdcard/tuya_log/iot/", 3);

        ioTGatewaySDKManager = IoTGatewaySDKManager.getInstance();

//        //?????????????????? IoTGatewaySDKManager.getInstance()??????
//        ioTGatewaySDKManager.setIpCallBack(new IoTGatewaySDKManager.IPCallBack() {
//            @Override
//            public String setIp() {
//                //?????????????????????ip,??????????????????
//                return "127.0.0.1";
//            }
//        });

        IoTGatewaySDKManager.Config config = new IoTGatewaySDKManager.Config();

        config.mPath = "/sdcard/";
        config.mFirmwareKey = "djduEADRRj1kdBc9"; // ??????pid???demo??????
        //config.mFirmwareKey = "anlukk0sz8gsd0lu"; // ?????????????????????pid
        //config.mFirmwareKey = "5ffenf9ikkvqat5h"; // ?????????????????????pid


        //config.mFirmwareKey = "ZkOA5uJGrtu4R2f8"; // ??????pid
        //config.mFirmwareKey = "anlukk0sz8gsd0lu"; // ????????????PID

        config.mUUID = "tuya19342bc6e2d80143";
        config.mAuthKey = "IbOFAiLzNAxgT84zd1mrjpy0sUSbJBt3";
        config.mVersion = "3.2.3";
        // config.mVersion = "1.0.0";
        config.mGwAsDev = true; // false;

        config.mEngrMode = false;//true;

        GwAttachAttr attachAttr[] = new GwAttachAttr[1];

  //      byte type = 10;
  //      attachAttr[1] = new GwAttachAttr(type, "3.0.0");

        attachAttr[0] = new GwAttachAttr(GwAttachAttr.DEV_ZB_SNGL, "3.0.0");

        registerCallbacks();

        ioTGatewaySDKManager.IotGatewayStart(this, config, attachAttr);
    }

    public int registerCallbacks() {
        /* ?????????????????? */
        IoTGwCallbacks ioTGwCallbacks = new IoTGwCallbacks() {
            /* ????????????????????? */
            @Override
            public int onGwAddDev(int tp, int permit, int timeout) {
                Log.d(TAG, "add sub device get called. " + "tp: " + tp + " perimit: " + permit + " timeout: " + timeout);

                if (permit == 0)
                    return 0;

                /* ??????????????? */
                /* ???????????? */
                //ioTGatewaySDKManager.gwBindDevice(10, 0x02000200, "changcheng01", "dIsttrZk", "1.0.0");

                ioTGatewaySDKManager.gwBindDevice(ioTGatewaySDKManager.GP_DEV_ZB, 0x02000200, "changcheng01", "dIsttrZk", "1.0.0");

               // ioTGatewaySDKManager.gwBindDevice(ioTGatewaySDKManager.GP_DEV_ZB, 0x02000200, "changcheng02", "2lyujxor", "2.0.0");

                /* ???????????? */
                ioTGatewaySDKManager.gwBindDevice(ioTGatewaySDKManager.GP_DEV_ZB, 0x02000200, "changcheng03", "bofdlh85", "1.0.0");

                /* ???????????? */
                ioTGatewaySDKManager.gwBindDevice(ioTGatewaySDKManager.GP_DEV_ZB, 0x02000200, "changcheng04", "fkevhlxe", "1.0.0");

                ioTGatewaySDKManager.gwBindDevice(ioTGatewaySDKManager.GP_DEV_ZB, 0x02000200, "changcheng05", "ncdapbwy", "1.0.0");


                return 0;
            }

            @Override
            public void onGwDelDev(String dev_id, int type) {
                Log.d(TAG, "delete sub device get called. dev_id " + dev_id + " type: " + type);

                /* ??????????????? */
                if (dev_id.equals("changcheng01"))
                    ioTGatewaySDKManager.gwUnbindDevice("changcheng01");

                /* ??????????????? */
                if (dev_id.equals("changcheng02"))
                    ioTGatewaySDKManager.gwUnbindDevice("changcheng02");
            }
            /* ?????? */
            @Override
            public int onGwDevGrp(int action, String dev_id, String grp_id) {
                Log.d(TAG, "delete sub device get called. dev_id " + dev_id + "dev_id: " + dev_id + " grp_id: " + grp_id);

                return 0;
            }
            /* ?????? */
            @Override
            public int onGwDevScene(int action, String dev_id, String grp_id, String sce_id) {
                Log.d(TAG, "sub device scene get called. action " + action + "dev_id: " + dev_id + " grp_id: " + grp_id + " sce_id: " + sce_id);

                return 0;
            }

            @Override
            public void onGwIfm(String dev_id, int op_ret) {

                Log.d(TAG, "bind sub device result inform. dev_id " + dev_id + " op_ret: " + op_ret);

            }

            /* ?????? */
            @Override
            public void onGwDevSigmeshTopoUpdate(String dev_id) {

            }
            /* ?????? */
            @Override
            public void onGwDevSigmeshDevCacheDel(String dev_id) {

            }
            /* ?????? */
            @Override
            public void onGwDevWakeup(String dev_id, int duration) {

            }
            /* ?????? */
            @Override
            public void onGwDevSigmeshConn(String sigmesh_dev_conn_inf_json) {

            }
        };

        ioTGatewaySDKManager.setIoTGwCallbacks(ioTGwCallbacks);

        IoTCallbacks ioTCallbacks = new IoTCallbacks() {
            /* GW?????????????????????????????? */
            @Override
            public void onGwDevStatusChanged(byte status) {

                Log.d(TAG, "GW TUYA-Cloud Status: " + status);

            }

            /* ?????? */
            @Override
            public int onGwDevRevUpgradeInfo(Object fw) {
                return 0;
            }

            /* GW?????????????????????????????? */
            @Override
            public void onGwDevRestartReq(int type) {

                Log.d(TAG, "please restart system " + "type: " + type);

            }

            @Override
            public void onDpQuery(DPEvent event) {

            }

            @Override
            public void onDpQuery(DPQuery[] queries) {

            }

            @Override
            public void onDpEvent(int cmd_tp, int dtt_tp, String cid, String mb_id, DPEvent event) {

                Log.d(TAG, "on dp event get called: " + event.toString() + " devid: " + cid);

                ioTGatewaySDKManager.sendDP(cid, event.dpid, event.type, event.value);
            }

            /* ?????? */
            @Override
            public void onGwDevObjDpCmd(Object dp) {

            }
            /* ?????? */
            @Override
            public void onGwDevRawDpCmd(Object dp) {

            }
            /* ?????? */
            @Override
            public void onGwDevDpQuery(Object dp_qry) {

            }
            /* ?????? */
            @Override
            public int onDevUgInform(String dev_id, Object fw) {
                return 0;
            }

            /* ????????????????????? */
            @Override
            public void onDevReset(String dev_id, int type) {
                Log.d(TAG, "on device reset get called: " + "dev_id: " + dev_id + " type: " + type);

            }

            /* ????????????????????? */
            @Override
            public int onOpeHttpcGetChcode(int is_gw, String dev_id, ChCode ch_code) {
                return 0;
            }

            /* ???dp????????????flash */
            @Override
            public int onGwOfflineDpSave(String dev_id, DPEvent dpEvent) {

                Log.d(TAG, "saving offline dp data: " + "dev_id: " + dev_id + " dpEvent: " + dpEvent);

                return 0;
            }

            @Override
            public int onNetworkStatus(int status) {
                Log.d(TAG, "on network status get called: " + "status: " + status);
                return 0;
            }

            /* ????????????????????????????????????????????????????????????????????? NULL??? */
            @Override
            public int onEngrGwSetChannel(int channel) {
                Log.d(TAG, "on engr gw set channel. " + "channel: " + channel);
                return 0;
            }

            /* ??????????????????????????????????????????????????????????????????????????? NULL??? */
            @Override
            public int onEngrGwGetChannel(int channel) {
                Log.d(TAG, "on engr gw get channel. " + "channel: " + channel);
                return 0;
            }

            /* ????????????????????????????????????????????????path???????????? */
            @Override
            public int onEngrGwGetLog(int lengthLimit) {
                Log.d(TAG, "on engr gw get log called: " + "len: " + lengthLimit);
                String logPath = "/sdcard/tuya.log";
                ioTGatewaySDKManager.setGwEngrLogPath(logPath);
                return 0;
            }

            /* ?????????????????????????????????????????????SDK ?????????????????????tuya_user.db????????? tuya_enckey.db */
            @Override
            public int onEngrGwSyncConfig() {
                Log.d(TAG, "on engr gw sync config. ");
                return 0;
            }

            /* ??????????????????????????????????????????????????????????????????????????? */
            @Override
            public int onEngrGwEngineerFinish() {

                Log.d(TAG, "on engr gw engineer finish. " + " please resart your app" );

                return 0;
            }
        };

        ioTGatewaySDKManager.setIoTCallbacks(ioTCallbacks);

        IoTAppCallbacks ioTAppCallbacks = new IoTAppCallbacks() {
            @Override
            public void onAppLogPath(int lengthLimit) {
                Log.d(TAG, "on engr gw get log called: " + "len: " + lengthLimit);

                String logPath = "/sdcard/tuya.log";
                ioTGatewaySDKManager.setGwAppLogPath(logPath);
            }

            // ????????????????????????????????????????????????
            // ???????????????
            @Override
            public void onHomeSecurityIf(String modeStr, int time, int isSound) {
                Log.d(TAG, "onHomeSecurityIf: " + "modeStr: " + modeStr + " time???" + time + " isSound: " + isSound);
            }

            // ???????????????????????? DP ??????????????????????????????????????????
            @Override
            public void onHomeSecurityAlarmDev(String cid, String jsonDpInf) {
                Log.d(TAG, "onHomeSecurityAlarmDev: " + "cid: " + cid + " jsonDpInf???" + jsonDpInf);
            }

            // ?????????????????? dp ???????????????????????????????????????
            @Override
            public void onHomeSecurityAlarmEnvDev(String cid, String jsonDpInf) {
                Log.d(TAG, "onHomeSecurityAlarmEnvDev: " + "cid: " + cid + " jsonDpInf???" + jsonDpInf);
            }

            // ?????????????????? SDK ????????????????????????????????????????????????????????????????????????????????????????????????
            // ???????????????
            @Override
            public void onHomeSecurityAlarmDelayStatus(int alarmStatus) {
                Log.d(TAG, "onHomeSecurityAlarmDelayStatus: " + "alarmStatus: " + alarmStatus);
            }

            // ????????????????????????
            // ???????????????
            @Override
            public void onHomeSecurityEvent(int securityEventStatus, int data) {
                Log.d(TAG, "onHomeSecurityEvent: " + "securityEventStatus: " + securityEventStatus + " data???" + data);
            }

            // ??????????????????????????????????????????????????????????????????dp 32?????? FALSE???
            @Override
            public void onHomeSecurityCancelAlarm() {
                Log.d(TAG, "onHomeSecurityCancelAlarm");
            }

            // sdk???????????????????????????
            @Override
            public void onHomeSecurityAlarmDevNew(String cid, String jsonDpInf, int dev_type) {

            }

            // sdk???????????????????????????
            @Override
            public void onHomeSecurityEnterAlarm(int alarmStatus, String alarmInfo) {

            }

            // sdk???????????????????????????
            @Override
            public void onGwEngrToNormalFinish(String path) {

            }

            /* ????????????????????????????????????????????????????????????????????????????????????????????????????????? */
            @Override
            public void onDevHeartbeatSend(String devId) {

                Log.d(TAG, "sub device " + devId + " heartbeat");

                ioTGatewaySDKManager.freshDeviceHeartbeat(devId);

            }

            // ????????????????????????
            @Override
            public int onEngrGwScePanel(String devId, ScePanel scePanel, int btnNum) {

                Log.d(TAG, "sub device " + devId + " scePanel" + scePanel.toString() + " btnNum: " + btnNum);

                // TODO:

                return 0;
            }
        };

        ioTGatewaySDKManager.setIoTAppCallbacks(ioTAppCallbacks);

        return 0;
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        initSDK();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onClick(View v) {
        if (ioTGatewaySDKManager == null) {
            return;
        }

        int timestamp = (int) (System.currentTimeMillis() / 1000L);
        switch (v.getId()) {
            case R.id.get_engr_mode:
                Log.d(TAG, "get engr mode: " + ioTGatewaySDKManager.getEngineerMode());
                break;
            case R.id.reset:
                Log.d(TAG, "reset device: " + ioTGatewaySDKManager.gwUnactive());
                break;
            case R.id.dp_send:
                DPEvent event0 = new DPEvent(1, (byte) DPEvent.Type.PROP_BOOL, true, timestamp);
                DPEvent event1 = new DPEvent(102, (byte) DPEvent.Type.PROP_VALUE, 12, timestamp);
                DPEvent event2 = new DPEvent(105, (byte) DPEvent.Type.PROP_RAW, "test".getBytes(Charset.forName("UTF-8")), timestamp);
                DPEvent[] events = {event0, event1, event2};
                ioTGatewaySDKManager.sendDPWithTimeStamp("changcheng01", events);
                //ioTGatewaySDKManager.sendDPWithTimeStamp("842E14FFFE22E281-0002", events);
                //String devid = "842E14FFFE22E281-0002";
                //ioTGatewaySDKManager.sendDP(devid, 4, 0, true);
                //ioTGatewaySDKManager.sendDP(devid, events);
                //ioTGatewaySDKManager.sendDP(devid, events);
                //ioTGatewaySDKManager.sendDP(devid, 5, 0, true);
                break;
            case R.id.sync_net_mode:
                Log.d(TAG, "syncing net mode.... ");
                Log.d(TAG, "net mode return: " + ioTGatewaySDKManager.netModeReport(ioTGatewaySDKManager.HOME_SECURITY_NET_MODE_WIFI));
                break;
            case R.id.set_secruity_mode:
                Log.d(TAG, "setting home security info...");
                String node_id = "changcheng01";
                String delay = "{\"1\": 10}";
                Log.d(TAG, "security info return: " + ioTGatewaySDKManager.homeSecurityInfoSet(ioTGatewaySDKManager.AT_HOME_ARM, node_id, null));
                break;
            case R.id.get_secruity_mode:
                Log.d(TAG, "getting home security info...");
                Log.d(TAG, "get alarm info return: " + ioTGatewaySDKManager.homeSecurityAlarmInfoGet().toString());
                break;
            case R.id.sync_alarm_status:
                Log.d(TAG, "syncing alarm status...");
                Log.d(TAG, "sync alarm status return: " + ioTGatewaySDKManager.homeSecuritySyncAlarmStatus(ioTGatewaySDKManager.HOME_SECURITY_ALARM_STATUS_ALARMING));
                break;

            case R.id.sub_device_heartbeat_man:
                Log.d(TAG, "start subdevice heartbeat manage...");
                int ret = 0;
                // ?????????????????????????????????
                ret = ioTGatewaySDKManager.sysManageHeartbeatInit();
                Log.d(TAG, "1. start subdevice heartbeat manage... " + ret);
                if (ret != 0) break;
                // ????????????????????????????????????????????????
                ret = ioTGatewaySDKManager.setDeviceHeartbeatTimeout("changcheng01", 100);
                Log.d(TAG, "2. start subdevice heartbeat manage... " + ret);
                break;
            case R.id.update_subdevice_onoffline_status:
                Log.d(TAG, "update subdevice onoffline status...");
                ioTGatewaySDKManager.deviceOnlineStatusUpdate("changcheng01", 0);
                break;
            case R.id.traversal_device:
            {
                DevDescIf devDescIf = null;

                while (true) {
                    devDescIf = ioTGatewaySDKManager.IotGateWayDevTraversal();
                    if (devDescIf == null) {
                        Log.d(TAG, "Traversal done ");
                        break;
                    } else {
                        Log.d(TAG, "device: " + devDescIf.toString());
                        if (devDescIf.mAttr != null) {
                            Log.d(TAG, "devDescIf.mAttr.length: " + devDescIf.mAttr.length);
                            for (int i = 0; i < devDescIf.mAttr.length; i++)
                                Log.d(TAG, "attr[" + i + "]: "+ devDescIf.mAttr[i].toString());
                        }
                    }
                }
                break;
            }
            case R.id.native_start: {
                //??????????????????
                IoTJNIApi.native_memory_start();
                break;
            }
            case R.id.native_stop:{
                //??????????????????
                IoTJNIApi.native_memory_stop("/mnt/sdcard/leaktracerxsj.log");
                break;
            }
        }
    }
}
