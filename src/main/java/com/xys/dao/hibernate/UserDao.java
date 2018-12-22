package com.xys.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

import com.xys.dao.IUserDao;
import com.xys.dto.hibernate.UserDto;
import com.xys.entity.hibernate.User;
import com.xys.util.Pagination;

@Repository("userDao")
public class UserDao extends ZbaseDao implements IUserDao {

	public Pagination findUsers(Pagination pager) {
		String ssql = " select u.id, u.createTime, u.editTime, u.age, u.username, u.birthday ";
		String where = " from user as u where 1=1 ";
		String csql = " select count(*) ";
		if(pager.getConditions() != null && pager.getConditions().size() > 0) {
			Set<String> keys = pager.getConditions().keySet();
			for(String key : keys) {
				if("eq_id".equals(key)) {
					where += " and u.id = " + pager.getConditions().get(key);
				}
			}
		}
		String cql = csql + where;
		Object obj = getSession().createSQLQuery(cql).uniqueResult();
		if(obj != null) {
			Long totalCount = Long.valueOf(obj.toString());
			pager.setTotalCount(totalCount);
			if(totalCount != null && totalCount > 0) {
				String order = " order by u.id desc ";
				String limit = " limit " + (pager.getPage()-1)*pager.getRows() + "," + pager.getRows();
				String sql = ssql + where + order + limit;
				List<UserDto> userDtos = getSession().createSQLQuery(sql)
						.addScalar("id", LongType.INSTANCE)
						.addScalar("createTime", TimestampType.INSTANCE)
						.addScalar("editTime", TimestampType.INSTANCE)
						.addScalar("birthday", TimestampType.INSTANCE)
						.addScalar("username")
						.addScalar("age", IntegerType.INSTANCE)
						.setResultTransformer(Transformers.aliasToBean(UserDto.class)).list();
				pager.setElements(userDtos);
			}
		}
		return pager;
	}

	public void insert(User user) {
		getSession().save(user);
	}

	public void deletes(Long[] ids) {
		if(ids != null && ids.length > 0) {
			String idsStr = "";
			for(Long l : ids) {
				idsStr += l + ",";
			}
			idsStr = idsStr.substring(0, idsStr.length()-1);
			String sql = " DELETE FROM user WHERE id in (" + idsStr + ") ";
			getSession().createSQLQuery(sql).executeUpdate();
		}
	}

	public void update(User user) {
		getSession().update(user);
	}

	public User fetch(Long id) {
		String hql = " FROM User as u where u.id = " + id;
		return (User) getSession().createQuery(hql).uniqueResult();
	}

	public UserDto fetchById(Long id) {
		String ssql = " select u.id, u.createTime, u.editTime, u.age, u.username, u.birthday ";
		String where = " from user as u where 1=1 ";
		where += " and u.id = " + id;
		String sql = ssql + where;
		UserDto userDto = (UserDto) getSession().createSQLQuery(sql)
							.addScalar("id", LongType.INSTANCE)
							.addScalar("createTime", TimestampType.INSTANCE)
							.addScalar("editTime", TimestampType.INSTANCE)
							.addScalar("birthday", TimestampType.INSTANCE)
							.addScalar("username")
							.addScalar("age", IntegerType.INSTANCE)
							.setResultTransformer(Transformers.aliasToBean(UserDto.class)).uniqueResult();
		return userDto;
	}

	
}
