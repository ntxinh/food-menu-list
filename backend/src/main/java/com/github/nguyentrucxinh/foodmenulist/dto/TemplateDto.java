package com.github.nguyentrucxinh.foodmenulist.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Builder
@Value
public class TemplateDto {

    private String templateName;
    private Map<String, Object> dataModel;
}
