package edu.ldcollege.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.ldcollege.orm.domain.LdHomeWork;
import edu.ldcollege.orm.domain.LdHomeWorkFB;
import edu.ldcollege.orm.mapping.LdClassMapper;
import edu.ldcollege.orm.mapping.LdHomeWorkFBMapper;
import edu.ldcollege.orm.mapping.LdHomeWorkMapper;
import edu.ldcollege.utils.SpringContextUtil;
import edu.ldcollege.web.view.ViewModel;

@Service("ldClassService")
public class LdClassService {

	@Autowired
	private LdClassMapper ldClassMapper;
	
	
}
