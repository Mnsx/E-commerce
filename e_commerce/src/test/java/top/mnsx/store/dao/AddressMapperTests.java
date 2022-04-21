package top.mnsx.store.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.store.entity.Address;
import top.mnsx.store.entity.User;

import java.util.Date;
import java.util.List;

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
    public void testCountByUid() {
        Integer uid = 10;
        Integer count = addressMapper.countByUid(uid);
        System.out.println("count=" + count);
    }

    @Test
    public void testFindByUid() {
        List<Address> list = addressMapper.findByUid(10);
        System.out.println(list);
    }

    @Test
    public void testFindByAid() {
        System.out.println(addressMapper.findByAid(4));
    }

    @Test
    public void testUpdateNonDefault() {
        addressMapper.updateNonDefault(10);
    }

    @Test
    public void testUpdateDefaultByAid() {
        addressMapper.updateDefaultByAid(6, "Mnsx_x", new Date());
    }
}
