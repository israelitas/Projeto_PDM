package com.example.projeto_pdm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Repository extends SQLiteOpenHelper {
    private static final String NOME_DB = "conta";
    private static final int VERSION = 6;

    public Repository(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
        getWritableDatabase();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlUser = "CREATE TABLE usuario(\n" +
                "    id int not null,\n" +
                "    nome text,\n" +
                "    cpf text,\n" +
                "    senha text,\n" +
                "    saldo double,\n" +
                "    PRIMARY KEY (id)\n" +
                ")";
        sqLiteDatabase.execSQL(sqlUser);

        String sqlExtrato = "CREATE TABLE extrato(\n" +
                "    id_fk int,\n" +
                "    log text,\n" +
                "    FOREIGN KEY (id_fk)\n" +
                "    REFERENCES usuario (id)\n" +
                ")";
        sqLiteDatabase.execSQL(sqlExtrato);

        String sqlChaves = "CREATE TABLE chaves(\n" +
                "    id_fk int,\n" +
                "    chave text,\n" +
                "    usos int,\n" +
                "    FOREIGN KEY (id_fk)\n" +
                "    REFERENCES usuario (id)\n" +
                ")";
        sqLiteDatabase.execSQL(sqlChaves);

        String sqlAdd = "INSERT INTO `usuario`(`id`, `nome`, `cpf`, `senha`, `saldo`) VALUES ('1','Alisson','42','123','2100')";
        sqLiteDatabase.execSQL(sqlAdd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sqlUser = "DROP TABLE IF EXISTS usuario;";
        String sqlExtrato = "DROP TABLE IF EXISTS extrato;";
        String sqlChaves = "DROP TABLE IF EXISTS chaves;";
        sqLiteDatabase.execSQL(sqlUser);
        sqLiteDatabase.execSQL(sqlExtrato);
        sqLiteDatabase.execSQL(sqlChaves);
        onCreate(sqLiteDatabase);
    }

    public String getCpf(){
        String cpf;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT cpf FROM `usuario` WHERE 1", null);
        cursor.moveToFirst();
        cpf = cursor.getString(0);
        cursor.close();
        return cpf;
    }

    public String getSenha(){
        String senha;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT senha FROM `usuario` WHERE 1", null);
        cursor.moveToFirst();
        senha = cursor.getString(0);
        cursor.close();
        return senha;
    }

    public String getNome(){
        String nome;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nome FROM `usuario` WHERE 1", null);
        cursor.moveToFirst();
        nome = cursor.getString(0);
        cursor.close();
        return nome;
    }

    public Double getSaldo(){
        Double saldo;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT saldo FROM `usuario` WHERE 1", null);
        cursor.moveToFirst();
        saldo = cursor.getDouble(0);
        cursor.close();
        return saldo;
    }

    public void movimentacao(Double valorDeposito){
        String sql = "UPDATE `usuario` SET `saldo`='"+valorDeposito+"' WHERE 1";
        getWritableDatabase().execSQL(sql);
    }

    public void log(String log){
        String sql = "INSERT INTO `extrato`(`id_fk`, `log`) VALUES ('1','"+log+"')";
        getWritableDatabase().execSQL(sql);
    }

    public ArrayList<String> extrato(){
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT log FROM `extrato` WHERE 1", null);
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public void setChave(String chave){
        String sql = "INSERT INTO `chaves`(`id_fk`, `chave`) VALUES ('1','"+chave+"')";
        getWritableDatabase().execSQL(sql);
    }

    public ArrayList<String> getChaves(){
        ArrayList<String> list = new ArrayList<>(); // Agora é uma lista de Strings
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT chave FROM `chaves` WHERE 1", null);
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            list.add(cursor.getString(0)); // Obtém o valor como uma String
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    public void rmChave(String chave){
        String sql = "DELETE FROM `chaves` WHERE chave = "+chave;
        getWritableDatabase().execSQL(sql);
    }

    public ArrayList<Integer> getUsos(){
        ArrayList<Integer> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from chaves order by usos DESC LIMIT 2", null);
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            list.add(cursor.getInt(1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public int getUso(String chave){
        int uso;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select usos from chaves where chave = "+chave, null);
        cursor.moveToFirst();
        uso = cursor.getInt(0);
        cursor.close();
        return uso;
    }

    public void upDateUsos(String chave, int usos){
        String sql = "UPDATE `chaves` SET `usos`='"+usos+"' WHERE chave = "+chave;
        getWritableDatabase().execSQL(sql);
    }
}