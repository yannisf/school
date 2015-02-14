package fraglab.registry.child;

import fraglab.data.GenericDao;

import java.util.List;

public interface ChildDao extends GenericDao<Child, String> {

    List<Child> fetchAll();

    List<Child> fetchClassroom(String id);
}
