package util;

import domain.Problem;
import domain.User;

public class Replaces {
	public User reapuser(User user1) {
		User user = user1;
		user.setUsername(user.getUsername().replace(" ", ""));
		user.setPassword(user.getPassword().replace(" ", ""));
		user.setRealname(user.getRealname().replace(" ", ""));
		user.setRoot(user.getRoot().replace(" ", ""));
		return user;
	}

	public Problem reapproblem(Problem problem1) {
		Problem problem = problem1;
		problem.setPro(problem.getPro().replace(" ", ""));
		problem.setOne(problem.getOne().replace(" ", ""));
		problem.setTwo(problem.getTwo().replace(" ", ""));
		problem.setThree(problem.getThree().replace(" ", ""));
		problem.setFour(problem.getFour().replace(" ", ""));
		problem.setResult(problem.getResult().replace(" ", ""));
		problem.setIndex(problem.getIndex().replace(" ", ""));
		return problem;
	}

}
