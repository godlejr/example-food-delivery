package fooddelivery;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

@Entity
@Table(name="OrderTable_table")
public class OrderTable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String orderId;
    private Double price;
    private String status;

    @PrePersist
    public void onPrePersist(){

        if("cancel".equals(status)){
            OrderCanceled OrderCanceled = new OrderCanceled();
            BeanUtils.copyProperties(this, OrderCanceled);
            OrderCanceled.publish();

        }else{
            OrderConfirmed OrderConfirmed = new OrderConfirmed();
            BeanUtils.copyProperties(this, OrderConfirmed);

            //바로 이벤트를 보내버리면 주문정보가 커밋되기도 전에 배송발송ed 이벤트가 발송되어 주문테이블의 상태가 바뀌지 않을 수 있다.
            // TX 리스너는 커밋이 완료된 후에 이벤트를 발생하도록 만들어준다.
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void beforeCommit(boolean readOnly) {
                    OrderConfirmed.publish();
                }
            });


            try {
                Thread.currentThread().sleep((long) (400 + Math.random() * 220));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
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


    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }
}
