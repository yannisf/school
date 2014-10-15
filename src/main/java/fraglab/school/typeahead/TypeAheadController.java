package fraglab.school.typeahead;

import fraglab.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/typeahead")
public class TypeAheadController {

    @Autowired
    TypeAheadService typeAheadService;

    @RequestMapping(value = "/firstnames", method = RequestMethod.GET)
    public List<String> getMatchingFirstNames(@RequestParam(value = "search", required = true) String startsWith) {
        String reEncodedStartsWith = Utils.reEncodeString(startsWith);
        return typeAheadService.findMatchingFirstNames(reEncodedStartsWith);
    }

    @RequestMapping(value = "/lastnames", method = RequestMethod.GET)
    public List<String> getMatchingLastNames(@RequestParam(value = "search", required = true) String startsWith) {
        return typeAheadService.findMatchingLastNames(startsWith);
    }

    @RequestMapping(value = "/professions", method = RequestMethod.GET)
    public List<String> getMatchingProfessions(@RequestParam(value = "search", required = true) String startsWith) {
        return typeAheadService.findMatchingProfessions(startsWith);
    }

    @RequestMapping(value = "/nationalities", method = RequestMethod.GET)
    public List<String> getMatchingNationalities(@RequestParam(value = "search", required = true) String startsWith) {
        return typeAheadService.findMatchingNationalities(startsWith);
    }

}
