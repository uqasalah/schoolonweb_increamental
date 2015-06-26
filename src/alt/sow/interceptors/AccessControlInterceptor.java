package alt.sow.interceptors;

import java.util.Map;

import alt.sow.domain.User;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AccessControlInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4420816782088811814L;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("inside cp interceptor");
		Map<String, Object> sessionAttributes = actionInvocation
				.getInvocationContext().getSession();

		actionInvocation.getAction().getClass().getName();
		User user = (User) sessionAttributes.get("currentUser");
		
		if (user == null) {
			return Action.LOGIN;
		} else {
			Action action = (Action) actionInvocation.getAction();
			if (action instanceof UserAware) {
				((UserAware) action).setUser(user);
			}
			return actionInvocation.invoke();
		}
	}

}
