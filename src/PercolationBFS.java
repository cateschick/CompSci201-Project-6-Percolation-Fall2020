// Cate Schick
// CompSci 201
// Project 6: Percolation
// Filename: PercolationBFS.java

import java.util.*;
public class PercolationBFS extends PercolationDFSFast {
    // initialize constructor

    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationBFS(int n) {
        super(n);
    }

    // override dfs to use a queue

    /**
     * @param row coordinate of the cell being examined
     * @param col Row,col added to queue
     *            While queue not empty, removes an element from the queue
     *            checks the neighbor of that element
     *            If the neighbor should be filled, fill it and add to queue
     */
    @Override
    protected void dfs(int row, int col) {
        // out of bounds check
        if (!inBounds(row, col)) {
            return;
        }

        // helper arrays to check neighbors
        int[] rDelta = {-1, 1, 0, 0};
        int[] cDelta = {0, 0, -1, 1};

        // not part of what we are looking for
        if (myGrid[row][col] != OPEN) {
            return;
        }
        // create queue
        Queue<Integer> q = new LinkedList<>();
        // fill cell
        myGrid[row][col] = FULL;

        // put cell on queue
        q.add(h(row, col));

        // dequeue a cell
        while (q.size() != 0) {
            int remove = q.remove();
            for (int k = 0; k < rDelta.length; k++) {
                row = remove / myGrid.length + rDelta[k];
                col = remove % myGrid.length + cDelta[k];
                if (inRange(row, col) && myGrid[row][col] == OPEN) {
                    myGrid[row][col] = FULL;
                    q.add(h(row, col));
                }
            }
        }
    }

    /**
     *
     * @param row
     * @param col
     * @return the index of the grid, which is calculated with row*n+col
     */
    public int h(int row, int col){
        return row*myGrid.length + col;
    }
    /**
     *
     * @param row
     * @param col
     * @return if the index is valid
     */
    public boolean inRange(int row, int col){
        return row>-1 && row < myGrid.length && col > -1 && col < myGrid[0].length;
    }
}

