package com.company.Algorithms.Matrix;

public class MatrixInterview {

        /**matrix = [[1, 0, 0, 2],
         [0, 5, 0, 1],
         [0, 0, 3, 5]]
         rowsToDelete = [1], and columnsToDelete = [0, 2],
         constructSubmatrix(matrix, rowsToDelete, columnsToDelete) = [[0, 2],
         [0, 5]]
         * */
    int[][] constructSubmatrix(int[][] matrix, int[] rowsToDelete, int[] columnsToDelete) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[] unUsedRows = new boolean[row];
        boolean[] unUsedCols = new boolean[col];
        int[][] ans = new int[row - rowsToDelete.length][col - columnsToDelete.length];

        for(int i=0;i<rowsToDelete.length;i++)
            unUsedRows[rowsToDelete[i]] = true;
        for(int i=0;i<columnsToDelete.length;i++)
            unUsedCols[columnsToDelete[i]] = true;

        int colAns , rowAns =0;
        for(int i=0;i< row; i++){
            colAns =0;
            if(!unUsedRows[i]){
                for(int j = 0; j< col; j++){
                    if(!unUsedCols[j])
                        ans[rowAns][colAns++] = matrix[i][j];
                }
                rowAns++;
            }
        }
        return ans;
    }


    /**For a = [13, 20, 7, 4], the output should be [20, 4, 13, 7].*/
    static int[] digitRootSort(int[] a) {

        for(int i=0;i <a.length-1;i++){
            for(int j=i+1;j<a.length;j++){
                if(sumDigits(a[i]) > sumDigits(a[j])){
                    a[i] += a[j];
                    a[j] = a[i] - a[j];
                    a[i] -= a[j];
                }else if(sumDigits(a[i]) == sumDigits(a[j]) && a[j] < a[i]){
                    a[i] += a[j];
                    a[j] = a[i] - a[j];
                    a[i] -= a[j];
                }
            }
        }
        return a;
    }

    static int sumDigits(int n){
        if(n <10) return n;
        return n%10 + sumDigits(n/10);
    }

}
