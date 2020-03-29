package DynamicProgramming;
import org.checkerframework.common.value.qual.*;
import org.checkerframework.checker.index.qual.*;
/**
 * @author Kshitij VERMA (github.com/kv19971)
 * LEVENSHTEIN DISTANCE dyamic programming implementation to show the difference between two strings (https://en.wikipedia.org/wiki/Levenshtein_distance)
 */

public class LevenshteinDistance {
    private static int minimum(int a, int b, int c) {
        if (a < b && a < c) {
            return a;
        } else if (b < a && b < c) {
            return b;
        } else {
            return c;
        }
    }

    private static int calculate_distance(String a, String b) {
        @Positive int len_a = a.length() + 1;
        @Positive int len_b = b.length() + 1;
        /* In the below line, the warning assignment.type.incompatible is raised. 
           The warning is raised because the checker is not able to statically verify that the dimension of the array 
           distance_mat is (len_a,len_b) where len_a, len_b is greater than 1. */
        @SuppressWarnings("assignment.type.incompatible") int @MinLen(2) [] @MinLen(2)[] distance_mat = new int[len_a][len_b];
        for (int i = 0; i < len_a; i++) {
            distance_mat[i][0] = i;
        }
        /* In the below line, the warning assignment.type.incompatible is raised. 
           The warning is raised because the checker is not able to statically verify that the dimension of the array 
           distance_mat is (len_a,len_b) 
           A very important point is that the the checker is not able to process that for a 2-dimensional array distance_mat 
           len(distance_mat[i]) == len(distance_mat[0]). Because of this, I have to annotate it every time whenever a new index is used. */
        @SuppressWarnings("assignment.type.incompatible") @LTEqLengthOf("distance_mat[0]") int temp_len = len_b;
        for (int j = 0; j < temp_len; j++) {
            distance_mat[0][j] = j;
        }
        /* In the below line, the warning assignment.type.incompatible is raised. 
       The warning is raised because the checker is not able to statically verify that the dimension of the array 
       distance_mat is (len_a,len_b) */ 
        @SuppressWarnings("assignment.type.incompatible") @LTEqLengthOf(value={"distance_mat", "a"}) int temp_len1 = len_a;
        for (int i = 1; i < temp_len1; i++) {
            int temp_index_1 = i-1;
            /* In the below line, the warning assignment.type.incompatible is raised. 
            The warning is raised because the checker is not able to statically verify that the dimension of the array 
            distance_mat is (len_a,len_b).*/
            @SuppressWarnings("assignment.type.incompatible") @LTEqLengthOf(value={"distance_mat[i]", "distance_mat[temp_index_1]", "b"}) int temp_len2 = len_b;
            for (int j = 1; j < temp_len2; j++) {
                int cost;
                if (a.charAt(i) == b.charAt(j)) {
                    cost = 0;
                } else {
                    cost = 1;
                }
                /* In the below line, the warning assignment.type.incompatible is raised. 
                The warning is raised because the checker is not able to statically verify that the dimension of the array 
                distance_mat is (len_a,len_b).*/
                @SuppressWarnings("assignment.type.incompatible") @IndexFor(value={"distance_mat[i]", "distance_mat[temp_index_1]"}) int temp_index_2 = j-1;
                distance_mat[i][j] = minimum(distance_mat[temp_index_1][j], distance_mat[temp_index_1][temp_index_2], distance_mat[i][temp_index_2]) + cost;


            }

        }
        /* In the below line, the warning assignment.type.incompatible is raised. 
        The warning is raised because the checker is not able to statically verify that the dimension of the array 
        distance_mat is (len_a,len_b).*/
        int temp_index_3 = len_a - 1;
        @SuppressWarnings("assignment.type.incompatible") @IndexFor("distance_mat[temp_index_3]") int temp_index_4 = len_b - 1;
        return distance_mat[temp_index_3][temp_index_4];

    }

    public static void main(String[] args) {
        String a = ""; // enter your string here
        String b = ""; // enter your string here

        System.out.print("Levenshtein distance between " + a + " and " + b + " is: ");
        System.out.println(calculate_distance(a, b));


    }
}
