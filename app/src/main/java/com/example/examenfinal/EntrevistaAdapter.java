package com.example.examenfinal;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfinal.config.Entrevista;
import com.example.examenfinal.config.Utils;

import java.util.List;

public class EntrevistaAdapter extends RecyclerView.Adapter<EntrevistaAdapter.EntrevistaViewHolder> {

    private Context context;
    private List<Entrevista> listaEntrevistas;

    public EntrevistaAdapter(Context context, List<Entrevista> listaEntrevistas) {
        this.context = context;
        this.listaEntrevistas = listaEntrevistas;
    }

    @NonNull
    @Override
    public EntrevistaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entrevista, parent, false);
        return new EntrevistaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntrevistaViewHolder holder, int position) {
        Entrevista entrevista = listaEntrevistas.get(position);

        // Asignar datos a las vistas en el ViewHolder
        holder.txtId.setText(entrevista.getId());
        holder.txtDescripcion.setText(entrevista.getDescripcion());
        Bitmap imagenBitmap = Utils.base64ToBitmap(entrevista.getImagenBase64());
        holder.imageViewEntrevista.setImageBitmap(imagenBitmap);

        // Puedes asignar otros datos según la estructura de tu vista
    }

    @Override
    public int getItemCount() {
        return listaEntrevistas.size();
    }

    public static class EntrevistaViewHolder extends RecyclerView.ViewHolder {
        TextView txtId, txtDescripcion; // Agrega otros TextViews u otros elementos de vista aquí
        ImageView imageViewEntrevista;

        public EntrevistaViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inicializa los TextViews u otros elementos de vista aquí
            txtId = itemView.findViewById(R.id.text_id); // R.id.text_id es un ejemplo, usa tu propio ID
            txtDescripcion = itemView.findViewById(R.id.textViewDescripcion); // R.id.textViewDescripcion es un ejemplo
            imageViewEntrevista = itemView.findViewById(R.id.imageViewEntrevista);
            // Agrega inicializaciones para otros elementos de la vista si los tienes
        }
    }
}
