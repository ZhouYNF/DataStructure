package thread;

import jdk.nashorn.internal.ir.CallNode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author ZHOU
 * @date 2021/3/25 19:49
 */
public class ThreadCreateDemo1 {
    /*public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start(); //该方法调用多次,出现IllegalThreadStateException
    }*/
    static class Aaa{
        public int aa;
        public String bb;

        public int getAa() {
            return aa;
        }

        public void setAa(int aa) {
            this.aa = aa;
        }

        public String getBb() {
            return bb;
        }

        public void setBb(String bb) {
            this.bb = bb;
        }

        public Aaa(int aa, String bb) {
            this.aa = aa;
            this.bb = bb;
        }


    }

    public synchronized static Object[] deleteSubString(String str1,String str2) {

        if(str1.length() != str2.length()){
            str2 +=";";
        }
        StringBuffer sb = new StringBuffer(str1);
        int delCount = 0;
        Object[] obj = new Object[2];

        while (true) {
            int index = sb.indexOf(str2);
            if(index == -1) {
                break;
            }
            sb.delete(index, index+str2.length());
            delCount++;

        }
        if(delCount!=0) {
            obj[0] = sb.toString();
            obj[1] = delCount;
        }else {
            //不存在返回-1
            obj[0] = -1;
            obj[1] = -1;
        }

        return obj;
    }


    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(2);
        BigDecimal b = new BigDecimal(1000);
        BigDecimal c = new BigDecimal(50);
        BigDecimal d = new BigDecimal(5);
        BigDecimal f =  a.divide(b).multiply(c).multiply(d.divide(b));
        System.out.println(a.divide(b).multiply(d.divide(b).multiply(c)));
        //System.out.println(d.divide(b));

    }
}
