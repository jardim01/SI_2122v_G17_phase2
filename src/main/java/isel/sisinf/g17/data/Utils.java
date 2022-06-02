package isel.sisinf.g17.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class Utils {
    @SuppressWarnings("unchecked")
    public static <T> List<T> genericQuery(EntityManager em, String jpql, Object... params) {
        Query q = em.createQuery(jpql);
        for (int i = 0; i < params.length; ++i)
            q.setParameter(i + 1, params[i]);

        return (List<T>) q.getResultList();
    }
}
