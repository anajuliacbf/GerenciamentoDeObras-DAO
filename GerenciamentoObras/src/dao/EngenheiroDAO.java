package dao;

import entity.Engenheiro;
import entity.Projeto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EngenheiroDAO extends BaseDAO {

    public void inserir(Engenheiro engenheiro) {
        String sql = """
                insert into Engenheiro(Nome_Engenheiro, Especialidade) values(?, ?);
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1, engenheiro.getNomeEngenheiro());
            pre.setString(2, engenheiro.getEspecialidade());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(Engenheiro engenheiro) {
        String sql = """
                delete from Engenheiro where ID_Engenheiro = ?;
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, engenheiro.getId());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Engenheiro engenheiro) {
        String sql = """
                update Engenheiro set Nome_Engenheiro = ?, Especialidade = ? where ID_Engenheiro = ?;
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1, engenheiro.getNomeEngenheiro());
            pre.setString(2, engenheiro.getEspecialidade());
            pre.setInt(3, engenheiro.getId());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Engenheiro> obterTodos() {
        List<Engenheiro> lista = new ArrayList<>();
        String sql = """
                select ID_Engenheiro, Nome_Engenheiro, Especialidade from Engenheiro
                order by Nome_Engenheiro asc
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Engenheiro e = new Engenheiro();
                e.setId(rs.getInt("ID_Engenheiro"));
                e.setNomeEngenheiro(rs.getString("NomeEngenheiro"));
                e.setEspecialidade(rs.getString("Especialidade"));
                lista.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Optional<Engenheiro> obterPeloId(int id) {
        String sql = """
                select ID_Engenheiro, Nome_Engenheiro, Especialidade from Engenheiro
                where ID_Engenheiro = ?
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                Engenheiro e = new Engenheiro();
                e.setId(rs.getInt("ID_Engenheiro"));
                e.setNomeEngenheiro(rs.getString("NomeEngenheiro"));
                e.setEspecialidade(rs.getString("Especialidade"));
                return Optional.of(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


}
