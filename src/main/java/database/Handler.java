package database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Orders;


public class Handler {
    static Connection connection = null;
    static PreparedStatement preparedStatement = null;
    
    private static final String SELECT_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ?";
    private static final String SELECT_ALL_ORDERS = "SELECT * FROM orders";
    private static final String SELECT_PENDING_ORDERS = "SELECT * FROM orders WHERE isPending = 1";
    private static final String SELECT_SERVED_ORDERS = "SELECT * FROM orders WHERE isPending = 0";
    private static final String UPDATE_PENDING_STATUS = "UPDATE orders SET isPending = ? WHERE id = ?";
    private static final String INSERT_ORDERS_SQL = "INSERT INTO orders (`name`, `order`, `price`, `qty`) VALUES (?, ?, ?, ?)";

    public Handler() {
        // initialize database handler
        try {
            connection = ConnectionHandler.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int addOrder(Orders order) throws SQLException {
        int result = 0;
        

        try {
            preparedStatement = connection.prepareStatement(INSERT_ORDERS_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, order.getName());
            preparedStatement.setString(2, order.getOrder());
            preparedStatement.setDouble(3, order.getPrice());
            preparedStatement.setInt(4, order.getQuantity());


            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                result = generatedKeys.getInt(1);
            } else {
                System.out.println("No ID obtained");
                result = -1;
            }
           
        } catch (SQLException e) {
            ConnectionHandler.printSQLException(e);
        }
        return result;
    }

    public int setPendingStatus(int id, boolean isPending) throws SQLException {
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_PENDING_STATUS);
            preparedStatement.setInt(1, isPending? 1:0);
            preparedStatement.setInt(2, id);
            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            ConnectionHandler.printSQLException(e);
        }
        return result;
    }

    public Orders selectOrder(int id) {
        Orders order = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String orderName = rs.getString("order");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("qty");
                boolean isPending = rs.getInt("isPending") == 1? true:false;
                Timestamp dateTime = rs.getTimestamp("date_time");
                order = new Orders(id, name, orderName, price, quantity, isPending, dateTime);
            }
        } catch (SQLException e) {
            ConnectionHandler.printSQLException(e);
        }
        return order;
    }

    public List<Orders> selectAllOrders() {
        List<Orders> orders = new ArrayList<>();
        
        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String orderName = rs.getString("order");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("qty");
                boolean isPending = rs.getInt("isPending") == 1? true:false;
                Timestamp dateTime = rs.getTimestamp("date_time");
                orders.add(new Orders(id, name, orderName, price, quantity, isPending, dateTime));
            }
        } catch (SQLException e) {
            ConnectionHandler.printSQLException(e);
        }
        return orders;
    }

    public List<Orders> selectPendingOrders() {
        List<Orders> orders = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SELECT_PENDING_ORDERS);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String orderName = rs.getString("order");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("qty");
                boolean isPending = true;
                Timestamp dateTime = rs.getTimestamp("date_time");
                orders.add(new Orders(id, name, orderName, price, quantity, isPending, dateTime));
            }
        } catch (SQLException e) {
            ConnectionHandler.printSQLException(e);
        }
        return orders;
    }

    public List<Orders> selectServedOrders() {
        List<Orders> orders = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SELECT_SERVED_ORDERS);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String orderName = rs.getString("order");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("qty");
                boolean isPending = false;
                Timestamp dateTime = rs.getTimestamp("date_time");
                orders.add(new Orders(id, name, orderName, price, quantity, isPending, dateTime));
            }
        } catch (SQLException e) {
            ConnectionHandler.printSQLException(e);
        }
        return orders;
    }

}
