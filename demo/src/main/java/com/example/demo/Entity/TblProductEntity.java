package com.example.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_Product", schema = "dbo", catalog = "EcommerceNashTech")
public class TblProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "category_id")
    private Integer categoryId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "product_image")
    private String productImage;
    @Basic
    @Column(name = "isActive")
    private Boolean isActive;
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "id",insertable=false, updatable=false)
    private TblCategoryEntity tblCategoryByCategoryId;
    @OneToMany(mappedBy = "tblProductByProductId")
    private Collection<TblProductItemEntity> tblProductItemsById;

}
