package com.xue.data.xipcutils.utils;

import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;

/**
 * @ProjectName: AndroidStudyDemoJ
 * @Package: com.xue.data.xipcutils.utils
 * @ClassName: TypeUtils
 * @Description:
 * @Author: 李雪
 * @CreateDate: 2021-07-08 17:49
 * @UpdateUser:
 * @UpdateDate: 2021-07-08 17:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class TypeUtils {


    public static  String getMethodId(Method method) {
        StringBuilder result = new StringBuilder();
        result.append(method.getName()).append("(").append(getMethodParams(method.getParameterTypes())).append(")");
        return  result.toString();
    }

    private static String getMethodParams(Class<?>[] params) {
        StringBuilder result = new StringBuilder();
        int length = params.length;
        if (length == 0){
            return result.toString();
        }
        result.append(getClassName(params[0]));
        for (int i = 1; i < length; i++){
            result.append(",").append(getClassName(params[i]));
        }

        return result.toString();
    }

    /**
     * 转换 类型名
     * @param clazz
     * @return
     */
    private static  String getClassName(Class<?> clazz){
        if (clazz == Boolean.class) {
            return "boolean";
        } else if (clazz == Byte.class) {
            return "byte";
        } else if (clazz == Character.class) {
            return "char";
        } else if (clazz == Short.class) {
            return "short";
        } else if (clazz == Integer.class) {
            return "int";
        } else if (clazz == Long.class) {
            return "long";
        } else if (clazz == Float.class) {
            return "float";
        } else if (clazz == Double.class) {
            return "double";
        } else if (clazz == Void.class) {
            return "void";
        } else {
            return clazz.getName();
        }
    }

    public static Method getMethodGetInstance(Class<?> resultClass, String methodName, Class<?>[] params) {
        Method[] methods = resultClass.getMethods();
        Method result = null;
        if (params == null){
            params = new Class[0];
        }
        for (int i =0; i < methods.length; i++){
            String tempName = methods[i].getName();
            if (tempName.equals("getInstance")){
                if (classAssignable(methods[i].getParameterTypes(), params)){
                    result = methods[i];
                }
            }
        }
        return result;
    }

    private static boolean classAssignable( Class<?>[] classes1, Class<?>[] classes2) {
        if (classes1.length != classes2.length){
            return false;
        }
        int length = classes1.length;
        for (int i =0; i < length ; i++){
            if (classes2[i] == null){
                continue;
            }
            if (primitiveMathch(classes1[i], classes2[i])){
                continue;
            }
            if (!classes1[i].isAssignableFrom(classes2[i])){
                return false;
            }
        }
        return true;
    }

    private static boolean primitiveMathch(Class<?> class1, Class<?> class2) {
        if (!class1.isPrimitive() && !class2.isPrimitive()){
            return false;
        } else if (class1 == class2){
            return true;
        } else if (class1.isPrimitive()){
            primitiveMathch(class2, class1);
        }  else if (class1 == Boolean.class && class2 == boolean.class) {
            return true;
        } else if (class1 == Byte.class && class2 == byte.class) {
            return true;
        } else if (class1 == Character.class && class2 == char.class) {
            return true;
        } else if (class1 == Short.class && class2 == short.class) {
            return true;
        } else if (class1 == Integer.class && class2 == int.class) {
            return true;
        } else if (class1 == Long.class && class2 == long.class) {
            return true;
        } else if (class1 == Float.class && class2 == float.class) {
            return true;
        } else if (class1 == Double.class && class2 == double.class) {
            return true;
        } else if (class1 == Void.class && class2 == void.class) {
            return true;
        } else {
            return false;
        }
        return false;
    }

    public static Method getMethod(Class<?> aClass, String methodName, Class[] params) {
        Method[] methods = aClass.getMethods();
        Method result = null;
        for (int i =0; i < methods.length; i++){
            if (methods[i].getName().equals(methodName) && classAssignable(methods[i].getParameterTypes(), params)){
                result = methods[i];
            }
        }


        return result;
    }
}
