package com.collegeportal.utils;

import com.collegeportal.entities.Certificate;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CertificatePdfUtil {

    @Value("${app.frontend-url}")
    private String frontendUrl;

    private final QrCodeUtil qrCodeUtil;

    public CertificatePdfUtil(QrCodeUtil qrCodeUtil) {
        this.qrCodeUtil = qrCodeUtil;
    }

    public byte[] generate(Certificate certificate) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document(new Rectangle(842, 595), 48, 48, 48, 48);
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 28);
            Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 14);

            Paragraph title = new Paragraph("Certificate of Participation", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));

            Paragraph text = new Paragraph(
                    certificate.getUser().getFullName() + " has successfully participated in " + certificate.getEvent().getTitle(), headingFont);
            text.setAlignment(Element.ALIGN_CENTER);
            document.add(text);
            document.add(new Paragraph(" "));

            Paragraph meta = new Paragraph(
                    "Certificate No: " + certificate.getCertificateNumber() + "\nVerification Code: " + certificate.getVerificationCode(), bodyFont);
            meta.setAlignment(Element.ALIGN_CENTER);
            document.add(meta);
            document.add(new Paragraph(" "));

            Image qrImage = Image.getInstance(qrCodeUtil.generateQrCode(frontendUrl + "/verify-certificate?code=" + certificate.getVerificationCode()));
            qrImage.scalePercent(70);
            qrImage.setAlignment(Element.ALIGN_CENTER);
            document.add(qrImage);

            Paragraph footer = new Paragraph("Scan the QR code or verify online.", bodyFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            document.close();
            return outputStream.toByteArray();
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to generate certificate PDF", ex);
        }
    }
}

