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
@Table(name = "tbl_order_history", schema = "dbo", catalog = "EcommerceNashTech")
public class TblOrderHistoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "productItemId", nullable = false)
    private Integer productItemId;
    @Basic
    @Column(name = "orderId")
    private Integer orderId;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @Basic
    @Column(name = "prirce")
    private Double prirce;
    @OneToMany(mappedBy = "tblOrderHistoryByOrderedProductId")
    private Collection<TblRatingEntity> tblRatingsById;
    @ManyToOne(optional = false)
    @JoinColumn(name = "productItemId", referencedColumnName = "id", insertable = false, updatable = false)
    private TblProductItemEntity tblProductItemByProductItemId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "orderId", referencedColumnName = "id", insertable = false, updatable = false)
    private TblShopOrderEntity tblShopOrderByOrderId;

}