package test.utils.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import test.utils.TestConstants;

/**
 * <P>A {@code Language} is used to hold the winners of best foreign language
 * films.
 * </P>
 * <P>
 * The {@code @id} field ({@code year}) is a primitive type, so will be
 * autoboxed by the JVM.
 * </P>
 * 
 * @author Neil Stevenson
 * @see https://en.wikipedia.org/wiki/List_of_Academy_Award_winners_and_nominees_for_Best_Foreign_Language_Film
 */
@KeySpace(TestConstants.LANGUAGE_MAP_NAME)
public class Language implements Comparable<Language>, Serializable {

	private static final long serialVersionUID = 1L;

	@Id private int year;
	private String language;

	// Sort by year only
	@Override
	public int compareTo(Language that) {
		return year - that.getYear();
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Language [year=" + year + ", language=" + language + "]";
	}
	
}
