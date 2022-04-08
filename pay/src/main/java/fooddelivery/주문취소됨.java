package fooddelivery;


public class 주문canceled extends AbstractEvent {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
