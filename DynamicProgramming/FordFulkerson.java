package DynamicProgramming;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import org.checkerframework.common.value.qual.*;
import org.checkerframework.checker.index.qual.*;

public class FordFulkerson {
    final static int INF = 987654321;
    // edges
    static @Positive int V;
    static int @MinLen(6) [] @MinLen(6) [] capacity;
    static int[][] flow;

    public static void main(String[] args) {
        System.out.println("V : 6");
        V = 6;
        capacity = new int[V][V];

        capacity[0][1] = 12;
        capacity[0][3] = 13;
        capacity[1][2] = 10;
        capacity[2][3] = 13;
        capacity[2][4] = 3;
        capacity[2][5] = 15;
        capacity[3][2] = 7;
        capacity[3][4] = 15;
        capacity[4][5] = 17;

        System.out.println("Max capacity in networkFlow : " + networkFlow(0, 5));
    }
    /* The cast.unsafe warning is raised in for loops at line 65 and 72. The warning is due to casting @NonNegative to p. 
    The checker cannot statically verify that the variable p is Non-negative and it is difficult to verify that. */
    /* The array.access.unsafe.high warning is raised at line 76. This is false positive as the variable p will have value less than V (size of flow) 
    This also could not be proved statically.*/
    /* I tried to write the below warnings in front of method declarations but still the warnings where raised.*/
    @SuppressWarnings({"array.access.unsafe.high", "cast.unsafe"})
    private static int networkFlow(@NonNegative int source,@NonNegative int sink) {
        flow = new int[V][V];
        int totalFlow = 0;
        while (true) {
            Vector<@GTENegativeOne Integer> parent = new Vector<>(V);
            for (int i = 0; i < V; i++)
                parent.add(-1);
            Queue<@NonNegative Integer> q = new LinkedList<>();
            parent.set(source, source);
            q.add(source);
            while (!q.isEmpty() && parent.get(sink) == -1) {
                /* In the below line, the warning assignment.type.incompatible is raised. 
                The warning is raised because the checker is not able to statically verify that the dimension of the array 
                flow and capacity is (V,V). */
                @SuppressWarnings("assignment.type.incompatible") @IndexFor(value={"DynamicProgramming.FordFulkerson.capacity", "DynamicProgramming.FordFulkerson.flow"}) int here = q.peek();
                q.poll();
                /* In the below line, the warning assignment.type.incompatible is raised. 
                The warning is raised because the checker is not able to statically verify that the dimension of the array 
                flow and capacity is (V,V). */
                @SuppressWarnings("assignment.type.incompatible") @IndexFor(value={"DynamicProgramming.FordFulkerson.capacity[here]", "DynamicProgramming.FordFulkerson.flow[here]"}) int len = V;
                for (int there = 0; there < len; ++there)
                    if (capacity[here][there] - flow[here][there] > 0 && parent.get(there) == -1) {
                        q.add(there);
                        parent.set(there, here);
                    }
            }
            if (parent.get(sink) == -1)
                break;

            int amount = INF;
            String printer = "path : ";
            StringBuilder sb = new StringBuilder();
            @NonNegative int p;
            for (p = sink; p != source; p = (@NonNegative int) parent.get((@NonNegative int )p)) {
                /* In the below line, the warning assignment.type.incompatible is raised. 
                The warning is raised because the checker is not able to statically verify that the dimension of the array 
                flow and capacity is (V,V). */
                @SuppressWarnings("assignment.type.incompatible") @IndexFor(value={"DynamicProgramming.FordFulkerson.capacity", "DynamicProgramming.FordFulkerson.flow"}) int tempindex = parent.get(p);
                /* In the below line, the warning assignment.type.incompatible is raised. 
                The warning is raised because the checker is not able to statically verify that the dimension of the array 
                flow and capacity is (V,V). */
                @SuppressWarnings("assignment.type.incompatible") @IndexFor(value={"DynamicProgramming.FordFulkerson.capacity[tempindex]", "DynamicProgramming.FordFulkerson.flow[tempindex]"}) int tempindex1 = p;
                amount = Math.min(capacity[tempindex][tempindex1] - flow[tempindex][tempindex1], amount);
                sb.append(p + "-");
            }
            sb.append(source);
            for (p = sink; p != source; p = (@NonNegative int) parent.get((@NonNegative int )p)) {
                /* In the below line, the warning assignment.type.incompatible is raised. 
                The warning is raised because the checker is not able to statically verify that the dimension of the array 
                flow and capacity is (V,V). */
                @SuppressWarnings("assignment.type.incompatible") @IndexFor(value={"DynamicProgramming.FordFulkerson.flow[p]", "DynamicProgramming.FordFulkerson.flow"}) int tempindex = parent.get(p);
                /* In the below line, the warning assignment.type.incompatible is raised. 
                The warning is raised because the checker is not able to statically verify that the dimension of the array 
                flow and capacity is (V,V). */                
                @SuppressWarnings("assignment.type.incompatible") @IndexFor("DynamicProgramming.FordFulkerson.flow[tempindex]") int tempindex1 = p;
                flow[tempindex][tempindex1] += amount;
                flow[p][tempindex] -= amount;
            }
            totalFlow += amount;
            printer += sb.reverse() + " / max flow : " + totalFlow;
            System.out.println(printer);
        }

        return totalFlow;
    }
}
