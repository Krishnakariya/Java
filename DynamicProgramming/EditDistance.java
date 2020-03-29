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


    public static int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        // len1+1, len2+1, because finally return dp[len1][len2]
        /* Here, the dimensions of the array dp are positive, because len1 and len2 are length of strings which is always non-negative. 
        But the checker is unable to verify it statically. So, I have casted the array to @MinLen(1). 
        The checker raises a warning assignment.type.incompatible, but it can be suppressed as the cast is safe and correct.
        The need of the annotation is due to the below line where dp[0] and dp[i][0] is accessed. */
        @SuppressWarnings("assignment.type.incompatible") int @MinLen(1) [] @MinLen(1) [] dp = new int[len1 + 1][len2 + 1];
     	/* If second string is empty, the only option is to
   	  insert all characters of first string into second*/
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
     	/* If first string is empty, the only option is to
   	  insert all characters of second string into first*/
        /* The below line gives cast.unsafe warning for annotating index j to @LTLengthOf("dp[0]").
        This happens as the checker is unable to verify statically that the range of j is from 0 to len2 as well as 
        that the length of array dp[0] is len2+1.
        So, it is safe to suppress this warning.*/
        @SuppressWarnings("assignment.type.incompatible") @LTLengthOf("dp[0]") int temp = len2;
        for (int j = 0; j <= temp; j++) {
            dp[0][j] = j;
        }
        //iterate though, and check last char
        for (int i = 0; i < len1; i++) {
            char c1 = word1.charAt(i);
            int tempindex = i+1;

            /* The below line gives assignment.type.incompatible warning for annotating index j to @LTLengthOf("dp[i]").
            This happens as the checker is unable to verify statically that the range of j is from 0 to len2-1 as well as 
            that the length of array dp[i] and dp[tempindex] is len2+1.
            So, it is safe to suppress this warning.*/
            @SuppressWarnings("assignment.type.incompatible") @LTLengthOf({"dp[i]", "dp[tempindex]"}) int temp2 = len2;
            for (int j = 0; j < temp2; j++) {
                char c2 = word2.charAt(j);
                /* The below line gives assignment.type.incompatible warning for annotating index j to @LTLengthOf("dp[tempindex]").
                This happens as the checker is unable to verify statically that the range of j is from 0 to len2-1 as well as 
                that the length of array dp[tempindex] is len2+1.
                So, it is safe to suppress this warning.*/
                @SuppressWarnings("assignment.type.incompatible") @LTLengthOf("dp[tempindex]") int tempindex2 = j+1;
                //if last two chars equal
                if (c1 == c2) {
                    //update dp value for +1 length
                    dp[tempindex][tempindex2] = dp[i][j];
                } else {
			/* if two characters are different ,
			then take the minimum of the various operations(i.e insertion,removal,substitution)*/
                    int replace = dp[i][j] + 1;
                    int insert = dp[i][tempindex2] + 1;
                    int delete = dp[tempindex][j] + 1;

                    int min = replace > insert ? insert : replace;
                    min = delete > min ? min : delete;
                    dp[tempindex][tempindex2] = min;
                }
            }
        }
        /* return the final answer , after traversing through both the strings*/
         /* The below line gives assignment.type.incompatible warning for annotating index j to @LTLengthOf("dp[len1]").
        This happens as the checker is unable to verify statically that the range of j is from 0 to len2-1 as well as 
        that the length of array dp[len1] is len2+1.
        So, it is safe to suppress this warning.*/
        @SuppressWarnings("assignment.type.incompatible") @LTLengthOf("dp[len1]") int temp3 = len2;
        return dp[len1][temp3];
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
