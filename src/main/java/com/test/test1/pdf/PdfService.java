package com.test.test1.pdf;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    private static final Logger logger = LoggerFactory.getLogger(PdfService.class);
    public ByteArrayInputStream createPdf() {
         logger.info("pdf creating process started");
         String title="Creating Pdf Logic";
         String content="providing pdf content creation logic in details";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Document document = new Document();

        PdfWriter writer = PdfWriter.getInstance(document, outputStream);

        HeaderFooter footer = new HeaderFooter(true, new Phrase(" Page"));
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setBorderWidthBottom(0);
        document.setFooter(footer);

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE,15);
        Paragraph paragraph = new Paragraph(title,font);
            paragraph.setAlignment(Element.ALIGN_CENTER);

            document.add(paragraph);

        Font parafont = FontFactory.getFont(FontFactory.HELVETICA,10);
        Paragraph paragraphCnt = new Paragraph(content,parafont);
        paragraphCnt.setAlignment(Element.ALIGN_CENTER);

        document.add(paragraphCnt);
        document.add(new Chunk("This content is added after paragraph"));

        document.close();
        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
