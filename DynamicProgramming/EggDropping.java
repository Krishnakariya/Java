package DynamicProgramming;
import org.checkerframework.common.value.qual.*;
import org.checkerframework.checker.index.qual.*;
/**
 * DynamicProgramming solution for the Egg Dropping Puzzle
 */
public class EggDropping {

    // min trials with n eggs and m floors 
    private static int minTrials(@Positive int n, @Positive int m) {
        /* In the below line, the warning assignment.type.incompatible is raised. 
           The warning is raised because the checker is not able to statically verify that the dimension of the array 
           eggfloor is (n+1,m+1). */
        @SuppressWarnings("assignment.type.incompatible") int @MinLen(2) [] @MinLen(2) [] eggFloor = new int[n + 1][m + 1];
        int result, x;

        for (int i = 1; i <= n; i++) {
            eggFloor[i][0] = 0;   // Zero trial for zero floor.
            eggFloor[i][1] = 1;   // One trial for one floor 
        }

        // j trials for only 1 egg
        /* In the below line, the warning assignment.type.incompatible is raised. 
           The warning is raised because the checker is not able to statically verify that the dimension of the array 
           eggfloor is (n+1,m+1). */
        @SuppressWarnings("assignment.type.incompatible") @LTLengthOf("eggFloor[1]") int temp1 = m;
        for (int j = 1; j <= temp1; j++)
            eggFloor[1][j] = j;

        // Using bottom-up approach in DP

        for (int i = 2; i <= n; i++) {
            /* In the below line, the warning assignment.type.incompatible is raised. 
            The warning is raised because the checker is not able to statically verify that the dimension of the array 
            eggfloor is (n+1,m+1). */
            @SuppressWarnings("assignment.type.incompatible") @LTLengthOf("eggFloor[i]") int temp2 = m;
            int tempindex2 = i-1;
            for (int j = 2; j <= temp2; j++) {
                eggFloor[i][j] = Integer.MAX_VALUE;
                for (x = 1; x <= j; x++) {
                    /* In the below lines, the warning assignment.type.incompatible is raised. 
                       The warning is raised because the checker is not able to statically verify that the dimension of the array 
                       eggfloor is (n+1,m+1). */
                    @SuppressWarnings("assignment.type.incompatible") @IndexFor("eggFloor[tempindex2]") int tempindex1 = x-1;
                    @SuppressWarnings("assignment.type.incompatible") @IndexFor("eggFloor[i]") int tempindex3 = j-x;
                    result = 1 + Math.max(eggFloor[tempindex2][tempindex1], eggFloor[i][tempindex3]);

                    // choose min of all values for particular x
                    if (result < eggFloor[i][j])
                        eggFloor[i][j] = result;
                }
            }
        }
        /* In the below lines, the warning assignment.type.incompatible is raised. 
           The warning is raised because the checker is not able to statically verify that the dimension of the array 
           eggfloor is (n+1,m+1). */
        @SuppressWarnings("assignment.type.incompatible") @IndexFor("eggFloor[n]") int tempindex4 = m;
        return eggFloor[n][tempindex4];
    }

    public static void main(String args[]) {
        int n = 2, m = 4;
        // result outputs min no. of trials in worst case for n eggs and m floors
        int result = minTrials(n, m);
        System.out.println(result);
    }
}
