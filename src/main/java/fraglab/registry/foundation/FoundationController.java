package fraglab.registry.foundation;

import fraglab.registry.child.Child;
import fraglab.registry.foundation.meta.GroupDataTransfer;
import fraglab.registry.foundation.meta.GroupStatistics;
import fraglab.registry.foundation.meta.TreeElement;
import fraglab.web.BaseRestController;
import fraglab.web.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foundation")
public class FoundationController extends BaseRestController {

    @Autowired
    private FoundationService foundationService;

    @RequestMapping(method = RequestMethod.GET)
    public List<TreeElement> fetchSchoolTreeElements() {
        return foundationService.fetchSchoolTreeElements();
    }

    @RequestMapping(value = "/school", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdateSchool(@RequestBody School school) {
        foundationService.createOrUpdateSchool(school);
    }

    @RequestMapping(value = "/school", method = RequestMethod.GET)
    public List<School> fetchSchools() {
        return foundationService.fetchSchools();
    }

    @RequestMapping(value = "/school/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable String id) throws NotFoundException {
        foundationService.deleteSchool(id);
    }

    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public List<Department> fetchDepartmentsForSchool(@RequestParam(value = "schoolId", required = true) String schoolId) {
        return foundationService.fetchDepartmentsForSchool(schoolId);
    }

    @RequestMapping(value = "/department", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdateDepartmentForSchool(@RequestParam(value = "schoolId") String schoolId, @RequestBody Department department)
            throws NotFoundException {
        foundationService.createOrUpdateDepartmentForSchool(schoolId, department);
    }

    @RequestMapping(value = "/department/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteDepartment(@PathVariable String id) {
        foundationService.deleteDepartment(id);
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public List<Group> fetchGroupsForDepartment(@RequestParam(value = "departmentId", required = true) String departmentId)
            throws NotFoundException {
        return foundationService.fetchGroupsForDepartment(departmentId);
    }

    @RequestMapping(value = "/group", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdateGroup(@RequestBody Group group, @RequestParam(value = "departmentId") String departmentId)
            throws NotFoundException {
        foundationService.createOrUpdateGroupForDepartment(group, departmentId);
    }

    @RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteGroup(@PathVariable String id) {
        foundationService.deleteGroup(id);
    }

    @RequestMapping(value = "/group/{id}/child", method = RequestMethod.GET)
    public List<Child> fetchChildrenForGroup(@PathVariable String id) {
        return foundationService.fetchChildrenForGroup(id);
    }

    @RequestMapping(value = "/group/{id}/info", method = RequestMethod.GET)
    public GroupDataTransfer fetchSchoolData(@PathVariable String id) {
        return foundationService.fetchSchoolData(id);
    }

    @RequestMapping(value = "/group/{id}/statistics", method = RequestMethod.GET)
    public GroupStatistics fetchChildGroupStatistics(@PathVariable String id) {
        return foundationService.fetchChildGroupStatistics(id);
    }

}
