// Cate Schick
// CompSci 201
// P6: Percolation
// Filename: PercolationDFSFast.java

public class PercolationDFSFast extends PercolationDFS {
    // create constructor using super
    /**
     * Initialize a grid where all cells in the grid are blocked.
     *
     * @param x: size of simulated grid
     */
    public PercolationDFSFast(int x) {
        super(x);
    }

    // override updateOnOpen method for efficiency
    /**
     *
     * @param row
     * @param col
     * Updates sites when a new site opens
     */
    @Override
    public void updateOnOpen(int row, int col) {
        int x = 0;

        // conditions when cell should be marked as FULL
        if (row == 0)
            x = 1;
        if (row != 0 && myGrid[row - 1][col] == FULL)
            x = 1;
        if (row != myGrid[row].length - 1 && myGrid[row+1][col] == FULL)
            x = 1;
        if (col != 0 && myGrid[row][col - 1] == FULL)
            x = 1;
        if (col != myGrid[col].length - 1 && myGrid[row][col + 1] == FULL)
            x = 1;

        // make call to dfs if cell should be full
        if (x == 1) dfs(row, col);
    }
}
