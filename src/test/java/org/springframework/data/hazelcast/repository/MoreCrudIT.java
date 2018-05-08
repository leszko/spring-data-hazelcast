package org.springframework.data.hazelcast.repository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ActiveProfiles;

import test.utils.TestConstants;
import test.utils.TestDataHelper;
import test.utils.domain.Language;

/**
 * <P>
 * More C/R/U/D tests, as {@link CrudIT} is too big.
 * </P>
 *
 * @author Neil Stevenson
 */
@ActiveProfiles(TestConstants.SPRING_TEST_PROFILE_SINGLETON)
public class MoreCrudIT extends TestDataHelper {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Autowired 
	private CrudRepository<Language, Integer> languageRepository;

	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void doubleInsert() {
		int ONE = 1;
		
		assertThat("Year 1 missing before", this.languageRepository.findOne(ONE), nullValue());

		Language yearOne = new Language();
		yearOne.setYear(ONE);
		yearOne.setLanguage("One");

		this.languageRepository.save(yearOne);
		this.languageRepository.save(yearOne);

		assertThat("Year 1 present during", this.languageRepository.findOne(ONE), not(nullValue()));

		this.languageRepository.delete(ONE);

		assertThat("Year 1 missing after", this.languageRepository.findOne(ONE), nullValue());
	}

	@Test
	public void doubleInsert_int_zero_key() throws Exception {
		int ZERO = 0;
		
		assertThat("Year 0 missing before", this.languageRepository.findOne(ZERO), nullValue());

		Language yearZero = new Language();
		yearZero.setYear(ZERO);
		yearZero.setLanguage("Zero");

		this.languageRepository.save(yearZero);
		
		this.expectedException.expect(RuntimeException.class);
		this.expectedException.expectMessage("XXX");
		
		this.languageRepository.save(yearZero);
	}
	
}
