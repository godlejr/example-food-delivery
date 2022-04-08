package fooddelivery;

public class OrderConfirmed extends AbstractEvent {

    private Long id;
    private String orderId;
    private Double price;

    public OrderConfirmed(){
        super();
    }

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
    public Double getprice() {
        return price;
    }

    public void setprice(Double price) {
        this.price = price;
    }
}
