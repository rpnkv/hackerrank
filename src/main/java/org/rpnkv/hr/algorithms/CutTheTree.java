package org.rpnkv.hr.algorithms;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class TreeCutter {

    private int totalWeight = 0;
    private Node[] nodes;
    private int nodesCount = 0;

    public TreeCutter(List<Integer> data, List<List<Integer>> edges) {
        nodes = new Node[data.size()];

        for (int i = 0; i < data.size(); i++) {
            nodes[i] = new Node(data.get(i));
            totalWeight += nodes[i].weight;
        }

        for (List<Integer> edge : edges) {
            int v0 = edge.get(0) - 1, v1 = edge.get(1) - 1;

            nodes[v0].addChild(v1);
            nodes[v1].addChild(v0);
        }
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
        System.out.println(nodesCount);
        return smallestDiff();
    }

    private void cutTrees() {
        for (Integer child : nodes[0].children) {
            nodes[child].removeChild(0);
            Stack<Integer> stack = new Stack<>();
            stack.push(child);
            sum(stack);
        }
    }

    private void sum(Stack<Integer> stack) {
        while (!stack.empty()) {
            Integer current = stack.peek();
            if (!nodes[current].children.isEmpty() && !nodes[current].childrenProcessed) {
                pushChildren(current, stack);
            } else {
                stack.pop();
                Node currentNode = nodes[current];
                int childrenSum = 0;
                for (Integer child : currentNode.children) {
                    childrenSum += nodes[child].weight;
                }
                currentNode.weight += childrenSum;
                nodesCount++;
            }
        }
    }

    private void pushChildren(Integer current, Stack<Integer> stack) {
        Node currentNode = nodes[current];
        for (Integer edge : currentNode.children) {
            nodes[edge].removeChild(current);
            stack.push(edge);
        }
        currentNode.childrenProcessed = true;

    }

    private int smallestDiff() {
        int min = Integer.MAX_VALUE, otherTreeWeight, currMin = 0;
        for (int i = 1; i < nodes.length; i++) {
            otherTreeWeight = totalWeight - nodes[i].weight;
            currMin = Math.abs(nodes[i].weight - otherTreeWeight);
            if(currMin < min){
                min = currMin;
            }
        }

        return min;
    }

    private static class Node {
        private int weight;
        public boolean childrenProcessed;
        private Set<Integer> children;

        public Node(int weight) {
            this.weight = weight;
            children = new HashSet<>(3);
        }

        void addChild(Integer index) {
            boolean add = children.add(index);
            if (!add) {
                throw new IllegalStateException("Children already contain edge " + index);
            }
        }

        void removeChild(Integer index) {
            children.remove(index);
        }
    }

}

public class CutTheTree {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("in"));
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

        TreeCutter treeCutter = new TreeCutter(data, edges);
        int diff = treeCutter.cutTheTree();

        bufferedWriter.write(String.valueOf(diff));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
