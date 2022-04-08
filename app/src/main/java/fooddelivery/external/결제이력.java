package fooddelivery.external;

public class OrderList {

    private Long id;
    private String orderId;
    private Double price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Double getprice() {
        return price;
    }

    public void setprice(Double price) {
        this.price = price;
    }

}

