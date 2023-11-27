package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import database.Handler;

public class MainMenu {
    private Map<String, Double> itemPrices = new HashMap<>();
    private Handler handler;

    // constructor
    public MainMenu() {
        // initialize item prices
        itemPrices.put("Classic Milk Tea", 100.0);
        itemPrices.put("Wintermelon Milk Tea", 120.0);
        itemPrices.put("Taro Milk Tea", 110.0);
        itemPrices.put("Hokkaido Milk Tea", 130.0);
        itemPrices.put("Matcha Milk Tea", 140.0);
        itemPrices.put("Brown Sugar Milk Tea", 150.0);
        itemPrices.put("Classic Milk Tea with Pearls", 110.0);
        itemPrices.put("Wintermelon Milk Tea with Pearls", 130.0);
        itemPrices.put("Taro Milk Tea with Pearls", 120.0);
        itemPrices.put("Hokkaido Milk Tea with Pearls", 140.0);
        itemPrices.put("Matcha Milk Tea with Pearls", 150.0);
        itemPrices.put("Brown Sugar Milk Tea with Pearls", 160.0);

        // initialize database handler
        handler = new Handler();
    }

    public Map<String, Double> getItemPrices() {
        return itemPrices;
    }

    public void setItemPrices(Map<String, Double> itemPrices) {
        this.itemPrices = itemPrices;
    }

    public int addOrder(String name, String ord, int quantity) throws Exception {
        Orders order = new Orders(name, ord, itemPrices.get(ord), quantity, true);

        return handler.addOrder(order);    
    }

    public void setPendingStatus(int id, boolean isPending) throws Exception{
        handler.setPendingStatus(id, isPending);
    }

    public Orders selectOrder(int id) {
        return handler.selectOrder(id);
    }

    public List<Orders> getOrders(){
        return handler.selectAllOrders();
    }

    public List<Orders> getPendingOrders(){
        return handler.selectPendingOrders();
    }

    public List<Orders> getServedOrders(){
        return handler.selectServedOrders();
    }

    
}