package h2;

import dao.PampersDao;
import dao.h2.H2PampersDao;
import domain.Color;
import domain.Gender;
import domain.Pampers;
import domain.Size;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;

class H2PampersDaoTest {
    PampersDao pampersDao = new H2PampersDao();
    Pampers etalon = new Pampers(1, Size.SMALL, Color.WHITE, Gender.MALE);

    @Test
    public void get() {
        assertThat(pampersDao.get(1), is(etalon));
    }

    @Test
    public void getAll() {
        List<Pampers> all = pampersDao.getAll();
        assertThat(all, contains(etalon));
        assertThat(all.size(), is(1));
    }


}