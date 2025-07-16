package br.com.alura.domain;

public class Address
{
    public Address( String publicPlace, int number )
    {
        this.publicPlace = publicPlace;
        this.number      = number;
    }

    private String publicPlace;
    private int    number;
}
