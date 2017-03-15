package com.base.oneactivity.function;

/**
 * Created by Administrator on 2016/5/27
 */
public class BaseAction {
    public interface Action {
        void action();
    }

    public interface Action1<T> {
        void action(T one);
    }

    public interface Action2<T, T1> {
        void action(T one, T1 two);
    }

    public interface Action3<T, T1, T2> {
        void action(T one, T1 two, T2 three);
    }

    public interface Action4<T, T1, T2, T3> {
        void action(T one, T1 two, T2 three, T3 four);
    }

    public interface Action5<T, T1, T2, T3,T4> {
        void action(T one, T1 two, T2 three, T3 four, T4 five);
    }
    public interface ActionN{
        void action(Object... args);
    }
    public static <T> T creteAction(T t){
        return t;
    }
}