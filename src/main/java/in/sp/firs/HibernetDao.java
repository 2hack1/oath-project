package in.sp.firs;

import java.util.List;

import javax.transaction.Transactional;

import org.dom4j.util.PerThreadSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class HibernetDao {
@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Transactional
	public void creatt(Enty hh) {
		this.hibernateTemplate.save(hh);
	}
	
	//get all user
	
	public List<Enty> getfiFirstClas(){
	List<Enty> clas=this.hibernateTemplate.loadAll(Enty.class);
	return clas;	
	
	}
	
	//delete single user
	@Transactional
	public void deleteUser(int uid) {
		FirstCla a=this .hibernateTemplate.load(FirstCla.class,uid);
	}
	
       //get the sigle sigle info of user
	@Transactional
	public void update(Enty enty) {
		
		this.hibernateTemplate.update(enty);
	}
	
	
}