package com.example.javacw;

public class salesMovieSearch {

        String movieName;
        int movieId,quantity;
        double price;
    public salesMovieSearch (String movieName,Double price){
        this.movieName=movieName;
        this.movieId=movieId;
        this.quantity=quantity;
        this.price=price;

    }

    public String getMovieName(){
        return movieName;
    }

    public int getMovieId(){
        return movieId;
    }

    public int getQuantity(){
        return quantity;
    }

    public double getPrice(){
        return price;
    }



}
