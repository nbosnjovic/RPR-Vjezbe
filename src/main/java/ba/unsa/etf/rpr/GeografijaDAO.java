package ba.unsa.etf.rpr;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;


public class GeografijaDAO {
    private static GeografijaDAO instance;
    private Connection connection;

    private GeografijaDAO() {
        try {
            Class.forName("org.sqlite.JDBC");
            String bazaPutanja = System.getProperty("baza.putanja", "baza.db");
            String url = "jdbc:sqlite:" + bazaPutanja;

            File bazaFile = new File(bazaPutanja);
            boolean novaBaza = !bazaFile.exists();
            this.connection = DriverManager.getConnection(url);
            if (novaBaza) {
                kreirajTabele();
                popuniPodacima();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static GeografijaDAO getDao() {
        if (instance == null) {
            synchronized (GeografijaDAO.class) {
                    instance = new GeografijaDAO();
                }

        }
        return instance;
    }

    private void kreirajTabele() {
        String queryDrzava = "CREATE TABLE IF NOT EXISTS Drzava (" +
                "id INTEGER PRIMARY KEY," +
                "naziv TEXT," +
                "glavni_grad INTEGER," +
                "FOREIGN KEY (glavni_grad) REFERENCES Grad (id)" +
                ")";

        String queryGrad = "CREATE TABLE IF NOT EXISTS Grad (" +
                "id INTEGER PRIMARY KEY," +
                "naziv TEXT," +
                "broj_stanovnika INTEGER" +
                ")";

        try (Statement statement = connection.createStatement()) {
            statement.execute(queryDrzava);
            statement.execute(queryGrad);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void popuniPodacima() {
        try {
            String insertGradovi = "INSERT INTO Grad (naziv, broj_stanovnika) VALUES " +
                    "('Pariz', 2200000)," +
                    "('London', 8900000)," +
                    "('Beč', 1897000)," +
                    "('Manchester', 547627)," +
                    "('Graz', 280200)";

            String insertDrzave = "INSERT INTO Drzava (naziv, glavni_grad) VALUES " +
                    "('Francuska', 1)," +
                    "('Velika Britanija', 2)," +
                    "('Austrija', 3)," +
                    "('Velika Britanija', 4)," +
                    "('Austrija', 5)";

            try (Statement statement = connection.createStatement()) {
                statement.execute(insertGradovi);
                statement.execute(insertDrzave);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Grad glavniGrad(String drzava) {
        try {
            String query = "SELECT Grad.* FROM Grad, Drzava " +
                    "WHERE Drzava.naziv = ? AND Grad.id = Drzava.glavni_grad";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, drzava);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String naziv = resultSet.getString("naziv");
                        int brojStanovnika = resultSet.getInt("broj_stanovnika");
                        return new Grad(id, naziv, brojStanovnika);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void obrisiDrzavu(String drzava) {
        try {
            connection.setAutoCommit(false);

            String deleteDrzava = "DELETE FROM Drzava WHERE naziv = ?";
            String deleteGradovi = "DELETE FROM Grad WHERE id IN (SELECT glavni_grad FROM Drzava WHERE naziv = ?)";

            try (PreparedStatement preparedStatementDrzava = connection.prepareStatement(deleteDrzava);
                 PreparedStatement preparedStatementGradovi = connection.prepareStatement(deleteGradovi)) {
                preparedStatementDrzava.setString(1, drzava);
                preparedStatementDrzava.executeUpdate();

                preparedStatementGradovi.setString(1, drzava);
                preparedStatementGradovi.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Grad> gradovi() {
        ArrayList<Grad> gradovi = new ArrayList<>();

        try {
            String query = "SELECT * FROM Grad ORDER BY broj_stanovnika DESC";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String naziv = resultSet.getString("naziv");
                    int brojStanovnika = resultSet.getInt("broj_stanovnika");
                    Grad grad = new Grad(id, naziv, brojStanovnika);
                    gradovi.add(grad);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gradovi;
    }

    public void dodajGrad(Grad grad) {
        try {
            String query = "INSERT INTO Grad (naziv, broj_stanovnika) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, grad.getNaziv());
                preparedStatement.setInt(2, grad.getBrojStanovnika());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajDrzavu(Drzava drzava) {
        try {
            String query = "INSERT INTO Drzava (naziv, glavni_grad) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, drzava.getNaziv());
                preparedStatement.setInt(2, drzava.getGlavniGrad().getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void izmijeniGrad(Grad grad) {
        try {
            String query = "UPDATE Grad SET naziv = ?, broj_stanovnika = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, grad.getNaziv());
                preparedStatement.setInt(2, grad.getBrojStanovnika());
                preparedStatement.setInt(3, grad.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Drzava nadjiDrzavu(String drzava) {
        try {
            String query = "SELECT * FROM Drzava WHERE naziv = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, drzava);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String naziv = resultSet.getString("naziv");
                        int glavniGradId = resultSet.getInt("glavni_grad");
                        Grad glavniGrad = nadjiGrad(glavniGradId);
                        return new Drzava(id, naziv, glavniGrad);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Grad nadjiGrad(int id) {
        try {
            String query = "SELECT * FROM Grad WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String naziv = resultSet.getString("naziv");
                        int brojStanovnika = resultSet.getInt("broj_stanovnika");
                        String drzava = resultSet.getString("drzava");  // Dodajte ovo
                        return new Grad(id, naziv, brojStanovnika, drzava);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Grad nadjiGrad(String naziv) {
        try {
            String query = "SELECT * FROM Grad WHERE naziv = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, naziv);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        int brojStanovnika = resultSet.getInt("broj_stanovnika");
                        String drzava = resultSet.getString("drzava");  // Dodajte ovo
                        return new Grad(id, naziv, brojStanovnika, drzava);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
