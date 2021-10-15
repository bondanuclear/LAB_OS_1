package op_sys;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HardReset.pressToHardReset();
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        //System.out.println(Thread.activeCount());
        Manager manager = new Manager();
        manager.run(x);
    }

}
