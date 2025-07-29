package br.com.alura.domain;

public class Shelter
{
    private long   id;
    private String nome;
    private String telefone;
    private String email;
    private Pet[] pets;

    public Shelter() {}

    public Shelter( String name, String telephone, String email )
    {
        this.nome     = name;
        this.telefone = telephone;
        this.email    = email;
    }

    public long getId()
    {
        return id;
    }

    public String getNome()
    {
        return nome;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public String getEmail()
    {
        return email;
    }

    public Pet[] getPets()
    {
        return pets;
    }
}

