package com.example.demo.Controller;

import com.example.demo.Dto.VariationDto;
import com.example.demo.Dto.VariationOptionDto;
import com.example.demo.Entity.TblVariationOptionEntity;
import com.example.demo.Repository.IVariation_OptionRepository;
import com.example.demo.Service.Variation.VariationService;
import com.example.demo.Service.VariationOption.VariationOptionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/variationoption")
@AllArgsConstructor
public class VariationOptionController {
    private VariationOptionService repository;
@Autowired
    private ModelMapper modelMapper;
    @GetMapping()
    public ResponseEntity<List<VariationOptionDto>> getAll(){
        return ResponseEntity.ok().body(repository.findAll().stream().map(option ->modelMapper.map(option,VariationOptionDto.class)).collect(Collectors.toList()));
    }
    @PostMapping("/create")
    public ResponseEntity<TblVariationOptionEntity> createNew(@RequestBody TblVariationOptionEntity entity){
//        TblVariationOptionEntity r = repository.save(entity);

        return ResponseEntity.ok().body(repository.save(entity));
    }
    @GetMapping("/{id}")
    public ResponseEntity<VariationOptionDto> getById(@PathVariable int id){
        return  ResponseEntity.ok().body(modelMapper.map(repository.findById(id),VariationOptionDto.class));
    }
    @GetMapping("/variation/{id}")
    public List<TblVariationOptionEntity> getByVariationid(@PathVariable int id){
        return repository.findAllByVariationId(id);
    }

    }
