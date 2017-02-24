package edu.ldcollege.aspectj;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import edu.ldcollege.viewmodel.ViewModel;

@Component
@Aspect
public class viewModelAspectJ {
	
	@Around("edu.ldcollege.aspectj.CommonPointCutAspectJ.transHandler()")
	public Object viewModelWrap(ProceedingJoinPoint pjp) throws Throwable{
		Object obj = pjp.proceed();
		if(obj instanceof ViewModel && obj != null) {
			@SuppressWarnings("rawtypes")
			ViewModel vm = (ViewModel)obj;
			if(vm.getRows() == null) {
				vm.setTotal(0);
			}else{
				vm.setTotal(vm.getRows().size());
			}
		}
		return obj;
	}
}
