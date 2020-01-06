package LaiOffer;

import javafx.scene.control.Cell;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yuehu on 12/23/19.
 * Assumptions:
 * 1) matrix is not null, N * M where N > 0 and M > 0
 * 2) k > 0 and k <= N * M
 */
public class KSmallestInSortedMatrix {
    static class Cell {
        int row;
        int column;
        int value;

        Cell(int row, int column, int value) {
            this.row = row;
            this.column = column;
            this.value = value;
        }
    }
    public int kthsmallest(int[][] matrix, int k) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        // Best First Search, need a minheap on the value of each cells.
        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>() {
            @Override
            public int compare(Cell c1, Cell c2) {
                if (c1.value == c2.value) {
                    return 0;
                }
                return c1.value < c2.value ? -1 : 1;
            }
        });
        // all the generated cells will be marked true,
        // to avoid being generated more than once
        boolean[][] visited = new boolean[rows][columns];
        minHeap.offer(new Cell(0,0, matrix[0][0]));
        visited[0][0] = true;
        // iterate k-1 rounds, and Best First Search the smallest k-1 cells
            for (int i = 0; i < k - 1; i++) {
                Cell cur = minHeap.poll();
            // the neighbor cell will be inserted back to the minHeap only if
            // 1. it is not out of boundary
            // 2. it has not been generated before
            // Because for each cell it could be generated twice
            if (cur.row + 1 < rows && !visited[cur.row + 1][cur.column]) {
                minHeap.offer(new Cell(cur.row + 1, cur.column, matrix[cur.row + 1][cur.column]));
                visited[cur.row + 1][cur.column] = true;
            }
            if (cur.column + 1 < columns && !visited[cur.row][cur.column + 1]) {
                minHeap.offer(new Cell(cur.row, cur.column + 1, matrix[cur.row][cur.column + 1]));
                visited[cur.row][cur.column + 1] = true;
            }
        }
        return minHeap.peek().value;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{10,11,12,14}};
        KSmallestInSortedMatrix s = new KSmallestInSortedMatrix();
        System.out.println(s.kthsmallest(matrix,9));
    }
}
