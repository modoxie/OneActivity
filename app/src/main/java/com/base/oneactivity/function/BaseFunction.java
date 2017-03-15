package com.base.oneactivity.function;

/**
 * Created by Administrator on 2016/5/27
 */
public class BaseFunction {
    public interface Function<T> {
        T action();
    }

    public interface Function1<T,T1> {
        T1 action(T one);
    }

    public interface Function2<T,T1, T2> {
        T2 action(T one, T1 two);
    }

    public interface Function3<T, T1, T2 ,T3> {
        T3 action(T one, T1 two, T2 three);
    }

    public interface Function4<T, T1, T2, T3 ,T4> {
        T4 action(T one, T1 two, T2 three, T3 four);
    }

    public interface Function5<T, T1, T2, T3,T4 ,T5> {
        T5 action(T one, T1 two, T2 three, T3 four, T4 five);
    }

    public interface FunctionN<T> {
        T action(Object... args);
    }
    public static <T> T creteFunction(T t){
        return t;
    }
}