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
@Table(name = "tbl_variation_option", schema = "dbo", catalog = "EcommerceNashTech")
public class TblVariationOptionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "variation_id")
    private Integer variationId;
    @Basic
    @Column(name = "value")
    private String value;
    @OneToMany(mappedBy = "tblVariationOptionByVariationOptionId")
    @JsonIgnore
    private Collection<TblProductConfigurationEntity> tblProductConfigurationsById;
    @ManyToOne(optional = false)
    @JoinColumn(name = "variation_id", referencedColumnName = "id",insertable=false, updatable=false)
    private TblVariationEntity tblVariationByVariationId;

}
