package com.softserve.edu;

import com.softserve.training.Box;

public class Appl {
    
    public static void main(String[] args) {
        //Box box = new Box();
        //WrapperBox box = new WrapperBox();
        Box<String> box = new Box<>();
        box.set("abc");
        //...
        // Runtime Error for Box Class
        // Compile Error for WrapperBox()
        // Compile Error for Box<String>()
        //Integer k = (Integer) box.get();
        String k = box.get();
        System.out.println("k= " + k);
    }
    
}
