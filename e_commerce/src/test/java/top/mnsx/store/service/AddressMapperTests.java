package top.mnsx.store.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.store.entity.Address;
import top.mnsx.store.service.ex.ServiceException;

@SpringBootTest
public class AddressMapperTests {
    @Autowired
    private IAddressService addressService;

    @Test
    public void testAddNewAddress() {
        try {
            Integer uid = 9;
            String username = "test1";
            Address address = new Address();
            address.setUid(10);
            address.setName("test2");
            address.setPhone("110");
            address.setAddress("经天路3号院");
            addressService.addNewAddress(address, username, uid);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
