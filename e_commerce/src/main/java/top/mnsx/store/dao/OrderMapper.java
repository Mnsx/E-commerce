package top.mnsx.store.dao;

import top.mnsx.store.entity.Order;
import top.mnsx.store.entity.OrderItem;

public interface OrderMapper {
    Integer insertOrder(Order order);

    Integer insertOrderItem(OrderItem orderItem);
}
