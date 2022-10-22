package com.example.demo.Service.ShoppingCart;

import com.example.demo.Repository.IShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService{
    private IShoppingCartRepository repository;

}
