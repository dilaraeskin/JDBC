package com.uniyaz;

import java.sql.*;

public class DatabaseIslemleri {

    final static String JDBC_CONNECTION_STR = "jdbc:mysql://localhost:3306/marvel";
    final static String USERNAME = "root";
    final static String PASSWORD = "admin";

    boolean isBaglantiHazir = baglantiyiKontrolEt();

    static boolean baglantiyiKontrolEt() {

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD)) {

            if (conn != null) {
                return true;
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    /*  private static void movieDelete(Movie movie) {--------GEREK YOK-----

          String sql = "delete from movie where ID=?";

          try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
               PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

              preparedStatement.setInt(1, movie.getMovieId());

              int affectedRows = preparedStatement.executeUpdate();
              System.out.println(affectedRows + " satır silindi");
          } catch (SQLException e) {
              System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
          } catch (Exception e) {
              e.printStackTrace();
          }
      }*/
    static void heroDelete(Hero hero) {

        String sql = "delete from hero where ID=? ";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, hero.getHeroId());

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows + " satır silindi");
        } catch (SQLException e) {
            System.err.format("SQL State: %d", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void heroInsert(Hero hero) {

        String sql = "insert into hero (NAME, SURNAME) values (?, ?) ";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, hero.getHeroAd());
            preparedStatement.setString(2, hero.getHeroSoyad());

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows + " satır eklendi.");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void movieInsert(Movie movie) {

        String sql = "insert into movie (HERO_ID,MOVIE, BUDGET) " +
                "values (?, ?,?) ";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, movie.getMovieHeroId());
            preparedStatement.setString(2, movie.getMovieAd());
            preparedStatement.setFloat(3, movie.getBudget());

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows + " satır eklendi.");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void heroListele() {

        String sql = "Select * from hero";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int heroID = resultSet.getInt("ID");
                String heroAd = resultSet.getString("NAME");
                String heroSoyad = resultSet.getString("SURNAME");
                System.out.printf("%d - %s %s \n", heroID, heroAd, heroSoyad);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void heroButcesiListele() {

        String sql = "call get_hero_totalBudget()";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String heroAdSoyad = resultSet.getString("HERO");
                Float totalBudget = resultSet.getFloat("TOTAL_BUDGET");

                System.out.printf("%s %s \n", heroAdSoyad, totalBudget);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void heroMovieSayısıListele() {

        String sql = "call get_hero_MovieSayısı()";

        try (Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String heroAdSoyad = resultSet.getString("HERO");
                int movieCount = resultSet.getInt("MOVIE_COUNT");

                System.out.printf("%s %s \n", heroAdSoyad, movieCount);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
