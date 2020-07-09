package com.rabo.api.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"UNITTEST"})
public interface CustomerDaoImplForTest extends CustomerDao {
	
}
