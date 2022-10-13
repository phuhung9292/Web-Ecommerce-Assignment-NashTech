package com.example.demo.Entity;

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
    @Basic
    @Column(name = "product_item_id")
    private Integer productItemId;
    @Basic
    @Column(name = "variation_option_id")
    private Integer variationOptionId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_item_id", referencedColumnName = "id",insertable=false, updatable=false)
    private TblProductItemEntity tblProductItemByProductItemId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "variation_option_id", referencedColumnName = "id",insertable=false, updatable=false)
    private TblVariationOptionEntity tblVariationOptionByVariationOptionId;


}
