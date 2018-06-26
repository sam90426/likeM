package com.quygt.dkcs.utils;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JPushUtil {
    private static String APP_KEY ;
    private static String MASTER_SECRET ;
    private static int APP_TYPE;
    private static String CONTENT;
    private static int PUSH_TYPE;
    private static String PUSH_URL;
    private static String[] PUSH_USERS;
    private static boolean IOS_PATH=false;//true=生产环境 false=开发环境

    //region 发送PUSH

    /**
     * @param apptype   推送平台 1=全平台 2=Android 3=iOS
     * @param content   推送内容
     * @param pushtype  推送类型 1=广播，2=设备别名(Alias)，3=设备标签(Tag)
     * @param pushurl   推送附加URL
     * @param pushusers 非广播推送的用户标识(pushtype==2||3为必填)
     * @return
     */
    public static boolean sendPush(int apptype, String content, int pushtype, String pushurl, String[] pushusers) {

        //region 参数验证
        if(apptype!=1&&apptype!=2&&apptype!=3)
            return false;
        if(content.isEmpty())
            return false;
        if(pushtype!=1&&pushtype!=2&&pushtype!=3)
            return false;
        if(pushtype==2||pushtype==3){
            if(pushusers.length<1)
                return false;
        }
        if(pushurl.isEmpty())
            return false;
        //endregion

        APP_KEY=ConfigUtil.getInstance().getString("JPush_APP_KEY");
        MASTER_SECRET=ConfigUtil.getInstance().getString("JPush_MASTER_SECRET");
        APP_TYPE = apptype;
        CONTENT = content;
        PUSH_TYPE = pushtype;
        PUSH_URL = pushurl;
        PUSH_USERS = pushusers;

        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);

        //region 构建一个 PushPayload 对象
        PushPayload payload = null;
        if (apptype == 1) {
            if (pushtype == 1)
                payload = buildPushObject_allandall();
            else if (pushtype == 2)
                payload = buildPushObject_allandalias();
            else if (pushtype == 3)
                payload = buildPushObject_allandtag();
        } else if (apptype == 2) {
            if (pushtype == 1)
                payload = buildPushObject_androidandall();
            else if (pushtype == 2)
                payload = buildPushObject_androidandalias();
            else if (pushtype == 3)
                payload = buildPushObject_androidandtag();
        } else if (apptype == 3) {
            if (pushtype == 1)
                payload = buildPushObject_iosandall();
            else if (pushtype == 2)
                payload = buildPushObject_iosandalias();
            else if (pushtype == 3)
                payload = buildPushObject_iosandtag();
        }
        //endregion

        boolean result = false;

        try {
            PushResult data = jpushClient.sendPush(payload);
            if (data.statusCode == 0)
                result = true;
            System.out.println("JPush返回的结果:" + data);
        } catch (APIConnectionException e) {
            return false;
        } catch (APIRequestException e) {
            return false;
        }
        return result;
    }

    //endregion

    //region all+广播
    public static PushPayload buildPushObject_allandall() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.all())
                .setMessage(Message.newBuilder()
                        .setMsgContent(CONTENT)
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(IOS_PATH)
                        .build())
                .setNotification(Notification.newBuilder()
                                .addPlatformNotification(
                                        IosNotification.newBuilder()
                                                .setAlert(CONTENT)
                                                .addExtra("url", PUSH_URL)
                                                .build())
                                .addPlatformNotification(
                                        AndroidNotification.newBuilder()
                                                .setAlert(CONTENT)
                                                .addExtra("url", PUSH_URL)
                                                .build())
                                .build())
                .build();
    }
    //endregion

    //region android+广播
    public static PushPayload buildPushObject_androidandall() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setMessage(Message.newBuilder()
                        .setMsgContent(CONTENT)
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(IOS_PATH)
                        .build())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(
                                IosNotification.newBuilder()
                                        .setAlert(CONTENT)
                                        .addExtra("url", PUSH_URL)
                                        .build())
                        .addPlatformNotification(
                                AndroidNotification.newBuilder()
                                        .setAlert(CONTENT)
                                        .addExtra("url", PUSH_URL)
                                        .build())
                        .build())
                .build();
    }
    //endregion

    //region ios+广播
    public static PushPayload buildPushObject_iosandall() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.all())
                .setMessage(Message.newBuilder()
                        .setMsgContent(CONTENT)
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(IOS_PATH)
                        .build())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(
                                IosNotification.newBuilder()
                                        .setAlert(CONTENT)
                                        .addExtra("url", PUSH_URL)
                                        .build())
                        .addPlatformNotification(
                                AndroidNotification.newBuilder()
                                        .setAlert(CONTENT)
                                        .addExtra("url", PUSH_URL)
                                        .build())
                        .build())
                .build();
    }
    //endregion

    //region all+别名
    public static PushPayload buildPushObject_allandalias() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(PUSH_USERS))
                .setMessage(Message.newBuilder()
                        .setMsgContent(CONTENT)
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(IOS_PATH)
                        .build())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(
                                IosNotification.newBuilder()
                                        .setAlert(CONTENT)
                                        .addExtra("url", PUSH_URL)
                                        .build())
                        .addPlatformNotification(
                                AndroidNotification.newBuilder()
                                        .setAlert(CONTENT)
                                        .addExtra("url", PUSH_URL)
                                        .build())
                        .build())
                .build();
    }
    //endregion

    //region android+别名
    public static PushPayload buildPushObject_androidandalias() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias(PUSH_USERS))
                .setMessage(Message.newBuilder()
                        .setMsgContent(CONTENT)
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(IOS_PATH)
                        .build())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(
                                IosNotification.newBuilder()
                                        .setAlert(CONTENT)
                                        .addExtra("url", PUSH_URL)
                                        .build())
                        .addPlatformNotification(
                                AndroidNotification.newBuilder()
                                        .setAlert(CONTENT)
                                        .addExtra("url", PUSH_URL)
                                        .build())
                        .build())
                .build();
    }
    //endregion

    //region ios+别名
    public static PushPayload buildPushObject_iosandalias() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.alias(PUSH_USERS))
                .setMessage(Message.newBuilder()
                        .setMsgContent(CONTENT)
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(IOS_PATH)
                        .build())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(
                                IosNotification.newBuilder()
                                        .setAlert(CONTENT)
                                        .addExtra("url", PUSH_URL)
                                        .build())
                        .addPlatformNotification(
                                AndroidNotification.newBuilder()
                                        .setAlert(CONTENT)
                                        .addExtra("url", PUSH_URL)
                                        .build())
                        .build())
                .build();
    }
    //endregion

    //region all+tag
    public static PushPayload buildPushObject_allandtag() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.tag(PUSH_USERS))
                .setMessage(Message.newBuilder()
                        .setMsgContent(CONTENT)
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(IOS_PATH)
                        .build())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(
                                IosNotification.newBuilder()
                                        .setAlert(CONTENT)
                                        .addExtra("url", PUSH_URL)
                                        .build())
                        .addPlatformNotification(
                                AndroidNotification.newBuilder()
                                        .setAlert(CONTENT)
                                        .addExtra("url", PUSH_URL)
                                        .build())
                        .build())
                .build();
    }
    //endregion

    //region android+tag
    public static PushPayload buildPushObject_androidandtag() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag(PUSH_USERS))
                .setMessage(Message.newBuilder()
                        .setMsgContent(CONTENT)
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(IOS_PATH)
                        .build())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(
                                IosNotification.newBuilder()
                                        .setAlert(CONTENT)
                                        .addExtra("url", PUSH_URL)
                                        .build())
                        .addPlatformNotification(
                                AndroidNotification.newBuilder()
                                        .setAlert(CONTENT)
                                        .addExtra("url", PUSH_URL)
                                        .build())
                        .build())
                .build();
    }
    //endregion

    //region ios+tag
    public static PushPayload buildPushObject_iosandtag() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag(PUSH_USERS))
                .setMessage(Message.newBuilder()
                        .setMsgContent(CONTENT)
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(IOS_PATH)
                        .build())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(
                                IosNotification.newBuilder()
                                        .setAlert(CONTENT)
                                        .addExtra("url", PUSH_URL)
                                        .build())
                        .addPlatformNotification(
                                AndroidNotification.newBuilder()
                                        .setAlert(CONTENT)
                                        .addExtra("url", PUSH_URL)
                                        .build())
                        .build())
                .build();
    }
    //endregion
}
