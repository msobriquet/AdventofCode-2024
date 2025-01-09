import java.io.*;
import java.util.*;

/* 8 is max num in report
 * 
    The levels are either all increasing or all decreasing.
    Any two adjacent levels differ by at least one and at most three.

 *  7 6 4 2 1: Safe because the levels are all decreasing by 1 or 2.
    1 2 7 8 9: Unsafe because 2 7 is an increase of 5.
    9 7 6 2 1: Unsafe because 6 2 is a decrease of 4.
 * 
 */

public class Report2 {
    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("input2.csv"));
        } catch (FileNotFoundException e) {
            System.err.println("Cannot locate file.");
            System.exit(-1);
        }


        int safeReports = 0;
        while(scanner.hasNextLine()) { //loops for each report so each line
            String[] strReport = scanner.nextLine().split(" ");
            
            //change String array into int array
            int[] report = new int[strReport.length];
            for (int i = 0; i < report.length; i++) {
                report[i] = Integer.parseInt(strReport[i]);
            }


            if(routeCheck(report)) {
                safeReports++;
            }

            // if (report[0] > report[1]) { //decreasing
            //     if (decCheck(report)) {
            //         safeReports++;
            //     } 
            // } else if (report[0] < report[1]) { //increasing
            //     if (incCheck(report)) {
            //         safeReports++;
            //     }
            // } else {
            //     continue; //not increasing or decreasing in first step so unsafe
            // }
        }

        System.out.println(safeReports);

    }

    public static boolean routeCheck(int[] report) {
        if (decCheck(report) || incCheck(report)) {
            return true;
        }

        for (int i = 0; i < report.length; i++) {
            int[] modReport = removeLevel(report, i);
            if (decCheck(modReport) || incCheck(modReport)) {
                return true;
            }
        }

        return false; 
    }

    public static boolean decCheck(int[] report) {
        for (int i = 0; i < report.length - 1; i++) {
            if (report[i] < report[i+1]) {
                return false;
            } else if (report[i] - report[i+1] > 3 || report[i] - report[i+1] < 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean incCheck(int[] report) {
        for (int i = 0; i < report.length - 1; i++) {
            if (report[i] > report[i+1]) {
                return false;
            } else if (report[i+1] - report[i] > 3 || report[i+1] - report[i] < 1) {
                return false;
            }
        }
        return true;
    }

    public static int[] removeLevel(int[] report, int indexToRemove) {
        int[] result = new int[report.length - 1];

        for (int i = 0, j = 0; i < report.length; i ++) {
            if (i != indexToRemove) {
                result[j++] = report[i];
            }
        }

        return result;
    }
}
