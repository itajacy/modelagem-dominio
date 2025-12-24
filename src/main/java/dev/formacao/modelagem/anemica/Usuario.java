package dev.formacao.modelagem.anemica;
// criando conforme o mercado

import java.util.Objects;

// classe tem um id unico
// isso aqui é uma sacola de getters e setters
// veja que nao tem comportamento nenhum
// procure no google por "anemic domain model" + martin fowler
 
public class Usuario {
    private String id;  
    private String nome;
    private String email;
    private String senha;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public int hashCode() {
        // final int prime = 31;
        // int result = 1;
        // result = prime * result + ((id == null) ? 0 : id.hashCode());
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    

}


