package org.example;

import java.sql.*;

//jdbc --> java database connectivity
//problems with jdbc
//Boilerplate code
//Manual mapping
//Error prone
//Solution --> ORM
//ORM - Object relational mapping
//Hibernate --> ORM tool -> Actual work
//JPA - Java/Jakarta Persistence API --> Just define how
public class JDBC {
   private static final String URL= "jdbc:mysql://localhost:3306/demo_db?allowMultiQueries=true";
    private static  final String USER="root";
   private static final String PASSWORD="root";

    public static void main(String[] args) {
        try(Connection connection= DriverManager.getConnection(URL,USER,PASSWORD)){
            System.out.println("Connected to the Database");
//            insertStudent(connection,"Aman","example@gmail.com");
            deleteStudent(connection, "1 OR 1=1");
           // DELETE FROM student WHERE id=1 OR 1=1  //sql injection
//              updateStudent(connection,1,"AMAN","EAMPLE@gmail.com");
//            selectStudent(connection);
//              deleteStudent(connection,2);
            selectStudent(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void insertStudent(Connection connection,String name,String email) {
        String sql = "INSERT INTO student(name,email) VALUES ('" + name + "','" + email + "')";
        try (Statement stmt = connection.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            System.out.println("INSERTED: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        private static void selectStudent(Connection connection)
        {
            String sql="SELECT * FROM student";
            try(Statement statement= connection.createStatement())
            {
                ResultSet resultSet=statement.executeQuery(sql);
                System.out.println("Student list: ");
                while(resultSet.next())
                {
                    int id=resultSet.getInt("id");
                    String name=resultSet.getString("name");
                            String email= resultSet.getString("email");
                    System.out.println(id+ " : "+name+" : "+email);
                }
        }catch (SQLException e)
            {
                e.printStackTrace();
            }


    }

    private static void updateStudent(Connection connection,int id, String name,String email)
    {
        String sql= "UPDATE student SET name= '"+name+"',email= '"+email+"' WHERE id = "+id;
        //UPDATE student SET name= 'aman',email='example@gmail.com' WHERE id=1;
        try(Statement statement = connection.createStatement())
        {
           statement.executeUpdate(sql);
            System.out.println("UPDATED");
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //to prevent sql injection
    private static void safeUpdateStudent(Connection connection,int id, String name,String email)
    {
//        String sql= "UPDATE student SET name= '"+name+"',email= '"+email+"' WHERE id = "+id;
        String sql= "UPDATE student SET name= ? ,email= ? WHERE id = ?";
        //UPDATE student SET name= 'aman',email='example@gmail.com' WHERE id=1;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setInt(3,id);
            int rows=preparedStatement.executeUpdate();
            System.out.println("UPDATED: "+rows);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static void deleteStudent(Connection connection,String id)
    {
        String sql= "DELETE FROM student WHERE id="+id;
        try(Statement statement=connection.createStatement())
        {
            int rows=statement.executeUpdate(sql);
            System.out.println("DELETED: "+rows);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}