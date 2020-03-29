package DynamicProgramming;
import org.checkerframework.common.value.qual.*;
import org.checkerframework.checker.index.qual.*;

/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 */
public class CoinChange {

    // Driver Program
    public static void main(String[] args) {

        int amount = 12;
        @Positive int @ArrayLen(3) [] coins = {2, 4, 5};

        System.out.println("Number of combinations of getting change for " + amount + " is: " + change(coins, amount));
        System.out.println("Minimum number of coins required for amount :" + amount + " is: " + minimumCoins(coins, amount));

    }

    /**
     * This method finds the number of combinations of getting change for a given amount and change coins
     *
     * @param coins  The list of coins
     * @param amount The amount for which we need to find the change
     *               Finds the number of combinations of change
     **/
    
    public static int change(@Positive int[] coins,@NonNegative int amount) {
    
        /* Here, the length of the array combinations is positive, as amount will be non-negative. 
        But the checker is unable to verify it statically. So, I have annotated the array to @MinLen(1). 
        The checker raises a warning cast.unsafe, but it can be suppressed as the cast is safe and correct.
        The need of the below annotation is due to the line 36 where 0th index of combinations is accessed. */
        @SuppressWarnings("assignment.type.incompatible") int @MinLen(1) [] combinations = new int[amount + 1];
        combinations[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i < amount + 1; i++) {
                /* Here, the checker cannot statically verify that the i-coin will always be non-negative.
                So, it is safe to cast the i-coin to @NonNegative int. 
                The below line gives assignment.type.incompatible warning but the type is compatible so the warning can be suppressed.*/
                @SuppressWarnings("assignment.type.incompatible") @NonNegative int temp = i - coin;
                combinations[i] += combinations[temp];
            }
            // Uncomment the below line to see the state of combinations for each coin
            // printAmount(combinations);
        }

        return combinations[amount];
    }

    /**
     * This method finds the minimum number of coins needed for a given amount.
     *
     * @param coins  The list of coins
     * @param amount The amount for which we need to find the minimum number of coins.
     *               Finds the the minimum number of coins that make a given value.
     **/
    public static int minimumCoins(@Positive int[] coins,@NonNegative int amount) {

        /* Here, the length of the array minimumCoins is positive, as amount will be non-negative. 
        But the checker is unable to verify it statically. So, I have casted the array to @MinLen(1). 
        The checker raises a warning cast.unsafe, but it can be suppressed as the cast is safe and correct.
        The need of the below cast is due to the below line where 0th index of minimumCoins is accessed. */
        @SuppressWarnings("assignment.type.incompatible") int @MinLen(1)[] minimumCoins = new int[amount + 1];

        minimumCoins[0] = 0;

        for (int i = 1; i <= amount; i++) {
            minimumCoins[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    int sub_res = minimumCoins[i - coin];
                    if (sub_res != Integer.MAX_VALUE && sub_res + 1 < minimumCoins[i])
                        minimumCoins[i] = sub_res + 1;
                }
            }
        }
        // Uncomment the below line to see the state of combinations for each coin
        //printAmount(minimumCoins);
        return minimumCoins[amount];
    }

    // A basic print method which prints all the contents of the array
    public static void printAmount(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}