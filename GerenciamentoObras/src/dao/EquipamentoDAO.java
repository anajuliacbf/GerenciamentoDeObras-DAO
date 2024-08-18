package dao;

import entity.Equipamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EquipamentoDAO extends BaseDAO {

    public void inserir(Equipamento equipamento) {
        String sql = """
                insert into Equipamento(Nome_Equipamento, Tipo) values(?, ?);
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1, equipamento.getNomeEquipamento());
            pre.setString(2, equipamento.getTipo());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(Equipamento equipamento) {
        String sql = """
                delete from Equipamento where ID_Equipamento = ?;
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, equipamento.getId());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Equipamento equipamento) {
        String sql = """
                update Equipamento set Nome_Equipamento = ?, Tipo = ? where ID_Equipamento = ?;
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1, equipamento.getNomeEquipamento());
            pre.setString(2, equipamento.getTipo());
            pre.setInt(3, equipamento.getId());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Equipamento> obterTodos() {
        List<Equipamento> lista = new ArrayList<>();
        String sql = """
                select ID_Equipamento, Nome_Equipamento, Tipo from Equipamento
                order by Nome_Equipamento asc
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            ResultSet rs = pre.executeQuery();
            // Existe o proximo?
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

    public Optional<Equipamento> obterPeloId(int id) {
        String sql = """
                select ID_Equipamento, Nome_Equipamento, Tipo from Equipamento
                where ID_Equipamento = ?
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                Equipamento e = new Equipamento();
                e.setId(rs.getInt("ID_Equipamento"));
                e.setNomeEquipamento(rs.getString("Nome_Equipamento"));
                e.setTipo(rs.getString("Tipo"));
                return Optional.of(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
