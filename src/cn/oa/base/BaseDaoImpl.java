package cn.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T>{
	
	@Resource
	private SessionFactory sessionFactory;
	
	private Class<T> clazz;
	
	//反射来得到T的真实类型
	public BaseDaoImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
		System.out.println("-->  "+clazz);
	}
	/**
	 * 得到session
	 * @return
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T entity) {
		getSession().save(entity);
	}

	@Override
	public void delete(Long id) {
		Object obj = findById(id);
		if (obj != null) {
			getSession().delete(obj);
		}
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
	}

	@Override
	public T findById(Long id) {
		if (id == null) {
			return null;
		}
		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> findByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
//			return new ArrayList<T>();
			return Collections.EMPTY_LIST;
		}
		return getSession().createQuery("from "+clazz.getSimpleName()+" where id in (:ids)").setParameterList("ids", ids).list();

	}

	@Override
	public List<T> findAll() {
		return getSession().createQuery("from "+clazz.getSimpleName()).list();
	}

}
