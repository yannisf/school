package fraglab.registry.foundation;

import fraglab.registry.child.Child;
import fraglab.registry.foundation.meta.GroupDataTransfer;
import fraglab.registry.foundation.meta.GroupStatistics;
import fraglab.web.NotFoundException;

import java.util.List;

public interface FoundationService {

    GroupDataTransfer fetchSchoolData(String childGroupId);

    List<Child> fetchChildrenForGroup(String groupId);

    List<String> fetchChildrenIdsForGroup(String groupId);

    GroupStatistics fetchChildGroupStatistics(String childGroupId);

    void createOrUpdateSchool(School school);

    void createOrUpdateDepartment(Department department);

    void createOrUpdateGroup(Group group);

    List<School> fetchSchools();

    School fetchSchool(String id) throws NotFoundException;

    Department fetchDepartment(String id) throws NotFoundException;

    List<Department> fetchDepartmentsForSchool(String schoolId);

    void deleteSchool(String id);

    void createOrUpdateDepartmentForSchool(String schoolId, Department department) throws NotFoundException;

    List<Group> fetchGroupsForDepartment(String departmentId) throws NotFoundException;

    void deleteDepartment(String departmentId);

    void createOrUpdateGroupForDepartment(Group group, String departmentId) throws NotFoundException;

    void deleteGroup(String groupId);
}
