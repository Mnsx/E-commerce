package top.mnsx.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mnsx.store.entity.Address;
import top.mnsx.store.service.IAddressService;
import top.mnsx.store.util.JsonResult;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @RequestMapping({"", "/"})
    public JsonResult<List<Address>> getAddressByUid(HttpSession session) {
        Integer uid = getuidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new JsonResult<>(OK, data);
    }

    @GetMapping("/{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        addressService.setDefault(
                aid,
                getuidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

    @RequestMapping("/{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid, HttpSession session) {
        addressService.delete(
                aid,
                getuidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

    @RequestMapping("/find_by_aid/{aid}")
    public JsonResult<Address> findByAid(@PathVariable("aid") Integer aid) {
        Address data = addressService.getByAid(aid);
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("/update_by_aid/{aid}")
    public JsonResult<Void> updateByAid(@PathVariable("aid") Integer aid, Address address, HttpSession session) {
        String username = getUsernameFromSession(session);
        address.setAid(aid);
        addressService.updateAddressByAid(address, username);
        return new JsonResult<>(OK);
    }
}
