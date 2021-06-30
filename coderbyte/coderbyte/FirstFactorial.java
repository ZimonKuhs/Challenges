package coderbyte;

import java.util.Scanner;

class FirstFactorial {

    public static int firstFactorial(int num) {
        int product = 1;
        for (int i = num; i > 1; --i) {
            product *= i;
        }
        return product;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print(firstFactorial(s.nextInt()));
        s.close();
    }

}