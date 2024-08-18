package dao;

import entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjetoDAO extends BaseDAO {

    public void inserir(Projeto projeto) {
        String sql = """
                insert into Projeto(ID_Projeto, Nome_Projeto, Local, Data_Inicio, Data_Termino) values(?, ?, ?, ?, ?);
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, projeto.getId());
            pre.setString(2, projeto.getNomeProjeto());
            pre.setString(3, projeto.getLocal());
            pre.setString(4, projeto.getDataInicio());
            pre.setString(5, projeto.getDataTermino());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(Projeto projeto) {
        String sql = """
                delete from Projeto where ID_Projeto = ?;
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, projeto.getId());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Projeto projeto) {
        String sql = """
                update Projeto set Nome_Projeto = ?, Local = ?, Data_Inicio = ?, Data_Termino = ? where ID_Projeto = ?;
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1, projeto.getNomeProjeto());
            pre.setString(2, projeto.getLocal());
            pre.setString(3, projeto.getDataInicio());
            pre.setString(4, projeto.getDataTermino());
            pre.setInt(5, projeto.getId());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Projeto> obterTodos() {
        List<Projeto> lista = new ArrayList<>();
        String sql = """
                select ID_Projeto, Nome_Projeto, Local, Data_Inicio, Data_Termino from Projeto
                order by Nome_Projeto asc
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Projeto p = new Projeto();
                p.setId(rs.getInt("ID_Projeto"));
                p.setNomeProjeto(rs.getString("Nome_Projeto"));
                p.setLocal(rs.getString("Local"));
                p.setDataInicio(rs.getString("Data_Inicio"));
                p.setDataTermino(rs.getString("Data_Termino"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Optional<Projeto> obterPeloId(int id) {
        String sql = """
                select ID_Projeto, Nome_Projeto, Local, Data_Inicio, Data_Termino from Projeto
                where ID_Projeto = ?
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                Projeto p = new Projeto();
                p.setId(rs.getInt("ID_Projeto"));
                p.setNomeProjeto(rs.getString("Nome_Projeto"));
                p.setLocal(rs.getString("Local"));
                p.setDataInicio(rs.getString("Data_Inicio"));
                p.setDataTermino(rs.getString("Data_Termino"));
                return Optional.of(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void salvarEquipamentoEmUso(Projeto projeto, Equipamento equipamento) {
        String sql = """
                insert into Uso_Equipamento(ID_Projeto, ID_Equipamento) values(?, ?);
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, projeto.getId());
            pre.setInt(2, equipamento.getId());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salvarConsumoMaterial(Projeto projeto, Material material) {
        String sql = """
                insert into Consumo_Material(ID_Projeto, ID_Material) values(?, ?);
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, projeto.getId());
            pre.setInt(2, material.getId());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alocarEngenheiro(Projeto projeto, Engenheiro engenheiro) {
        String sql = """
                insert into Alocacao_Engenheiro(ID_Projeto, ID_Engenheiro) values(?, ?);
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, projeto.getId());
            pre.setInt(2, engenheiro.getId());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alocarOperario(Projeto projeto, Operario operario) {
        String sql = """
                insert into Alocacao_Operario(ID_Projeto, ID_Operario) values(?, ?);
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, projeto.getId());
            pre.setInt(2, operario.getId());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Engenheiro> obterEngenheirosPorProjeto(Projeto projeto) {
        List<Engenheiro> lista = new ArrayList<>();
        String sql = """
                select Engenheiro.ID_Engenheiro, Engenheiro.Nome_Engenheiro, Engenheiro.Especialidade from Engenheiro
                JOIN main.Alocacao_Engenheiro AE on Engenheiro.ID_Engenheiro
                JOIN main.Projeto P on AE.ID_Projeto = P.ID_Projeto
                where P.ID_Projeto = ?;
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, projeto.getId());
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Engenheiro e = new Engenheiro();
                e.setId(rs.getInt("ID_Engenheiro"));
                e.setNomeEngenheiro(rs.getString("Nome_Engenheiro"));
                e.setEspecialidade(rs.getString("Especialidade"));
                lista.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Operario> obterOperariosPorProjeto(Projeto projeto) {
        List<Operario> lista = new ArrayList<>();
        String sql = """
                select  Operario.ID_Operario, Nome_Operario, Funcao from Operario
                JOIN main.Alocacao_Operario AO on Operario.ID_Operario = AO.ID_Operario
                JOIN main.Projeto P on AO.ID_Projeto = P.ID_Projeto
                where P.ID_Projeto = ?;
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, projeto.getId());
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Operario e = new Operario();
                e.setId(rs.getInt("ID_Operario"));
                e.setNomeOperario(rs.getString("Nome_Operario"));
                e.setFuncao(rs.getString("Funcao"));
                lista.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Material> obterConsumoMaterialPorProjeto(Projeto projeto) {
        List<Material> lista = new ArrayList<>();
        String sql = """
                select Material.ID_Material, Nome_Material, Quantidade from Material
                join main.Consumo_Material CM on Material.ID_Material = CM.ID_Material
                join main.Projeto P on CM.ID_Projeto = P.ID_Projeto
                where P.ID_Projeto = ?;
                """;

        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, projeto.getId());
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Material e = new Material();
                e.setId(rs.getInt("ID_Material"));
                e.setNomeMaterial(rs.getString("Nome_Material"));
                e.setQuantidade(rs.getInt("Quantidade"));
                lista.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Equipamento> obterUsoEquipamentoPorProjeto(Projeto projeto) {
        List<Equipamento> lista = new ArrayList<>();
        String sql = """
                select Equipamento.ID_Equipamento, Nome_Equipamento, Tipo from Equipamento
                join main.Uso_Equipamento UE on Equipamento.ID_Equipamento = UE.ID_Equipamento
                join main.Projeto P on UE.ID_Projeto = P.ID_Projeto
                where P.ID_Projeto = ?;
                """;

        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, projeto.getId());
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Equipamento e = new Equipamento();
                e.setId(rs.getInt("ID_Equipamento"));
                e.setNomeEquipamento(rs.getString("Nome_Equipamento"));
                e.setTipo(rs.getString("Tipo"));
                lista.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
