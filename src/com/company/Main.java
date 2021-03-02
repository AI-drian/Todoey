package com.company;
import express.Express;
import express.middleware.Middleware;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //New express server and database.
        Express app = new Express();
        Database db= new Database();

        //Read items from database
        app.get("/rest/Items", ((request, response) -> {
            List<Item>items = db.getItems();
            response.json(items);
        }));

        //Add items to database
        app.post("/rest/Items", (request, response) -> {
            Item item = (Item) request.getBody(Item.class);
            db.createItem(item);
        });

        //Delete items from database
        app.delete("/rest/Items/id", (request, response) -> {
            Item item = (Item) request.getBody(Item.class);
            db.deleteItem(item);
        });

        try {
            app.use(Middleware.statics(Paths.get("src/Frontend").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Keeping track of events on this server port.
        app.listen(1337);
        System.out.println("Server started on port 1337");
    }
}
