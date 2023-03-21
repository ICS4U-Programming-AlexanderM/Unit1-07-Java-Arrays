import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
* This program calculates the mean, median, and mode of an array.
*
* @author  Alexander Matheson
* @version 1.0
* @since   2023-03-20
*/

public final class Array {
    /**
    * For style checker.
    *
    * @exception IllegalStateException Utility class.
    * @see IllegalStateException
    */
    private Array() {
        throw new IllegalStateException("Utility class");
    }

    /**
    * Print messages.
    *
    * @param args Unused
    */
    public static void main(String[] args) {
        // Declare list.
        final ArrayList<String> numList = new ArrayList<String>();

        try {
            // Choose a file to get input from.
            // Write desired name of file here.
            final File input = new File("Unit1-07-set3.txt");
            final Scanner scanInput = new Scanner(input);

            // Choose (or create) a file to print output to.
            final FileWriter output = new FileWriter("output.txt");

            // Loop to read each line of file.
            while (scanInput.hasNextLine()) {
                // Add the next line.
                numList.add(scanInput.nextLine());
            }

            // Create an array with all elements in the list.
            final int[] numArr = new int[numList.size()];
            for (int location = 0; location < numArr.length; location++) {
                numArr[location] = Integer.parseInt(numList.get(location));
            }

            // Sort the array.
            Arrays.sort(numArr);

            // Call functions.
            final double mean = calcMean(numArr);
            final double median = calcMedian(numArr);
            final int[] mode = calcMode(numArr);

            // Print results.
            System.out.println("Mean: " + mean);
            System.out.println("Median: " + median);
            System.out.println("Mode: " + Arrays.toString(mode));

            // Write results to output file.
            output.write("Mean: " + mean + "\n");
            output.write("Median: " + median + "\n");
            output.write("Mode: " + Arrays.toString(mode) + "\n");

            // Close writer.
            output.close();

        } catch (IOException err) {
            // For when no input file is found.
            System.err.println("Error: " + err.getMessage());
        }
    }

    /**
    * This function calculates mean.
    *
    * @param numbers in array
    * @return mean
    */
    public static double calcMean(int[] numbers) {
        // Variables for function.
        double sum = 0;
        double mean = 0;
        final double length = numbers.length;
        for (int position = 0; position < length; position++) {
            sum = sum + numbers[position];
        }
        mean = sum / length;
        return mean;
    }

    /**
    * This function calculates median.
    *
    * @param numbers in array
    * @return median
    */
    public static double calcMedian(int[] numbers) {
        double median = 0;
        final double length = numbers.length;
        if (numbers.length % 2 == 0) {
            median = (numbers[numbers.length / 2]
                     + numbers[numbers.length / 2 - 1]) / 2;
        } else {
            final int middle = (int) Math.floor(numbers.length / 2);
            median = numbers[middle];
        }
        return median;
    }

    /**
    * This function calculates mode.
    *
    * @param numbers in array
    * @return mode
    */
    public static int[] calcMode(int[] numbers) {
        // Variables and list for function.
        int maxCount = 0;
        final ArrayList<Integer> modes = new ArrayList<Integer>();
        int count = 0;

        // Check number of times each element appears.
        for (int position = 0; position < numbers.length; position++) {
            count = 0;
            for (int compare = 0; compare < numbers.length; compare++) {
                if (numbers[compare] == numbers[position]) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                modes.clear();
                modes.add(numbers[position]);
            } else if (count == maxCount) {
                modes.add(numbers[position]);
            }
        }

        // Remove duplicates.
        if (modes.size() > 1) {
            int number = 1;
            while (number < modes.size()) {
                if (modes.get(number) == modes.get(number - 1)) {
                    modes.remove(number);
                } else {
                    number++;
                }
            }
        }

        // Turn list to array.
        final int[] modeArr = new int[modes.size()];
        for (int location = 0; location < modeArr.length; location++) {
            modeArr[location] = modes.get(location);
        }
        return modeArr;
    }
}
