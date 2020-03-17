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
    @SuppressWarnings({"cast.unsafe", "array.access.unsafe.high"})
    /* The cast.unsafe warning is raised for the same reasons as in other files.*/
    /* The error array.access.unsafe.high is raised at some lines in the following way which cannot be resolved. 
    FordFulkerson.java:62: error: [array.access.unsafe.high] Potentially unsafe array access: the index could be larger than the array's bound
                amount = Math.min(capacity[(@NonNegative @IndexFor("DynamicProgramming.FordFulkerson.capacity") int)parent.get(p)][p] - flow[(@NonNegative @IndexFor("DynamicProgramming.FordFulkerson.flow") int)parent.get(p)][p], amount);
                                                                                                                                   ^
        found   : @UpperBoundUnknown int
        required: @IndexFor("DynamicProgramming.FordFulkerson.capacity[?]") or @LTLengthOf("DynamicProgramming.FordFulkerson.capacity[?]") -- an integer less than DynamicProgramming.FordFulkerson.capacity[?]'s length
    */
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
                int here = (@LTLengthOf(value={"DynamicProgramming.FordFulkerson.capacity", "DynamicProgramming.FordFulkerson.flow"}) int)q.peek();
                q.poll();
                for (int there = 0; there < (@LTLengthOf(value={"DynamicProgramming.FordFulkerson.capacity[here]", "DynamicProgramming.FordFulkerson.flow[here]"}) int)V; ++there)
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
            for (@NonNegative int p = sink; p != source; p = (@NonNegative int) parent.get((@NonNegative int )p)) {
                amount = Math.min(capacity[(@NonNegative @IndexFor("DynamicProgramming.FordFulkerson.capacity") int)parent.get(p)][p] - flow[(@NonNegative @IndexFor("DynamicProgramming.FordFulkerson.flow") int)parent.get(p)][p], amount);
                sb.append(p + "-");
            }
            sb.append(source);
            for (@NonNegative int p = sink; p != source; p = (@NonNegative int) parent.get((@NonNegative int )p)) {
                flow[(@IndexFor("DynamicProgramming.FordFulkerson.flow") @NonNegative int)parent.get(p)][p] += amount;
                flow[(@IndexFor("DynamicProgramming.FordFulkerson.flow") int)p][(@IndexFor("DynamicProgramming.FordFulkerson.flow[p]") @NonNegative int)parent.get(p)] -= amount;
            }
            totalFlow += amount;
            printer += sb.reverse() + " / max flow : " + totalFlow;
            System.out.println(printer);
        }

        return totalFlow;
    }
}
