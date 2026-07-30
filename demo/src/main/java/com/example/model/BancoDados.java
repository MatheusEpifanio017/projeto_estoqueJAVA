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
    
    
    private static final String ARQUIVO_PRODUTOS = "estoque.json";
    private static final String ARQUIVO_CLIENTES = "clientes.json";

    private static final Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .disableHtmlEscaping() 
        .create();


    public static void salvar(List<Produto> produtos) {
        try (Writer writer = new FileWriter(ARQUIVO_PRODUTOS)) {
            gson.toJson(produtos, writer);
            System.out.println("Produtos salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    public static List<Produto> carregar() {
        File arquivo = new File(ARQUIVO_PRODUTOS);

        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(ARQUIVO_PRODUTOS)) {
            Type tipoLista = new TypeToken<ArrayList<Produto>>(){}.getType();
            List<Produto> produtos = gson.fromJson(reader, tipoLista);

            return produtos != null ? produtos : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Erro ao carregar produtos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void salvarClientes(List<Cliente> clientes) {
        try (Writer writer = new FileWriter(ARQUIVO_CLIENTES)) {
            gson.toJson(clientes, writer);
            System.out.println("Clientes salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    public static List<Cliente> carregarClientes() {
        File arquivo = new File(ARQUIVO_CLIENTES);

        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(ARQUIVO_CLIENTES)) {
            Type tipoLista = new TypeToken<ArrayList<Cliente>>(){}.getType();
            List<Cliente> clientes = gson.fromJson(reader, tipoLista);

            return clientes != null ? clientes : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}