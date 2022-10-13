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
@Table(name = "tbl_Cart_Item", schema = "dbo", catalog = "EcommerceNashTech")
public class TblCartItemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "cartid")
    private Integer cartid;
    @Basic
    @Column(name = "product_item_id")
    private Integer productItemId;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @Basic
    @Column(name = "isActive")
    private Boolean isActive;
    @ManyToOne(optional = false)
    @JoinColumn(name = "cartid", referencedColumnName = "id",insertable=false, updatable=false)
    private TblShoppingCartEntity tblShoppingCartByCartid;
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_item_id", referencedColumnName = "id",insertable=false, updatable=false)
    private TblProductItemEntity tblProductItemByProductItemId;


}
