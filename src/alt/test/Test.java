package alt.test;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] s = new String[] { "login", "logout", "forgotPassword",
				"dashboard", "dashboard", "updateProfile", "viewProfile",
				"addTeacher", "deleteTeacher", "blockTeacher",
				"unBlockTeacher", "editTeacher", "listTeacher", "addCourse",
				"editCourse", "deleteCourse", "listCourse", "addChapter",
				"editChapter", "deleteChapter", "listChapter",
				"addMCQQuestion", "addLongQuestion", "addShortQuestion",
				"deleteQuestion", "listQuestion", "selectSyllabus",
				"submitSyllabus", "configExamAction", "downloadDoc",
				"fileDownl", "filePreviewS", "filePreviewT" };
		int rid = 3;
		int lastid = 77;
		for (int i = lastid + 0; i < lastid + s.length; i++) {
			System.out
					.println("INSERT INTO `sowdb`.`access` (`idAccess`, `Role_id`, `AccessTableName`) VALUES ("
							+ i + ", " + rid + ", '" + s[i - lastid] + "');");
		}
	}

}
