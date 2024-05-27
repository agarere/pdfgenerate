package com.evrimx.pdfgenerate.controller;

import com.evrimx.pdfgenerate.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
public class PdfController {

    private final PdfService pdfService;

    @Autowired
    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/generate-pdf")
    public ResponseEntity<InputStreamResource> generatePdf(@RequestParam String info1, @RequestParam String info2,
                                                           @RequestParam String info3, @RequestParam String info4) throws IOException {
        byte[] pdfBytes = pdfService.generatePdf(info1, info2, info3, info4);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=generated.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(inputStream));
    }
}


