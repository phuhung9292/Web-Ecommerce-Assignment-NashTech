package Service;

import com.example.demo.Dto.ProductDto;
import com.example.demo.EcommerceNashApplication;
import com.example.demo.Entity.TblProductEntity;
import com.example.demo.Repository.IProductRepository;
import com.example.demo.Service.Product.ProductService;
import com.example.demo.Service.Product.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    IProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ModelMapper modelMapper;


    @Test
    void whenGetAll_ShouldReturnList(){

        List<TblProductEntity> entities= new ArrayList<>();
        entities.add(new TblProductEntity());
        entities.add(new TblProductEntity());
        when(productRepository.findAll()).thenReturn(entities);
        List<ProductDto> listdto= entities.stream().map(product ->modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
        ResponseEntity<List<ProductDto>> expected = ResponseEntity.ok().body(listdto);
        ResponseEntity<List<ProductDto>> actual = productService.findAll();
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void whenFindByName_ShouldReturnList(){
        List<TblProductEntity> entities= new ArrayList<>();
        entities.add(new TblProductEntity());
        entities.add(new TblProductEntity());
        when(productRepository.findAllByNameContains("s")).thenReturn(entities);
        List<ProductDto> listdto= entities.stream().map(product ->modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
        ResponseEntity<List<ProductDto>> expected = ResponseEntity.ok().body(listdto);
        ResponseEntity<List<ProductDto>> actual = productService.findByName("s");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void whenFindProductByCateId_ShouldReTurnList(){
        List<TblProductEntity> entities= new ArrayList<>();
        entities.add(new TblProductEntity());
        entities.add(new TblProductEntity());
        when(productRepository.findTblProductEntitiesByCategoryId(1)).thenReturn(entities);
        List<ProductDto> listdto= entities.stream().map(product ->modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
        ResponseEntity<List<ProductDto>> expected = ResponseEntity.ok().body(listdto);
        ResponseEntity<List<ProductDto>> actual = productService.findProductsByCategory(1);
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void saveShouldReturnEnityty (){
        TblProductEntity entity = new TblProductEntity();
        TblProductEntity entity2 = new TblProductEntity();
        entity2.setIsActive(true);
        when(productRepository.save(entity2)).thenReturn(entity);
        ResponseEntity<TblProductEntity> expected = ResponseEntity.ok().body(entity);
        ResponseEntity<TblProductEntity> actual = productService.save(entity2);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void updateReturnNotFound(){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        TblProductEntity entity=new TblProductEntity();
        map.put("status", 0);
        map.put("message", "Data is not found");
        ResponseEntity expected= new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        ResponseEntity actual = productService.updateProduct(entity);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void updateReturnProduct(){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        TblProductEntity entity = new TblProductEntity();
        map.put("status", 1);
        map.put("message", "update successfully!");


        Optional<TblProductEntity> entityOptional = Optional.of(entity);
        when(productRepository.findById(entity.getId())).thenReturn(entityOptional);
        when(productRepository.save(entity)).thenReturn(entity);
        ProductDto dto= modelMapper.map(entity,ProductDto.class);
        map.put("data",dto);
        ResponseEntity expected= new ResponseEntity<>(map, HttpStatus.OK);
        ResponseEntity actual= productService.updateProduct(entity);
        Assertions.assertEquals(expected,actual);

    }


}
