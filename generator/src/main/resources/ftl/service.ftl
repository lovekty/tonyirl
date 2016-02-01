package ${servicePackage}${subPackage};

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.tonyirl.dao.mybatis.base.BaseMyBatisDao;
import me.tonyirl.service.base.BaseService;
import ${entityClassName};

@Service
@Transactional
public class ${entityClassSimpleName}Service extends BaseService<${entityClassSimpleName}, Long> {
	
	@Override
	@Resource(name="${entityBeanName}Dao")
	public void setBaseDao(BaseDao<${entityClassSimpleName}, ${pkClassSimpleName}> baseDao){
		this.baseDao = baseDao;
	}

}
