package op_sys;
import os.lab1.compfuncs.basic.DoubleOps;

import java.util.OptionalDouble;

public class MyThread extends Thread{
    //  private static long timeMillis;
    private final String function;

    private long start;
    private long end;
    private double millisF;
    private double millisG;

    private final int x;
    public OptionalDouble result;
    public boolean resultCalculated = false;

    public MyThread(String function, int x) {
        this.function = function;
        this.x = x;
    }

    @Override
    public void run() {
        //System.out.println(Thread.activeCount());

        try {

                start = System.nanoTime();

                if (function.equals("F")) {
                    Thread.currentThread().setName("Thread F");
                    System.out.print(Thread.currentThread().getName());
                    System.out.println(" ===> Computating F...");


                    result = OptionalDouble.of(DoubleOps.trialF(x).get());

                    synchronized (this) {

                        if (result.isPresent()) {
                            // System.out.println(Thread.currentThread().getName());
                            System.out.println("Function F ==> " + result.getAsDouble());
                            resultCalculated = true;
                        }

                    }
                    end = System.nanoTime();
                    millisF = (end - start) / 1_000_000;
                    // System.out.println("Took: F " + millisF + " millis");
                }

        } catch (Exception e) {
           // System.out.println(Thread.currentThread().getName());
            System.out.println("Function F ==> Couldn't computate");
        }
        try {

            if (function.equals("G")) {

                    start = System.nanoTime();
                Thread.currentThread().setName("Thread G");
                System.out.print(" " + Thread.currentThread().getName());
                System.out.println(" ===> Computating G...");
                    result = OptionalDouble.of(DoubleOps.trialG(x).get());

                    synchronized (this) {


                        if (result.isPresent()) {
                            //System.out.println(Thread.currentThread().getName());
                            System.out.println("Function G ==> " + result.getAsDouble());
                            resultCalculated = true;
                        }

                    }
                    end = System.nanoTime();
                    millisG = (end - start) / 1_000_000;
                    // System.out.println("Took: G " + millisG + " millis");
                }


        } catch (Exception e) {
           // System.out.println(Thread.currentThread().getName());
            System.out.println("Function G ==> Couldn't computate");
        }
    }
    public double getMillis(String function) {
        double result;
        if(function == "F") {
            result = this.millisF;
            return result;
        } else if(function == "G") {
            result = this.millisG;
            return result;
        } else
            return 0;
    }
}

