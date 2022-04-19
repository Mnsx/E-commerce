package top.mnsx.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mnsx.store.entity.Address;
import top.mnsx.store.service.IAddressService;
import top.mnsx.store.util.JsonResult;

import javax.servlet.http.HttpSession;

@RequestMapping("addresses")
@RestController
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(address, username, uid);
        return new JsonResult<Void>(OK);
    }
}
