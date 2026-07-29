
package com.example.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BancoDados {
    
    private static final String ARQUIVO_JSON ="produtos.json";

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void salvar(List<Produto> produtos) {
        try (Writer writer = new FileWriter(ARQUIVO_JSON)) {
            gson.toJson(produtos, writer);
            System.out.println("Dados salvo com sucesso no banco de dados!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar no banco de dados: "+ e.getMessage());
        }
    }

    public static List<Produto> carregar() {
        File arquivo = new File(ARQUIVO_JSON);

        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(ARQUIVO_JSON)) {
            Type tipoLista = new TypeToken<ArrayList<Produto>>(){}.getType();
            List<Produto> produtos = gson.fromJson(reader, tipoLista);

            return produtos != null ? produtos: new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados do banco: "+ e.getMessage());

            return new ArrayList<>();
        }
    }
}
