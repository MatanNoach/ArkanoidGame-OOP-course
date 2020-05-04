/**
 * @author Matan Noach <matan.noach@gmail.com>
 *     ID: 316441534
 * @version 1.0
 * @since 23/03/2020
 */
public class AveragePrimes {
    /**
     * The function checks if a number is a prime number or not.
     * <p>
     * The function finds the square root of the number. if the number can be divided by any
     * value between 2 and the root included, then the number is not prime.
     * </p>
     *
     * @param num represents the number to check
     * @return true if the number is prime and false if it isn't.
     **/
    public static boolean checkPrime(int num) {
        //Finds the square root of the number
        double squareRoot = Math.sqrt(num);
        int i = 2;
        //While loop that checks if the number can be divided
        while (i <= squareRoot) {
            //If it can, then the number is not prime
            if (num % i == 0) {
                return false;
            }
            i++;
        }
        //If the loop ended, then the number is prime. Return true
        return true;
    }

    /**
     * The function gets a number n from the user and prints the average
     * of all prime numbers between 2 and n.
     *
     * @param args command line arguments.
     **/
    public static void main(String[] args) {
        int sum = 0, count = 0;
        //The number received by the user parsed from string to int
        int n = Integer.parseInt(args[0]);
        double average = 0;
        //If the number is less then one, prints an error message and exit the function
        if (n <= 1) {
            System.out.println("Invalid value");
            return;
        }
        //For loop that sums and counts all the prime number from 2 to n
        for (int i = 2; i <= n; i++) {
            //If the number is prime, adds it to the sum and adds 1 to the counter
            if (checkPrime(i)) {
                sum += i;
                count++;
            }
        }
        //Calculates the average and prints it
        average = (double) sum / count;
        System.out.println(average);
    }
}