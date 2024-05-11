package com.example.recyclerviewwithfilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

public class CustomEditText extends AppCompatEditText {

    public CustomEditText(Context context) {
        super(context);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // Получаем изображение в исходном размере
        Drawable originalDrawable = ContextCompat.getDrawable(getContext(), R.drawable.baseline_search_24);

        if (originalDrawable != null) {
            // Применяем масштабирование к изображению
            Bitmap scaledBitmap = scaleBitmap(drawableToBitmap(originalDrawable), dpToPx(6), dpToPx(6));

            // Создаем Drawable из масштабированного Bitmap
            Drawable scaledDrawable = new BitmapDrawable(getResources(), scaledBitmap);

            // Устанавливаем изображение внутрь поля ввода
            setCompoundDrawablesWithIntrinsicBounds(null, null, scaledDrawable, null);
        }
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    public Bitmap scaleBitmap(Bitmap bitmap, int maxWidth, int maxHeight) {
        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();

        // Рассчитываем коэффициенты масштабирования для уменьшения размера изображения
        float widthScale = (float) maxWidth / originalWidth;
        float heightScale = (float) maxHeight / originalHeight;

        // Выбираем меньший коэффициент масштабирования, чтобы изображение не превышало максимальные размеры
        float scale = Math.min(widthScale, heightScale);

        // Создаем матрицу масштабирования
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        // Масштабируем изображение с помощью матрицы масштабирования
        return Bitmap.createBitmap(bitmap, 0, 0, originalWidth, originalHeight, matrix, true);
    }
}