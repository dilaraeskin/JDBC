package com.uniyaz;

import java.sql.*;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseIslemleri databaseIslemleri=new DatabaseIslemleri();

        boolean isBaglantiHazir = databaseIslemleri.baglantiyiKontrolEt();
        if (!isBaglantiHazir) {
            System.out.println("Bağlantı problemi var. Lütfen kontrol edin.");
        } else {

            System.out.println("1- Yeni bir kahramana ihtiyacımız olabilir.Eklemek ister misin?");
            System.out.println("2- Bayadır bir kahraman izlemedik sanki.Film eklemek ister misin?");
            System.out.println("3- Heroların Filmlerinin Toplam Bütçesi Listele");
            System.out.println("4- Herolar Toplam Kaç Filmde Oynadı Listele");
            System.out.println("5- Herolarda silinir..Silmek için seçiniz.");
            System.out.println("6- Çıkış Yap");

            while (true) {

                System.out.println("Hangi işlemi seçmek istersiniz?");

                int secim = scanner.nextInt();

                if (secim == 1) {

                    scanner.nextLine();
                    System.out.println("Hero Ad");
                    String heroAd = scanner.nextLine();

                    System.out.println("Hero Soyad");
                    String heroSoyad = scanner.nextLine();

                    Hero hero = new Hero();
                    hero.setHeroAd(heroAd);
                    hero.setHeroSoyad(heroSoyad);
                    databaseIslemleri.heroInsert(hero);


                } else if (secim == 2) {
                    databaseIslemleri.heroListele();
                    System.out.println("Hero Id:");
                    int movieHeroId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Movie Adı:");
                    String movieAd = scanner.nextLine();

                    System.out.println("Movie Budget:");
                    float budget = scanner.nextFloat();

                    Movie movie = new Movie();
                    movie.setMovieHeroId(movieHeroId);
                    movie.setMovieAd(movieAd);
                    movie.setBudget(budget);
                    databaseIslemleri.movieInsert(movie);


                } else if (secim == 3) {

                    databaseIslemleri.heroButcesiListele();

                }
                else if (secim == 4) {
                    databaseIslemleri.heroMovieSayısıListele();

                }
                else if (secim == 5) {
                    Hero hero=new Hero();
                    databaseIslemleri.heroListele();
                    System.out.println("Kimi kahramanlıktan men etmek istersin?");
                    System.out.println("Hero Id:");
                    int heroId = scanner.nextInt();
                    hero.setHeroId(heroId);
                    databaseIslemleri.heroDelete(hero);
                    System.out.println("Kahramanımız marveldan üzülerek ayrıldı :(");

                }
                else if (secim == 6) {
                    System.out.println("ÇIKIŞ");
                    break;
                }
            }
        }
        }



}
