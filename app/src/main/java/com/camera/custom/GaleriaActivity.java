package com.camera.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.camera.custom.dao.DaoSession;
import com.camera.custom.dao.FotoDao;

import java.util.ArrayList;

public class GaleriaActivity extends AppCompatActivity {

    private GaleriaAdapter galeriaAdapter;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        button = findViewById(R.id.btnSalvar);

        DaoSession daoSession = ((CustomApplication) getApplication()).getDaoSession();
        final FotoDao fotoDao = daoSession.getFotoDao();

        ArrayList<Foto> fotos = (ArrayList<Foto>) fotoDao.loadAll();


        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvNumbers);
        int numberOfColumns = 4;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        galeriaAdapter = new GaleriaAdapter(this, fotos, new GaleriaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Foto foto) {
                if (!foto.getEscolhido()){
                    foto.setEscolhido(true);
                    fotoDao.update(foto);
                    ArrayList<Foto> fotosSelecionadas =
                            (ArrayList<Foto>) fotoDao.queryBuilder().where(FotoDao.Properties.Escolhido.eq(true)).list();
                    String qtdFotos = String.valueOf(fotosSelecionadas.size());
                    if (fotosSelecionadas.size() > 0){
                        button.setText("Usar fotos " + qtdFotos + " selecionadas");
                    } else{
                        button.setText("Usar fotos selecionadas");
                    }
                    galeriaAdapter.notifyDataSetChanged();

                } else{
                    foto.setEscolhido(false);
                    fotoDao.update(foto);
                    ArrayList<Foto> fotosSelecionadas =
                            (ArrayList<Foto>) fotoDao.queryBuilder().where(FotoDao.Properties.Escolhido.eq(true)).list();
                    String qtdFotos = String.valueOf(fotosSelecionadas.size());
                    if (fotosSelecionadas.size() > 0){
                        button.setText("Usar fotos " + qtdFotos + " selecionadas");
                    } else{
                        button.setText("Usar fotos selecionadas");
                    }
                    galeriaAdapter.notifyDataSetChanged();
                }

            }
        });
        recyclerView.setAdapter(galeriaAdapter);

    }
}
