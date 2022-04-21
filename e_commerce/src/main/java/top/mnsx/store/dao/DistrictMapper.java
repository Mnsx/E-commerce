package top.mnsx.store.dao;

import top.mnsx.store.entity.District;

import java.util.List;

public interface DistrictMapper {
    /**
     * 根据父代号查询区域信息
     * @param parent 父代码
     * @return 某个区域线下的所有区域列表
     */
    List<District> findByParent(String parent);

    String findNameByCode(String code);
}
