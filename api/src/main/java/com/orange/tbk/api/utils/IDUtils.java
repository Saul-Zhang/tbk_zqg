package com.orange.tbk.api.utils;

public class IDUtils {
    static char chs[] = new char[37];
    static {
        for(int i = 0; i < 10 ; i++) {
            chs[i] = (char)('0' + i);
        }
        for(int i = 10; i < chs.length; i++) {
            chs[i] = (char)('A' + (i - 10));
        }
    }
    /**
     * 转换方法
     * @param num       元数据字符串
     * @param fromRadix 元数据的进制类型
     * @param toRadix   目标进制类型
     * @return
     */

    public static String transRadix(String num, int fromRadix, int toRadix) {
        Long number = Long.valueOf(num, fromRadix);
        StringBuilder sb = new StringBuilder();
        while (number != 0) {
            sb.append(chs[(int) (number%toRadix)]);
            number = number / toRadix;
        }
        return sb.reverse().toString();
    }

    public static String getID(String num){
        return transRadix(num,10,36);
    }
    public static void main(String[] args) {
        System.out.println(transRadix("15899883221",10,36));
    }


}
