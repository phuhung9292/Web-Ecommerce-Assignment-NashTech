package com.example.demo.Controller;

import com.example.demo.Dto.VariationDto;
import com.example.demo.Entity.TblUserEntity;
import com.example.demo.Entity.TblVariationEntity;
import com.example.demo.Repository.IVariationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/variation")
@RequiredArgsConstructor
public class VariationController {
    private final IVariationRepository variationRepository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/variations")
    public ResponseEntity<List<TblVariationEntity>> getAllVariations(){
//        return ResponseEntity.ok().body(variationRepository.findAll().stream().map(varia -> modelMapper.map(varia,VariationDto.class)).collect(Collectors.toList()));
        return ResponseEntity.ok().body(variationRepository.findAll());
    }
    @PostMapping("/createvariation")
    public ResponseEntity<TblVariationEntity> createVariation(@RequestBody TblVariationEntity tblVariation){

//        VariationDto variationDto = modelMapper.map(tblVariation,VariationDto.class);
        return ResponseEntity.ok().body(variationRepository.save(tblVariation));
    }
    @GetMapping("/{id}")
    public ResponseEntity<VariationDto> getVariationById(@PathVariable int id){
        TblVariationEntity variation = variationRepository.findById(id).get();
        VariationDto variationDto = modelMapper.map(variation,VariationDto.class);
        return ResponseEntity.ok().body(variationDto);
    }
    @PutMapping("/updatevaration")
    public ResponseEntity<?> updateVariable( @RequestBody TblVariationEntity entity ){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            variationRepository.save(entity);
            map.put("status", 1);
            map.put("message", "update successfully!");
            map.put("data",entity);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/cate/{id}")
    public List<TblVariationEntity> findByCateId(@PathVariable int id){
        return variationRepository.findAllByCategoryId(id);
    }
}
