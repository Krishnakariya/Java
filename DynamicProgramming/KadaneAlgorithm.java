package DynamicProgramming;
import org.checkerframework.common.value.qual.*;
import org.checkerframework.checker.index.qual.*;
import java.util.Scanner;

/**
 * Program to implement Kadane’s Algorithm to
 * calculate maximum contiguous subarray sum of an array
 * Time Complexity: O(n)
 *
 * @author Nishita Aggarwal
 */

public class KadaneAlgorithm {

    /**
     * This method implements Kadane's Algorithm
     *
     * @param arr The input array
     * @return The maximum contiguous subarray sum of the array
     */
    static int largestContiguousSum(int arr[]) {
        int i, len = arr.length, cursum = 0, maxsum = Integer.MIN_VALUE;
        if (len == 0)    //empty array
            return 0;
        for (i = 0; i < len; i++) {
            cursum += arr[i];
            if (cursum > maxsum) {
                maxsum = cursum;
            }
            if (cursum <= 0) {
                cursum = 0;
            }
        }
        return maxsum;
    }

    /**
     * Main method
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, arr[], i;
        n = sc.nextInt();
        if(n>=0)
        {
            arr = new int[n];
            for (i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            int maxContSum = largestContiguousSum(arr);
            System.out.println(maxContSum);
            sc.close();
        }
        else
        {
            System.out.println("The input should be greater than 0.");
        }
    }

}
