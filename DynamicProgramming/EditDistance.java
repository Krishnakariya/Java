package DynamicProgramming;
import org.checkerframework.common.value.qual.*;
import org.checkerframework.checker.index.qual.*;
/**
 * A DynamicProgramming based solution for Edit Distance problem In Java
 * Description of Edit Distance with an Example:
 * <p>
 * Edit distance is a way of quantifying how dissimilar two strings (e.g., words) are to one another,
 * by counting the minimum number of operations required to transform one string into the other. The
 * distance operations are the removal, insertion, or substitution of a character in the string.
 * <p>
 * <p>
 * The Distance between "kitten" and "sitting" is 3. A minimal edit script that transforms the former into the latter is:
 * <p>
 * kitten → sitten (substitution of "s" for "k")
 * sitten → sittin (substitution of "i" for "e")
 * sittin → sitting (insertion of "g" at the end).
 *
 * @author SUBHAM SANGHAI
 **/

import java.util.Scanner;

public class EditDistance {

    @SuppressWarnings({"cast.unsafe", "array.access.unsafe.high"})

    public static int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        // len1+1, len2+1, because finally return dp[len1][len2]
        /* Here, the dimensions of the array dp are positive, because len1 and len2 are length of strings which is always non-negative. 
        But the checker is unable to verify it statically. So, I have casted the array to @MinLen(1). 
        The checker raises a warning cast.unsafe, but it can be suppressed as the cast is safe and correct.
        The need of the cast is due to the below line where dp[0] and dp[i][0] is accessed. */
        int @MinLen(1) [] @MinLen(1) [] dp = (int @MinLen(1) [] @MinLen(1) [])new int[len1 + 1][len2 + 1];
     	/* If second string is empty, the only option is to
   	  insert all characters of first string into second*/
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
     	/* If first string is empty, the only option is to
   	  insert all characters of second string into first*/
        for (int j = 0; j <= len2; j++) {
            /*
            The below line gives cast.unsafe warning for casting index j to @IndexFor("dp[0]").
            This happens as the checker is unable to verify statically that the range of j is from 0 to len2 as well as 
            that the length of array dp[0] is len2+1.
            This casting is safe because the length of dp[0] array is len2+1 and index j has range from 0 to len2.
            So, it is safe to suppress this warning.*/
            dp[0][ (@IndexFor("dp[0]") int) j] = j;
        }
        //iterate though, and check last char
        for (int i = 0; i < len1; i++) {
            char c1 = word1.charAt(i);
            for (int j = 0; j < len2; j++) {
                char c2 = word2.charAt(j);
                //if last two chars equal
                if (c1 == c2) {
                    //update dp value for +1 length
                    /* The below Line produces array.access.unsafe.high error because the checker is unable to statically verify that j+1 is less than length of the array "dp[i+1]"
                       Here, the length of the array dp[i+1] is len2+1 and the range of the index j is from 0 to len2-1. 
                       So, it is safe to suppress this error.*/
                    /*
                       The below line gives cast.unsafe warning for casting index j to @IndexFor("dp[i]").
                       This happens as the checker is unable to verify statically that the range of j is from 0 to len2-1 as well as 
                        that the length of array dp[i] is len2+1.
                       This casting is safe because the length of dp[i] array is len2+1 and index j has range from 0 to len2-1.
                       So, it is safe to suppress this warning.
                    */
                    dp[i + 1] [j + 1] = dp[i][(@IndexFor("dp[i]") int)j];
                } else {
			/* if two characters are different ,
			then take the minimum of the various operations(i.e insertion,removal,substitution)*/
                    /* The below lines 76 and 77 produces the warning cast.unsafe with same reason as line 72. */
                    int replace = dp[i][(@IndexFor("dp[i]") int)j] + 1;
                    int insert = dp[i][(@IndexFor("dp[i]") int)(j + 1)] + 1;
                    /* The below lines 84 and 88 gives array.access.unsafe.high error and the checker message as
                    Potentially unsafe array access: the index could be larger than the array's bound
                    int delete = dp[i + 1][j] + 1;
                                           ^
                    found   : @LTLengthOf(value="word2", offset="0") int
                    required: @IndexFor("dp[?]") or @LTLengthOf("dp[?]") -- an integer less than dp[?]'s length */
                    int delete = dp[i + 1][j] + 1;

                    int min = replace > insert ? insert : replace;
                    min = delete > min ? min : delete;
                    dp[i + 1][j + 1] = min;
                }
            }
        }
        /* return the final answer , after traversing through both the strings*/
        /* The below line give array.access.unsafe.high error as the checker is unable to verify statically that
        the dimension of dp array is (len1+1,len2+1).*/
        return dp[len1][len2];
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s1, s2;
        System.out.println("Enter the First String");
        s1 = input.nextLine();
        System.out.println("Enter the Second String");
        s2 = input.nextLine();
        //ans stores the final Edit Distance between the two strings
        int ans = minDistance(s1, s2);
        System.out.println("The minimum Edit Distance between \"" + s1 + "\" and \"" + s2 + "\" is " + ans);
        input.close();
    }
}
