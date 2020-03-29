package DynamicProgramming;
import org.checkerframework.common.value.qual.*;
import org.checkerframework.checker.index.qual.*;
/**
 * A DynamicProgramming based solution for 0-1 Knapsack problem
 */

public class Knapsack {
    private static int knapSack(@Positive int W,@Positive int wt[],@Positive @SameLen("#2") int val[], @NonNegative @SameLen("#2") int n) throws IllegalArgumentException {
        if(wt == null || val == null)
            throw new IllegalArgumentException();
        int i, w;
        /* In the below line, the warning assignment.type.incompatible is raised. 
           The warning is raised because the checker is not able to statically verify that the dimension of the array 
           rv is (n+1,W+1). */
        @SuppressWarnings("assignment.type.incompatible") int rv @MinLen(1) [] @MinLen(1) [] = new int[n + 1][W + 1];    //rv means return value

        // Build table rv[][] in bottom up manner
        /* In the below line, the warning assignment.type.incompatible is raised. 
        The warning is raised because the checker is not able to statically verify that the dimension of the array 
        rv is (n+1,W+1). */
        @SuppressWarnings("assignment.type.incompatible") @LTLengthOf("rv") int temp_len1 = n;
        for (i = 0; i <= temp_len1; i++) {
            /* In the below line, the warning assignment.type.incompatible is raised. 
            The warning is raised because the checker is not able to statically verify that the dimension of the array 
            rv is (n+1,W+1). */
            @SuppressWarnings("assignment.type.incompatible") @LTLengthOf(value={"rv[i]"}) int temp_len2 = W;
            for (w = 0; w <= temp_len2; w++) {
                /* In the below line, the warning assignment.type.incompatible is raised. 
                The warning is raised because the checker is not able to statically verify that the dimension of the array 
                wt and val is n as well as that the index i is in range 0 to n. Note that the index could be -1 but such an index 
                will never be accessed due to the if - else if- else condition below. */
                @SuppressWarnings("assignment.type.incompatible") @IndexFor(value={"wt", "val"}) int temp_index = i-1;
                if (i == 0 || w == 0)
                    rv[i][w] = 0;
                else if (wt[temp_index] <= w) {
                    /*In the below line, the warning assignment.type.incompatible is raised. 
                    The warning is raised because the checker is not able to statically verify that value is positive and less than W. 
                    In the next line the warning about temp_index2 to be negative should not be raised becuase at run time the below code is 
                    executed once the else if condition is true. */
                    @SuppressWarnings("assignment.type.incompatible") @IndexFor("rv[temp_index]") int temp_index2 = w - wt[temp_index];
                    /* In the below line, the warning assignment.type.incompatible is raised. 
                    The warning is raised because the checker is not able to statically verify that the dimension of the array 
                    rv is (n+1,W+1). */
                    @SuppressWarnings("assignment.type.incompatible") @IndexFor("rv[temp_index]") int temp_index3 = w;
                    rv[i][w] = Math.max(val[temp_index] + rv[temp_index][temp_index2], rv[temp_index][temp_index3]);
                }
                else{
                    /* In the below line, the warning assignment.type.incompatible is raised. 
                    The warning is raised because the checker is not able to statically verify that the dimension of the array 
                    rv is (n+1,W+1). */
                    @SuppressWarnings("assignment.type.incompatible") @IndexFor("rv[temp_index]") int temp_index3 = w;
                    rv[i][w] = rv[temp_index][temp_index3];
                }
            }
        }
        /* In the below line, the warning assignment.type.incompatible is raised. 
        The warning is raised because the checker is not able to statically verify that the dimension of the array 
        rv is (n+1,W+1). */
        @SuppressWarnings("assignment.type.incompatible") @IndexFor("rv[n]") int temp_index4 = W;
        return rv[n][temp_index4];
    }

    // Driver program to test above function
    public static void main(String args[]) {
        @Positive int val @ArrayLen(3) [] = new int[]{50, 100, 130};
        /* In the below line, the warning assignment.type.incompatible is raised. 
        The warning is raised because the checker is not able to statically verify that the dimension of the array 
        val is same as wt even if ArrayLen(3) is annotated. */
        @SuppressWarnings("assignment.type.incompatible") @Positive @SameLen("val") int wt  @ArrayLen(3) [] = new int[]{10, 20, 40};
        int W = 50;
        int n = val.length;
        /* In the below line, the warning assignment.type.incompatible is raised. 
        The warning is raised because the checker is not able to statically verify that the dimension of the array 
        val is same as wt even when we know that ArrayLen(3) is annotated to both arrays and n=3 (can be computed statically). */
        @SuppressWarnings("assignment.type.incompatible") @NonNegative @LengthOf(value={"wt", "val"}) int temp_len = n;
        /* The below line generates cast.unsafe and argument.type.incompatible as 
        the checker cannot verify statically that the length of wt and val arrays is same statically which could be done easily.*/
        @SuppressWarnings("argument.type.incompatible") int ans = knapSack(W, wt, (@Positive  @SameLen("wt") int[]) val, temp_len);
        System.out.println(ans);
    }
}
