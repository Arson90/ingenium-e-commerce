package com.ingenium.ingeniumecommerce.util.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

public final class FontUtil {
    public static final Font TITLE_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.NORMAL, BaseColor.BLACK);
    public static final Font GENERAL_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL, BaseColor.BLACK);
    public static final Font PARAGRAPH_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
    private FontUtil() {
    }
}
