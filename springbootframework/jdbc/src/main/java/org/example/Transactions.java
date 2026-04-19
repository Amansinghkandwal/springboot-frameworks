package org.example;

import java.sql.*;

public class Transactions {
    private static final String URL= "jdbc:mysql://localhost:3306/demo_db?allowMultiQueries=true";
    private static  final String USER="root";
    private static final String PASSWORD="root";

    public static void main(String[] args) {
        try(Connection connection= DriverManager.getConnection(URL,USER,PASSWORD)){
            System.out.println("Connected to the Database");
            //turned off auto save
            connection.setAutoCommit(false);

            try{
                //Order , OrderItems
                //INSERT INTO ORDERS
                int orderId= insertOrder(connection,101,"Alice",2000.0);
                //INSERT INTO ORDER ITEM
                insertOrderItem(connection,orderId,"Laptop01",1,2000.0);
                //manual commit
                connection.commit();
                System.out.println("Transaction commit successfully");
            }catch (Exception e)
            {
                e.printStackTrace();
                connection.rollback();
                System.out.println("Operation rollback successfully");
            }
            finally {
                 connection.setAutoCommit(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void insertOrderItem(Connection connection, int orderId, String productName, int quantity, double price) {
        String sql = "INSERT INTO order_items(order_id,product_name,quantity,price) VALUES (?,?,?,?)";
        try(PreparedStatement preparedStatement=connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1,orderId);
            preparedStatement.setString(2,productName);
            preparedStatement.setDouble(3,quantity);
            preparedStatement.setDouble(4,price);
            int x=10/0;   // intentionally generating error
            int rows=preparedStatement.executeUpdate();
            System.out.println("INSERTED into order items : "+rows);


        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int insertOrder(Connection connection, int customerId, String customerName, double totalAmount) {
        String sql = "INSERT INTO orders (user_id,customer_name,total_amount) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.setString(2, customerName);
            preparedStatement.setDouble(3, totalAmount);

            int rows = preparedStatement.executeUpdate();
            System.out.println("INSERTED into order : " + rows);
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int orderId = resultSet.getInt(1);
                    System.out.println("ORDER ID : " + orderId);
                    return orderId;
                } else {
                    throw new SQLException("Order id not generated");
                }
            }
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
    }

}
