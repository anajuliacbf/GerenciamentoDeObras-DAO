import dao.EngenheiroDAO;
import dao.EquipamentoDAO;
import dao.MaterialDAO;
import dao.OperarioDAO;
import dao.ProjetoDAO;

import entity.*;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        //
        // Engenheiro e Operario
        //
        Engenheiro engenheiro1 = new Engenheiro();
        engenheiro1.setId(1);
        engenheiro1.setNomeEngenheiro("Thallys");
        engenheiro1.setEspecialidade("Asfalto");

        Engenheiro engenheiro2 = new Engenheiro();
        engenheiro2.setId(2);
        engenheiro2.setNomeEngenheiro("Ana");
        engenheiro2.setEspecialidade("Calçada");

        Operario operario1 = new Operario();
        operario1.setId(1);
        operario1.setNomeOperario("Joao");
        operario1.setFuncao("Eletricista");

        Operario operario2 = new Operario();
        operario2.setId(2);
        operario2.setNomeOperario("Maria");
        operario2.setFuncao("Medidora");

        //
        // Materiais e Equipamento
        //
        Material material1 = new Material();
        material1.setId(1);
        material1.setNomeMaterial("piso");
        material1.setQuantidade(20);

        Material material2 = new Material();
        material2.setId(2);
        material2.setNomeMaterial("viga");
        material2.setQuantidade(10);

        Equipamento equipamento1 = new Equipamento();
        equipamento1.setId(1);
        equipamento1.setNomeEquipamento("furadeira");

        Equipamento equipamento2 = new Equipamento();
        equipamento2.setId(2);
        equipamento2.setNomeEquipamento("Martelo");

        //
        // Projeto
        //
        Projeto projeto1 = new Projeto();
        projeto1.setId(1);
        projeto1.setNomeProjeto("Construcao Predio");
        projeto1.setLocal("Joinville");
        projeto1.setDataInicio("19/08/2024");
        projeto1.setDataTermino("19/08/2025");

        //
        // Instanciando DAO
        //
        EngenheiroDAO engenheiro_dao = new EngenheiroDAO();
        ProjetoDAO projeto_dao = new ProjetoDAO();
        OperarioDAO operario_dao = new OperarioDAO();
        MaterialDAO material_dao = new MaterialDAO();
        EquipamentoDAO equipamento_dao = new EquipamentoDAO();

        //
        // Salvando no banco de dados
        //
        engenheiro_dao.inserir(engenheiro1);
        engenheiro_dao.inserir(engenheiro2);

        operario_dao.inserir(operario1);
        operario_dao.inserir(operario2);

        material_dao.inserir(material1);
        material_dao.inserir(material2);

        equipamento_dao.inserir(equipamento1);
        equipamento_dao.inserir(equipamento2);

        projeto_dao.inserir(projeto1);

        //
        // relacionando dados ao projeto
        //
        projeto_dao.alocarEngenheiro(projeto1, engenheiro1);
        projeto_dao.alocarEngenheiro(projeto1, engenheiro2);

        projeto_dao.alocarOperario(projeto1, operario1);
        projeto_dao.alocarOperario(projeto1, operario2);

        projeto_dao.salvarConsumoMaterial(projeto1, material1);
        projeto_dao.salvarConsumoMaterial(projeto1, material2);

        projeto_dao.salvarEquipamentoEmUso(projeto1, equipamento1);
        projeto_dao.salvarEquipamentoEmUso(projeto1, equipamento2);


        //
        //  Listando Dados
        //
        List<Engenheiro> engenheiros = projeto_dao.obterEngenheirosPorProjeto(projeto1);
        List<Operario> operarios = projeto_dao.obterOperariosPorProjeto(projeto1);

        List<Material> materiais = projeto_dao.obterConsumoMaterialPorProjeto(projeto1);
        List<Equipamento> equipamentos = projeto_dao.obterUsoEquipamentoPorProjeto(projeto1);

        System.out.println(projeto1);

        System.out.println(engenheiros);
        System.out.println(operarios);

        System.out.println(materiais);
        System.out.println(equipamentos);
    }
}