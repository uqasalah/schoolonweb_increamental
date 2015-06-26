/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sn.file.html;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import alt.sow.domain.Answer;
import alt.sow.domain.Exam;
import alt.sow.domain.Question;
import alt.sow.util.DataSource;

import com.sn.file.FileReader;
import com.sn.util.StringUtils;

/**
 * 
 * @author Blade
 */
public class Convertor {
	//

	private static final File HEADERXML_FILE = new File(
			"Document_Template\\head.htm");
	private static final File MCQXML_FILE = new File(
			"Document_Template\\mcq.htm");
	private static final File SHORTANSWERXML_FILE = new File(
			"Document_Template\\shortAnswer.htm");
	private static final File LONGANSWER_FILE = new File(
			"Document_Template\\longAnswer.htm");
	private static final File MCQXML_NOTEFILE = new File(
			"Document_Template\\mcqNote.htm");
	private static final File SHORTANSWERXML_NOTEFILE = new File(
			"Document_Template\\shortAnswerNote.htm");
	private static final File LONGANSWER_NOTEFILE = new File(
			"Document_Template\\longAnswerNote.htm");
	private String OUTPUT_STD_COPY_FILENAME = "exam";
	private String OUTPUT_TCR_COPY_FILENAME = "exam_tcr";
	//
	private static String mcqNote = "Choose  the right answer in following options";
	private static String shortAnswerNote = "Write Short Answer";
	private static String longAnswerNote = "Write Long Answer";
	//
	private static String headerTemplateString;
	private static String mcqTemplateString;
	private static String shortAnswerTemplateString;
	private static String longAnswerTemplateString;
	//
	private HashMap<String, String> headerMap = new HashMap<String, String>();
	private HashMap<String, String> mcqMap = new HashMap<String, String>();
	private HashMap<String, String> shortAnswerMap = new HashMap<String, String>();
	private HashMap<String, String> longAnswerMap = new HashMap<String, String>();

	//
	private StringBuilder headerContainer = new StringBuilder();
	private StringBuilder mcqContainer = new StringBuilder();
	private StringBuilder saContainer = new StringBuilder();
	private StringBuilder laContainer = new StringBuilder();
	//
	public long timeTaken = 0;
	private String trunc = "</html>\n<html>";

	// private String down = "<a href=all.htm> Download </a>";

	public Convertor() throws IOException {

		try {
			// headerTemplateString = new FileReader(new FileInputStream(
			// HEADERXML_FILE), null).toString();
			headerTemplateString = new FileReader(this.getClass()
					.getClassLoader()
					.getResourceAsStream("Document_Template\\head.htm"), null)
					.toString();

			// mcqTemplateString = new FileReader(
			// new FileInputStream(MCQXML_FILE), null).toString();
			mcqTemplateString = new FileReader(this.getClass().getClassLoader()
					.getResourceAsStream("Document_Template\\mcq.htm"), null)
					.toString();
			// shortAnswerTemplateString = new FileReader(new FileInputStream(
			// SHORTANSWERXML_FILE), null).toString();
			shortAnswerTemplateString = new FileReader(this.getClass()
					.getClassLoader()
					.getResourceAsStream("Document_Template\\shortAnswer.htm"),
					null).toString();
			// longAnswerTemplateString = new FileReader(new FileInputStream(
			// LONGANSWER_FILE), null).toString();
			longAnswerTemplateString = new FileReader(this.getClass()
					.getClassLoader()
					.getResourceAsStream("Document_Template\\longAnswer.htm"),
					null).toString();

			//
			// mcqNote = new FileReader(new FileInputStream(MCQXML_NOTEFILE),
			// null)
			// .toString();
			mcqNote = new FileReader(this.getClass().getClassLoader()
					.getResourceAsStream("Document_Template\\mcqNote.htm"),
					null).toString();
			// shortAnswerNote = new FileReader(new FileInputStream(
			// SHORTANSWERXML_NOTEFILE), null).toString();
			shortAnswerNote = new FileReader(this
					.getClass()
					.getClassLoader()
					.getResourceAsStream(
							"Document_Template\\shortAnswerNote.htm"), null)
					.toString();
			// longAnswerNote = new FileReader(new FileInputStream(
			// LONGANSWER_NOTEFILE), null).toString();
			longAnswerNote = new FileReader(this
					.getClass()
					.getClassLoader()
					.getResourceAsStream(
							"Document_Template\\longAnswerNote.htm"), null)
					.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public File getFile(String fileName) {
		File returnFile = null;
		try {
			returnFile = new File(fileName);
			FileOutputStream fos = new FileOutputStream(returnFile);
			StringBuilder sb = new StringBuilder();
			sb.append(headerContainer);
			if (!StringUtils.isBlank(mcqContainer)) {
				sb.append(mcqNote);
				sb.append(mcqContainer);
			}
			if (!StringUtils.isBlank(saContainer)) {
				sb.append(shortAnswerNote);
				sb.append(saContainer);
			}
			if (!StringUtils.isBlank(longAnswerTemplateString)) {
				sb.append(longAnswerNote);
				sb.append(laContainer);
			}
			// sb.append(down);
			String total = sb.toString();
			total = total.replaceAll(trunc, "");
			fos.write(total.getBytes(Charset.forName("UTF-8")));
			fos.flush();
			fos.close();
			System.out.println("exam file : " + returnFile.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnFile;
	}

	public File getPaperFile(Exam exam, boolean isTeachersCopy) {
		long startTime = System.currentTimeMillis();
		Calendar cal = Calendar.getInstance();
		cal.setTime(exam.getConductedOn());
		headerMap.put("yearTag", "" + cal.get(Calendar.YEAR));
		headerMap
				.put("schoolNameTag",
						(exam.getUser().getProfile().getOrganisation()
								.getOrganisationName() + ((isTeachersCopy) ? " - Teachers Copy"
								: "")));
		headerMap.put("hoursTag", "" + getVisibleTime(exam.getTime()));
		headerMap.put("marksTag", "" + exam.getMarks());
		//
		headerContainer.append(FileReader.replaceKeyValPairs(
				headerTemplateString, headerMap));
		// MCQ START

		List<Question> mcqs = new ArrayList<Question>();
		List<Question> sas = new ArrayList<Question>();
		List<Question> las = new ArrayList<Question>();
		// DIVIDE INTO TYPES
		System.out.println("selected question in db :"
				+ exam.getQuestions().size());
		for (Iterator it = exam.getQuestions().iterator(); it.hasNext();) {
			Question question = (Question) it.next();
			if (question.getTag().equals(DataSource.getInstance().MCQ)) {
				mcqs.add(question);
			} else if (question.getTag().equals(
					DataSource.getInstance().SHORTANSWER)) {
				sas.add(question);
			} else if (question.getTag().equals(
					DataSource.getInstance().LONGANSWER)) {
				las.add(question);
			}
		}
		// MCQ
		System.out.println("selected MCQs :" + mcqs.size());
		int i = 1;
		for (Iterator<Question> it = mcqs.iterator(); it.hasNext();) {
			Question question = it.next();
			mcqMap.put("qnoTag", "" + i);
			mcqMap.put("questionStringTag", "" + question.getQuestion());
			Set<Answer> answers = question.getAnswers();
			int j = 1;
			for (Iterator<Answer> it1 = answers.iterator(); it1.hasNext();) {
				Answer answer = it1.next();
				mcqMap.put("Answer" + j, answer.getAnwer()
						+ ((isTeachersCopy && answer.getCorrect()) ? " #" : ""));
				j++;
			}
			mcqContainer.append(FileReader.replaceKeyValPairs(
					mcqTemplateString, mcqMap));
			i++;
			// System.out.println("q:" + question);
		}
		System.out.println("done mcqs");

		// SHORT ANSWER
		System.out.println("selected sas : " + sas.size());
		for (Iterator<Question> it = sas.iterator(); it.hasNext();) {
			Question question = it.next();
			shortAnswerMap.put("qnoTag", "" + i);

			shortAnswerMap
					.put("questionStringTag", "" + question.getQuestion());
			System.out.println("sa answer size : "
					+ question.getAnswers().size());
			for (Iterator ita = question.getAnswers().iterator(); ita.hasNext();) {
				Answer a = (Answer) ita.next();
				if (a != null)
					shortAnswerMap.put("answerTag",
							(isTeachersCopy ? "" + a.getAnwer() : " "));
			}

			saContainer.append(FileReader.replaceKeyValPairs(
					shortAnswerTemplateString, shortAnswerMap));
			i++;
		}
		// LONG ANSWER
		System.out.println("selected las : " + las.size());
		for (Iterator<Question> it = las.iterator(); it.hasNext();) {
			Question question = it.next();
			longAnswerMap.put("qnoTag", "" + i);
			longAnswerMap.put("questionStringTag", "" + question.getQuestion());
			System.out.println("la answer size : "
					+ question.getAnswers().size());
			for (Iterator ita = question.getAnswers().iterator(); ita.hasNext();) {
				Answer a = (Answer) ita.next();
				System.out.println("long answer : " + a);
				if (a != null)
					longAnswerMap.put("answerTag",
							(isTeachersCopy ? "" + a.getAnwer() : " "));
			}

			laContainer.append(FileReader.replaceKeyValPairs(
					longAnswerTemplateString, longAnswerMap));
			i++;
		}
		//
		String fn = ((isTeachersCopy) ? OUTPUT_TCR_COPY_FILENAME
				: OUTPUT_STD_COPY_FILENAME) + "-" + System.currentTimeMillis();
		File rtn = getFile(fn);
		long stopTime = System.currentTimeMillis();
		timeTaken = stopTime - startTime;
		return rtn;
	}

	public static String getVisibleTime(Integer timeInMin) {
		int hrs = timeInMin / 60;
		int min = timeInMin % 60;
		return hrs + ":" + min + " hrs";
	}
}
