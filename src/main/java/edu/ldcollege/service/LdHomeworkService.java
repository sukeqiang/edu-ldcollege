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
import edu.ldcollege.utils.SpringContextUtil;
import edu.ldcollege.viewmodel.ViewModel;

@Component("ldHomeworkService")
@Transactional
public class LdHomeworkService {

	@Autowired
	private LdHomeWorkMapper ldHomeWorkMapper;
	
	@Autowired
	private LdHomeWorkFBMapper ldHomeWorkFBMapper;
	
	@Transactional(propagation = Propagation.NEVER)
	public ViewModel<LdHomeWork> selectLdhomeworkByClassIdLessionId(Integer classId,Integer lessionId,
			String orderBy,String sortOrder) {
		@SuppressWarnings("unchecked")
		ViewModel<LdHomeWork> view = SpringContextUtil.getBean("viewModel",ViewModel.class);
		List<LdHomeWork> list = ldHomeWorkMapper.selectLdhomeworkByClassIdLessionId(classId,lessionId,orderBy,sortOrder);
		view.setRows(list);
		return view;
	}
	
	public LdHomeWork getLdhomeworkByCLUId(Integer classId, Integer lessionId, Integer userId) {
		List<LdHomeWork> list = ldHomeWorkMapper.selectLdhomeworkByClassIdLessionIdUserId(classId,lessionId,userId);
		if (list == null || list.size()<=0) {
			return null;
		}
		return list.get(0);
	}
	
	
	public int saveLdHomeWorkByOnDuplicateKeyUpdate(LdHomeWork ldHomeWork) {
		return ldHomeWorkMapper.saveByOnDuplicateKeyUpdate(ldHomeWork);
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
