package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.Set;
import java.util.HashSet;

public class Percolation {

    private int N;
    private boolean[] resultnumber;
    private int numofopen = 0;
    private WeightedQuickUnionUF connectedGrids;
    private Set<Integer> topFullIDs = new HashSet<>();
    private Set<Integer> bottomFullIDs = new HashSet<>();
    private boolean percolated = false;


    // Help method: 2D converted to 1D
    private int converttonumber(int row, int col) {
        return N * row + col;
    }

    // Constructor  // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        if (N <= 0) {
            throw new IllegalArgumentException(
                    "N should be greater than 0 but given N = " + N + "."
            );
        }
        this.N = N;
        resultnumber = new boolean[N*N];
        for(int i = 0; i < N*N; i += 1){
            resultnumber[i] = false;
        }
        connectedGrids = new WeightedQuickUnionUF(N * N);

    }

    public boolean checkinput(int row, int col){
        if(row < 0 || row >= N || col < 0 || col >= N ){
            return false;
        }
        return true;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col){
        if(checkinput(row, col)){
            int numberofblock = converttonumber(row, col);
            resultnumber[numberofblock] = true;
            numofopen += 1;

        } else {
            throw new IndexOutOfBoundsException("You should input a valid row and column");
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if(checkinput(row, col)){
            int numberofblock = converttonumber(row, col);
            return resultnumber[numberofblock];

        } else {
            throw new IndexOutOfBoundsException("You should input a valid row and column");
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {

    }


    public int numberOfOpenSites()           // number of open sites
    public boolean percolates()              // does the system percolate?
    public static void main(String[] args)   // use for unit testing (not required)

}
