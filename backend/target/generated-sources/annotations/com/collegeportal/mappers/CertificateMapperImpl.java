package com.collegeportal.mappers;

import com.collegeportal.dto.CertificateDto;
import com.collegeportal.entities.Certificate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-24T11:17:04+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 25.0.1 (Eclipse Adoptium)"
)
@Component
public class CertificateMapperImpl implements CertificateMapper {

    @Override
    public CertificateDto toDto(Certificate certificate) {
        if ( certificate == null ) {
            return null;
        }

        CertificateDto.CertificateDtoBuilder certificateDto = CertificateDto.builder();

        certificateDto.id( certificate.getId() );
        certificateDto.certificateNumber( certificate.getCertificateNumber() );
        certificateDto.verificationCode( certificate.getVerificationCode() );
        certificateDto.pdfUrl( certificate.getPdfUrl() );
        certificateDto.issuedAt( certificate.getIssuedAt() );
        certificateDto.emailed( certificate.getEmailed() );

        certificateDto.eventTitle( certificate.getEvent().getTitle() );
        certificateDto.recipientName( certificate.getUser().getFullName() );

        return certificateDto.build();
    }
}
