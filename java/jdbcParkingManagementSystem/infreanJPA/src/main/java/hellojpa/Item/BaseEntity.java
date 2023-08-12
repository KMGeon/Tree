package hellojpa.Item;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
    private String createBy;
    private String hi;
}
