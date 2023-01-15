package cs445.a3;

public class SudokuSolver {
    private int[][] initialBoard;

    public SudokuSolver(int[][] board) {
        // Here, you can do any pre-processing you want to the initial board
        initialBoard = board;
    }

    public static void printBoard(int[][] board) {
        if (board == null) {
            System.out.println("No assignment (null)");
            return;
        }
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----+-----+----");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 2 || j == 5) {
                    System.out.print(board[i][j] + " | ");
                } else {
                    System.out.print(board[i][j]);
                }
            }
            System.out.print("\n");
        }
    }

    /**
     * This public solve method simply calls the recursive helper method on
     * this Sudoku Solver's initialBoard variable.
     */
    public int[][] solve() {
        if(reject(initialBoard)) System.out.print("This doesn't have a solution.\n");
        int[][] result = solve(initialBoard);
        // Here, you can do any post-processing you want to the solution
        return result;
    }

    /**
     * This recursive method finds the first solution to a given Sudoku board.
     */
    private int[][] solve(int[][] board) {
        if (reject(board)) return null;
        if (isFullSolution(board)) return board;
        int[][] attempt = extend(board);
        while (attempt != null) {
            int[][] solution = solve(attempt);
            if (solution != null) return solution;
            attempt = next(attempt);
        }   
        return null;
    }

    ////
    // Backtracking helper methods start here
    ////

    private static boolean isFullSolution(int[][] board) {
        // TODO: Complete this method
        //row
        for(int i=8;i>=0;i--){
            
            for(int j=8;j>=0;j--){
                if(board[j][i]==0) return false;
            }
        }
        if(reject(board)) return false;
        for(int i=0;i<9;i++){//all rows
            for(int j=0;j<9;j++){
                if(board[i][j]>9) board[i][j]%=10;
            }
        }
        return true;
    }

    private static boolean reject(int[][] board) {
        // TODO: Complete this method
        //row
        for(int i=0;i<9;i++){//all rows
            for(int j=0;j<8;j++){//to be compared
                
                for(int k=j+1;k<9;k++){//behind the compared
                    if(board[i][j]==0) break;
                    if(board[i][j]%10==board[i][k]%10) return true;
                }
            }
        }
        //collumn
        for(int i=0;i<9;i++){//all cols
            for(int j=0;j<8;j++){//to be compared
                
                for(int k=j+1;k<9;k++){//behind the compared
                    if(board[j][i]==0) break;
                    if(board[j][i]%10==board[k][i]%10) return true;
                }
            }
        }
        //unit
        for(int i=0;i<9;i+=3){
            for(int j=0;j<9;j+=3){
                
                if(board[i][j]!=0){
                    if(board[i][j]%10==board[i+1][j+1]%10) return true;
                    if(board[i][j]%10==board[i+1][j+2]%10) return true;
                    if(board[i][j]%10==board[i+2][j+1]%10) return true;
                    if(board[i][j]%10==board[i+2][j+2]%10) return true;
                }
                if(board[i+1][j]!=0){
                    if(board[i+1][j]%10==board[i][j+1]%10) return true;
                    if(board[i+1][j]%10==board[i][j+2]%10) return true;
                    if(board[i+1][j]%10==board[i+2][j+1]%10) return true;
                    if(board[i+1][j]%10==board[i+2][j+2]%10) return true;
                }
                if(board[i+2][j]!=0){
                    if(board[i+2][j]%10==board[i][j+1]%10) return true;
                    if(board[i+2][j]%10==board[i][j+2]%10) return true;
                    if(board[i+2][j]%10==board[i+1][j+2]%10) return true;
                    if(board[i+2][j]%10==board[i+1][j+1]%10) return true;
                }
                
                if(board[i][j+1]!=0){
                    if(board[i][j+1]%10==board[i+1][j+2]%10) return true;
                    if(board[i][j+1]%10==board[i+2][j+2]%10) return true;
                }
                if(board[i+1][j+1]!=0){
                    if(board[i+1][j+1]%10==board[i][j+2]%10) return true;
                    if(board[i+1][j+1]%10==board[i+2][j+2]%10) return true;
                }
                if(board[i+2][j+1]!=0){
                    if(board[i+2][j+1]%10==board[i][j+2]%10) return true;
                    if(board[i+2][j+1]%10==board[i+1][j+2]%10) return true;
                }

                
            }
        }
        return false;
    }

    private static int[][] extend(int[][] board) {
        // TODO: Complete this method
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[j][i]==0){
                    board[j][i]=11;
                    return board;
                }
            }
        }
        return null;
    }

    private static int[][] next(int[][] board) {
        // TODO: Complete this method
        for(int i=8;i>=0;i--){
            for(int j=8;j>=0;j--){
                if(board[j][i]>10&&board[j][i]!=19) {
                    board[j][i]+=1;
                    if(board[0][0]%10==9&&board[1][0]%10==9) System.out.print("This doesn't have a solution.");
                    return board;
                }
                if(board[j][i]==19){
                    board[j][i]=0;
                    return null;
                }
            }
        }
        return null;
    }


    ////
    // Internal testing methods start here
    ////

    private static void testIsFullSolution() {
        System.out.print("!!Claim: numbers greater than 10 are put by the player, otherwise they are the original number.");
        System.out.print("\n\ntestIsFullSolution\ntest one: Board should be 'isFull'\nBoard is:\n");


        // TODO: Complete this method
        int[][] Board = new int[][] {
            {3, 6, 1, 17, 9, 4, 2, 5, 8},
            {2, 18, 14, 5, 6, 3, 1, 9, 17},
            {17, 9, 5, 8, 2, 1, 4, 6, 3},
            {6, 2, 3, 9, 17, 8, 5, 14, 1},
            {5, 7, 9, 1, 4, 6, 3, 8, 12},
            {1, 4, 18, 3, 5, 2, 9, 7, 6},
            {9, 3, 7, 2, 18, 5, 6, 1, 4},
            {4, 15, 2, 6, 1, 7, 8, 3, 9},
            {8, 1, 6, 4, 13, 9, 7, 2, 5},};
        
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----------------------------------------------------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 3|| j == 6) {
                    System.out.print("|"+Board[i][j]  +"\t");
                } else {
                    System.out.print(Board[i][j]+"\t");
                }
            }
            System.out.print("\n");
        }
        if(!isFullSolution(Board)) System.out.print("testIsFull is wrong,board isFull");
        else System.out.print("pass test one\n");



        System.out.print("\n\ntestIsFullSolution\ntest two: Board should be 'isFull'\nBoard is:\n");
        int[][]  Board1= new int[][] {
            {4, 11, 7, 6, 3, 2, 8, 19, 5},
            {12, 16, 5, 14, 19, 8, 3, 17, 1},
            {3, 19, 8, 17, 1, 5, 4, 2, 6},
            {1, 4, 9, 5, 7, 3, 2, 16, 8},
            {7, 8, 3, 9, 2, 6, 1, 5, 4},
            {6, 15, 2, 8, 4, 11, 9, 3, 7},
            {5, 17, 1, 13, 8, 9, 16, 4, 2},
            {9, 2, 4, 11, 6, 7, 5, 8, 3},
            {8, 3, 6, 2, 5, 4, 7, 1, 9},
        };
        
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----------------------------------------------------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 3|| j == 6) {
                    System.out.print("|"+Board1[i][j]  +"\t");
                } else {
                    System.out.print(Board1[i][j]+"\t");
                }
            }
            System.out.print("\n");
        }
        if(!isFullSolution(Board1)) System.out.print("testIsFull is wrong,board isFull");
        else System.out.print("pass test two\n");



        System.out.print("\n\ntestIsFullSolution\ntest three: Board shouldn't 'isFull', because there are 0 (row:8 column:9) in it.\nBoard is:\n");
        int[][] Board2 = new int[][] {
            {14, 8, 6, 5, 1, 3, 9, 7, 2},
            {7, 5, 1, 16, 9, 2, 3, 18, 4},
            {3, 9, 2, 7, 4, 8, 1, 5, 6},
            {2, 1, 7, 3, 6, 9, 5, 4, 8},
            {8, 4, 15, 2, 7, 1, 6, 13, 9},
            {6, 3, 9, 4, 18, 5, 7, 2, 1},
            {1, 16, 3, 8, 15, 4, 2, 9, 7},
            {5, 7, 4, 9, 2, 6, 8, 1, 0},
            {9, 2, 8, 11, 3, 7, 4, 6, 5},
        };
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----------------------------------------------------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 3|| j == 6) {
                    System.out.print("|"+Board2[i][j]  +"\t");
                } else {
                    System.out.print(Board2[i][j]+"\t");
                }
            }
            System.out.print("\n");
        }
        if(isFullSolution(Board2)) System.out.print("testIsFull is wrong,board2 isn't isFull, there are 0 in the board2");
        else System.out.print("pass test three\n");

        System.out.print("\n\ntestIsFullSolution\ntest four: Board shouldn't be 'isFull', because of 12(row:8 column:9), repeated number are in the same line, column and unit\nBoard is:\n");
        int[][] Board3 = new int[][] {
            {14, 8, 6, 5, 1, 3, 9, 7, 2},
            {7, 5, 1, 16, 9, 2, 3, 18, 4},
            {3, 9, 2, 7, 4, 8, 1, 5, 6},
            {2, 1, 7, 3, 6, 9, 5, 4, 8},
            {8, 4, 15, 2, 7, 1, 6, 13, 9},
            {6, 3, 9, 4, 18, 5, 7, 2, 1},
            {1, 16, 3, 8, 15, 4, 2, 9, 7},
            {5, 7, 4, 9, 2, 6, 8, 1, 12},
            {9, 2, 8, 11, 3, 7, 4, 6, 5},
        };
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----------------------------------------------------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 3|| j == 6) {
                    System.out.print("|"+Board3[i][j]  +"\t");
                } else {
                    System.out.print(Board3[i][j]+"\t");
                }
            }
            System.out.print("\n");
        }
        if(isFullSolution(Board3)) System.out.print("testIsFull is wrong,board2 isn't isFull, repeated numbers are in the same collumn and line in board3");
        else System.out.print("pass test four\n");

        

    }

    private static void testReject() {
        // TODO: Complete this metho
        System.out.print("!!Claim: numbers greater than 10 are put by the player, otherwise they are the original number.");
        System.out.print("\n\ntestReject\ntest one: Board shouldn't be rejected \nBoard is:\n");
        int[][]  Board4= new int[][] {
            {4, 11, 7, 6, 3, 2, 8, 19, 5},
            {12, 16, 5, 14, 19, 8, 3, 17, 1},
            {3, 19, 8, 17, 1, 5, 4, 2, 6},
            {1, 4, 9, 0, 7, 3, 2, 16, 8},
            {7, 8, 3, 9, 2, 6, 1, 5, 4},
            {6, 15, 2, 8, 4, 0, 9, 3, 7},
            {5, 17, 1, 13, 8, 9, 16, 4, 2},
            {9, 2, 4, 11, 6, 7, 5, 8, 3},
            {8, 3, 6, 2, 5, 4, 7, 1, 9},
        };
        
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----------------------------------------------------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 3|| j == 6) {
                    System.out.print("|"+Board4[i][j]  +"\t");
                } else {
                    System.out.print(Board4[i][j]+"\t");
                }
            }
            System.out.print("\n");
        }
        if(reject(Board4)) System.out.print("board4 shouldn't be rejected");
        else System.out.print("pass test one\n");



        System.out.print("\n\ntestReject\ntest two: Board should be rejected, because there are repeated numbers in the same colume(19 and 9 in column8)\nBoard is:\n");
        int[][] Board = new int[][] {
            {3, 6, 1, 17, 9, 4, 2, 5, 8},
            {2, 18, 14, 5, 6, 3, 1, 9, 17},
            {17, 9, 5, 8, 2, 1, 4, 6, 3},
            {6, 2, 3, 9, 17, 8, 5, 14, 1},
            {5, 7, 9, 1, 4, 6, 3, 8, 12},
            {1, 4, 18, 3, 5, 2, 9, 7, 6},
            {9, 3, 7, 2, 18, 5, 6, 1, 4},
            {4, 15, 2, 6, 1, 7, 8, 19, 0},
            {8, 1, 6, 4, 13, 9, 7, 2, 0},};
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----------------------------------------------------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 3|| j == 6) {
                    System.out.print("|"+Board[i][j]  +"\t");
                } else {
                    System.out.print(Board[i][j]+"\t");
                }
            }
            System.out.print("\n");
        }
        if(!reject(Board)) System.out.print("board: reject is wrong,there are repeated numbers in the same column");
        else System.out.print("pass test two\n");

        System.out.print("\n\ntestReject\ntest three: Board should be rejected, because there are repeated numbers in the same row(9 and 19 in row 9)\nBoard is:\n");
        int[][] Board1 = new int[][] {
            {3, 6, 1, 17, 9, 4, 2, 5, 8},
            {2, 18, 14, 5, 6, 3, 1, 9, 17},
            {17, 9, 5, 8, 2, 1, 4, 6, 3},
            {6, 2, 3, 9, 17, 8, 5, 14, 1},
            {5, 7, 9, 1, 4, 6, 3, 8, 12},
            {1, 4, 18, 3, 5, 2, 9, 7, 6},
            {9, 3, 7, 2, 18, 5, 6, 1, 4},
            {4, 15, 2, 6, 1, 7, 8, 0, 0},
            {8, 1, 6, 4, 13, 9, 0, 0,19},};
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----------------------------------------------------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 3|| j == 6) {
                    System.out.print("|"+Board1[i][j]  +"\t");
                } else {
                    System.out.print(Board1[i][j]+"\t");
                }
            }
            System.out.print("\n");
        }
        if(!reject(Board1)) System.out.print("board1: reject is wrong,there are repeated numbers in the same row");
        else System.out.print("pass test three\n");

        System.out.print("\n\ntestReject\ntest four: Board should be rejected, because there are repeated numbers in the same unit(19 and 19 on bottom right)\nBoard is:\n");
        int[][] Board2 = new int[][] {
            {3, 6, 1, 17, 9, 4, 2, 5, 8},
            {2, 18, 14, 5, 6, 3, 1, 9, 17},
            {17, 9, 5, 8, 2, 1, 4, 6, 3},
            {6, 2, 3, 9, 17, 8, 5, 14, 1},
            {5, 7, 9, 1, 4, 6, 3, 8, 12},
            {1, 4, 18, 3, 5, 2, 9, 7, 6},
            {0, 3, 7, 2, 18, 5, 6, 1, 19},
            {4, 15, 2, 6, 1, 7, 8, 19, 0},
            {8, 1, 6, 4, 13, 9, 7, 2, 0},};
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----------------------------------------------------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 3|| j == 6) {
                    System.out.print("|"+Board2[i][j]  +"\t");
                } else {
                    System.out.print(Board2[i][j]+"\t");
                }
            }
            System.out.print("\n");
        }
        if(!reject(Board2)) System.out.print("board2:reject is wrong,there are repeated numbers in the same unit");
        else System.out.print("pass test three\n");
    }

    private static void testExtend() {
        // TODO: Complete this method
        System.out.print("!!Claim: numbers greater than 10 are put by the player, otherwise they are the original number.");
        System.out.print("\n\ntestExtend\ntest one: Board should be extended at row4 column 9, so the 0 will change to 11\nBoard is:\n");
        int[][] Board = new int[][] {
            {3, 6, 1, 7, 9, 4, 2, 5, 8},
            {2, 8, 4, 5, 6, 3, 1, 9, 7},
            {7, 9, 5, 8, 2, 1, 4, 6, 3},
            {6, 2, 3, 9, 7, 8, 5, 4, 0},
            {5, 7, 9, 1, 4, 6, 13, 8, 2},
            {1, 4, 8, 3, 5, 2, 9, 7, 0},
            {9, 3, 7, 2, 8, 5, 6, 1, 0},
            {4, 5, 2, 6, 1, 7, 8, 3, 0},
            {8, 1, 6, 4, 3, 9, 7, 5, 0},};
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----------------------------------------------------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 3|| j == 6) {
                    System.out.print("|"+Board[i][j]  +"\t");
                } else {
                    System.out.print(Board[i][j]+"\t");
                }
            }
            System.out.print("\n");
        }
        int [][] result=extend(Board);
        System.out.print("After put into extend, the result is:\n");
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----------------------------------------------------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 3|| j == 6) {
                    System.out.print("|"+result[i][j]  +"\t");
                } else {
                    System.out.print(result[i][j]+"\t");
                }
            }
            System.out.print("\n");
        }
        int[][] R = new int[][] {
            {3, 6, 1, 7, 9, 4, 2, 5, 8},
            {2, 8, 4, 5, 6, 3, 1, 9, 7},
            {7, 9, 5, 8, 2, 1, 4, 6, 3},
            {6, 2, 3, 9, 7, 8, 5, 4, 11},
            {5, 7, 9, 1, 4, 6, 13, 8, 2},
            {1, 4, 8, 3, 5, 2, 9, 7, 0},
            {9, 3, 7, 2, 8, 5, 6, 1, 0},
            {4, 5, 2, 6, 1, 7, 8, 3, 0},
            {8, 1, 6, 4, 3, 9, 7, 5, 0},};
        boolean pass=true;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(R[j][i]!=result[j][i]){
                    System.out.print("extend is wrong\n");
                    pass=false;
                    System.out.print("column:"+(i+1)+"  row:"+(j+1)+"\n");
                    System.out.print("R"+R[j][i]+"\n");
                    System.out.print("Board"+Board[j][i]+"\n");
                  
                }
               
            }
        }
        if(pass) System.out.print("\ntest one pass\n");

        System.out.print("\n\ntestExtend\ntest two: Board should be extended at row 1 column 6, so the 0 will change to 11. It doesn't make mistake when changing to next column.\nBoard is:\n");
        int[][] Board2 = new int[][] {
            {3, 6, 1, 7, 9, 0, 2, 5, 8},
            {2, 8, 4, 5, 6, 3, 1, 9, 7},
            {7, 9, 5, 8, 2, 1, 4, 6, 3},
            {6, 2, 3, 9, 7, 8, 5, 4, 0},
            {5, 7, 9, 1, 4, 6, 13, 8, 2},
            {1, 4, 8, 3, 5, 2, 9, 7, 0},
            {9, 3, 7, 2, 8, 5, 6, 1, 0},
            {4, 5, 2, 6, 1, 7, 8, 3, 0},
            {8, 1, 6, 4, 3, 9, 7, 5, 0},};
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----------------------------------------------------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 3|| j == 6) {
                    System.out.print("|"+Board2[i][j]  +"\t");
                } else {
                    System.out.print(Board2[i][j]+"\t");
                }
            }
            System.out.print("\n");
        }
        int [][] result2=extend(Board2);
        System.out.print("After put into extend, the result is:\n");
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----------------------------------------------------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 3|| j == 6) {
                    System.out.print("|"+result2[i][j]  +"\t");
                } else {
                    System.out.print(result2[i][j]+"\t");
                }
            }
            System.out.print("\n");
        }
        int[][] R2 = new int[][] {
            {3, 6, 1, 7, 9, 11, 2, 5, 8},
            {2, 8, 4, 5, 6, 3, 1, 9, 7},
            {7, 9, 5, 8, 2, 1, 4, 6, 3},
            {6, 2, 3, 9, 7, 8, 5, 4, 0},
            {5, 7, 9, 1, 4, 6, 13, 8, 2},
            {1, 4, 8, 3, 5, 2, 9, 7, 0},
            {9, 3, 7, 2, 8, 5, 6, 1, 0},
            {4, 5, 2, 6, 1, 7, 8, 3, 0},
            {8, 1, 6, 4, 3, 9, 7, 5, 0},};
        boolean pass1=true;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(R2[j][i]!=result2[j][i]){
                    System.out.print("extend is wrong\n");
                    System.out.print("column:"+(i+1)+"  row:"+(j+1)+"\n");
                    System.out.print("R2: "+R2[j][i]+"\n");
                    System.out.print("result2: "+result2[j][i]+"\n");
                  
                }
               
            }
        }
        if(pass1) System.out.print("\ntest two pass\n");

        System.out.print("\n\ntestExtend\ntest three: Board shouldn't be extended, because there is no 0\nBoard is:\n");
        int[][] Board3 = new int[][] {
            {3, 6, 1, 7, 9, 1, 2, 5, 8},
            {2, 8, 4, 5, 6, 3, 1, 9, 7},
            {7, 9, 5, 8, 2, 1, 4, 6, 3},
            {6, 2, 3, 9, 7, 8, 5, 4, 1},
            {5, 7, 9, 1, 4, 6, 13, 8, 2},
            {1, 4, 8, 3, 5, 2, 9, 7, 1},
            {9, 3, 7, 2, 8, 5, 6, 1, 1},
            {4, 5, 2, 6, 1, 7, 8, 3, 1},
            {8, 1, 6, 4, 3, 9, 7, 5, 1},};
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----------------------------------------------------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 3|| j == 6) {
                    System.out.print("|"+Board3[i][j]  +"\t");
                } else {
                    System.out.print(Board3[i][j]+"\t");
                }
            }
            System.out.print("\n");
        }
        if(extend(Board3)!=null) System.out.print("board3: extend should return null");
        else System.out.print("\npass test 3\n");
         
        
        
    }

    private static void testNext() {
        System.out.print("!!Claim: numbers greater than 10 are put by the player, otherwise they are the original number.");
        System.out.print("\n\ntestNext\ntest one: row3 column9 should change from 13 to 14, because the numbers that greater than 10 are put by player\nBoard is:\n");
        int[][] Board = new int[][] {
            {3, 6, 1, 7, 9, 4, 2, 5, 8},
            {2, 8, 4, 5, 6, 3, 1, 9, 7},
            {7, 9, 5, 8, 2, 1, 4, 6, 13},
            {6, 2, 3, 9, 7, 8, 5, 4, 0},
            {5, 7, 9, 1, 4, 6, 14, 8, 2},
            {1, 4, 8, 3, 5, 2, 9, 7, 0},
            {9, 3, 7, 2, 8, 5, 6, 1, 0},
            {4, 5, 2, 6, 1, 7, 8, 3, 0},
            {8, 1, 6, 4, 3, 9, 7, 5, 0},};
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----------------------------------------------------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 3|| j == 6) {
                    System.out.print("|"+Board[i][j]  +"\t");
                } else {
                    System.out.print(Board[i][j]+"\t");
                }
            }
            System.out.print("\n");
        }
        int [][] result=next(Board);
        System.out.print("After put into next, the result is:\n");
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----------------------------------------------------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 3|| j == 6) {
                    System.out.print("|"+result[i][j]  +"\t");
                } else {
                    System.out.print(result[i][j]+"\t");
                }
            }
            System.out.print("\n");
        }
        int[][] R = new int[][] {
            {3, 6, 1, 7, 9, 4, 2, 5, 8},
            {2, 8, 4, 5, 6, 3, 1, 9, 7},
            {7, 9, 5, 8, 2, 1, 4, 6, 14},
            {6, 2, 3, 9, 7, 8, 5, 4, 0},
            {5, 7, 9, 1, 4, 6, 14, 8, 2},
            {1, 4, 8, 3, 5, 2, 9, 7, 0},
            {9, 3, 7, 2, 8, 5, 6, 1, 0},
            {4, 5, 2, 6, 1, 7, 8, 3, 0},
            {8, 1, 6, 4, 3, 9, 7, 5, 0},};
        boolean pass=true;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(R[j][i]!=Board[j][i]){
                    System.out.print("next is wrong\n");
                    pass=false;
                    System.out.print("column:"+(i+1)+"  row:"+(j+1)+"\n");
                    System.out.print("R: "+R[j][i]+"\n");
                    System.out.print("result: "+result[j][i]+"\n");
                }
               
            }
        }
        if(pass) System.out.print("pass test one.\n");

        System.out.print("\n\ntestNext\ntest two: this board should be null after putting into next, because the last number put by the player is 19 \nBoard is:\n");
        int[][] Board3 = new int[][] {
            {3, 6, 1, 7, 9, 1, 2, 5, 8},
            {2, 8, 4, 5, 6, 3, 1, 9, 7},
            {7, 9, 5, 8, 2, 1, 4, 6, 3},
            {6, 2, 3, 9, 7, 8, 5, 4, 19},
            {5, 7, 9, 1, 4, 6, 13, 8, 2},
            {1, 4, 8, 3, 5, 2, 9, 7, 0},
            {9, 3, 7, 2, 8, 5, 6, 1, 0},
            {4, 5, 2, 6, 1, 7, 8, 3, 0},
            {8, 1, 6, 4, 3, 9, 7, 5, 0},};
            for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----------------------------------------------------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 3|| j == 6) {
                    System.out.print("|"+Board3[i][j]  +"\t");
                } else {
                    System.out.print(Board3[i][j]+"\t");
                }
            }
            System.out.print("\n");
        }
        if(next(Board3)!=null) System.out.print("board3: next should return null");
        else System.out.print("\npass test two\n");

        System.out.print("\n\ntestNext\ntest three: this result shouldn't be null after putting into next, because the last number put by the player is 12 \nBoard is:\n");
        int[][] Board4 = new int[][] {
            {3, 6, 1, 7, 9, 1, 2, 5, 8},
            {2, 8, 4, 5, 6, 3, 1, 9, 7},
            {7, 9, 5, 8, 2, 1, 4, 6, 3},
            {6, 2, 3, 9, 7, 8, 5, 4, 9},
            {5, 7, 9, 1, 4, 6, 13, 8, 12},
            {1, 4, 8, 3, 5, 2, 9, 7, 0},
            {9, 3, 7, 2, 8, 5, 6, 1, 0},
            {4, 5, 2, 6, 1, 7, 8, 3, 0},
            {8, 1, 6, 4, 3, 9, 7, 5, 0},};
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----------------------------------------------------------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 3|| j == 6) {
                    System.out.print("|"+Board4[i][j]  +"\t");
                } else {
                    System.out.print(Board4[i][j]+"\t");
                }
            }
            System.out.print("\n");
        }
        if(next(Board4)==null) System.out.print("board4: next shouldn't return null");
        else System.out.print("\npass test three\n");
        int[][] Board5 = new int[][] {
            {3, 6, 1, 7, 9, 1, 2, 5, 0},
            {2, 8, 4, 5, 6, 3, 1, 9, 0},
            {7, 9, 5, 8, 2, 1, 4, 6, 0},
            {6, 2, 3, 9, 7, 8, 5, 4, 0},
            {5, 7, 9, 1, 4, 6, 13, 8, 0},
            {1, 4, 8, 3, 5, 2, 19, 7, 0},
            {9, 3, 7, 2, 8, 5, 6, 1, 0},
            {4, 5, 2, 6, 1, 7, 8, 3, 0},
            {8, 1, 6, 4, 3, 9, 7, 5, 0},};
        if(next(Board5)!=null) System.out.print("board5: next should return null");
        // TODO: Complete this method
        
    }


    /**
     * If this class is run directly, just run the internal test methods.
     */
    public static void main(String[] args) {
        System.out.println("Executing test methods.");
        testIsFullSolution();
        testReject();
        testExtend();
        testNext();
    }
}

