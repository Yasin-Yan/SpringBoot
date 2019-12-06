package com.yanwu.springsecurityvalidatecode;

import org.junit.Test;

import java.math.BigInteger;

public class TestCase {

    @Test
    public void test1(){
        BigInteger f1 = new BigInteger("1");
        BigInteger f2 = new BigInteger("1");
        BigInteger f3 = new BigInteger("1");
        BigInteger f = null;
        BigInteger mod = new BigInteger("10000");
        for (long i = 4; i <= 20190324L; i++) {
            f = f1.add(f2).add(f3);
            f = f.remainder(mod);
//            System.out.println(f);
            f3 = f2;
            f2 = f1;
            f1 = f;
        }
        System.out.println(f);
    }

    @Test
    public void test2(){
        double a = 0.1D;
        double b = 0.2D;
        System.out.println(a+b);
    }
}
