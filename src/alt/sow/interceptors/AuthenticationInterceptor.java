package alt.sow.interceptors;

import java.util.Arrays;
import java.util.Map;

import alt.sow.domain.Access;
import alt.sow.domain.User;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthenticationInterceptor implements Interceptor {
	private static final long serialVersionUID = -5011962009065225959L;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		System.out.println("inside auth interceptor");
		Map<String, Object> sessionAttributes = actionInvocation
				.getInvocationContext().getSession();

		System.out.println(actionInvocation.getAction().getClass().getName());
		User user = (User) sessionAttributes.get("currentUser");
		if (user == null) {
			return Action.LOGIN;
		} else {
			Action action = (Action) actionInvocation.getAction();
			if (action instanceof UserAware) {
				((UserAware) action).setUser(user);
			}
			String accessTableName = actionInvocation.getProxy()
					.getActionName();
			System.out.println("Action : " + accessTableName);
			Access a = new Access();
			a.setRole(user.getRole());
			a.setAccessTableName(accessTableName);
			System.out.println(Arrays.toString(user.getRole().getAccesses()
					.toArray()));
			if (!user.getRole().getAccesses().contains(a)) {
				System.out.println("permission denied");
				return Action.NONE;
			}
			String t = actionInvocation.invoke();
			System.out.println("Invoking : " + t);
			return t;
		}
	}
}
