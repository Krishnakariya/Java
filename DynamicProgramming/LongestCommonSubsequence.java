package DynamicProgramming;
import org.checkerframework.common.value.qual.*;
import org.checkerframework.checker.index.qual.*;

class LongestCommonSubsequence {
    // @SuppressWarnings({"assignment.type.incompatible", "cast.unsafe", "array.access.unsafe.high"})
    public static String getLCS(String str1, String str2) {

        //At least one string is null
        if (str1 == null || str2 == null)
            return null;

        //At least one string is empty
        if (str1.length() == 0 || str2.length() == 0)
            return "";

        String [] arr1 = str1.split("");
        String [] arr2 = str2.split("");

        //lcsMatrix[i][j]  = LCS of first i elements of arr1 and first j characters of arr2
        /* In the below line, the warning assignment.type.incompatible is raised. 
           The warning is raised because the checker is not able to statically verify that the dimension of the array 
           lcsMatrix is (arr1.length+1,arr2.length+1). */
        @SuppressWarnings("assignment.type.incompatible") int @MinLen(2) [] @MinLen(2)[] lcsMatrix = new int[arr1.length + 1][arr2.length + 1];

        for (int i = 0; i < arr1.length + 1; i++)
            lcsMatrix[i][0] = 0;
        /* In the below line, the warning assignment.type.incompatible is raised. 
           The warning is raised because the checker is not able to statically verify that the dimension of the array 
           lcsMatrix is (arr1.length+1,arr2.length+1). */        
        @SuppressWarnings("assignment.type.incompatible") @LTLengthOf("lcsMatrix[0]") int temp_len = arr2.length + 1;
        for (int j = 1; j < temp_len; j++)
            lcsMatrix[0][j] = 0;
        /* In the below line, the warning assignment.type.incompatible is raised. 
           The warning is raised because the checker is not able to statically verify that the dimension of the array 
           lcsMatrix is (arr1.length+1,arr2.length+1). */        
        @SuppressWarnings("assignment.type.incompatible") @LTLengthOf(value={"lcsMatrix", "arr1"}) int temp_len_1 = arr1.length + 1;
        for (int i = 1; i < temp_len_1 ; i++) {
            /* In the below line, the warning assignment.type.incompatible is raised. 
               The warning is raised because the checker is not able to statically verify that the dimension of the array 
               lcsMatrix is (arr1.length+1,arr2.length+1). */
            @SuppressWarnings("assignment.type.incompatible") @IndexFor("arr1") int temp_index_1 = i-1;
            /* In the below line, the warning assignment.type.incompatible is raised. 
               The warning is raised because the checker is not able to statically verify that the dimension of the array 
               lcsMatrix is (arr1.length+1,arr2.length+1). */            
            @SuppressWarnings("assignment.type.incompatible") @LTLengthOf(value={"lcsMatrix[i]", "arr2", "lcsMatrix[temp_index_1]"}) int temp_len_2 = arr2.length + 1;
            for (int j = 1; j < temp_len_2; j++) {
            /* In the below line, the warning assignment.type.incompatible is raised. 
               The warning is raised because the checker is not able to statically verify that the dimension of the array 
               lcsMatrix is (arr1.length+1,arr2.length+1). */
                @SuppressWarnings("assignment.type.incompatible") @IndexFor(value={"arr2", "lcsMatrix[temp_index_1]"}) int temp_index_2 = j-1;
                if (arr1[temp_index_1].equals(arr2[temp_index_2])) {
                    lcsMatrix[i][j] = lcsMatrix[temp_index_1][temp_index_2] + 1;
                } else {
                    lcsMatrix[i][j] = lcsMatrix[temp_index_1][j] > lcsMatrix[i][temp_index_2] ? lcsMatrix[temp_index_1][j] : lcsMatrix[i][temp_index_2];
                }
            }
        }
        return lcsString(str1, str2, lcsMatrix);
    }

    public static String lcsString(String str1, String str2, int [][] lcsMatrix) {
        StringBuilder lcs = new StringBuilder();
        int i = str1.length(),
                j = str2.length();
        while (i > 0 && j > 0) {
            /* In the below line, the warning assignment.type.incompatible is raised. 
               The warning is raised because the checker is not able to statically verify that the dimension of the array 
               lcsMatrix is (str1.length+1,str2.length+1). 
               This is a while loop so it is not possible for compiler to figure out that i,j are indices. */            
            @SuppressWarnings("assignment.type.incompatible") @IndexFor(value={"lcsMatrix", "str1"}) int temp_index_1 = i-1;
            @SuppressWarnings("assignment.type.incompatible") @IndexFor(value={"lcsMatrix[temp_index_1]"}) int temp_index_3 = j;
            @SuppressWarnings("assignment.type.incompatible") @IndexFor(value={"lcsMatrix"}) int temp_index_4 = i;
            @SuppressWarnings("assignment.type.incompatible") @IndexFor(value={"lcsMatrix[temp_index_4]", "str2"}) int temp_index_2 = j-1;
            if (str1.charAt(temp_index_1) == str2.charAt(temp_index_2)) {
                lcs.append(str1.charAt(temp_index_1));
                i--;
                j--;
            } else if (lcsMatrix[temp_index_1][temp_index_3] > lcsMatrix[temp_index_4][temp_index_2]) {
                i--;
            } else {
                j--;
            }
        }
        return lcs.reverse().toString();
    }

    public static void main(String[] args) {
        String str1 = "DSGSHSRGSRHTRD";
        String str2 = "DATRGAGTSHS";
        String lcs = getLCS(str1, str2);

        //Print LCS
        if (lcs != null) {
            System.out.println("String 1: " + str1);
            System.out.println("String 2: " + str2);
            System.out.println("LCS: " + lcs);
            System.out.println("LCS length: " + lcs.length());
        }
    }
}