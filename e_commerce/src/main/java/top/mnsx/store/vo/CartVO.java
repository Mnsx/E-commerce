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
    private String title;
    private String image;
    private Long realPrice;
}
