/**
 * @author Matan Noach <matan.noach@gmail.com>
 *     ID: 316441534
 * @version 1.0
 * @since 23/03/2020
 */
public class CharCount {
    /**
     * <p>
     * The function gets a sequence of strings and a char in the end.
     * The function checks which strings contains the char, and reprints them before
     * the strings that doesn't contains it.
     * </p>
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        //If there are less then 2 inputs from the user, or if the last string is not a char
        //Prints an error message and exit the function
        if ((args.length < 2) || (args[args.length - 1].length() != 1)) {
            System.out.println("Invalid input");
            return;
        }
        //c represents the char we want to check
        //The char is always the last string in the sequence
        char c = args[args.length - 1].charAt(0);
        //Array of strings that contains the char
        String[] groupA = new String[args.length];
        //Array of strings that doesn't contains the char
        String[] groupB = new String[args.length];
        //Int that represents both array's size
        int aSize = 0, bSize = 0;
        //For loop that scans the arguments for the command line
        for (String str : args) {
            //If c exists in the str, than add it to group A
            //Else, add it to group B
            if (str.indexOf(c) >= 0) {
                groupA[aSize] = str;
                aSize++;
            } else {
                groupB[bSize] = str;
                bSize++;
            }
        }
        //Prints group A
        for (int i = 0; i < aSize - 1; i++) {
            System.out.println(groupA[i]);
        }
        //Prints group B
        for (int i = 0; i < bSize; i++) {
            System.out.println(groupB[i]);
        }
    }
}
