package com.base.oneactivity.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.base.oneactivity.function.BaseAction;
import com.base.oneactivity.function.RequestPermissionsResultAction;

/**
 * Created by Administrator on 2016/6/21
 */
public interface UIControl {
    Activity getActivity();
    LayoutInflater getLayoutInflater();
    UIControl show(UI to);

    UIControl show(UI to, ChangeAnimator changeAnimator);

    UIControl back();

    UIControl back(ChangeAnimator changeAnimator);

    UIControl backTo(int uiID);

    UIControl backTo(String name);

    UIControl destroy(UI ui);

    UIControl destroy(int uiID);

    ViewGroup getRoot();

    boolean hasUI(String name);

    UI findUI(String name);

    UI findUI(int id);

    UIControl removeAll();

    UI getTop();

    UIControl addRequest(String[] permission, RequestPermissionsResultAction action);

    void useDexClassLoader(String path);

    void loadResources(String dexPath);

    interface OnChange extends BaseAction.Action3<UI,UI,Float> {
    }

    interface OnFinish extends BaseAction.Action2<UI,UI>{
    }

    class ChangeAnimator {
        private OnChange onChange;
        private OnFinish onFinish;
        private OnFinish onStart;
        private long time=600;
        public OnChange getOnChange() {
            if (onChange == null) {
                onChange = new OnChange() {
                    @Override
                    public void action(UI one, UI two, Float three) {
                    }
                };
            }
            return onChange;
        }

        public ChangeAnimator setOnChange(OnChange onChange) {
            this.onChange = onChange;
            return this;
        }
        public OnFinish getOnStart(){
            if (onStart == null) {
                onStart = new OnFinish() {
                    @Override
                    public void action(UI one,UI two) {
                    }
                };
            }
            return onStart;
        }
        public ChangeAnimator setOnStart(OnFinish onStart) {
            this.onStart = onStart;
            return this;
        }
        public OnFinish getOnFinish() {
            if (onFinish == null) {
                onFinish = new OnFinish() {
                    @Override
                    public void action(UI one,UI two) {
                    }
                };
            }
            return onFinish;
        }

        public ChangeAnimator setOnFinish(OnFinish onFinish) {
            this.onFinish = onFinish;
            return this;
        }

        public long getTime() {
            return time;
        }

        public ChangeAnimator setTime(int time) {
            this.time = time;
            return this;
        }
    }
}
