package alt.sow.view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.SessionAware;

import alt.sow.domain.Chapter;
import alt.sow.domain.Exam;
import alt.sow.domain.Question;
import alt.sow.domain.User;
import alt.sow.util.DataSource;
import alt.sow.util.GenericDAO;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sn.file.html.Convertor;
import com.sn.util.StringUtils;

public class ExamAction extends ActionSupport implements SessionAware,
		ModelDriven<Exam> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8288667967290576476L;
	private Map<String, Object> session;
	private GenericDAO<Exam, Integer> manager = new GenericDAO<Exam, Integer>();
	//
	private List<Chapter> chapters;
	private Map<Integer, Boolean> std = new HashMap<Integer, Boolean>();
	private List<Chapter> trgtChapters = new ArrayList<Chapter>();
	private int chapterid;
	private String source;
	//
	private int mcq_noq;
	private int sa_noq;
	private int la_noq;
	private int mcq_mks;
	private int sa_mks;
	private int la_mks;
	private Exam exam;

	@Deprecated
	private List<Question> appendList(List<Question> total, List<Question> mcqs) {
		if (mcqs == null) {
			return total;
		}
		for (Iterator it = mcqs.iterator(); it.hasNext();) {
			Question q = (Question) it.next();
			total.add(q);
		}
		return total;
	}

	public String config() {
		System.out.println("configNew");
		User cu = (User) session.get("currentUser");
		trgtChapters = (List<Chapter>) session.get("selectedChapters");
		System.out.println("config started");
		String result = "";
		//
		exam = getExam();
		String key[] = { "user.profile.organisation.organisationName",
				"exam.conductedOn", "exam.time", "mcq_noq", "mcq_mks",
				"sa_noq", "sa_mks", "la_noq", "la_mks" };

		try {

			Object val[] = {
					cu.getProfile().getOrganisation().getOrganisationName(),
					exam.getConductedOn(), exam.getTime(), getMcq_noq(),
					getMcq_mks(), getSa_noq(), getSa_mks(), getLa_noq(),
					getLa_mks() };

			for (int i = 0; i < val.length; i++) {
				if (val[i] == null || StringUtils.isBlank(val.toString())) {
					addFieldError(key[i], getText("required.field"));
					result = INPUT;
				}
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
			return INPUT;
		}

		if (!result.equals(INPUT)) {

			exam.setUser(cu);
			cu.getExams().add(exam);

			//
			System.out.println(exam);
			List<Question> mcqs = null;
			List<Question> sas = null;
			List<Question> las = null;
			//
			System.out.println(trgtChapters);
			StringBuilder sb = new StringBuilder();
			for (Chapter chapter : trgtChapters) {
				sb.append(chapter.getIdChapter());
				sb.append(",");
			}
			String sql = "select * from Question as q where (q.cat=3 OR q.cat="
					+ exam.getCat() + ") AND q.Chapter_id IN ("
					+ sb.substring(0, sb.length() - 1) + ") ";
			// MCQS
			int qtype = DataSource.getInstance().MCQ.getIdTag();
			mcqs = getAllPercentagesQuestionPrepared(sql, qtype);
			// SHORT
			qtype = DataSource.getInstance().SHORTANSWER.getIdTag();
			sas = getAllPercentagesQuestionPrepared(sql, qtype);
			// LONG
			qtype = DataSource.getInstance().LONGANSWER.getIdTag();
			las = getAllPercentagesQuestionPrepared(sql, qtype);
			//
			int total_mks = getMcq_mks() * getMcq_noq() + getSa_mks()
					* getSa_noq() + getLa_mks() * getLa_noq();
			int total_noq = getMcq_noq() + getSa_noq() + getLa_noq();
			exam.setNoquestion(total_noq);
			exam.setMarks(total_mks);
			//
			List<Question> total = new ArrayList<Question>();
			total.addAll(mcqs);
			total.addAll(sas);
			total.addAll(las);
			Set<Question> qstns = new HashSet<Question>(total.size());
			// total = appendList(total, mcqs);
			// total = appendList(total, sas);
			// total = appendList(total, las);
			//
			for (Iterator it = total.iterator(); it.hasNext();) {
				Question question = (Question) it.next();
				question.getExams().add(exam);
				qstns.add(question);
				// exam.getQuestions().add(question);
				// System.out.println(question);
			}
			exam.setQuestions(qstns);
			// Debug
			if (manager.save(exam) != null) {
				session.put("exam", exam);
				result = SUCCESS;
				if (!generateDocument(exam)) {
					result = ERROR;
				}
			} else {
				result = ERROR;
			}
		}
		return result;
	}

	private List<Question> getAllPercentagesQuestionPrepared(String sql, int qid) {
		// TODO Auto-generated method stub
		List<Question> mcqs = new ArrayList<Question>();
		List<Question> q1 = getQuestions(sql,
				Math.round(getMcq_noq() * 20 / 100), qid, DataSource.EASY);
		List<Question> q2 = getQuestions(sql,
				Math.round(getMcq_noq() * 60 / 100), qid, DataSource.MED);
		List<Question> q3 = getQuestions(sql,
				Math.round(getMcq_noq() * 20 / 100), qid, DataSource.HARD);
		mcqs.addAll(q1);
		mcqs.addAll(q2);
		mcqs.addAll(q3);
		Collections.shuffle(mcqs);
		return mcqs;
	}

	private List<Question> getQuestions(String sql, int noq, int qtype, int dif) {
		// TODO Auto-generated method stub
		String random = " order by rand() ";
		String limit = " LIMIT " + noq;
		String t = sql + " AND q.Tag_idTag =" + qtype + " AND q.dif = " + dif
				+ random;
		if (noq != 0)
			t = t + limit;

		List<Question> tmp = (List<Question>) manager.runSQLQuery(t,
				Question.class);
		System.out.println("Executing " + t + "\nfount recourds : "
				+ tmp.size());
		return tmp;
	}

	public String configOld() {
		System.out.println("config");
		User cu = (User) session.get("currentUser");
		trgtChapters = (List<Chapter>) session.get("selectedChapters");
		System.out.println("config started");
		String result = "";

		//
		exam = getExam();
		String key[] = { "user.profile.organisation.organisationName",
				"exam.conductedOn", "exam.time", "mcq_noq", "mcq_mks",
				"sa_noq", "sa_mks", "la_noq", "la_mks" };

		try {

			Object val[] = {
					cu.getProfile().getOrganisation().getOrganisationName(),
					exam.getConductedOn(), exam.getTime(), getMcq_noq(),
					getMcq_mks(), getSa_noq(), getSa_mks(), getLa_noq(),
					getLa_mks() };

			for (int i = 0; i < val.length; i++) {
				if (val[i] == null || StringUtils.isBlank(val.toString())) {
					addFieldError(key[i], getText("required.field"));
					result = INPUT;
				}
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
			return INPUT;
		}

		if (!result.equals(INPUT)) {

			exam.setUser(cu);
			cu.getExams().add(exam);
			//
			System.out.println(exam);
			List<Question> mcqs = new ArrayList<Question>();
			List<Question> sas = new ArrayList<Question>();
			List<Question> las = new ArrayList<Question>();
			//
			System.out.println(trgtChapters);
			String conditions = "";
			String hql = "from Question q where q.tag.idTag=";
			String qry = "";
			for (int i = 0; i < trgtChapters.size(); i++) {
				Chapter c = trgtChapters.get(i);
				qry = hql + DataSource.getInstance().MCQ.getIdTag()
						+ " and q.chapter.idChapter=" + c.getIdChapter();
				System.out.println("mcq:" + qry);
				mcqs = appendList(mcqs, (List<Question>) manager.runQuery(qry));
				//
				qry = hql + DataSource.getInstance().SHORTANSWER.getIdTag()
						+ " and q.chapter.idChapter=" + c.getIdChapter();
				System.out.println("sa:" + qry);
				sas = appendList(sas, (List<Question>) manager.runQuery(qry));
				//
				qry = hql + DataSource.getInstance().LONGANSWER.getIdTag()
						+ " and q.chapter.idChapter=" + c.getIdChapter();
				System.out.println("la:" + qry);
				las = appendList(las, (List<Question>) manager.runQuery(qry));
			}

			//
			Collections.shuffle(mcqs);
			mcqs = mcqs.subList(0, (mcqs.size() < mcq_noq) ? mcqs.size() - 1
					: mcq_noq);
			Collections.shuffle(sas);
			sas = sas.subList(0, (sas.size() < sa_noq) ? sas.size() - 1
					: sa_noq);
			Collections.shuffle(las);
			las = las.subList(0, (las.size() < la_noq) ? las.size() - 1
					: la_noq);
			//
			int total_mks = getMcq_mks() * getMcq_noq() + getSa_mks()
					* getSa_noq() + getLa_mks() * getLa_noq();
			int total_noq = getMcq_noq() + getSa_noq() + getLa_noq();
			exam.setNoquestion(total_noq);
			exam.setMarks(total_mks);
			//
			List<Question> total = new ArrayList<Question>();
			total = appendList(total, mcqs);
			total = appendList(total, sas);
			total = appendList(total, las);
			//
			for (Iterator it = total.iterator(); it.hasNext();) {
				Question question = (Question) it.next();
				question.getExams().add(exam);
				exam.getQuestions().add(question);
				// System.out.println(question);
			}
			// exam.setQuestions(ListToSet(total));
			// Debug

			if (manager.save(exam) != null) {
				session.put("exam", exam);
				result = SUCCESS;
				if (!generateDocument(exam)) {
					result = ERROR;
				}
			} else {
				result = ERROR;
			}
		}
		return result;
	}

	public String echo() {
		System.out.println("echo from " + getSource());
		// if (getSource() == null) {
		// list();
		// return SUCCESS;
		// } else if (getSource().equalsIgnoreCase("select")) {
		// return submit();
		// } else if (getSource().equalsIgnoreCase("config")) {
		// return config();
		// }
		// return INPUT;
		list();
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("exec");
		return echo();
	}

	private boolean generateDocument(Exam exam2) {
		boolean success = false;
		Convertor docConvertor = null;
		Convertor docConvertor1 = null;

		try {
			docConvertor = new Convertor();
			docConvertor1 = new Convertor();
			File examDoc = docConvertor.getPaperFile(exam, false);
			File tchrDoc = docConvertor1.getPaperFile(exam, true);
			session.put("examDoclink", examDoc.getAbsolutePath());
			session.put("tchrDoclink", tchrDoc.getAbsolutePath());
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
		return success;
	}

	public int getChapterid() {
		return chapterid;
	}

	public List<Chapter> getChapters() {
		return chapters;
	}

	public Exam getExam() {
		return exam;
	}

	public int getLa_mks() {
		return la_mks;
	}

	public int getLa_noq() {
		return la_noq;
	}

	public int getMcq_mks() {
		return mcq_mks;
	}

	public int getMcq_noq() {
		return mcq_noq;
	}

	public Exam getModel() {
		return exam;
	}

	public int getSa_mks() {
		return sa_mks;
	}

	public int getSa_noq() {
		return sa_noq;
	}

	public Map<Integer, Boolean> getStd() {
		return std;
	}

	private void list() {
		chapters = (List<Chapter>) manager.findAll(Chapter.class);
	}

	private Set<Question> ListToSet(List<Question> total) {
		Set<Question> set = new HashSet<Question>(total.size());
		for (Iterator it = total.iterator(); it.hasNext();) {
			Question q = (Question) it.next();
			set.add(q);
		}
		return set;
	}

	public void setChapterid(int chapterid) {
		this.chapterid = chapterid;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public void setLa_mks(int la_mks) {
		this.la_mks = la_mks;
	}

	public void setLa_noq(int la_noq) {
		this.la_noq = la_noq;
	}

	public void setMcq_mks(int mcq_mks) {
		this.mcq_mks = mcq_mks;
	}

	public void setMcq_noq(int mcq_noq) {
		this.mcq_noq = mcq_noq;
	}

	public void setSa_mks(int sa_mks) {
		this.sa_mks = sa_mks;
	}

	public void setSa_noq(int sa_noq) {
		this.sa_noq = sa_noq;
	}

	//
	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}

	public void setStd(Map<Integer, Boolean> std) {
		this.std = std;
	}

	public String submit() {
		String result = INPUT;
		list();
		trgtChapters.clear();
		Map<Integer, Boolean> sd = getStd();
		System.out.println("selected:" + sd);
		for (Map.Entry<Integer, Boolean> entry : sd.entrySet()) {
			Integer index = entry.getKey();
			Boolean sel = entry.getValue();
			if (sel) {
				trgtChapters.add(chapters.get(index));
			}
		}
		if (trgtChapters.isEmpty()) {
			addActionError("required.least.one.chapter");
			result = INPUT;
		} else {
			result = SUCCESS;
		}
		for (Iterator itr = trgtChapters.iterator(); itr.hasNext();) {
			Chapter b = (Chapter) itr.next();
			System.out.println(b);
		}
		std.clear();
		session.put("selectedChapters", trgtChapters);
		return result;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSource() {
		return source;
	}

}
