package br.yuh.suites;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.yuh.core.BaseTest;

@RunWith(Suite.class)
@SuiteClasses({
})

public class SuiteAuthTest extends BaseTest{
	@BeforeClass
	public static void setup() {
	}
}
