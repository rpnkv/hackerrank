package org.rpnkv.hr.algorithms;

import java.util.*;
import java.util.concurrent.*;

public class QueensAttackII {

    private Set<Obstacle> rowObstacles;
    private Set<Obstacle> colObstacles;

    private int dimension;

    // Complete the queensAttack function below.
    int queensAttack(int dimension, int nObstacles, int r_q, int c_q, int[][] obstacles) throws Exception {
        fillObstacles(nObstacles, obstacles);
        Collection<Callable<Integer>> directions = defineDirections(r_q, c_q);
        this.dimension = dimension;

        int moves = 0;

        ExecutorService es = Executors.newFixedThreadPool(1);
        List<Future<Integer>> futures = es.invokeAll(directions);

        for (Future<Integer> future : futures) {
            moves += future.get();
        }

        return moves;
    }

    private void fillObstacles(int nObstacles, int[][] obstacles) {
        rowObstacles = new HashSet<>();
        colObstacles = new HashSet<>();

        for (int i = 0; i < nObstacles; i++) {
            Obstacle obstacle = new Obstacle(obstacles[i][0], obstacles[i][1]);
            rowObstacles.add(obstacle);
            colObstacles.add(obstacle);
        }

        rowObstacles = Collections.unmodifiableSet(rowObstacles);
        colObstacles = Collections.unmodifiableSet(colObstacles);
    }

    private Collection<Callable<Integer>> defineDirections(int r_q, int c_q) {
        Collection<Callable<Integer>> directions = new ArrayList<>(8);
        MoveResolver moveResolver;

        //Up direction
        moveResolver = new MoveResolver() {
            @Override
            public int nextRow(int currentRow) {
                return currentRow + 1;
            }
        };
        directions.add(new Direction(moveResolver, r_q, c_q, rowObstacles));

        //UpRight direction
        moveResolver = new MoveResolver() {
            @Override
            public int nextRow(int currentRow) {
                return currentRow + 1;
            }

            @Override
            public int nextColumn(int currentColumn) {
                return currentColumn + 1;
            }
        };
        directions.add(new Direction(moveResolver, r_q, c_q, rowObstacles));

        //Right direction
        moveResolver = new MoveResolver() {
            @Override
            public int nextColumn(int currentColumn) {
                return currentColumn + 1;
            }
        };
        directions.add(new Direction(moveResolver, r_q, c_q, colObstacles));

        //RightDown direction
        moveResolver = new MoveResolver() {
            @Override
            public int nextRow(int currentRow) {
                return currentRow - 1;
            }

            @Override
            public int nextColumn(int currentColumn) {
                return currentColumn + 1;
            }
        };
        directions.add(new Direction(moveResolver, r_q, c_q, colObstacles));

        //DownDirection
        moveResolver = new MoveResolver() {
            @Override
            public int nextRow(int currentRow) {
                return currentRow - 1;
            }
        };
        directions.add(new Direction(moveResolver, r_q, c_q, rowObstacles));

        //LeftDown direction
        moveResolver = new MoveResolver() {
            @Override
            public int nextRow(int currentRow) {
                return currentRow - 1;
            }

            @Override
            public int nextColumn(int currentColumn) {
                return currentColumn - 1;
            }
        };
        directions.add(new Direction(moveResolver, r_q, c_q, colObstacles));

        //Left direction
        moveResolver = new MoveResolver() {
            @Override
            public int nextColumn(int currentColumn) {
                return currentColumn - 1;
            }
        };
        directions.add(new Direction(moveResolver, r_q, c_q, colObstacles));

        //TopLeft direction
        moveResolver = new MoveResolver() {
            @Override
            public int nextRow(int currentRow) {
                return currentRow + 1;
            }

            @Override
            public int nextColumn(int currentColumn) {
                return currentColumn - 1;
            }
        };
        directions.add(new Direction(moveResolver, r_q, c_q, colObstacles));

        return directions;
    }

    private class Obstacle {
        public final int r, c;

        public Obstacle(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Obstacle obstacle = (Obstacle) o;
            return r == obstacle.r &&
                    c == obstacle.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }


    private interface MoveResolver {
        default int nextRow(int currentRow) {
            return currentRow;
        }

        default int nextColumn(int currentColumn) {
            return currentColumn;
        }
    }

    class Direction implements Callable<Integer> {
        private final MoveResolver moveResolver;
        private final Set<Obstacle> obstacles;
        private int r, c;

        Direction(MoveResolver moveResolver, int r, int c, Set<Obstacle> obstacles) {
            this.moveResolver = moveResolver;
            this.r = r;
            this.c = c;
            this.obstacles = obstacles;
        }

        boolean inRange() {
            return r > 0 && r <= dimension && c > 0 && c <= dimension;
        }

        @Override
        public Integer call() throws Exception {
            int moves = 0;
            r = moveResolver.nextRow(r);
            c = moveResolver.nextColumn(c);

            while (inRange()){
                if (obstacles.contains(new Obstacle(r, c))) {
                    break;
                } else {
                    moves++;
                    r = moveResolver.nextRow(r);
                    c = moveResolver.nextColumn(c);
                }
            }

            return moves;
        }
    }

}
