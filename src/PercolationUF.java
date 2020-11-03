// Cate Schick
// CompSci 201
// Project 6: Percolation
// Filename: PercolationUF.java

public class PercolationUF implements IPercolate {
    // intialize variables:

   private IUnionFind myFinder;
   private boolean[][] myGrid;
   private final int VTOP;
   private final int VBOTTOM;
   private int myOpenCount;

    // constructor for PercolationUF
    /**
     * Constructs and initializes the NxN grid stored
     * in myGrid.
     * @param size
     * @param finder
     */
    public PercolationUF (IUnionFind finder, int size) {
        // initialize grid
        myGrid = new boolean[size][size];
        myFinder = finder;
        // initialize IUnionFind of size N*N+2
        myFinder.initialize((size * size + 2) );
        // intiialize instance variables
        VTOP = size * size;
        VBOTTOM = size * size + 1;
        myOpenCount = 0;
    }

    // override isOpen method
    /**
     * Throws an exception when needed
     * Returns appropriate myGrid value
     * @param row row index
     * @param col column index
     */
    @Override
    public boolean isOpen(int row, int col) {
        // throws out of bounds exception when needed
        if(! inBounds(row, col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        // otherwise, return appropriate myGrid value
        return myGrid[row][col];
    }

    // override isFull method
    /**
     * Throws exception when needed
     * Otherwise checks if (row, col) cell is connected to VTOP
     * @param row row index
     * @param col column index
     */
    @Override
    public boolean isFull(int row, int col) {
        // throws exception when needed
        if(!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        // otherwise, check if connected to VTOP
        return myFinder.connected(getIndex(row, col, myGrid.length), VTOP);
    }

    // override percolates method
    /**
     * Checks to see if VTOP is connected to VBOTTOM using myFinder
     */
    @Override
    public boolean percolates() {
        // check for connection
        return myFinder.connected(VTOP, VBOTTOM);
    }

    // override numberofOpenSites
    /**
     * Returns the number of sites that are open
     */
    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }

    // override open method

    /**
     * Determine if (row, col) is valid for given grid
     * @param row specifies row
     * @param col specifies column
     * @return true if (row, col) on grid, false otherwise
     */
    protected boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length) return false;
        if (col < 0 || col >= myGrid[0].length) return false;
        return true;
    }

    /**
     *
     * @param row row index
     * @param col column index
     * @param size size of myGrid
     * @return index of the specified row and column coordinates in grid
     */
    public int getIndex(int row, int col, int size) {
        return row*size + col;
    }


    /**
     * Throws an Exception when needed, checks to be sure
     * the cell is not already open, and then marks the cell
     * as open and connects with open neighbors
     * @param row row index
     * @param col column index
     */
    @Override
    public void open(int row, int col) {
        if(!inBounds(row, col))
        {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        int index = getIndex(row, col, myGrid.length);
        if(myGrid[row][col]) return;

        myGrid[row][col] = true;
        myOpenCount += 1;

        if(inBounds(row, col - 1) && isOpen(row, col - 1))
            myFinder.union(index, getIndex(row, col - 1, myGrid.length));

        if(inBounds(row, col + 1) && isOpen(row, col + 1))
            myFinder.union(index, getIndex(row, col + 1, myGrid.length));

        if(inBounds(row - 1, col) && isOpen(row - 1, col))
            myFinder.union(index, getIndex(row - 1, col, myGrid.length));

        if(inBounds(row + 1, col) && isOpen(row + 1, col))
            myFinder.union(index, getIndex(row + 1, col, myGrid.length));

        if(row == 0) myFinder.union(index, VTOP);

        if(row == myGrid.length - 1) myFinder.union(index, VBOTTOM);
    }
}