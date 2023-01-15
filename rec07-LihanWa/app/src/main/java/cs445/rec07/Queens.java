package cs445.rec07;

import java.util.Arrays;

public class Queens {
    /**
     * Checks if a partial solution is a complete solution.
     * @param partial The partial solution
     * @return true if the partial solution is complete, false otherwise.
     */
    public static boolean isFullSolution(int[] partial) {
        // TODO: Implement this method
        boolean nozero=true;
        for (int i=0;i<partial.length;i++){
            if (partial[i]==0) nozero=false; 
        }
        if(nozero&&!reject(partial)) return true;
        return false;
    }

    /**
     * Checks if a partial solution should be rejected because it can never be extended to a
     * complete solution.
     * @param partial The partial solution
     * @return true if the partial solution should be rejected, false otherwise.
     */
    public static boolean reject(int[] partial) {
        // TODO: Implement this method
        for (int i=0;(i<partial.length&&partial[i]!=0);i++){
            for(int j=i+1;j<partial.length&&partial[j]!=0;j++){
                if (partial[j]==partial[i]||(partial[j]-partial[i])==(j-i)||(partial[j]-partial[i])==(i-j)) return true;
            }
        }
          
        return false;
    }

    /**
     * Extends a partial solution by adding one additional queen.
     * @param partial The partial solution
     * @return a partial solution with one more queen added, or null if no queen can be added.
     */
    public static int[] extend(int[] partial) {
        // TODO: implement this method
        for(int i=0;i<partial.length;i++){
            if (partial[i]==0) {
                partial[i]=1;
                return partial;}
            
        }
        return null;
    }

    /**
     * Moves the most recently-placed queen to its next possible position.
     * @param partial The partial solution
     * @return a partial solution with the most recently-placed queen moved to its next possible
     * position, or null if we are out of options for the most recent queen.
     */
    public static int[] next(int[] partial) {
        // TODO: implement this method
        if(partial[0]==8&&partial[1]==8) return null;
        for(int i=1;i<partial.length;i++){
            if(partial[i]==0 && partial[i-1]!=8) {
               
                partial[i-1]=partial[i-1]+1;   
                return partial;}            
            if(partial[i]==0){
                break;
            }
        }
        if(partial[7]!=8&&partial[6]!=0){
            partial[7]=partial[7]+1;   
            return partial;
         
        }
     
        if(partial[7]==8) partial[7]=0;
        for(int i=partial.length-2;i>=0;i--){
            if(partial[i]==8&&partial[i+1]==0){
                partial[i]=0;
                if(partial[i]==0)  return null;
            
            }
           
        }
        
        return null;
    }

    /**
     * Solves the 8-queens problem and outputs all solutions
     * @param partial The partial solution
     */
    public static void solve(int[] partial) {
        if (reject(partial)) return;
        
        if (isFullSolution(partial)) {System.out.println(Arrays.toString(partial));}
        int[] attempt = extend(partial);
        while (attempt != null) {
            solve(attempt);
            attempt = next(attempt);
        }
    }

    /**
     * Solves the 8-queens problem and returns one solution
     * @param partial The partial solution
     * @return A full, correct solution
     */
    public static int[] solveOnce(int[] partial) {
        
        if (reject(partial)) return null;
        if (isFullSolution(partial)) return partial;
        int[] attempt = extend(partial);
        
        while (attempt != null) {
            int[] solution = solveOnce(attempt);
            if (solution != null) return solution;
            attempt = next(attempt);
           
        }
        
        
        return null;
    }

}

