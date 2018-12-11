package org.rpnkv.hr;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class TreeCutter {

    private int[] weights;
    private final List<List<Integer>> edges;
    private boolean inсidence[][];
    private int[][] subtreesWeights;
    private int totalWeight = 0;

    public TreeCutter(List<Integer> data, List<List<Integer>> edges) {
        weights = new int[data.size()];
        this.edges = edges;
        for (int i = 0; i < data.size(); i++) {
            weights[i] = data.get(i);
            totalWeight += weights[i];
        }

        inсidence = new boolean[data.size()][edges.size()];
        for (int i = 0; i < edges.size(); i++) {
            List<Integer> edge = edges.get(i);
            inсidence[edge.get(0) - 1][i] = true;
            inсidence[edge.get(1) - 1][i] = true;
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
        cutTrees();

        return smallestDiff();
    }

    private void cutTrees() {
        for (int edgeIndex = 0; edgeIndex < inсidence[0].length; edgeIndex++) {
            if (inсidence[0][edgeIndex]) {
                int childIndex = getChildIndex(edgeIndex, 0);
                sum(edgeIndex, childIndex);
                int edgeSum = subtreesWeights[edgeIndex][0];
                subtreesWeights[edgeIndex][1] = totalWeight - edgeSum;
            }
        }
    }

    private int getChildIndex(int edgeIndex, int parentIndex) {
        /*for (int i = 0; i < weights.length; i++) {
            if (inсidence[i][edgeIndex] && i != parentIndex) {
                return i;
            }
        }
        throw new IllegalArgumentException(String.format("Cannot find second node for edge %d with parent %d",
                edgeIndex, parentIndex));*/
        List<Integer> integers = edges.get(edgeIndex);
        if(integers.get(0) == parentIndex){
            return integers.get(1);
        }else {
            return integers.get(0);
        }
    }

    private void sum(int parentEdgeIndex, int currentIndex) {
        int childrenWeights = 0;
        for (int edgeIndex = 0; edgeIndex < inсidence[0].length; edgeIndex++) {
            if (inсidence[currentIndex][edgeIndex] && edgeIndex != parentEdgeIndex) {
                int childIndex = getChildIndex(edgeIndex, currentIndex);
                sum(edgeIndex, childIndex);
                Integer subtreeWeight = subtreesWeights[edgeIndex][0];
                subtreesWeights[edgeIndex][1] = totalWeight - subtreeWeight;


                childrenWeights += subtreeWeight;
            }
        }

        if (subtreesWeights[parentEdgeIndex] != null) {
            throw new IllegalStateException("Edge " + parentEdgeIndex + " has already been modified");
        } else {
            subtreesWeights[parentEdgeIndex] =
                    new int[]{childrenWeights + weights[currentIndex], 0};
        }
    }


    private int smallestDiff() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < subtreesWeights.length; i++) {
            int currentDiff = Math.abs(subtreesWeights[i][0] - subtreesWeights[i][1]);
            if (currentDiff < min) {
                min = currentDiff;
            }
        }
        return min;
    }
}

public class CutTheTree {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("ctt.in"));
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
        });
        /*List<Integer> data = Arrays.asList(1, 2, 4, 4, 5, 6, 7, 9);
        List<List<Integer>> edges = Arrays.asList(
                Arrays.asList(7, 8),
                Arrays.asList(1, 7),
                Arrays.asList(1, 4),
                Arrays.asList(4, 6),
                Arrays.asList(4, 5),
                Arrays.asList(1, 2),
                Arrays.asList(2, 3)
        );*/

        TreeCutter treeCutter = new TreeCutter(data, edges);
        int diff = treeCutter.cutTheTree();
        /*System.out.println(diff);*/

        bufferedWriter.write(String.valueOf(diff));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
