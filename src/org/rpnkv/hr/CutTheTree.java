package org.rpnkv.hr;

import java.io.*;
import java.util.Arrays;
import java.util.List;

class TreeCutter {

    private int[] weights;
    private boolean inсidence[][];
    private int[][] subtreesWeights;

    public TreeCutter(List<Integer> data, List<List<Integer>> edges) {
        weights = new int[data.size()];
        for (int i = 0; i < data.size(); i++) {
            weights[i] = data.get(i);
        }

        inсidence = new boolean[data.size()][edges.size()];
        for (int i = 0; i < edges.size(); i++) {
            List<Integer> edge = edges.get(i);
            inсidence[edge.get(0)][i] = true;
            inсidence[edge.get(1)][i] = true;
        }

        subtreesWeights = new int[edges.size()][];
    }

    /*
     * Complete the 'cutTheTree' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY data
     *  2. 2D_INTEGER_ARRAY edges
     */

    public int cutTheTree() {
        int totalWeight = totalWeight();



        return 0;
    }

    private int totalWeight() {
        return 0;
    }

    private int cutTrees() {
        int total = weights[0];
        for (int edgeIndex = 0; edgeIndex < inсidence[0].length; edgeIndex++) {
            if (inсidence[0][edgeIndex]) {
                int childIndex = getChildIndex(edgeIndex, 0);
                total += sum(edgeIndex, childIndex);
            }
        }
        return total;
    }

    private int getChildIndex(int edgeIndex, int parentIndex) {
        for (int i = 0; i < weights.length; i++) {
            if (inсidence[i][edgeIndex] && i != parentIndex) {
                return i;
            }
        }
        throw new IllegalArgumentException(String.format("Cannot find second node for edge %d with parent %d",
                edgeIndex, parentIndex));
    }

    private int sum(int parentEdgeIndex, int currentIndex) {
        int childrenWeights = 0;
        for (int edgeIndex = 0; edgeIndex < inсidence[0].length; edgeIndex++) {
            if (inсidence[currentIndex][edgeIndex] && edgeIndex != parentEdgeIndex) {
                int childIndex = getChildIndex(edgeIndex, currentIndex);
                childrenWeights += sum(edgeIndex, childIndex);
            }
        }

        return childrenWeights + weights[currentIndex];
    }

}

public class CutTheTree {
    public static void main(String[] args) throws IOException {
        /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> data = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<List<Integer>> edges = new ArrayList<>();

        IntStream.range(0, n - 1).forEach(i -> {
            try {
                edges.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });*/

        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<List<Integer>> edges = Arrays.asList(
                Arrays.asList(6, 7),
                Arrays.asList(0, 6),
                Arrays.asList(0, 3),
                Arrays.asList(3, 5),
                Arrays.asList(3, 4),
                Arrays.asList(0, 1),
                Arrays.asList(1, 2)
        );

        TreeCutter treeCutter = new TreeCutter(data, edges);
        int diff = treeCutter.cutTheTree();
        System.out.println(diff);

        /*bufferedWriter.write(String.valueOf(treeCutter));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();*/
    }
}
