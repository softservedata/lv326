package com.softserve.edu.project.service;


public class MyFilter {

    public static String firstLetterUppCaseOtherLowercase(String str){
       if (str!=null) {
            StringBuilder string = new StringBuilder(str.toLowerCase());
            return string.substring(0, 1).toUpperCase() + string.substring(1);
        }
        return "";
    }
}
