package com.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import express.utils.Utils;
import java.sql.*;
import java.util.List;

public class Database {


    private Connection conn;

    public Database(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:todos.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Getting data from db, return a list of items
    public List<Item> getItems(){
        List<Item> items = null;

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM items");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Item[] itemsFromRs = new Item[0];
        try{
            itemsFromRs = (Item[]) Utils.readResultSetToObject(rs, Item[].class);
        } catch ( JsonProcessingException e) {
            e.printStackTrace();
        }

        items = List.of(itemsFromRs);

        return items;
    }

    //creating new data in db.
    public void createItem(Item item){

        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO items (text, checked) VALUES(?, ?)");

            stmt.setString(1, item.getText());
            stmt.setBoolean(2, item.isChecked());

            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //method for removing data/item from db
   public void deleteItem(Item item){

        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM items WHERE id = ?");
            stmt.setInt(1,item.getId());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}