package com.camera.custom;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GaleriaAdapter extends RecyclerView.Adapter<GaleriaAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<Foto> fotos;

    private OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(Foto foto);
    }

    public GaleriaAdapter(Context context, ArrayList<Foto> fotos, OnItemClickListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.fotos = fotos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_row, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bind(fotos.get(position), listener);

        boolean escolhido = fotos.get(position).getEscolhido();

        if (escolhido){
            viewHolder.check.setVisibility(View.VISIBLE);
            viewHolder.unchek.setVisibility(View.GONE);
            viewHolder.image.setColorFilter(R.color.fotoSelecionada);
        } else{
            viewHolder.check.setVisibility(View.GONE);
            viewHolder.unchek.setVisibility(View.VISIBLE);
            viewHolder.image.setColorFilter(null);
        }

    }

    @Override
    public int getItemCount() {
        return fotos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private ImageView check;
        private ImageView unchek;


        public ViewHolder(View view) {
            super(view);

            image = view.findViewById(R.id.imagem);
            check = view.findViewById(R.id.check);
            unchek = view.findViewById(R.id.uncheck);
        }

        public void bind(final Foto foto, final OnItemClickListener listener) {

            File fotoAnterior = new File(foto.getCaminho());
            int w = image.getWidth();
            int h = image.getHeight();
            Bitmap bitmap = ImageResizeUtils.getResizedImage(Uri.fromFile(fotoAnterior), w, h, false);
            Bitmap fotoRotacionada = rotacionarFoto(fotoAnterior, bitmap);
            image.setImageBitmap(fotoRotacionada);

            image.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                    listener.onItemClick(foto);
                }
            });

        }
    }

    public static Bitmap rotacionarFoto(File file, Bitmap bitmap) {
        ExifInterface ei = null;
        try {
            ei = new ExifInterface(file.getAbsolutePath());


        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);

        Bitmap rotatedBitmap = null;
        switch (orientation) {

            case ExifInterface.ORIENTATION_ROTATE_90:
                rotatedBitmap = rotateImage(bitmap, 90);
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                rotatedBitmap = rotateImage(bitmap, 180);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                rotatedBitmap = rotateImage(bitmap, 270);
                break;

            case ExifInterface.ORIENTATION_NORMAL:
            default:
                rotatedBitmap = bitmap;
        }

        return rotatedBitmap;
    }

    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }

}
