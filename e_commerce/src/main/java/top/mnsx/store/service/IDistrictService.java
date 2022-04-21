package top.mnsx.store.service;

import top.mnsx.store.entity.District;

import java.util.List;

public interface IDistrictService {
    /**
     * 根据附带好来查询区域信息
     * @param parent 父代码
     * @return 多个区域的信息
     */
    List<District> getByParent(String parent);

    String getNameByCode(String code);
}
