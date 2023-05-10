package org.example;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;
public class App
{
    //find restaurant
    public void Restaurant(String location, Statement st) throws SQLException {
        String query = String.format("SELECT restaurant_name FROM restaurant WHERE restaurant_location = '%S'", location);
        System.out.println(query);
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            System.out.println(rs.getString(1));
        }
    }
    //menu
    public void Menu(String restaurant, Statement st) throws SQLException {
        String getRestaurantidQuery = String.format("SELECT food_item FROM menu WHERE restaurant_id IN (select restaurant_id from restaurant where restaurant_name = '%s') ", restaurant);
        ResultSet rs = st.executeQuery(getRestaurantidQuery);
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }

        public void order(int user_id, Statement st) throws  SQLException {
            String query = String.format("SELECT * FROM menu JOIN restaurant ON menu.restaurant_id = restaurant.restaurant_id");
            System.out.println(query);
            int j=1;
            int ch;
            while (true){
                int i = 1;
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter the choice: ");
                ch = sc.nextInt();
                ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    System.out.println(i+ "----->" +rs.getString(2)+ "   " + rs.getString(5)+ "   "+ rs.getString(6));
                    i++;
                }

                if(ch<i){
                    break;
                }
                else{
                    System.out.println("Wrong choice");
                }
            }


            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                if (j == ch){
                    String addQuery = String.format("INSERT INTO orders (customer_id, restaurant_id, menu_id) VALUES (%S, %S, %S)",user_id, rs.getInt(4), rs.getInt(1));
                    System.out.println(addQuery);
                    int result = st.executeUpdate(addQuery);
                    break;

                }
                j++;
            }

        }
        public void orderHistory(int user_id, Statement st) throws SQLException{
            String query = String.format("SELECT * from menu JOIN restaurant ON menu.restaurant_id = restaurant.restaurant_id\n" +
                    " WHERE menu.restaurant_id IN(SELECT restaurant_id FROM orders WHERE customer_id=%s)\n" +
                    " AND menu.menu_id IN(select menu_id FROM orders where customer_id=%s)\n", user_id, user_id);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                System.out.println(rs.getString(2)+ " " + rs.getString(5)+ " " +rs.getString(6));
            }
        }

    public static void main( String[] args ) throws ClassNotFoundException, SQLException {

        App app = new App();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_delivery", "root", "pass@word1");

        Statement st = connection.createStatement();
        app.Restaurant("kochi", st);
        app.Menu("RestA", st);
        app.order(2,st);
        app.orderHistory(2, st);







//

    }
}
