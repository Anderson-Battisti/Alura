package br.com.alura.domain;

public class Shelter
{
    private long   id;
    private String name;
    private String telephone;
    private String email;

    public Shelter( String name, String telephone, String email )
    {
        this.name      = name;
        this.telephone = telephone;
        this.email     = email;
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public String getEmail()
    {
        return email;
    }
}

