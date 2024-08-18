package entity;

public class Operario {
    private int id;
    private String nomeOperario;
    private String funcao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeOperario() {
        return nomeOperario;
    }

    public void setNomeOperario(String nomeOperario) {
        this.nomeOperario = nomeOperario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return "Operario{" +
                "id=" + id +
                ", nome='" + nomeOperario + '\'' +
                ", funcao='" + funcao + '\'' +
                '}';
    }
}

