package com.invillia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TeamDAO {

    private final EntityManager entityManager;

    public TeamDAO() {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("incubadora_teams");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Team> findAll() {
        final TypedQuery<Team> query = entityManager.createQuery("from Team", Team.class);
        return query.getResultList();
    }

    public void insert(final Team team) {
        entityManager.getTransaction().begin();
        entityManager.persist(team);
        entityManager.getTransaction().commit();
    }

    public Team findById(final Long id) {
        return entityManager.find(Team.class, id);
    }

}
