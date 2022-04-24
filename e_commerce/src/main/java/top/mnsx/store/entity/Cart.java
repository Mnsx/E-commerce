package top.mnsx.store.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Cart extends BaseEntity implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
}
