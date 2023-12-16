package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.examenfinal.config.*;
import com.example.examenfinal.EntrevistaAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ActividadListaEntrevistas extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EntrevistaAdapter entrevistaAdapter;
    private List<Entrevista> listaEntrevistas; // Asegúrate de inicializar esta lista con tus datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_lista_entrevistas); // Reemplaza con el layout de tu actividad

        recyclerView = findViewById(R.id.recyclerViewEntrevistas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Llama al método para obtener las entrevistas de Firestore
        obtenerListaEntrevistas();

        // Crear el adaptador y asignarlo al RecyclerView
        entrevistaAdapter = new EntrevistaAdapter(this, listaEntrevistas);
        recyclerView.setAdapter(entrevistaAdapter);
    }

    private void obtenerListaEntrevistas() {
        listaEntrevistas = new ArrayList<>(); // Inicializa la lista vacía

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Entrevista")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Entrevista entrevista = document.toObject(Entrevista.class);
                            listaEntrevistas.add(entrevista);
                        }
                        // Luego de completar la lista, actualiza el RecyclerView
                        actualizarRecyclerView();
                    } else {
                        Toast.makeText(ActividadListaEntrevistas.this, "Error getting documents: " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void actualizarRecyclerView() {
        // Verifica si la lista no está vacía y actualiza el adaptador
        if (!listaEntrevistas.isEmpty()) {
            entrevistaAdapter = new EntrevistaAdapter(this, listaEntrevistas);
            recyclerView.setAdapter(entrevistaAdapter);
        } else {
            Toast.makeText(ActividadListaEntrevistas.this, "No hay datos disponibles", Toast.LENGTH_SHORT).show();
        }
    }

}
