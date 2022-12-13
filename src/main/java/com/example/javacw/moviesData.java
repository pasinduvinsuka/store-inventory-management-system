package com.example.javacw;

public class moviesData {

    private String movieName,category,status,image;
    private Integer movieId  ;
    private double price;
    private int itemsAmount;

    //private String movieName;
    //private String status;
    //private String image;
    //private String category;

    public moviesData(Integer movieId, String movieName_data, String category_data, Double price_data, Integer itemsInStock_data, String status_data){

        this.movieId=movieId;

        this.movieName=movieName_data;
        this.price=price_data;
        this.itemsAmount=itemsInStock_data;
        this.status=status_data;
        this.image=image;
        this.category=category_data;
    }


   public Integer getMovieId(){return movieId;}
    public String getMovieName(){return movieName;}
    public double getPrice(){return price;}
    public int getItemsAmount(){return itemsAmount;}
    public String getStatus(){return status;}
    public String getImage(){return image;}
    public String getCategory(){return  category;}


}
