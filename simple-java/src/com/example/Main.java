package com.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Main {


    public static void main(String[] args) {

        DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("dd/MM/yy",Locale.US);

        LocalDate localDate= LocalDate.parse("01/12/15",dateTimeFormatter);

        System.out.println(localDate.toString());

        String string = "May 1, 2016";

        DateTimeFormatter dateTimeFormatter1= DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);

        LocalDate localDate1=LocalDate.parse(string,dateTimeFormatter1);

        var format = dateTimeFormatter1.format(localDate1);


        System.out.println(localDate1.toString());
        System.out.println(format);


    }
}
