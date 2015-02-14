package fraglab.registry.guardian;

import fraglab.data.GenericDaoImpl;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class GuardianDaoImpl extends GenericDaoImpl<Guardian, String> implements GuardianDao {

}
