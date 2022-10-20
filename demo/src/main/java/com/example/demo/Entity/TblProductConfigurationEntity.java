package com.example.demo.Entity;

import com.example.demo.Entity.ManyToManyId.TblProductConfigurationId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_Product_Configuration", schema = "dbo", catalog = "EcommerceNashTech")
public class TblProductConfigurationEntity {
    @EmbeddedId
    private TblProductConfigurationId id;

    @ManyToOne(optional = false)
    @JsonIgnore
    @JoinColumn(name = "product_item_id", referencedColumnName = "id",insertable=false, updatable=false)
    private TblProductItemEntity tblProductItemByProductItemId;
    @ManyToOne(optional = false)
//    @JsonIgnore
    @JoinColumn(name = "variation_option_id", referencedColumnName = "id",insertable=false, updatable=false)
    private TblVariationOptionEntity tblVariationOptionByVariationOptionId;


}
