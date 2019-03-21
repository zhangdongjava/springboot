package com.zzz;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Test {

    private int i = 0;

    private Map<String,String> aaa = new HashMap<>(100);

    public String lsc(String str1, String str2) {
        String key = str1+"__"+str2;
        String val;
        if((val=aaa.get(key))!=null){
            return val;
        }
        System.out.println(i++);
        int len1 = str1.length();

        int len2 = str2.length();

        if (str1.charAt(len1 - 1) == str2.charAt(len2 - 1)) {
            if (len1 == 0 || len2 == 0) {
                aaa.put(key,"");
                return "";
            }
            return lsc(str1.substring(0, len1 - 1), str2.substring(0, len2 - 1)) + str1.charAt(len1 - 1);
        } else {
            if (len1 == 1 || len2 == 1) {
                aaa.put(key,"");
                return "";
            }
            String a = lsc(str1, str2.substring(0, len2 - 1));
            String b = lsc(str1.substring(0, len1 - 1), str2);
            if (a.length() > b.length()) {
                aaa.put(key,a);
                return a;
            }
            aaa.put(key,b);
            return b;
        }
    }


    public String lsc2(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        String[][] m = new String[len2][len1];
        for (int y = 0; y < m.length; y++) {
            char yc = str2.charAt(y);
            for (int x = 0; x < m[0].length; x++) {
                char xc = str1.charAt(x);
                String val = "";

                //根据左上的点来计算
                if (xc == yc) {
                    if (x >= 1 && y >= 1) {
                        String leftTop = m[y - 1][x - 1];
                        val = Objects.toString(leftTop, "");
                    }
                    val += xc;
                } else {//不等获取左或者上 最长的
                    if (x >= 1 && y >= 1) {
                        String left = m[y][x - 1];
                        String top = m[y - 1][x];
                        if (left != null && strLen(left) > strLen(top)) {
                            val = left;
                        } else if (top != null) {
                            val = top;
                        }
                    }
                }
                //左上角没保留了!
                if (x >= 1 && y >= 1) {
                    m[y - 1][x - 1] = null;
                }
                if (y > 0 && x == m[0].length - 1) {//每行最后一位 上边也没必要保留了
                    m[y - 1][x] = null;
                }
                if (x > 0 && y == m.length - 1) {//最后一行左边没必要保留了
                    m[y][x - 1] = null;
                }
                m[y][x] = val;
                printMap(m, str1, str2);
            }

        }
        return Objects.toString(m[len2 - 1][len1 - 1], "");
    }

    public int strLen(String str) {
        if (str == null)
            return 0;
        return str.length();
    }

    public void printMap(String[][] str, String str1, String str2) {
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.print("\t");
        for (int i = 0; i < str1.length(); i++) {
            System.out.print(str1.charAt(i) + "\t");

        }
        for (int i = 0; i < str.length; i++) {
            String[] strings = str[i];
            System.out.print(str2.charAt(i)+"\t");
            for (int j = 0; j < strings.length; j++) {
                String string = strings[j];
                System.out.print(Objects.toString(string,"") + "\t");
            }
            System.out.println();

        }
    }


    @org.junit.Test
    public void ew() {
        String[][] m = new String[5][7];
        System.out.println(m.length);
    }


    @org.junit.Test
    public void test2() {
        System.out.println(lsc2(
                "transportfromthetarget",
                "Connetargetctedtransport"
        ));
    }

    @org.junit.Test
    public void test1() {
        System.out.println(lsc(
                "111111111111111",
                "222222222222222"
        ));
    }
}
