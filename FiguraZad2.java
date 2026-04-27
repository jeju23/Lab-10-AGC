package com.example.skomplikowane;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class Figura extends View {

    private int[] liczby_losowe = new int[100];

    public Figura(Context context, AttributeSet attrs) {
        super(context, attrs);
        java.util.Random r = new java.util.Random();
        for (int i = 0; i < 100; i++) {
            liczby_losowe[i] = r.nextInt(1000);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int szer = getWidth();
        int szer2 = szer / 2;
        int wys = getHeight();
        int rozmiar = (szer2 < wys ? szer2 : wys) - 10;
        int x, y, dx, dy;
        int idx = 0;

        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);


        p.setColor(Color.GRAY);
        canvas.drawRect(0, 0, szer - 1, wys - 1, p);

        CharSequence opis = getContentDescription();
        if (opis == null) opis = "brak";

        for (int i = 0; i < 10; i++) {
            p.setARGB(255,
                    liczby_losowe[idx++ % 100] % 256,
                    liczby_losowe[idx++ % 100] % 256,
                    liczby_losowe[idx++ % 100] % 256);

            if (opis.equals("kolo")) {
                dx = liczby_losowe[idx++ % 100] % rozmiar;
                x  = liczby_losowe[idx++ % 100] % (szer2 - dx);
                y  = liczby_losowe[idx++ % 100] % (wys - dx);
                canvas.drawCircle(x, y, dx, p);

            } else if (opis.equals("elipsa")) {
                dx = liczby_losowe[idx++ % 100] % rozmiar;
                dy = liczby_losowe[idx++ % 100] % rozmiar;
                x  = liczby_losowe[idx++ % 100] % (szer2 - dx);
                y  = liczby_losowe[idx++ % 100] % (wys - dy);
                RectF rect = new RectF(x, y, x + dx, y + dy);
                canvas.drawOval(rect, p);

            } else if (opis.equals("prostokat")) {
                dx = liczby_losowe[idx++ % 100] % rozmiar;
                dy = liczby_losowe[idx++ % 100] % rozmiar;
                x  = liczby_losowe[idx++ % 100] % (szer2 - dx);
                y  = liczby_losowe[idx++ % 100] % (wys - dy);
                RectF rect = new RectF(x, y, x + dx, y + dy);
                canvas.drawRect(rect, p);

            } else if (opis.equals("prostokat okragly")) {
                dx = liczby_losowe[idx++ % 100] % rozmiar;
                dy = liczby_losowe[idx++ % 100] % rozmiar;
                x  = liczby_losowe[idx++ % 100] % (szer2 - dx);
                y  = liczby_losowe[idx++ % 100] % (wys - dy);
                RectF rect = new RectF(x, y, x + dx, y + dy);
                canvas.drawRoundRect(rect, 10, 10, p);

            } else if (opis.equals("luk")) {
                dx = liczby_losowe[idx++ % 100] % rozmiar;
                dy = liczby_losowe[idx++ % 100] % rozmiar;
                x  = liczby_losowe[idx++ % 100] % (szer2 - dx);
                y  = liczby_losowe[idx++ % 100] % (wys - dy);
                RectF rect = new RectF(x, y, x + dx, y + dy);
                canvas.drawArc(rect,
                        liczby_losowe[idx++ % 100] % 360,
                        liczby_losowe[idx++ % 100] % 360,
                        false, p);

            } else if (opis.equals("linia")) {
                dx = liczby_losowe[idx++ % 100] % szer2;
                dy = liczby_losowe[idx++ % 100] % wys;
                x  = liczby_losowe[idx++ % 100] % szer2;
                y  = liczby_losowe[idx++ % 100] % wys;
                canvas.drawLine(x, y, dx, dy, p);
            }
        }


        p.setTextSize(getResources().getDimension(R.dimen.wys_napisu));
        p.setTextAlign(Paint.Align.RIGHT);
        p.setColor(Color.BLUE);
        canvas.drawText((String) opis, szer - 20, wys / 2, p);


        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(1);
        p.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, szer - 1, wys - 1, p);

        super.onDraw(canvas);
    }
}
