package com.example.food_marketplace.controllers;

import com.example.food_marketplace.domain.customField.CustomField;
import com.example.food_marketplace.dto.customField.CustomFieldRequestDTO;
import com.example.food_marketplace.service.CustomFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/customField")
public class CustomFieldController {

    @Autowired
    private CustomFieldService customFieldService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<CustomField> createCustomField(@RequestParam("name") String name,
                                                         @RequestParam("primary_color") String primary_color,
                                                         @RequestParam("secondary_color") String secondary_color,
                                                         @RequestParam(value = "logoUrl", required = false)MultipartFile logoUrl,
                                                         @RequestParam("font_name") String font_name,
                                                         @RequestParam("storeId") UUID storeId) {
        CustomFieldRequestDTO customFieldRequestDTO = new CustomFieldRequestDTO(name, primary_color, secondary_color, logoUrl, font_name, storeId);
        CustomField newCustomField = this.customFieldService.createCustomField(customFieldRequestDTO);
        return ResponseEntity.ok(newCustomField);
    }
}
