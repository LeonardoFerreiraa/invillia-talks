package com.invillia.jdbc;

public class Application {

    public static void main(String[] args) {
        final TeamDAO teamDAO = new TeamDAO();

        System.out.println(teamDAO.findAll());

        teamDAO.findById(4L)
                .ifPresent(System.out::println);

        teamDAO.deleteById(4L);

        teamDAO.findById(4L)
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("nao encontrado")
                );

    }

}
