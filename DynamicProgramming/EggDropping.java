package DynamicProgramming;
import org.checkerframework.common.value.qual.*;
import org.checkerframework.checker.index.qual.*;
/**
 * DynamicProgramming solution for the Egg Dropping Puzzle
 */
public class EggDropping {

    // min trials with n eggs and m floors 
    @SuppressWarnings({"cast.unsafe" , "expression.unparsable.type.invalid", "array.access.unsafe.high"})
    private static int minTrials(@Positive int n, @Positive int m) {
        /* The below Line gives cast.unsafe warning because the compiler is unable to statically verify that the length of the array "eggFloor" and "eggFloor[i]" is greater than 1*/
        int @MinLen(2) [] @MinLen(2) [] eggFloor = (int @MinLen(2) [] @MinLen(2) [])new int[n + 1][m + 1];
        int result, x;

        for (int i = 1; i <= n; i++) {
            eggFloor[i][0] = 0;   // Zero trial for zero floor.
            eggFloor[i][1] = 1;   // One trial for one floor 
        }

        // j trials for only 1 egg
        /* In the below for loop at lines 24 and 30, the warning cast.unsafe is raised. 
           The warning is raised because the checker is not able to statically verify that the dimension of the array 
           eggfloor is n+1 and m+1. 
           But, it can be easily seen that the cast is safe. 
        */
        for (int j = 1; j <= (@LTLengthOf("eggFloor[1]") int)m; j++)
            eggFloor[1][j] = j;

        // Using bottom-up approach in DP

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= (@LTLengthOf("eggFloor[i]") int)m; j++) {
                eggFloor[i][j] = Integer.MAX_VALUE;
                for (x = 1; x <= j; x++) {
                    /* The line 43 raises the below error: 
                    EggDropping.java:33: error: [expression.unparsable.type.invalid] Expression invalid in dependent type annotation: [error for expression: eggFloor[i-1]; 
                    error: Invalid 'eggFloor[i' because is an invalid expression]
                    result = 1 + Math.max(eggFloor[i - 1][(@IndexFor("eggFloor[i-1]") @NonNegative int)(x - 1)], eggFloor[i][(@NonNegative int)(j - x)]);
                    Therefore the error is suppressed. */
                    
                    // The warning cast.unsafe is suppressed due to the same reason as for the "for loops".
                    
                    /* The error array.access.unsafe.high has below message which cannot be resolved:
                    EggDropping.java:33: error: [array.access.unsafe.high] Potentially unsafe array access: the index could be larger than the array's bound
                                        result = 1 + Math.max(eggFloor[i - 1][(@IndexFor("eggFloor[i-1]") @NonNegative int)(x - 1)], eggFloor[i][(@NonNegative int)(j - x)]);
                                                                              ^
                      found   : @LTLengthOf(value="[error for expression: eggFloor[i-1]; error: Invalid 'eggFloor[i' because is an invalid expression]") int
                      required: @IndexFor("eggFloor[?]") or @LTLengthOf("eggFloor[?]") -- an integer less than eggFloor[?]'s length */
                    result = 1 + Math.max(eggFloor[i - 1][(@IndexFor("eggFloor[i-1]") @NonNegative int)(x - 1)], eggFloor[i][(@NonNegative int)(j - x)]);

                    // choose min of all values for particular x
                    if (result < eggFloor[i][j])
                        eggFloor[i][j] = result;
                }
            }
        }
        /* The below line give cast.unsafe warning as the checker is unable to verify statically that
        the dimension of eggFloor array is (n,m).*/
        return eggFloor[n][(@IndexFor("eggFloor[n]") int)m];
    }

    public static void main(String args[]) {
        int n = 2, m = 4;
        // result outputs min no. of trials in worst case for n eggs and m floors
        int result = minTrials(n, m);
        System.out.println(result);
    }
}
