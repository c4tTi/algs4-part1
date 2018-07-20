// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int[] solution(String S, int[] P, int[] Q) {
        // write your code in Java SE 8
        
        int[][] result = new int[S.length()][4];
        int[] returnResult = new int[P.length];
        
        //how many different solutions exist?
        //'A' [0], [0] - 1 solution
        //'AG' [0, 0, 1], [0, 1, 1] - 3
        // 'AGT' [0,0,0,1,1,2], [0,1,1,2,2,2] - 6
        // 10
        // 15
        // 21
        
        //Prefix Sum - calculation
        
        for (int i = 1; i< S.length()+1; i++){
            
            if (i == 1){
                switch (S.substring(i-1, i)){
                    case "A": result[i-1][0] = 1;
                        break;
                    case "C": result[i-1][1] = 1;
                        break;
                    case "G": result[i-1][2] = 1;
                        break;
                    case "T": result[i-1][3] = 1;
                        break;
                }
            }else{
                int a = 0;
                switch (S.substring(i-1, i)){
                    case "A": a = 0;
                        break;
                    case "C": a= 1;
                        break;
                    case "G": a =2;
                        break;
                    case "T": a =3;
                        break;
                }
                result[i-1][a] = 1 + result[i-2][a];
                result[i-1][(a+1)%4] = result[i-2][(a+1)%4];
                result[i-1][(a+2)%4] = result[i-2][(a+2)%4];
                result[i-1][(a+3)%4] = result[i-2][(a+3)%4];
            }

        }
        
        /*
        for (double i : result){
            System.out.println(i);
        }
        */
        
        //A = 1, G = 2, C = 3, T = 4
        //when A found return 1 and move to next computation.
        // P[k] <= Q[K]
        int lowerBound = 0;
        int upperBound = 0;
        for (int i = 0; i < P.length; i++){
            lowerBound = P[i];
            upperBound = Q[i];
            
            int[] toEvaluate = new int[4];

            //System.out.println(lowerBound);
            if (lowerBound == 0){
                toEvaluate[0]= result[upperBound][0];
                toEvaluate[1]= result[upperBound][1];
                toEvaluate[2]= result[upperBound][2];
                toEvaluate[3]= result[upperBound][3];
            }else{
                toEvaluate[0]= result[upperBound][0] - result[lowerBound-1][0];    
                toEvaluate[1]= result[upperBound][1] - result[lowerBound-1][1];
                toEvaluate[2]= result[upperBound][2] - result[lowerBound-1][2];
                toEvaluate[3]= result[upperBound][3] - result[lowerBound-1][3];
            }
            
            /*
            for (int eval : toEvaluate){
                System.out.println(eval);
                
            }
            */
            if (toEvaluate[0] > 0) {returnResult[i] = 1;
            }else if (toEvaluate[1] > 0) {returnResult[i] = 2;
            }else if (toEvaluate[2] > 0) {returnResult[i] = 3;
            }else if (toEvaluate[3] > 0) {returnResult[i] = 4;
            }
            
        }
        
        return returnResult;   
    }
}