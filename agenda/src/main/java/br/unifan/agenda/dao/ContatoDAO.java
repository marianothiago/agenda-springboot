package br.unifan.agenda.dao;

import br.unifan.agenda.model.Contato;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class ContatoDAO {

    private static List<Contato> contatos = new ArrayList<Contato>();

    public static void salvar(Contato contato) {
        contatos.add(contato);
    }

    public static boolean atualizar(Contato contatoParaAtualizar, Contato novoContato) {
        int idx = contatos.indexOf(contatoParaAtualizar);
        if (idx != -1) {
            contatos.set(idx, novoContato);
            return true;
        }
        return false;
    }

    public static void atualizarPorChave(Contato novoContato) {
      contatos.replaceAll(
        c -> 
          c.getEmail().equalsIgnoreCase(novoContato.getEmail()) ? novoContato : c
      );
    }

    public boolean atualizarPorChaveComRetorno(Contato novoContato) {
        AtomicBoolean trocou = new AtomicBoolean(false);
        contatos.replaceAll(c -> {
            if (c.getEmail().equalsIgnoreCase(novoContato.getEmail())) {
                trocou.set(true);
                return novoContato;
            }
            return c;
        });
        if (!trocou.get()) {
            return false;
        }
        return true;
    }

    public static Contato obterPorEmail(String email) {
        return contatos.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public static Contato obterPorEmailComForEach(String email) {
        AtomicReference<Contato> encontrado = new AtomicReference<>(null);
        contatos.forEach(c -> {
            if (c.getEmail().equalsIgnoreCase(email)) {
                encontrado.set(c);
            }
        });
        return encontrado.get();
    }

    public static List<Contato> obterTodos(){
        return contatos;
    }

    public static boolean remover(String email){
        return contatos.removeIf(c -> c.getEmail().equalsIgnoreCase(email));
    }
}