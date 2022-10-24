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
@Table(name = "tbl_shopping_cart", schema = "dbo", catalog = "EcommerceNashTech")
public class TblShoppingCartEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "userid")
    private Integer userid;
    @OneToMany(mappedBy = "tblShoppingCartByCartid")
    @JsonIgnore
    private Collection<TblCartItemEntity> tblCartItemsById;
    @ManyToOne(optional = false)
    @JoinColumn(name = "userid", referencedColumnName = "id",insertable=false, updatable=false)
    private TblUserEntity tblUserByUserid;

   }
