package top.mnsx.store.service;

import top.mnsx.store.entity.Order;

public interface IOrderService {
    Order create(Integer aid, Integer[] cids, Integer uid, String username);
}
