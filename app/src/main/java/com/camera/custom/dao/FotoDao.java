package com.camera.custom.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.camera.custom.Foto;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "FOTO".
*/
public class FotoDao extends AbstractDao<Foto, Long> {

    public static final String TABLENAME = "FOTO";

    /**
     * Properties of entity Foto.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Caminho = new Property(1, String.class, "caminho", false, "CAMINHO");
        public final static Property Pesquisa = new Property(2, int.class, "pesquisa", false, "PESQUISA");
        public final static Property Escolhido = new Property(3, boolean.class, "escolhido", false, "ESCOLHIDO");
    }


    public FotoDao(DaoConfig config) {
        super(config);
    }
    
    public FotoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FOTO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"CAMINHO\" TEXT," + // 1: caminho
                "\"PESQUISA\" INTEGER NOT NULL ," + // 2: pesquisa
                "\"ESCOLHIDO\" INTEGER NOT NULL );"); // 3: escolhido
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FOTO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Foto entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String caminho = entity.getCaminho();
        if (caminho != null) {
            stmt.bindString(2, caminho);
        }
        stmt.bindLong(3, entity.getPesquisa());
        stmt.bindLong(4, entity.getEscolhido() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Foto entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String caminho = entity.getCaminho();
        if (caminho != null) {
            stmt.bindString(2, caminho);
        }
        stmt.bindLong(3, entity.getPesquisa());
        stmt.bindLong(4, entity.getEscolhido() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Foto readEntity(Cursor cursor, int offset) {
        Foto entity = new Foto( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // caminho
            cursor.getInt(offset + 2), // pesquisa
            cursor.getShort(offset + 3) != 0 // escolhido
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Foto entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCaminho(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPesquisa(cursor.getInt(offset + 2));
        entity.setEscolhido(cursor.getShort(offset + 3) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Foto entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Foto entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Foto entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
