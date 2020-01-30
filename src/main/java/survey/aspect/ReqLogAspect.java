package survey.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Configuration
@Slf4j
public class ReqLogAspect {

	// @Around("")
	@Around("execution(@(@org.springframework.web.bind.annotation.RequestMapping *) * *(..)) || @annotation(org.springframework.web.bind.annotation.RequestMapping) && execution(* *(..))")
	public Object log(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		HttpServletRequest request =
				((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

		Object value;

		try {
			value = proceedingJoinPoint.proceed();
		} catch (Throwable throwable) {
			throw throwable;
		} finally {
			log.info("{} {}", request.getMethod(), request.getRequestURI());
		}

		return value;
	}


}
