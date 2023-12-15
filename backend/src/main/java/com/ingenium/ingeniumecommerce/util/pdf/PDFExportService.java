package com.ingenium.ingeniumecommerce.util.pdf;

import java.io.ByteArrayInputStream;

public interface PDFExportService {
    ByteArrayInputStream exportPDF(final Long orderId);
}
