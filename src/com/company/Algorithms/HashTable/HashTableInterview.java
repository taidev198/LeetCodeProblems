package com.company.Algorithms.HashTable;

import java.util.*;

public class HashTableInterview {

    static List<Integer> topKFrequent(int[] nums, int k) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> hashtable = new Hashtable<>();
        for(int i =0;i < n;i++){
         if (!hashtable.containsKey(nums[i]))
             hashtable.put(nums[i], 1);
         else {
             int frequency = hashtable.get(nums[i]);
             hashtable.replace(nums[i], frequency, frequency+1);
         }
        }
        hashtable = sortByValue(hashtable);
       List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(hashtable.entrySet());

        for (int i = 0; i < k; i++) {
            Map.Entry<Integer, Integer> entry = entryList.get(entryList.size() -1 - i);
            ans.add(entry.getKey());
        }

        Collections.sort(ans);
        return ans;
    }

    static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        //  list.sort(Map.Entry.comparingByValue());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                if (((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue()) ==0 )
                    return ((Comparable) ((Map.Entry) (o1)).getKey())
                            .compareTo(((Map.Entry) (o2)).getKey());
                return  ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static void main(String...args){

        System.out.println(topKFrequent(new int[]{4,1,-1,2,-1,2,3}, 2));

    }
}
