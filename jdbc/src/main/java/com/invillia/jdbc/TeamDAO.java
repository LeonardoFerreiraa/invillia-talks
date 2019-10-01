package com.invillia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.postgresql.ds.PGConnectionPoolDataSource;
import org.postgresql.ds.PGSimpleDataSource;

public class TeamDAO {

    private final DataSource dataSource;

    public TeamDAO() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser("s2it_leferreira");
        dataSource.setPassword("123123");
        dataSource.setDatabaseName("incubadora_teams");
        this.dataSource = dataSource;
    }

    public void insert(final Team team) {
        try (
                final Connection connection = dataSource.getConnection();
                final PreparedStatement stmt = connection.prepareStatement(
                        "insert into team (name) values (?)"
                );
        ) {
            stmt.setString(1, team.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("deu ruim");
            throw new RuntimeException(e);
        }
    }

    public List<Team> findAll() {
        try (
                final Connection connection = dataSource.getConnection();
                final Statement stmt = connection.createStatement();
                final ResultSet rs = stmt.executeQuery("select id, name from team")
        ) {
            final List<Team> teams = new ArrayList<>();

            while (rs.next()) {
                final Team team = new Team(
                        rs.getLong("id"),
                        rs.getString("name")
                );
                teams.add(team);
            }

            return teams;
        } catch (SQLException e) {
            System.err.println("deu ruim");
            throw new RuntimeException(e);
        }
    }

    public Optional<Team> findById(final Long id) {
        try (
                final Connection connection = dataSource.getConnection();
                final PreparedStatement stmt = connection.prepareStatement(
                        "select id, name from team where id = ?"
                );
        ) {
            stmt.setLong(1, id);
            final ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(
                        new Team(
                                rs.getLong("id"),
                                rs.getString("name")
                        )
                );
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.err.println("deu ruim");
            throw new RuntimeException(e);
        }
    }

    public void update(final Long id, final UpdateTeamRequest updateTeamRequest) {
        try (
                final Connection connection = dataSource.getConnection();
                final PreparedStatement stmt = connection.prepareStatement(
                        "update team set name = ? where id = ?"
                )
        ) {
            stmt.setString(1, updateTeamRequest.getName());
            stmt.setLong(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("deu ruim");
            throw new RuntimeException(e);
        }
    }

    public void deleteById(final Long id) {
        try (
                final Connection connection = dataSource.getConnection();
                final PreparedStatement stmt = connection.prepareStatement(
                        "delete from team where id = ?"
                )
        ) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("deu ruim");
            throw new RuntimeException(e);
        }
    }

}
