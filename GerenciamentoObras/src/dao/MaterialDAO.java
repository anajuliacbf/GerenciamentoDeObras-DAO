package dao;

import entity.Material;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MaterialDAO extends BaseDAO {

    public void inserir(Material material) {
        String sql = """
                insert into Material(Nome_Material, Quantidade) values(?, ?);
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1, material.getNomeMaterial());
            pre.setInt(2, material.getQuantidade());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(Material material) {
        String sql = """
                delete from Material where ID_Material = ?;
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, material.getId());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Material material) {
        String sql = """
                update Material set Nome_Material = ?, Quantidade = ? where ID_Material = ?;
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1, material.getNomeMaterial());
            pre.setInt(2, material.getQuantidade());
            pre.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Material> obterTodos() {
        List<Material> lista = new ArrayList<>();
        String sql = """
                select ID_Material, Nome_Material, Quantidade from Material
                order by Nome_Material asc
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Material m = new Material();
                m.setId(rs.getInt("ID_Material"));
                m.setNomeMaterial(rs.getString("Nome_Material"));
                m.setQuantidade(rs.getInt("setQuantidade"));
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Optional<Material> obterPeloId(int id) {
        String sql = """
                select ID_Material, Nome_Material, Quantidade from Material
                where ID_Material = ?
                """;
        try (Connection con = con();
             PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                Material m = new Material();
                m.setId(rs.getInt("ID_Material"));
                m.setNomeMaterial(rs.getString("Nome_Material"));
                m.setQuantidade(rs.getInt("Quantidade"));
                return Optional.of(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
