package top.mnsx.store.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderItem extends BaseEntity implements Serializable {
    private Integer id;
    private Integer oid;
    private Integer pid;
    private String title;
    private String image;
    private Long price;
    private Integer num;
}
