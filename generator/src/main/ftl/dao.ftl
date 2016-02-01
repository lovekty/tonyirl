package ${daoPackage}${subPackage};

import org.springframework.stereotype.Repository;

import me.tonyirl.dao.mybatis.base.BaseMyBatisDao;
import ${entityClassName};

@Repository
public class ${entityClassSimpleName}Dao extends BaseDao<${entityClassSimpleName}, ${pkClassSimpleName}> {

}
