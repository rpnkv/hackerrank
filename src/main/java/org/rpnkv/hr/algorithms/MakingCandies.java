package org.rpnkv.hr.algorithms;

import java.lang.reflect.Field;

public class MakingCandies {

    private Candy candy;

    private Candy get() throws Exception {
        if(candy == null){
            synchronized (this){
                if(candy == null){
                    candy = new Candy();
                }
            }
        }
        return candy;
    }

    private static class Candy {
        private int x1, x2;

        public Candy() throws Exception {
            x1 = 1;
            Thread.sleep(1000);
            Field field = Candy.class.getField("x2");
            field.setAccessible(true);
            x1 = field.getInt(this) + 1;
        }

        @Override
        public String toString() {
            return "Candy{" +
                    "x1=" + x1 +
                    ", x2=" + x2 +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        MakingCandies mc = new MakingCandies();
        Thread t2 = new Thread(() -> {
            try {
                System.out.println(mc.get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        t2.start();
        System.out.println(mc.get());
    }
}
