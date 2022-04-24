package top.mnsx.store.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartVO implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String tile;
    private String image;
    private Long realPrice;
}
