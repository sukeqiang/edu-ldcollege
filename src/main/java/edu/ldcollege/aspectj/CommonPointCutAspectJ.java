package edu.ldcollege.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CommonPointCutAspectJ {

	@Pointcut("execution(* edu.ldcollege.service.*Service.select*(..))")
	public void transHandler(){}
}
