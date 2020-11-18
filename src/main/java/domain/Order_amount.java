package domain;

public class Order_amount {
    private int Order_amount;
    private int Order_amount_count;

    public int getOrder_amount() {
        return Order_amount;
    }

    public void setOrder_amount(int order_amount) {
        Order_amount = order_amount;
    }

    public int getOrder_amount_count() {
        return Order_amount_count;
    }

    public void setOrder_amount_count(int order_amount_count) {
        Order_amount_count = order_amount_count;
    }

    @Override
    public String toString() {
        return "Order_amount{" +
                "Order_amount=" + Order_amount +
                ", Order_amount_count=" + Order_amount_count +
                '}';
    }
}
