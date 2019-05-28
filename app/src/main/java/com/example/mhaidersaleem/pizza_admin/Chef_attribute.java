package com.example.mhaidersaleem.pizza_admin;

/**
 * Created by M.HAiDER Saleem on 09/05/2018.
 */

public class Chef_attribute {
    String user_name;
    String user_password;

    String itemName;
    int itemPrice;
    String itemDescription;
    public Chef_attribute()
    {

    }
    public void set_user(String Number,String Password)
    {
        user_name= Number;
        user_password= Password;
    }
    public void set_item(String name,int price,String des)
    {
        itemDescription= des;
        itemPrice= price;
        itemName= name;
    }
}
