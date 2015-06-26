/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sn.file.html;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import alt.sow.util.DataSource;
import alt.sow.util.GenericDAO;

/**
 * 
 * @author Blade
 */
public class TestGenerator {
	public static void main(String arg[]) throws IOException {

		GenericDAO DAO = DataSource.getInstance().DAO;
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis()));

		System.out.println(c.get(Calendar.YEAR));
		// System.out.println(new File("hello").createNewFile());
		//
		// Chapter c = new Chapter(DataSource.getInstance().DEBUGADMIN, new
		// Course(
		// DataSource.DEBUGADMIN));
		// Exam exam = new Exam();
		// exam.setConductedOn(new Date(2011, 5, 18));
		// exam.setMarks(80);
		// exam.setTime(90);
		// exam.setUser(DataSource.DEBUGADMIN);
		// exam.setExamtype(new Examtype("unit test", null));
		// // edao.save(exam);
		// for (int i = 0; i < 10; i++) {
		// Question q = new Question();
		// q.setUser(DataSource.DEBUGADMIN);
		// q.setExam(exam);
		// q.setChapter(c);
		// q.setTag(DataSource.MCQ);
		// q.setQuestion("what is the question exactly");
		// for (int j = 0; j < 4; j++) {
		// Answer an = new Answer(DataSource.DEBUGADMIN, q,
		// "answer is being typed", j == 3);
		// q.getAnswers().add(an);
		// an.setQuestion(q);
		// }
		// // qdao.save(q);
		// } //
		//
		// Convertor m = new Convertor();
		// File f = m.getPaperFile((Exam) DAO.findByID(Exam.class, 2), true);
		// f.renameTo(new File(f.getName() + ".doc"));
		// System.out.println("Time taken to create document : " + m.timeTaken
		// + " ms");
		//
		// System.out.println(f.getAbsoluteFile() + "/n" + f.getCanonicalPath()
		// + "\n" + f.getPath());
		//
		// String[] process = { "C:\\Windows\\explorer.exe",
		// f.getCanonicalPath() + ".doc" };
		// Runtime.getRuntime().exec(process);
	}
}
