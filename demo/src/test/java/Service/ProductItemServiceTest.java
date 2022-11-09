package Service;

import com.example.demo.Dto.*;
import com.example.demo.Entity.*;
import com.example.demo.Repository.IProductConfigRepository;
import com.example.demo.Repository.IProductItemRepository;
import com.example.demo.Repository.IProductRepository;
import com.example.demo.Repository.IVariation_OptionRepository;
import com.example.demo.Service.ProductItem.ProductServiceItemImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductItemServiceTest {

    @Mock
    IProductItemRepository productItemRepository;
    @InjectMocks
    ProductServiceItemImpl productServiceItem;
    @Mock
    ModelMapper modelMapper;
    @Mock
    TblProductItemEntity productItemMock;

    @Mock
    IProductRepository productRepository;
    @Mock
     IProductConfigRepository productConfigRepository;
    @Mock
    IVariation_OptionRepository r2;


    @Test
    void whenGetAllShouldReturnList(){
        List<TblProductItemEntity> productItemEntities = new ArrayList<>();
        TblProductItemEntity entity = new TblProductItemEntity();
        productItemEntities.add(entity);
        productItemEntities.add(entity);
        when(productItemRepository.findAll()).thenReturn(productItemEntities);
        List<TblProductItemEntity> listActual=productServiceItem.findAll();
        Assertions.assertEquals(productItemEntities,listActual);
    }
    @Test
    void whenSaveProductItem_ReturnProductIsExist() {
        TblProductItemEntity entity = new TblProductItemEntity();
        when(productItemRepository.findProducItemByProductIdAndSizeAndColor(1,2,3)).thenReturn(entity);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("status", 0);
        map.put("message", "this product already Exist");
        ResponseEntity<?> expected =  new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        ResponseEntity<?> actual = productServiceItem.save(entity,2,3,1);
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void whenSaveProductItemSuccess(){
        TblProductItemEntity entity = new TblProductItemEntity();
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        TblProductItemEntity entity1 = new TblProductItemEntity();
        TblVariationOptionEntity v1 = new TblVariationOptionEntity();
        TblVariationOptionEntity v2 = new TblVariationOptionEntity();
        int idV1=1;
        int idV2=2;
        map.put("status", 1);
        map.put("message", "update successfully!");
        Optional<TblVariationOptionEntity> v1Optional = Optional.of(v1);
        Optional<TblVariationOptionEntity> v2Optional = Optional.of(v2);
        when(productItemRepository.save(entity)).thenReturn(entity1);
        when(r2.findById(idV1)).thenReturn(v1Optional);
        when(r2.findById(idV2)).thenReturn(v2Optional);
        TblProductConfigurationEntity e3 = new TblProductConfigurationEntity();
        TblProductConfigurationEntity e4 = new TblProductConfigurationEntity();
        e3.setTblProductItemByProductItemId(entity1);
        e3.setTblVariationOptionByVariationOptionId(v1);
        e4.setTblProductItemByProductItemId(entity1);
        e4.setTblVariationOptionByVariationOptionId(v2);

        e3.setTblProductItemByProductItemId(entity1);
        e4.setTblProductItemByProductItemId(entity1);
//        when(productConfigRepository.save(e3)).thenReturn();
//        when(productConfigRepository.save(e4));
        ResponseEntity<?> expected =  new ResponseEntity<>(map, HttpStatus.OK);
        ResponseEntity<?> actual = productServiceItem.save(entity,1,2,3);
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void updateProductItemNotFound(){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        TblProductItemEntity entity = new TblProductItemEntity();
        map.put("status", 0);
        map.put("message", "Data is not found");
        ResponseEntity expected= new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        ResponseEntity actual = productServiceItem.updateProductItem(entity);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void updateProductItemSuccess(){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        TblProductItemEntity entity = new TblProductItemEntity();
        TblProductItemEntity entity2 = new TblProductItemEntity();
        Optional<TblProductItemEntity> entityOptional = Optional.of(entity);

        when(productItemRepository.findById(entity.getId())).thenReturn(entityOptional);
        when(productItemRepository.save(entity)).thenReturn(entity2);
        map.put("status", 1);
        map.put("message", "update successfully!");
//            ProductItemDto dto = new ;
        map.put("data",entity2);
        ResponseEntity expected= new ResponseEntity<>(map, HttpStatus.OK);
        ResponseEntity actual = productServiceItem.updateProductItem(entity);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void whenFindProductItemByProductIdAndSizeIdAndColor(){
        TblProductItemEntity entity = new TblProductItemEntity();

        when(productItemRepository.findProducItemByProductIdAndSizeAndColor(1,2,3)).thenReturn(entity);
        ProductItemDetail expected = modelMapper.map(entity,ProductItemDetail.class);
        ProductItemDetail actual = productServiceItem.findByProductIdAndSizeIdAndColor(1,2,3);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void whenGetListProductItem_ShouldReturnList(){
        List< TblProductItemEntity> entities = new ArrayList<>();

        when(productItemRepository.findAllByProductId(1)).thenReturn(entities);
        List<TblProductItemEntity> expected = new ArrayList<>(entities);
        List<TblProductItemEntity> actual = productServiceItem.adminGetProductItemById(1);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void whenFindByIdShouldReturnProduct(){
        TblProductItemEntity entity = new TblProductItemEntity();
        Optional<TblProductItemEntity> entityOptional = Optional.of(entity);

        when(productItemRepository.findById(1)).thenReturn(entityOptional);
        TblProductItemEntity actual = productServiceItem.findById(1);
        Assertions.assertEquals(entity,actual);
    }
    @Test
    void whenFindProducItemByProductId_ShouldReturnNotFound(){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("status", 0);
        map.put("message", "Data is not found");
//        List<TblProductItemEntity> listProductItem = new ArrayList<>();
        ResponseEntity<?> expected = new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        ResponseEntity<?> actual = productServiceItem.findByProductId(1);
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void whenFindProductItemShouldReturnProduct(){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        TblProductEntity product = new TblProductEntity();
        Optional<TblProductEntity> entityOptional = Optional.of(product);
        List<TblProductItemEntity> listProductItem = new ArrayList<>();
        TblProductItemEntity item1 = new TblProductItemEntity();
        TblProductItemEntity item2 = new TblProductItemEntity();
        item1.setId(1);
        item2.setId(2);
        listProductItem.add(productItemMock);

//        listProductItem.add(item1);

        when(productItemRepository.findAllByProductId(1)).thenReturn(listProductItem);
        TblProductItemEntity productItem=new TblProductItemEntity();
        Collection<TblProductConfigurationEntity> listconfig = new ArrayList<>();
        map.put("status", 1);
        map.put("message", " successfully!");
        List<sizeDto> listSize = new ArrayList<>();
        sizeDto size1 = new sizeDto(2,"green");
        listSize.add(size1);
        List<colorDto> listColor = new ArrayList<>();
        List<imageDto> listImage = new ArrayList<>();
//        List<TblProductItemEntity> productItem= new ArrayList<>();
        ProductItemDto productDto = new ProductItemDto();

        when( productItemMock.getTblProductConfigurationsById()).thenReturn(listconfig);
        when(productRepository.findById(1)).thenReturn(entityOptional);
        when(modelMapper.map(product,ProductItemDto.class)).thenReturn(productDto);
        productDto.setSize(listSize);
        productDto.setColor(listColor);
        productDto.setProductImage(listImage);
        map.put("data",productDto);
        ResponseEntity<?> expected = new ResponseEntity<>(map, HttpStatus.OK);
        ResponseEntity<?> actual = productServiceItem.findByProductId(1);
    Assertions.assertEquals(expected,actual);


    }
}


