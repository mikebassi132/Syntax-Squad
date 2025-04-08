package com.example.schoolboardapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PDFUtils {

    public static void generateTranscriptPDF(Context context, List<String> currentSemester, List<String> historySemester, String studentName) {
        PdfDocument document = new PdfDocument();
        Paint paint = new Paint();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create(); // A4 size

        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        int x = 40, y = 50;
        paint.setTextSize(16);
        paint.setFakeBoldText(true);
        canvas.drawText("Transcript - " + studentName, x, y, paint);

        paint.setFakeBoldText(false);
        paint.setTextSize(14);
        y += 40;

        canvas.drawText("Current Semester Courses:", x, y, paint);
        y += 25;

        for (String line : currentSemester) {
            canvas.drawText("• " + line, x + 20, y, paint);
            y += 20;
        }

        y += 30;
        canvas.drawText("Course History:", x, y, paint);
        y += 25;

        for (String line : historySemester) {
            canvas.drawText("• " + line, x + 20, y, paint);
            y += 20;
        }

        document.finishPage(page);

        File filePath = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "transcript.pdf");

        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(context, "Transcript PDF saved at: " + filePath.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error creating PDF: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        document.close();
    }
}
