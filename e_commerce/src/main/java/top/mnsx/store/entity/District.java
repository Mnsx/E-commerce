package top.mnsx.store.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class District implements Serializable {
    private Integer id;
    private String parent;
    private String code;
    private String name;
}
