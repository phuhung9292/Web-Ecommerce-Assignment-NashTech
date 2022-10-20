package com.example.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_Product_Item", schema = "dbo", catalog = "EcommerceNashTech")
public class TblProductItemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "product_id")
    private Integer productId;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @Basic
    @Column(name = "price")
    private Double price;
    @Basic
    @Column(name ="CreateDate")
    private Date createDate;
    @Basic
    @Column(name="updateDate")
    private Date updateDate;
    @Basic
    @Column(name = "product_image")
    private String productImage;
    @Basic
    @Column(name = "isActice")
    private Boolean isActice;
    @OneToMany(mappedBy = "tblProductItemByProductItemId")
    private Collection<TblCartItemEntity> tblCartItemsById;
    @OneToMany(mappedBy = "tblProductItemByProductItemId")
    private Collection<TblProductConfigurationEntity> tblProductConfigurationsById;
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id",insertable=false, updatable=false)
    private TblProductEntity tblProductByProductId;
    @OneToMany(mappedBy = "tblProductItemByProductItemId")
    private Collection<TblOrderHistoryEntity> tblOrderHistoriesById;


}
