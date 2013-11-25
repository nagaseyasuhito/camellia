package com.github.nagaseyasuhito.camellia.dao;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.github.nagaseyasuhito.camellia.entity.User;
import com.github.nagaseyasuhito.camellia.entity.User_;

@Stateless
public class UserDao extends BaseDao<User> {

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

	public User findByApikey(final String apikey) {
		return this.getSingleResult(new EntityQuery<User>() {
			@Override
			public CriteriaQuery<User> execute(CriteriaBuilder builder, CriteriaQuery<User> query, Root<User> root) {
				Predicate apikeyPredicate = builder.equal(root.get(User_.apikey), apikey);

				return query.select(root).where(apikeyPredicate);
			}
		});
	}

	public User findByName(final String name) {
		return this.getSingleResult(new EntityQuery<User>() {
			@Override
			public CriteriaQuery<User> execute(CriteriaBuilder builder, CriteriaQuery<User> query, Root<User> root) {
				Predicate namePredicate = builder.equal(root.get(User_.name), name);

				return query.select(root).where(namePredicate);
			}
		});
	}

	public long count() {
		return this.getSingleResult(new Query<User, Long>() {
			@Override
			public CriteriaQuery<Long> execute(CriteriaBuilder builder, CriteriaQuery<Long> query, Root<User> root) {
				return query.select(builder.count(root));
			}
		}, Long.class);
	}
}
