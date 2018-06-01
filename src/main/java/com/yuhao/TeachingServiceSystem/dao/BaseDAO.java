package com.yuhao.TeachingServiceSystem.dao;


import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;

import com.yuhao.TeachingServiceSystem.dto.UserDTO;
import com.yuhao.TeachingServiceSystem.model.BaseModel;
import com.yuhao.TeachingServiceSystem.model.User;
import com.yuhao.TeachingServiceSystem.util.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
public abstract class BaseDAO<T> implements Serializable{


    protected static Logger logger = Logger.getLogger(BaseDAO.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private DataSource dataSource;

    @SuppressWarnings("unchecked")
    protected Class<T> clz = (Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public BaseDAO() {
    }

    protected Session getSession() {
        try {
            return this.sessionFactory.getCurrentSession();
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }

    }


    @SuppressWarnings("unchecked")
    public T findOne(String hql, Object[] paras) {
        Page page = new Page();
        page.setPage(Integer.valueOf(1));
        page.setPageSize(Integer.valueOf(1));
        List<T> list = this.find(hql, paras, page);

        System.out.println("测试!!!");

        return CollectionUtils.isEmpty(list) ? null : list.get(0);


    }
    @SuppressWarnings("unchecked")
    public List<T> find(String hql, Object[] paras, Integer start, Integer limit) {
        Query<T> query = this.getSession().createQuery(hql);
        if (logger.isDebugEnabled()) {
            String str = "";
            str = str + "hql: " + hql;

            for(int i = 0; i < paras.length; ++i) {
                str = str + ", 参数 " + i + ": " + paras[i];
            }

            logger.debug(str);
        }

        if (paras != null) {
            for(int i = 0; i < paras.length; ++i) {
                query.setParameter(i, paras[i]);
            }
        }

        if (start != null && start.intValue() >= 0 && limit != null && limit.intValue() >= 0) {
            query.setFirstResult(start.intValue());
            query.setMaxResults(limit.intValue());
        }

        return query.list();
    }

    @SuppressWarnings("unchecked")
    public <K> K find2(String hql, Object[] paras, Page page) {
        Query query = this.getSession().createQuery(hql);
        int totalPages;
        if (logger.isDebugEnabled()) {
            String str = "";
            str +=  "hql: " + hql;
            for(totalPages = 0; totalPages < paras.length; ++totalPages) {

                str = str + ", 参数 " + totalPages + ": " + paras[totalPages];
            }

            logger.debug(str);
        }

        int totalCount;
        if (paras != null) {
            for(totalCount = 0; totalCount < paras.length; ++totalCount) {
                query.setParameter(totalCount, paras[totalCount]);
            }
        }

        if (page != null) {
            if (StringUtils.isNotBlank(page.getSort())) {

            }

            totalCount = this.queryTotalCount(hql, paras);
            page.setTotalRecords(totalCount);
            totalPages = totalCount % page.getPageSize().intValue() == 0 ? totalCount / page.getPageSize().intValue() : totalCount / page.getPageSize().intValue() + 1;
            page.setTotalPages(totalPages);
            query.setFirstResult((page.getPage().intValue() - 1) * page.getPageSize().intValue());
            query.setMaxResults(page.getPageSize().intValue());
        }

        return (K) query.list();
    }

    @SuppressWarnings("unchecked")
    public List<T> find(String hql, Object[] paras, Page page) {
        Query<T> query = this.getSession().createQuery(hql);
        int totalPages;
        if (logger.isDebugEnabled()) {
            String str = "";
            str = str + "hql: " + hql;

            for(totalPages = 0; totalPages < paras.length; ++totalPages) {
                str = str + ", 参数 " + totalPages + ": " + paras[totalPages];
            }

            logger.debug(str);
        }

        int totalCount;
        if (paras != null) {
            for(totalCount = 0; totalCount < paras.length; ++totalCount) {
                query.setParameter(totalCount, paras[totalCount]);
            }
        }

        if (page != null) {
            if (StringUtils.isNotBlank(page.getSort())) {
                ;
            }

            totalCount = this.queryTotalCount(hql, paras);
            page.setTotalRecords(totalCount);
            totalPages = totalCount % page.getPageSize().intValue() == 0 ? totalCount / page.getPageSize().intValue() : totalCount / page.getPageSize().intValue() + 1;
            page.setTotalPages(totalPages);
            query.setFirstResult((page.getPage().intValue() - 1) * page.getPageSize().intValue());
            query.setMaxResults(page.getPageSize().intValue());
        }

        return query.list();
    }

    @SuppressWarnings("unchecked")
    public int queryTotalCount(String hql, Object[] paras) {
        int beginPos = hql.toLowerCase().indexOf("from");
        String countHql = "select count(*) " + hql.substring(beginPos);
        Query<Object> query = this.getSession().createQuery(countHql);
        if (paras != null) {
            for(int i = 0; i < paras.length; ++i) {
                query.setParameter(i, paras[i]);
            }
        }

        return Integer.parseInt(query.uniqueResult().toString());
    }

    public Long create(T t) {
        return (Long)this.getSession().save(t);
    }

    public T load(Serializable id) {
        return this.getSession().load(this.clz, id);
    }

    public T get(Serializable id) {
        return this.getSession().get(this.clz, id);
    }



    public void update(T t) {

        this.getSession().update(t);

    }

    public void deleteById(Serializable id) {
        this.getSession().delete(this.load(id));
    }

    @SuppressWarnings("unchecked")
    public int executeUpdate(String hql, Object[] paras) {
        Query<Object> query = this.getSession().createQuery(hql);
        if (paras != null) {
            for(int i = 0; i < paras.length; ++i) {
                query.setParameter(i, paras[i]);
            }
        }

        return query.executeUpdate();
    }

    public int executeSQLUpdate(String sql, Object[] paras) {
        NativeQuery sqlQuery = this.getSession().createNativeQuery(sql);
        if (paras != null) {
            for(int i = 0; i < paras.length; ++i) {
                sqlQuery.setParameter(i, paras[i]);
            }
        }

        return sqlQuery.executeUpdate();
    }

    private SessionFactoryImplementor getSessionFactoryImplementor() {
        SessionFactoryImpl sfi = (SessionFactoryImpl)this.getSession().getSessionFactory();
        return sfi;
    }

    public void deleteAll() {
        this.getSession().createNativeQuery("delete from `" + this.clz.getSimpleName().toLowerCase() + "`").executeUpdate();
    }

    protected void appendManagerSql(StringBuilder hql, List<Object> paras) {
    }

    protected Object executeSQLQueryUniqueResult(String sql, List<? extends Object> paras) {
        NativeQuery sqlQuery = this.getSession().createNativeQuery(sql);
        if (CollectionUtils.isNotEmpty(paras)) {
            for(int i = 0; i < paras.size(); ++i) {
                sqlQuery.setParameter(i, paras.get(i));
            }
        }

        return sqlQuery.uniqueResult();
    }

    protected List<Object[]> executeSQLQuery(String sql, List<? extends Object> paras) {
        NativeQuery sqlQuery = this.getSession().createNativeQuery(sql);
        if (CollectionUtils.isNotEmpty(paras)) {
            for(int i = 0; i < paras.size(); ++i) {
                sqlQuery.setParameter(i, paras.get(i));
            }
        }

        return sqlQuery.list();
    }

    protected List<Object[]> executeSQLQuery(String sql, Object[] paras, Page page) {
        NativeQuery sqlQuery = this.getSession().createNativeQuery(sql);
        if (paras != null && paras.length > 0) {
            for(int i = 0; i < paras.length; ++i) {
                sqlQuery.setParameter(i, paras[i]);
            }
        }

        if (page != null) {
            if (StringUtils.isNotBlank(page.getSort())) {
                ;
            }

            String countSql = "select count(*) from (" + sql + ") as aaaaaaaaaaaaaaa";
            int totalCount = this.countBySql(countSql, paras);
            page.setTotalRecords(totalCount);
            int totalPages = totalCount % page.getPageSize().intValue() == 0 ? totalCount / page.getPageSize().intValue() : totalCount / page.getPageSize().intValue() + 1;
            page.setTotalPages(totalPages);
            sqlQuery.setFirstResult((page.getPage().intValue() - 1) * page.getPageSize().intValue());
            sqlQuery.setMaxResults(page.getPageSize().intValue());
        }

        return sqlQuery.list();
    }

    public List findBySQL(String sql, Object[] paras, Class<? extends Object> class1) {
        if (logger.isDebugEnabled()) {
            logger.debug("sql: " + sql);
            logger.debug("paras: " + Arrays.toString(paras));
        }

        Connection connection = null;

        ArrayList dbColumns;
        try {
            connection = this.dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            if (paras != null) {
                for(int i = 0; i < paras.length; ++i) {
                    stmt.setObject(i + 1, paras[i]);
                }
            }

            ResultSet rs = stmt.executeQuery();
            List list = new ArrayList();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            List<String> dbColumnNames = new ArrayList();

            for(int i = 1; i <= columnCount; ++i) {
                dbColumnNames.add(md.getColumnName(i));
            }

            while(rs.next()) {
                dbColumns = new ArrayList();

                for(int i = 1; i <= columnCount; ++i) {
                    dbColumns.add(rs.getObject(i));
                }

                Object t = this.toModel(dbColumnNames, dbColumns, class1);
                list.add(t);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("sql: " + sql);
                logger.debug("paras: " + Arrays.toString(paras));
                logger.debug("results: " + list);
            }

            dbColumns = (ArrayList) list;
        } catch (Exception var20) {
            throw new RuntimeException(var20.getMessage(), var20);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException var19) {
                    ;
                }
            }

        }

        return dbColumns;
    }

    private Object toModel(List<String> dbColumnNames, List<Object> dbColumns, Class<? extends Object> class1) {
        try {
            Object t = class1.newInstance();

            for(int i = 0; i < dbColumnNames.size(); ++i) {
                String name = (String)dbColumnNames.get(i);
                Method m = this.getSetter(name, class1);
                Class<? extends Object> type = m.getParameterTypes()[0];
                Object value = dbColumns.get(i);
                if (value == null) {
                    return null;
                }

                if (type != Long.class && type != Long.TYPE) {
                    if (type != Integer.class && type != Integer.TYPE) {
                        if (type == String.class) {
                            value = value.toString();
                        } else if (type == BigDecimal.class) {
                            value = new BigDecimal(value.toString());
                        } else if (type == Date.class) {
                            value = (Date)value;
                        } else if (type == Timestamp.class) {
                            value = (Timestamp)value;
                        } else if (type != Double.class && type != Double.TYPE) {
                            if (type == Boolean.class || type == Boolean.TYPE) {
                                value = Boolean.valueOf(value.toString());
                            }
                        } else {
                            value = Double.valueOf(value.toString());
                        }
                    } else {
                        value = Integer.valueOf(value.toString());
                    }
                } else {
                    value = Long.valueOf(value.toString());
                }

                m.invoke(t, value);
            }

            return t;
        } catch (IllegalArgumentException var10) {
            logger.error(var10, var10);
            var10.printStackTrace();
        } catch (InstantiationException var11) {
            logger.error(var11, var11);
            var11.printStackTrace();
        } catch (IllegalAccessException var12) {
            logger.error(var12, var12);
            var12.printStackTrace();
        } catch (InvocationTargetException var13) {
            logger.error(var13, var13);
            var13.printStackTrace();
        }

        return null;
    }

    private Method getSetter(String name, Class<? extends Object> clazz) {
        Method[] ms = clazz.getDeclaredMethods();
        Method[] var4 = ms;
        int var5 = ms.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Method m = var4[var6];
            if (("set" + name).equalsIgnoreCase(m.getName())) {
                return m;
            }
        }

        return null;
    }

    public int countByHql(String hql, Object[] params) {
        Query query = this.getSession().createQuery(hql);
        if (params != null) {
            for(int i = 0; i < params.length; ++i) {
                query.setParameter(i, params[i]);
            }
        }

        return Integer.parseInt(query.uniqueResult().toString());
    }

    protected int countBySql(String sql, Object[] params) {
        NativeQuery query = this.getSession().createNativeQuery(sql);
        if (params != null) {
            for(int i = 0; i < params.length; ++i) {
                query.setParameter(i, params[i]);
            }
        }

        return Integer.parseInt(query.uniqueResult().toString());
    }

    public List<T> findModelBySql(String sql, Object[] paras, Page page) {
        NativeQuery sqlQuery = this.getSession().createNativeQuery(sql);
        this.setQueryParameters(paras, sqlQuery);
        sqlQuery.addEntity(this.clz);
        if (page != null) {
            String countSql = "select count(*) from (" + sql + ") ";
            int totalCount = this.countBySql(countSql, paras);
            page.setTotalRecords(totalCount);
            int totalPages = totalCount % page.getPageSize().intValue() == 0 ? totalCount / page.getPageSize().intValue() : totalCount / page.getPageSize().intValue() + 1;
            page.setTotalPages(totalPages);
            sqlQuery.setFirstResult((page.getPage().intValue() - 1) * page.getPageSize().intValue());
            sqlQuery.setMaxResults(page.getPageSize().intValue());
        }

        return sqlQuery.list();
    }

    public <K> K findOneModelBySql(String sql, Object[] paras, Class<? extends BaseModel> clzz) {
        NativeQuery<K> sqlQuery = this.getSession().createNativeQuery(sql);
        this.setQueryParameters(paras, sqlQuery);
        sqlQuery.addEntity(clzz);
        sqlQuery.setFirstResult(0);
        sqlQuery.setMaxResults(1);
        List<K> list = sqlQuery.list();
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    private void setQueryParameters(Object[] paras, Query sqlQuery) {
        if (paras != null) {
            for(int i = 0; i < paras.length; ++i) {
                sqlQuery.setParameter(i, paras[i]);
            }

        }
    }

    public double sumByHql(String hql, Object[] paras) {
        Query query = this.getSession().createQuery(hql);
        this.setQueryParameters(paras, query);
        if (query.uniqueResult() == null) {
            return 0.0D;
        } else {
            return StringUtils.isBlank(query.uniqueResult().toString()) ? 0.0D : Double.parseDouble(query.uniqueResult().toString());
        }
    }
}
