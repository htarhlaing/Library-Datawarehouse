package com.example.datawarehouse;

/**
 * This model will use to determine how book data is stored and retrieved
 *
 * @author Gusto Htar Htar Hlaing
 */
public class BookModel {
    String gender;
    String data;
    int quantity;

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {this.gender = gender;}

    public String getData()
    {
        return data;
    }
    public void setData(String data)
    {
        this.data=data;
    }

    public int getQuantity()
    {
        return quantity;
    }
    public void setQuantity(int quantity)
    {
        this.quantity=quantity;
    }

    public BookModel() {}

    public BookModel(String data)
{
    this.data=data;
}

    public BookModel(int quantity,String data)
    {
    this.quantity=quantity;
    this.data=data;
    }
    public BookModel(int quantity,String data,String gender)

    {
    this.quantity=quantity;
    this.data=data;
    this.gender=gender;
    }

    }
