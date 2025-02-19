package top.mnsx.store.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.store.entity.Address;
import top.mnsx.store.service.ex.ServiceException;

@SpringBootTest
public class AddressServiceTests {
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

    @Test
    public void testSetDefault() {
        addressService.setDefault(5, 10, "mnsx");
    }

    @Test
    public void testDelete() {
        addressService.delete(4, 10, "mnsx");
    }

    @Test
    public void testGetByAid() {
        Address address = addressService.getByAid(7);
        System.out.println(address);
    }

    @Test
    public void testUpdateAddressByAid() {
        Address address = new Address();
        address.setAid(6);
        address.setName("肖恺琦");
        addressService.updateAddressByAid(address, "mnsx");
    }
}
