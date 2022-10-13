package com.example.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_shop_Order", schema = "dbo", catalog = "EcommerceNashTech")
public class TblShopOrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "orderDate")
    private Date orderDate;
    @Basic
    @Column(name = "shipping_address")
    private String shippingAddress;
    @Basic
    @Column(name = "total")
    private Double total;
    @Basic
    @Column(name = "userId")
    private Integer userId;
    @Basic
    @Column(name = "status")
    private Integer status;
    @OneToMany(mappedBy = "tblShopOrderByOrderId")
    private Collection<TblOrderHistoryEntity> tblOrderHistoriesById;
    @ManyToOne(optional = false)
    @JoinColumn(name = "userId", referencedColumnName = "id",insertable=false, updatable=false)
    private TblUserEntity tblUserByUserId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "status", referencedColumnName = "id",insertable=false, updatable=false)
    private TblOrderStatusEntity tblOrderStatusByStatus;


}
