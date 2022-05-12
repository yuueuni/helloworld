class PrimeNumber {
    static int primeNumber1() {
        int counter = 0;

        for (int n = 2; n <= 1000; n++) {
            int i;
            for (i = 2; i < n; i++) {
                counter++;
                if (n % i == 0)
                    break;
            }
        //     if (n == i)
        //         System.out.println(n);
        }

        // System.out.println("counter" + counter);

        return counter;
    }

    static int primeNumber2() {
        int counter = 0;
        int ptr = 0;
        int[] prime = new int[500];

        prime[ptr++] = 2;

        for (int n = 3; n <= 1000; n += 2) {
            int i;
            for (i = 1; i < ptr; i++) {
                counter++;
                if (n % prime[i] == 0) 
                    break;
            }

            if (ptr == i)
                prime[ptr++] = n;
        }

        // for (int i = 0; i < ptr; i++) {
        //     System.out.println(prime[i]);
        // }

        // System.out.println("counter" + counter);

        return counter;
    }

    static int primeNumber3() {
        int counter = 0;
        int ptr = 0;
        int[] prime = new int[500];

        prime[ptr++] = 2;
        prime[ptr++] = 3;

        for (int n = 5; n <= 1000; n += 2) {
            boolean flag = false;
            for (int i = 1; prime[i] * prime[i] <= n; i++) {
                counter += 2;
                if (n % prime[i] == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                prime[ptr++] = n;
                counter++;
            }
        }

        // for (int i = 0; i < ptr; i++) {
        //     System.out.println(prime[i]);
        // }

        // System.out.println("counter" + counter);

        return counter;
    }

    public static void main(String[] args) {
        int pn1, pn2, pn3;
        System.out.println("==== start prime number 1=====");
        pn1 = primeNumber1();
        System.out.println("==== start prime number 2 =====");
        pn2 = primeNumber2();
        System.out.println("==== start prime number 3 =====");
        pn3 = primeNumber3();

        System.out.printf("1 : %d, 2 : %d, 3 : %d", pn1, pn2, pn3);
    }
}
