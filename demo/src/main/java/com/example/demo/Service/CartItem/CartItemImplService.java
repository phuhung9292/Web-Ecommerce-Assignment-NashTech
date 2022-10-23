package com.example.demo.Service.CartItem;

import com.example.demo.Dto.ProductDetailOnCartDto;
import com.example.demo.Entity.TblCartItemEntity;
import com.example.demo.Entity.TblProductItemEntity;
import com.example.demo.Repository.ICartItemRepository;
import com.example.demo.Repository.IProductItemRepository;
import com.example.demo.Repository.IShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class CartItemImplService implements CartItemService{
    private ICartItemRepository repository;
    private IShoppingCartRepository cartShopingrepo;

    private IProductItemRepository Itemrepository;
    private ModelMapper modelMapper;


    @Override
    public ResponseEntity<?> save(TblCartItemEntity entity, int iduser, int productId, int variation1, int variation2) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        int cartId= cartShopingrepo.findTblShoppingCartEntityByUserid(iduser).getId();
        List<TblCartItemEntity> cart = repository.findAllByCartid(cartId);
        TblProductItemEntity productItem = Itemrepository.findProducItemByProductIdAndSizeAndColor(productId,variation1,variation2);
        if(entity.getQuantity() > productItem.getQuantity()){
            map.put("status",0);
            map.put("message","Not enough product");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
        if(cart.isEmpty()){
                entity.setCartid(cartId);
                entity.setProductItemId(productItem.getId());
                entity.setIsActive(true);
                repository.save(entity);
                map.put("status",1);
                map.put("message","Add success");
                return new ResponseEntity<>(map, HttpStatus.OK);

        }else {
            if(repository.findTblCartItemEntityByProductItemIdAndCartid(productItem.getId(),cartId)==null){
                entity.setCartid(cartId);
                entity.setProductItemId(productItem.getId());
                entity.setIsActive(true);
                repository.save(entity);
                map.put("status",1);
                map.put("message","Add success");
                return new ResponseEntity<>(map, HttpStatus.OK);
            }else {
                TblCartItemEntity oldItem = repository.findTblCartItemEntityByProductItemIdAndCartid(productItem.getId(),cartId);
                int quantityProduct = oldItem.getQuantity()+entity.getQuantity();
                if(quantityProduct > productItem.getQuantity()){
                    map.put("status",0);
                    map.put("message","Not enough product");
                    return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
                }
                oldItem.setQuantity(quantityProduct);
                repository.save(oldItem);
                map.put("status",1);
                map.put("message","Add success");
                return new ResponseEntity<>(map, HttpStatus.OK);
            }
        }

    }

    @Override
    public List<ProductDetailOnCartDto> findAllByCartid(int userId) {
        int cartId= cartShopingrepo.findTblShoppingCartEntityByUserid(userId).getId();
        List<ProductDetailOnCartDto> listCart = repository.findAllByCartid(cartId).stream().map(product ->(modelMapper.map(product,ProductDetailOnCartDto.class))).collect(Collectors.toList());
        for (ProductDetailOnCartDto p:listCart) {
            TblProductItemEntity item = Itemrepository.findTblProductItemEntityById(p.getProductItemId());
            p.setImage(item.getProductImage());
            p.setPrice(item.getPrice()*p.getQuantity());
            p.setActive(item.getIsActice());
        }
        return listCart;
    }

    @Override
    public ResponseEntity<?> deleteByProductId(int productid,int userid){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        int cartId= cartShopingrepo.findTblShoppingCartEntityByUserid(userid).getId();

        try{
            TblCartItemEntity cartItem =repository.findTblCartItemEntityByProductItemIdAndCartid(productid,cartId);
            repository.deleteById(cartItem.getId());
            map.put("status",1);
            map.put("message","Delete Success");
            return new ResponseEntity<>(map,HttpStatus.OK);
        }catch (Exception ex){
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> updateQuantityProductItemOnCart(TblCartItemEntity entity, int productid, int userid){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        int cartId= cartShopingrepo.findTblShoppingCartEntityByUserid(userid).getId();
        try{
            TblCartItemEntity cartItem =repository.findTblCartItemEntityByProductItemIdAndCartid(productid,cartId);
            cartItem.setQuantity(entity.getQuantity());
            repository.save(cartItem);
            map.put("status",1);
            map.put("message","Update Success");
            return new ResponseEntity<>(map,HttpStatus.OK);
        }catch (Exception ex){
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

}
