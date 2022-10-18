package com.example.demo.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString

@Table(name = "tbl_User", schema = "dbo", catalog = "EcommerceNashTech")
public class TblUserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "fullName")
    private String fullName;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name="password")
    private String password;
    @Basic
    @Column(name = "roleId")
    private Integer roleId;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "isActive")
    private Boolean isActive;
    @OneToMany(mappedBy = "tblUserByUserid")
    private Collection<TblRatingEntity> tblRatingsById;
    @ManyToOne(optional = false)
    @JoinColumn(name = "roleId", referencedColumnName = "id",insertable=false, updatable=false)
    private TblRoleEntity tblRoleByRoleId;
    @OneToMany(mappedBy = "tblUserByUserId")
    private Collection<TblShopOrderEntity> tblShopOrdersById;
    @OneToMany(mappedBy = "tblUserByUserid")
    private Collection<TblShoppingCartEntity> tblShoppingCartsById;


}
