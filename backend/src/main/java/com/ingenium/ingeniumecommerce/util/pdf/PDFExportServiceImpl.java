package com.ingenium.ingeniumecommerce.util.pdf;

import com.ingenium.ingeniumecommerce.customer.CustomerView;
import com.ingenium.ingeniumecommerce.order.OrderService;
import com.ingenium.ingeniumecommerce.order.OrderView;
import com.ingenium.ingeniumecommerce.orderEntry.OrderEntryView;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PDFExportServiceImpl implements PDFExportService{
    private final OrderService orderService;

    @Override
    public ByteArrayInputStream exportPDF(final Long orderId) {
        return this.generatePdfDocument(orderId);
    }

    private ByteArrayInputStream generatePdfDocument(final Long orderId) {
        final OrderView order = orderService.findOrderById(orderId);
        try {
            Document document = new Document();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, os);
            document.open();

            addTitle(document);
            addCustomerData(order, document);
            addOrderInfo(order, document);
            PdfPTable table = new PdfPTable(3);
            addTableHeader(table);
            order.getOrderEntries()
                    .forEach(entry -> addRows(table, entry));
            document.add(table);
            document.add(new Chunk(new LineSeparator()));
            addTotalPriceParagraph(order, document);

            document.close();
            return new ByteArrayInputStream(os.toByteArray());
        } catch (DocumentException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    private void addTitle(Document document) throws DocumentException {
        Paragraph title = new Paragraph(new Chunk("Order confirmation", FontUtil.TITLE_FONT));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Chunk(Chunk.NEWLINE));
    }

    private void addCustomerData(final OrderView order, Document document) throws DocumentException {
        final CustomerView customer = order.getCustomer();
        document.add(new Paragraph(new Chunk("Customer data:", FontUtil.GENERAL_FONT)));
        document.add(new Paragraph(new Chunk(customer.getFirstName() + " " + customer.getLastName(), FontUtil.PARAGRAPH_FONT)));
        document.add(new Paragraph(new Chunk(customer.getEmail(), FontUtil.PARAGRAPH_FONT)));
        document.add(new Paragraph(new Chunk(customer.getPhoneNumber(), FontUtil.PARAGRAPH_FONT)));
        document.add(new Chunk(Chunk.NEWLINE));
    }

    private void addOrderInfo(final OrderView order, Document document) throws DocumentException {
        Chunk orderId = new Chunk("Order #: " + order.getId(), FontUtil.PARAGRAPH_FONT);
        Chunk orderDate = new Chunk("Order date #: " + order.getDate(), FontUtil.PARAGRAPH_FONT);

        Paragraph paragraph = new Paragraph();
        paragraph.setTabSettings(new TabSettings(350f));
        paragraph.add(orderId);
        paragraph.add(Chunk.TABBING);
        paragraph.add(orderDate);

        document.add(paragraph);
        document.add(new Chunk(new LineSeparator()));
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("product name", "quantity", "price")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    header.setFixedHeight(20);
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, OrderEntryView entry) {
        table.addCell(entry.getProduct().getProductName());
        table.addCell(String.valueOf(entry.getQuantity()));
        table.addCell(String.valueOf(entry.getProduct().getPrice().getPrice()));
    }

    private void addTotalPriceParagraph(final OrderView order, Document document) throws DocumentException {
        String price = String.valueOf(order.getTotalPrice().getPrice());
        Chunk totalPrice = new Chunk("Total price: " + price, FontUtil.PARAGRAPH_FONT);
        Paragraph paragraph = new Paragraph();
        paragraph.add(totalPrice);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragraph);
    }
}
