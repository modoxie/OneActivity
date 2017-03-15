package com.base.oneactivity.function;

/**
 * Created by Administrator on 2016/9/23
 */

public class RequestPermissionsResultAction {
    public BaseAction.Action permissionGranted;
    public BaseAction.Action permissionDenied;
    public BaseAction.Action toast;
    public RequestPermissionsResultAction(BaseAction.Action permissionGranted, BaseAction.Action permissionDenied){
        this.permissionGranted=permissionGranted;
        this.permissionDenied=permissionDenied;
    }
    public RequestPermissionsResultAction(BaseAction.Action permissionGranted, BaseAction.Action permissionDenied, BaseAction.Action toast){
        this.permissionGranted=permissionGranted;
        this.permissionDenied=permissionDenied;
        this.toast=toast;
    }
}
