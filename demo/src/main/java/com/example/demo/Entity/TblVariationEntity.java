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
@Table(name = "tbl_variation", schema = "dbo", catalog = "EcommerceNashTech")
public class TblVariationEntity {
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
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "id",insertable=false, updatable=false)
    private TblCategoryEntity tblCategoryByCategoryId;
    @OneToMany(mappedBy = "tblVariationByVariationId")
    @JsonIgnore
    private Collection<TblVariationOptionEntity> tblVariationOptionsById;


}
