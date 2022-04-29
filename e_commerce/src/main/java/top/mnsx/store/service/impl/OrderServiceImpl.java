package top.mnsx.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mnsx.store.dao.OrderMapper;
import top.mnsx.store.entity.Address;
import top.mnsx.store.entity.Order;
import top.mnsx.store.entity.OrderItem;
import top.mnsx.store.service.IAddressService;
import top.mnsx.store.service.ICartService;
import top.mnsx.store.service.IOrderService;
import top.mnsx.store.service.ex.InsertException;
import top.mnsx.store.vo.CartVO;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;

    @Transactional
    @Override
    public Order create(Integer aid, Integer[] cids, Integer uid, String username) {
        Date now = new Date();

        List<CartVO> carts = cartService.getVOByCids(uid, cids);

        // 计算这些商品的总价
        long totalPrice = 0;
        for (CartVO cart : carts) {
            totalPrice += cart.getRealPrice() * cart.getNum();
        }

        // 创建订单数据对象
        Order order = new Order();
        order.setUid(uid);

        Address address = addressService.getByAid(aid, uid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        order.setTotalPrice(totalPrice);
        order.setStatus(0);
        order.setOrderTime(now);
        order.setCreatedUser(username);
        order.setCreatedTime(now);
        order.setModifiedUser(username);
        order.setModifiedTime(now);

        Integer rows1 = orderMapper.insertOrder(order);
        if (rows1 != 1) {
            throw new InsertException("插入订单数据时出现未知错误，请联系系统管理员");
        }

        for (CartVO cart : carts) {
            OrderItem item = new OrderItem();
            item.setOid(order.getOid());
            item.setPid(cart.getPid());
            item.setTitle(cart.getTitle());
            item.setImage(cart.getImage());
            item.setPrice(cart.getRealPrice());
            item.setNum(cart.getNum());
            item.setCreatedUser(username);
            item.setCreatedTime(now);
            item.setModifiedUser(username);
            item.setModifiedTime(now);

            Integer rows2 = orderMapper.insertOrderItem(item);
            if (rows2 != 1) {
                throw new InsertException("插入订单商品数据时出现未知错误，请联系系统管理员");
            }
        }

        return order;
    }
}
