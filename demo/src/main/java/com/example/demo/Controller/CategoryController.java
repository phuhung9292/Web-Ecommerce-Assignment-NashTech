package com.example.demo.Controller;

import com.example.demo.Dto.CategoryDto;
import com.example.demo.Entity.TblCategoryEntity;
import com.example.demo.Entity.TblUserEntity;
import com.example.demo.Service.Category.CategoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    private ModelMapper modelMapper;
    private final CategoryService categoryService;
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        return ResponseEntity.ok().body(categoryService.findAll().stream().map(cate -> modelMapper.map(cate,CategoryDto.class)).collect(Collectors.toList()));
    }
    @PostMapping("/createcategory")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody TblCategoryEntity tblCategory){
        tblCategory.setIsActive(true);
        return ResponseEntity.ok().body(modelMapper.map(categoryService.save(tblCategory),CategoryDto.class));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            TblCategoryEntity category= categoryService.findById(id);
            category.setIsActive(false);
            categoryService.save(category);
            map.put("status", 1);
            map.put("message", "Record is deleted successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "This category is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int id){
        TblCategoryEntity category = categoryService.findById(id);
        CategoryDto categoryResponse = modelMapper.map(category,CategoryDto.class);
        return ResponseEntity.ok().body(categoryResponse);
    }
}
