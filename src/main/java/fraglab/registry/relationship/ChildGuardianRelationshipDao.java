package fraglab.registry.relationship;

import fraglab.GenericDao;
import fraglab.web.NotFoundException;

import java.util.List;

public interface ChildGuardianRelationshipDao extends GenericDao<ChildGuardianRelationship, String> {

    List<ChildGuardianRelationship> fetchAllForChild(String childId);

    ChildGuardianRelationship fetchForChildAndGuardian(String childId, String guardianId) throws NotFoundException;

}