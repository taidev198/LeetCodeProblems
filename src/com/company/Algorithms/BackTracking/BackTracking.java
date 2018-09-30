package com.company.Algorithms.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BackTracking {


    public static void permute(int[] nums, int left, int right){
        if(left == right)
            System.out.println(Arrays.toString(nums));
        else {
            for (int i = left; i <=right; i++) {
                int temp = nums[left];
                nums[left] = nums[i];
                nums[i] = temp;
                permute(nums, left+1, right);
                temp = nums[left];
                nums[left] = nums[i];
                nums[i] = temp;
            }
        }
    }

    public static void main(String...args){

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(Arrays.stream(new int[]{1,2,3,4,5}).boxed().collect(Collectors.toList()));
        System.out.println(ans);
        //permute(new int[]{1,2,3,4}, 0, 3);
        System.out.println("thanh tai nguyen");
    }
}
