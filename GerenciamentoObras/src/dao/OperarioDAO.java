package dao;

import entity.Operario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OperarioDAO extends BaseDAO {

    public void inserir(Operario operario) {
        String sql = """
                insert into Operario(Nome_Operario, Funcao) values(?, ?);
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1, operario.getNomeOperario());
            pre.setString(2, operario.getFuncao());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(Operario operario) {
        String sql = """
                delete from Operario where ID_Operario = ?;
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, operario.getId());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Operario operario) {
        String sql = """
                update Operario set Nome_Operario = ?, Funcao = ? where ID_Operario = ?;
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1, operario.getNomeOperario());
            pre.setString(2, operario.getFuncao());
            pre.setInt(3, operario.getId());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Operario> obterTodos() {
        List<Operario> lista = new ArrayList<>();
        String sql = """
                select ID_Operario, Nome_Operario, Funcao from Operario
                order by Nome_Operario asc
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            ResultSet rs = pre.executeQuery();
            // Existe o proximo?
            while (rs.next()) {
                Operario o = new Operario();
                o.setId(rs.getInt("ID_Operario"));
                o.setNomeOperario(rs.getString("Nome_Operario"));
                o.setFuncao(rs.getString("Funcao"));
                lista.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Optional<Operario> obterPeloId(int id) {
        String sql = """
                select ID_Operario, Nome_Operario, Funcao from Operario
                where ID_Operario = ?
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            // Existe o proximo?
            if (rs.next()) {
                Operario o = new Operario();
                o.setId(rs.getInt("ID_Operario"));
                o.setNomeOperario(rs.getString("Nome_Operario"));
                o.setFuncao(rs.getString("Funcao"));
                return Optional.of(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
