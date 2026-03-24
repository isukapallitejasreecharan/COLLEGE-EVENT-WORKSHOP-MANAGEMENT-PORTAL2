package com.collegeportal.mappers;

import com.collegeportal.dto.CertificateDto;
import com.collegeportal.entities.Certificate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CertificateMapper {

    @Mapping(target = "eventTitle", expression = "java(certificate.getEvent().getTitle())")
    @Mapping(target = "recipientName", expression = "java(certificate.getUser().getFullName())")
    CertificateDto toDto(Certificate certificate);
}

