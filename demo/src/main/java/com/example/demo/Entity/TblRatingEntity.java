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
@Table(name = "tbl_Rating", schema = "dbo", catalog = "EcommerceNashTech")
public class TblRatingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "userid")
    private Integer userid;
    @Basic
    @Column(name = "ordered_productId")
    private Integer orderedProductId;
    @Basic
    @Column(name = "rating_value")
    private Integer ratingValue;
    @Basic
    @Column(name = "comment")
    private String comment;
    @Basic
    @Column(name = "isActice")
    private Boolean isActice;
    @ManyToOne(optional = false)
    @JoinColumn(name = "userid", referencedColumnName = "id",insertable=false, updatable=false)
    private TblUserEntity tblUserByUserid;
    @ManyToOne(optional = false)
    @JoinColumn(name = "ordered_productId", referencedColumnName = "id",insertable=false, updatable=false)
    private TblOrderHistoryEntity tblOrderHistoryByOrderedProductId;


}
