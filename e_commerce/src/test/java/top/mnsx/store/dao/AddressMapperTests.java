package top.mnsx.store.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.store.entity.Address;
import top.mnsx.store.entity.User;

import java.util.Date;

@SpringBootTest
public class AddressMapperTests {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void testInsert(){
        Address address = new Address();
        address.setUid(10);
        address.setName("test2");
        address.setPhone("110");
        address.setAddress("经天路3号院");
        Integer rows = addressMapper.insert(address);
        System.out.println("rows=" + rows);
    }

    @Test
    public void countByUid() {
        Integer uid = 10;
        Integer count = addressMapper.countByUid(uid);
        System.out.println("count=" + count);
    }
}
