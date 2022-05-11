package Java.DataStructure;
import java.util.Scanner;

class ArrayEqual {
    static boolean equals(int[] a, int[] b) {
        if (a.length != b.length)
            return false;
        
        for (int i = 0; i < a.length; i++)
            if (a[i] != b[i])
                return false;
        
        return true;
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.print("array a");
        int na = stdIn.nextInt();

        int[] a = new int[na];

        for (int i = 0; i < na; i++) {
            System.out.print("a["+i+"] :");
            a[i] = stdIn.nextInt();
        }

        System.out.print("array b");
        int nb = stdIn.nextInt();

        int[] b = new int[nb];
        for (int i = 0; i < nb; i++) {
            System.out.print("b["+i+"] :");
            b[i] = stdIn.nextInt();
        }

        System.out.print("array a , b " + (equals(a, b) ? " equal" : "not equal"));
    }

}