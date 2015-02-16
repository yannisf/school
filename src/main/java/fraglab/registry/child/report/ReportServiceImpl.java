package fraglab.registry.child.report;

import fraglab.registry.child.Child;
import fraglab.registry.child.ChildService;
import fraglab.registry.common.Telephone;
import fraglab.registry.guardian.Guardian;
import fraglab.registry.guardian.GuardianService;
import fraglab.registry.relationship.Relationship;
import fraglab.registry.relationship.RelationshipService;
import fraglab.registry.school.SchoolDao;
import fraglab.registry.school.SchoolData;
import fraglab.web.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ChildService childService;

    @Autowired
    GuardianService guardianService;

    @Autowired
    RelationshipService relationshipService;

    @Autowired
    SchoolDao schoolDao;

    @Override
    public List<ReportChild> getReportChildrenForChildGroup(String childGroupId) throws NotFoundException {
        List<ReportChild> reportChildren = new ArrayList<>();
        List<Child> children = childService.fetchChildGroup(childGroupId);
        for (Child child : children) {
            reportChildren.add(mapChild(child));
        }

        return reportChildren;
    }

    private ReportChild mapChild(Child child) throws NotFoundException {
        ReportChild reportChild = new ReportChild(child.getInformalFullName());
        reportChild.setNotes(child.getNotes());
        List<Relationship> relationships = relationshipService.fetchRelationships(child.getId());
        for (Relationship relationship : relationships) {
            mapRelationship(reportChild, relationship);
        }

        return reportChild;
    }

    private void mapRelationship(ReportChild reportChild, Relationship relationship) throws NotFoundException {
        Guardian guardian = guardianService.fetch(relationship.getGuardianId());
        ReportGuardian reportGuardian = new ReportGuardian(guardian.getFullName(),
                relationship.getMetadata().getType(), relationship.getMetadata().getPickup());
        mapTelephones(guardian, reportGuardian);
        reportChild.addGuardian(reportGuardian);
    }

    private void mapTelephones(Guardian guardian, ReportGuardian reportGuardian) {
        for (Telephone telephone : guardian.getTelephones()) {
            reportGuardian.addTelephone(telephone.getNumber(), telephone.getType());
        }
    }

    @Override
    public SchoolData getSchoolDataForChildGroup(String childGroupId) {
        return schoolDao.fetchSchoolData(childGroupId);
    }

}
