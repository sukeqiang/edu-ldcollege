package edu.ldcollege.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.ldcollege.domain.LdHomeWork;
import edu.ldcollege.domain.LdHomeWorkFB;
import edu.ldcollege.mapping.LdHomeWorkFBMapper;
import edu.ldcollege.mapping.LdHomeWorkMapper;

@Component("ldHomeworkService")
@Transactional
public class LdHomeworkService {

	@Autowired
	private LdHomeWorkMapper ldHomeWorkMapper;
	
	@Autowired
	private LdHomeWorkFBMapper ldHomeWorkFBMapper;
	
	public List<LdHomeWork> selectLdhomeworkByClassIdLessionId(Integer classId,Integer lessionId,
			String orderBy,String sortOrder) {
		return ldHomeWorkMapper.selectLdhomeworkByClassIdLessionId(classId,lessionId,orderBy,sortOrder);
	}
	
	public void saveLdHomeWorkByOnDuplicateKeyUpdate(LdHomeWork ldHomeWork) {
		ldHomeWorkMapper.saveByOnDuplicateKeyUpdate(ldHomeWork);
	}
	
	public void saveCommentHomeWork(LdHomeWorkFB comment, Integer homeworkId, int negativeStarFlag) {
		char bestFlag = '0';
		if(StringUtils.equalsIgnoreCase(comment.getLevelFlag(), "A")) {
			bestFlag = '1';
		}
		ldHomeWorkFBMapper.insert(comment);
		ldHomeWorkMapper.updateCommentByPrimaryKey(homeworkId, negativeStarFlag, bestFlag);
	}
	
	@Transactional(propagation = Propagation.NEVER)
	public List<LdHomeWorkFB> getAllComment(Integer homeworkId) {
		return ldHomeWorkFBMapper.selectByhomeworkId(homeworkId);
	}
}
