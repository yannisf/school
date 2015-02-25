package fraglab.registry.child;

import fraglab.data.GenericDao;
import fraglab.registry.address.AddressService;
import fraglab.registry.foundation.Group;
import fraglab.web.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ChildServiceImpl implements ChildService {

    @Autowired
    private GenericDao dao;

    @Autowired
    private AddressService addressService;

    @Override
    public void delete(String id) throws NotFoundException {
        Child child = fetch(id);
        dao.delete(child);
        updateGroupMembersNum(child.getGroup());
    }

    @Override
    public void createOrUpdate(Child child) {
        dao.createOrUpdate(child);
        updateGroupMembersNum(child.getGroup());
    }

    @Override
    public Child fetch(String id) throws NotFoundException {
        Child child = dao.fetch(Child.class, id);
        if (child == null) {
            throw new NotFoundException("Child not found");
        }

        return child;
    }

    @Override
    public List<Child> fetchChildrenForGroup(String id) {
        String query = "select c from Child c where c.group.id=:groupId";
        Map<String, Object> params = new HashMap<>();
        params.put("groupId", id);
        return  dao.findByQuery(Child.class, query, params);
    }

    @Override
    public Group fetchGroup(String id) {
        return dao.fetch(Group.class, id);
    }

    private void updateGroupMembersNum(Group group) {
        String query = "update Group g set g.members=(select count(c) from Child c where c.group=:group) where g=:group";
        Map<String, Object> params = new HashMap<>();
        params.put("group", group);
        dao.executeUpdate(query, params);
    }

}
