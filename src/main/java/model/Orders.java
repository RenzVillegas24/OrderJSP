package model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "name")
    private String name;

    @Column(name = "order")
    private String order;
    
    @Column(name = "price")
    private double price;
    
    @Column(name = "qty")
    private int quantity;
    
    @Column(name = "total")
    private double total;

    @Column(name = "isPending")
    private int isPending;

    @Column(name = "date_time")
    private Timestamp dateTime;
    
    public Orders() {
        
    }
    
    public Orders(String name, String order, double price, int quantity, boolean isPending){
        this.name = name;
        this.order = order;
        this.price = price;
        this.quantity = quantity;
        this.total = price * quantity;
        this.isPending = isPending? 1:0;
        
    }

    
    
    public Orders(int id, String name, String order, double price, int quantity, boolean isPending, Timestamp dateTime){
        this.id = id;
        this.name = name;
        this.order = order;
        this.price = price;
        this.quantity = quantity;
        this.total = price * quantity;
        this.isPending = isPending? 1:0;
        this.dateTime = dateTime;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order){
        this.order = order;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getTotal() {
        return total;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isPending() {
        return isPending == 1;
    }

    public void setPending(Boolean isPending){
        this.isPending = isPending? 1:0;
    }

    public Timestamp getDateTime(){
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime){
        this.dateTime = dateTime;
    }
    
    
    @Override
    public String toString() {
        return "Orders [orderId=" + id + ", name=" + name + ", price=" + price
                + ", quantity=" + quantity + ", total=" + total + ", orderStatus=" + (isPending == 1 ? "true": "false") + "]";
    }
}
