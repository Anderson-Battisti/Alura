public class Person
{
    public Person( Integer id, String name, String cpf )
    {
        this.id   = id;
        this.name = name;
        this.cpf  = cpf;
    }

    private Integer id;
    private String  name;
    private String  cpf;

    public String getName()
    {
        return name;
    }

    public String getCpf()
    {
        return cpf;
    }
}