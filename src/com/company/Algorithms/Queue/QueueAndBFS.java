package com.company.Algorithms.Queue;

import java.util.LinkedList;
import java.util.Queue;

public class QueueAndBFS {



    static int numIslands(char[][] grid) {
        if(grid.length ==0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        Queue<Object[]> demension = new LinkedList<>();
        int count=0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]=='1'){//if grid[i][j] inside island so check all neibough of its.
                    count ++;
                    // count +=  countNumIsLand(grid, i, j, grid.length, grid[0].length);
                    int c = j;
                    while (c < n && grid[i][c] == '1'){
                        demension.add(new Object[]{i,c});
                        grid[i][c++] = '0';
                    }
                    while(!demension.isEmpty()){
                        int size = demension.size();
                        for (int k = 0; k <size ; k++) {
                            Object[] position =  demension.poll();
                            grid[(int) position[0]][(int) position[1]] = '0';
                            if (position[0].equals(m-1) ){
                                continue;
                            }
                            demension.add(new Object[]{(int)(position[0])+1, position[1]});
                        }
                    }

                }
            }
        }

        return count;
    }
    public static void main(String...args){

        System.out.println(numIslands(new char[][]{{'1','1','1'},
                {'1', '0','1'},
                {'1','1','1'},
                }));

    }
}
