package com.github.nguyentrucxinh.foodmenulist.dto;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class MailDto {

    private SenderDto senderDto;
    @Singular
    private List<RecipientDto> recipientDtos;
    private String subject;
    private String content;
    private String contentType;
    @Singular
    private List<FileAttachmentDto> fileAttachmentDtos;
    private boolean useTemplate;
    private TemplateDto templateDto;
}
