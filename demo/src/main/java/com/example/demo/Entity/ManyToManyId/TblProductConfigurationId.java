package com.example.demo.Entity.ManyToManyId;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
public class TblProductConfigurationId implements Serializable {
    @Column(name = "product_item_id")
    private Integer productItemId;
    @Column(name = "variation_option_id")
    private Integer variationOptionId;
}
