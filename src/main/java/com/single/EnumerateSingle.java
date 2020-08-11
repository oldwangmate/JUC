package com.single;

/**
 * enum本身也是一个类
 */
public enum EnumerateSingle {

    ISTSNCE;

    public EnumerateSingle getIstsnce(){
        return ISTSNCE;
    }

}

class Test{

    public static void main(String[] args) {
        EnumerateSingle istsnce = EnumerateSingle.ISTSNCE;
        EnumerateSingle istsnce1 = EnumerateSingle.ISTSNCE;
        System.out.println(istsnce == istsnce1);
    }
}
