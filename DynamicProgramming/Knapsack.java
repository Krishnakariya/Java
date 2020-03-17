package DynamicProgramming;
import org.checkerframework.common.value.qual.*;
import org.checkerframework.checker.index.qual.*;
/**
 * A DynamicProgramming based solution for 0-1 Knapsack problem
 */

public class Knapsack {
    @SuppressWarnings({"cast.unsafe", "array.access.unsafe.high", "expression.unparsable.type.invalid"})
    /*Knapsack.java:31: error: [expression.unparsable.type.invalid] Expression invalid in dependent type annotation: [error for expression: wt[?]; error: Invalid 'wt[?]' because is an invalid expression]
                    rv[i][w] = Math.max(val[(@IndexFor("val") int)(i - 1)] + rv[i - 1][(@Positive int)(w - wt[(@NonNegative @IndexFor("wt") int)(i - 1)])], rv[i - 1][w]);
                                                                                        ^
        1 error
    The above error cannot be resolved. */
    /* The cast.unsafe warning is raised for the same reasons as in other files.*/
    /* The error array.access.unsafe.high has the following message, which cannot be resolved.
    Knapsack.java:25: error: [array.access.unsafe.high] Potentially unsafe array access: the index could be larger than the array's bound
                        rv[i][w] = Math.max(val[(@IndexFor("val") int)(i - 1)] + rv[i - 1][(@Positive int)(w - wt[(@NonNegative @IndexFor("wt") int)(i - 1)])], rv[i - 1][w]);
                                                                                           ^
      found   : @LTLengthOf(value={"rv[i]", "rv[i]"}, offset={"0", "[error for expression: wt[?]; error: Invalid 'wt[?]' because is an invalid expression]"}) int
      required: @IndexFor("rv[?]") or @LTLengthOf("rv[?]") -- an integer less than rv[?]'s length
    */
    private static int knapSack(@Positive int W,@Positive int wt[],@Positive @SameLen("#2") int val[], @NonNegative @SameLen("#2") int n) throws IllegalArgumentException {
        if(wt == null || val == null)
            throw new IllegalArgumentException();
        int i, w;
        int rv[][] = (int @MinLen(1) [] @MinLen(1) [])new int[n + 1][W + 1];    //rv means return value

        // Build table rv[][] in bottom up manner
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= (@LTLengthOf("rv[i]") int ) W; w++) {
                if (i == 0 || w == 0)
                    rv[i][w] = 0;
                else if (wt[(@IndexFor("wt") int)(i - 1)] <= w)
                    rv[i][w] = Math.max(val[(@IndexFor("val") int)(i - 1)] + rv[i - 1][(@Positive int)(w - wt[(@NonNegative @IndexFor("wt") int)(i - 1)])], rv[i - 1][w]);
                else
                    rv[i][w] = rv[i - 1][w];
            }
        }

        return rv[n][(@IndexFor("rv[n]") int)W];
    }

    @SuppressWarnings({"argument.type.incompatible", "cast.unsafe"})
    // Driver program to test above function
    public static void main(String args[]) {
        @Positive int val @ArrayLen(3) [] = new int[]{50, 100, 130};
        @Positive int wt @ArrayLen(3) [] = new int[]{10, 20, 40};
        int W = 50;
        int n = val.length;
        /* The below line generates cast.unsafe and argument.type.incompatible as 
        the checker cannot verify statically that the length of wt and val arrays is same.*/
        System.out.println(knapSack(W, wt, (@Positive  @SameLen("wt") int[]) val, (@NonNegative @LengthOf(value={"wt", "val"}) int) n));
    }
}
