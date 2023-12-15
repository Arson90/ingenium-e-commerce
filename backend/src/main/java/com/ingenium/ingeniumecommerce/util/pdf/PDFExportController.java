package com.ingenium.ingeniumecommerce.util.pdf;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class PDFExportController {
    private final PDFExportService pdfExportService;

    @GetMapping(value = "/ingenium/pdf/confirmation-order/{orderId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> exportPDF(@PathVariable final Long orderId) {
        final ByteArrayInputStream byteArrayInputStream = pdfExportService.exportPDF(orderId);
        return ResponseEntity
                .ok()
                .headers(getHeaders("order_confirmation"))
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }

    private HttpHeaders getHeaders(final String fileName) {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final String currentDateTime = dateFormat.format(new Date());
        var headers = new HttpHeaders();
        final String headerName = "Content-Disposition";
        final String headerValue = "attachment; filename=" + fileName + "_" + currentDateTime + ".pdf";
        headers.add(headerName, headerValue);
        return headers;
    }
}
