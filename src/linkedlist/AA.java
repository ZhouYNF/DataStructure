package linkedlist;

/**
 * @author ZHOU
 * @date 2021/3/17 21:35
 */
public class AA {
    public static void main(String[] args) {
      /*  String arg = args[1];
        System.out.println(arg);
        System.out.println(new B().getValue());*/
        int[] arr=new int[10];
        System.out.println(arr.length);
    }
    static class A {
        protected int value;
        public A (int v) {
            setValue(v);
        }
        public void setValue(int value) {
            this.value= value;
        }
        public int getValue() {
            try {
                value ++;
                return value;
            } finally {
                this.setValue(value);
                System.out.println(value);
            }
        }
    }
    static class B extends A {
        public B () {
            super(5);
            setValue(getValue()- 3);
        }
        public void setValue(int value) {
            super.setValue(2 * value);
        }
    }
}
