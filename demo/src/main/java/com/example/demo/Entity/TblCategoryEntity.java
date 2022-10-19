package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "tbl_Category", schema = "dbo", catalog = "EcommerceNashTech")
public class TblCategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "typeCategory")
    private String typeCategory;
    @Basic
    @Column(name = "isActive")
    private Boolean isActive;
    @OneToMany(mappedBy = "tblCategoryByCategoryId")
    @JsonIgnore
    private Collection<TblProductEntity> tblProductsById;
    @OneToMany(mappedBy = "tblCategoryByCategoryId")
    @JsonIgnore
    private Collection<TblVariationEntity> tblVariationsById;



}
