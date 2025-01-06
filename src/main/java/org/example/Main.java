package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection con = null;
        try {
            con = Database.getConnection();
            if (con!=null){
                System.out.println("Database Connected");
            }

            String Query1="CREATE TABLE `test`.`new_table` (  `id` INT NOT NULL,  `password` INT NOT NULL, `marks` INT NOT NULL,   `firstName` VARCHAR(45) NOT NULL,  `lastName` VARCHAR(45) NOT NULL,  PRIMARY KEY (`id`));";
            String Query2="INSERT INTO `test`.`users`(`id`,`password`,`marks`,`firstName`,`lastName`) VALUES (113,123,0,'test','test');";
            System.out.println("Values Added");
            con.createStatement();
            Statement query = con.createStatement();
            query.execute(Query1);
            query.execute(Query2);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}