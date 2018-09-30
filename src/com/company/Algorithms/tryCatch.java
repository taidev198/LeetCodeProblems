package com.company.Algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class tryCatch {



    public static void main(String...args)throws IOException {
        InputStreamReader reader;
        BufferedReader bufferedReader;
        reader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(reader);
        String s;
        while (null != (s = bufferedReader.readLine()))
            System.out.println(s);

        System.out.println("thanh tai nguyen");
    }
}
