package top.mnsx.store.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.store.entity.District;

import java.util.List;

@SpringBootTest
public class DistrictMapperTests {
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void testFindByParent() {
        List<District> list = districtMapper.findByParent("210100");
        for(District d : list) {
            System.out.println(d);
        }
    }

    @Test
    public void testFindNameByCode(){
        String name = districtMapper.findNameByCode("610000");
        System.out.println(name);
    }
}
