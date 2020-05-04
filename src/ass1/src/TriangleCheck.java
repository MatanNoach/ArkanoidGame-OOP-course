/**
 * @author Matan Noach <matan.noach@gmail.com>
 *     ID: 316441534
 * @version 1.0
 * @since 23/03/2020
 */
public class TriangleCheck {
    /****
     * The function gets 3 numbers from the user and checks if they can represents 3 sides of a triangle.
     * <p>
     *     The function prints "triangle!", if they can, "not triangle" if they,
     *     and if it's a right triangle, also print "right triangle!.
     * </p>
     * @param args command line arguments.
     ****/
    public static void main(String[] args) {
        double a, b, c;
        double tmp = 0, min = 0;
        //If there are not 3 number, print an error message and exit the function
        if (args.length != 3) {
            System.out.println("invalid input");
            return;
        }
        //set the minimum value as the first number
        min = Double.parseDouble(args[0]);
        //For loop that checks if all of the numbers are positive
        //The loop also finds the lowest number in the arguments and sets it's value in the first string in args
        for (int i = 0; i < 3; i++) {
            tmp = Double.parseDouble(args[i]);
            //If the number is not positive, prints an error message and exit the function
            if (tmp <= 0) {
                System.out.println("invalid input");
                return;
            }
            //Finds the lowest number and sets it's value in args[0]
            if (min > tmp) {
                min = tmp;
                args[i] = args[0];
                args[0] = Double.toString(min);
            }
        }
        //set a, b, c as the arguments when a is the lowest at that point
        a = Double.parseDouble(args[0]);
        b = Double.parseDouble(args[1]);
        c = Double.parseDouble(args[2]);
        //if c is smaller than b, replace them
        if (c < b) {
            tmp = b;
            b = c;
            c = tmp;
        }
        //If a+b>c then then the number can represents a triangle
        //Else, prints "not triangle" message
        if (a + b > c) {
            //prints a message
            System.out.println("triangle!");
            //checks if the triangle is right triangle
            if (Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2)) {
                //prints a message
                System.out.println("right angled!");
            }
        } else {
            System.out.println("not triangle");
        }
    }

}