package com.example.helloworld;

public class OutputStruct {
    int[] image_data;
    double left_percentage, right_percentage, top_percentage, bottom_percentage;
    double x_percentage, y_percentage;
    int status; //0 - not detected, 1 - horizontal grading, 2 - vertical grading

    int[] output_image;
    int output_width, output_height;
    boolean furry;

    OutputStruct(){
        left_percentage = 0.0;
        right_percentage = 0.0;
        top_percentage = 0.0;
        bottom_percentage = 0.0;
        x_percentage = 0.0;
        y_percentage = 0.0;
        status = 0;

        output_height = 0;
        output_width = 0;
        furry = false;

    }
}
