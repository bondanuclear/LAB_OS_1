package op_sys;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.ArrayList;

public class Manager {

        public ArrayList<Double> storeResult = new ArrayList<>();
        public double finalResult = 1;
        private currentStatus curStatus;
        public enum currentStatus {
            SUCCEEDED, FAILED, ISNAN,INFINITE
        }
        public void run(int x) {
            System.out.println("Cancellation: Press Escape to hard reset the program");
            MyThread threadFunctionF = new MyThread("F",x);
            threadFunctionF.start();
            MyThread threadFunctionG = new MyThread("G",x);
            threadFunctionG.start();
            try {
                threadFunctionF.join();
                threadFunctionG.join();

            } catch (InterruptedException e) {
                e.printStackTrace();

            }


            getResult(threadFunctionF, threadFunctionG, x);
            if(!storeResult.isEmpty()) {
                System.out.println("RESULT ==>" + calculateAnswer(storeResult));
            } else System.out.println("Failed to find the product of the functions");
            System.exit(0);
        }
        public void getResult(MyThread threadFunctionF,MyThread threadFunctionG, int x) {

            if (threadFunctionF.resultCalculated == true) {
                double finalResult = threadFunctionF.result.getAsDouble();

                if (Double.isNaN(finalResult)) {
                    curStatus = currentStatus.ISNAN;
                    System.out.println(curStatus);

                    return;

                } else if (Double.isInfinite(finalResult)) {
                    curStatus = currentStatus.INFINITE;
                    System.out.println(curStatus);

                    return;
                }
                storeResult.add(finalResult);
                curStatus = currentStatus.SUCCEEDED;
                System.out.println("F " + curStatus);
                System.out.println("\tF(" + x + ") =" + finalResult);


            } else {
                curStatus = currentStatus.FAILED;
                System.out.println(curStatus);

            }


            if (threadFunctionG.resultCalculated == true) {
                double finalResult = threadFunctionG.result.getAsDouble();

                if (Double.isNaN(finalResult)) {
                    curStatus = currentStatus.ISNAN;
                    System.out.println(curStatus);

                    return;

                } else if (Double.isInfinite(finalResult)) {
                    curStatus = currentStatus.INFINITE;
                    System.out.println(curStatus);

                    return;
                }
                storeResult.add(finalResult);
                curStatus = currentStatus.SUCCEEDED;
                System.out.println("G " + curStatus);
                System.out.println("\tG(" + x + ") = " + finalResult);


            }else {
                curStatus = currentStatus.FAILED;
                System.out.println(curStatus);

            }
        }
        private static void interruptAllThreads(MyThread threadFunctionF,MyThread threadFunctionG) {
            threadFunctionF.interrupt();
            threadFunctionG.interrupt();
        }
        private static double calculateAnswer(ArrayList<Double> storeResult) {
            double finalAnswer = 1;
            for( double i : storeResult) {
                finalAnswer = finalAnswer * i;
            }
            if(storeResult.isEmpty()) {
                System.out.println("Array is empty");
            }

            return finalAnswer;
        }



}
