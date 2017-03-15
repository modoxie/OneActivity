package com.base.oneactivity.ui;

import android.support.annotation.Nullable;
import android.view.View;

import com.base.oneactivity.function.BaseAction;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/23
 */
public class UIModel {
    private View mView;
    private JSONObject data;
    private Map<String, UpdateView> map = new HashMap<>();

    public UIModel(UI ui) {
        this(ui.getView(), ui.getData());
    }

    public UIModel(View v, JSONObject data) {
        this.data = data == null ? new JSONObject() : data;
        mView = v;
    }

    public View getmView() {
        return mView;
    }

    public JSONObject getData() {
        return data;
    }

    public UIModel initView(InitView initView) {
        if (initView == null) return this;
        initView.action(this, data);
        return this;
    }

    public UIModel updateView(String key, @Nullable JSONObject res) {
        UpdateView updateView = map.get(key);
        if (updateView != null) {
            updateView.action(this,data,res==null?new JSONObject():res);
        }
        return this;
    }

    public UIModel addUpdateListener(String key, UpdateView updateView) {
        if (updateView == null) return this;
        map.put(key,updateView);
        return this;
    }

    public UIModel removeAllUpdateListener() {
        map.clear();
        return this;
    }

    public UIModel removeUpdateListener(UpdateView updateView) {
        if (map.containsValue(updateView)) {
            map.remove(updateView);
        }
        return this;
    }

    public UIModel removeUpdateListener(String key) {
        if (map.containsKey(key)) {
            map.remove(key);
        }
        return this;
    }

    public interface InitView extends BaseAction.Action2<UIModel, JSONObject> {

    }

    public interface UpdateView extends BaseAction.Action3<UIModel, JSONObject,JSONObject> {

    }

    public <T extends View> T find(int id) {
        return (T) mView.findViewById(id);
    }
}
