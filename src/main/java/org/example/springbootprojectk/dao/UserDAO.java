package org.example.springbootprojectk.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.springbootprojectk.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager em;

    public UserDAO() {
    }

    @Transactional
    public List<User> showUser() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Transactional
    public void addUser(User user) {
        em.persist(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = em.find(User.class, id);
        if(user != null){
            em.remove(user);
        }
    }

    @Transactional
    public void editUser(User user) {
        em.merge(user);
    }

    public User showUserById(Long id) {
        return em.find(User.class, id);
    }
}
