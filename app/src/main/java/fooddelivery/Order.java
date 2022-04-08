package fooddelivery;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String item;
    private Integer qty;
    private String status;
    private String macstore;
    private Long price;

    @PostPersist
    public void onPostPersist(){

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        MacDelivery.external.OrderList OrderList = new fooddelivery.external.OrderList();

        // this is Context Mapping (Anti-corruption Layer)
        OrderList.setOrderId(String.valueOf(getId()));
        if(getprice()!=null)
            OrderList.setprice(Double.valueOf(getprice()));

        Application.applicationContext.getBean(fooddelivery.external.OrderListService.class)
                .pay(OrderList);


    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    public Integer getqty() {
        return qty;
    }

    public void setqty(Integer qty) {
        this.qty = qty;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    public String getmacstore() {
        return macstore;
    }

    public void setmacstore(String macstore) {
        this.macstore = macstore;
    }

    public Long getprice() {
        return price;
    }

    public void setprice(Long price) {
        this.price = price;
    }
}
