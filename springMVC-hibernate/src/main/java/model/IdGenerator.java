package model;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;

public class IdGenerator implements IdentifierGenerator {
    public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {
        Random random = new Random();
        String id = String.format("%04d", random.nextInt(10000));
        return  id;
    }
}
